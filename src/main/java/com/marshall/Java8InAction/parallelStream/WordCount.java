package com.marshall.Java8InAction.parallelStream;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by yaojie.hou on 2017/4/5.
 *
 * 单词计数器
 */
public class WordCount {
	public static void main(String[] args) {
		final String SENTENCE = " Nel mezzo del cammin di nostra vita " +
				"mi ritrovai in una selva oscura" +
				" ché la dritta via era  smarrita ";
		System.out.println("Found " + countWordsIteratively(SENTENCE) + " Words");
		//Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
		//System.out.println("Found " + countWords(stream) + " Words");
		// 并行流结果不正确，因为拆分为子部分时，原始的String在任意位置拆分，有时一个词会被分为两个词
		//System.out.println("Found " + countWords(stream.parallel()) + " Words");
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> spliteratorStream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(spliteratorStream) + " Words");
    }

	public static int countWordsIteratively (String s) {
		int counter = 0;
		boolean lastSpace = true;
		for (char c : s.toCharArray()) {
			if (Character.isWhitespace(c)) {
				lastSpace = true;
			} else {
				if (lastSpace) {
					// 上一个字符是空格，而当前遍历的字符不是空格，将单词计数器加一
					counter++;
				}
				lastSpace = false;
			}
		}
		return counter;
	}

	private static int countWords (Stream<Character> stream) {
		WordCounter wordCounter = stream.reduce(
				new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
		return wordCounter.getCounter();
	}

	static class WordCounter {
		private final int counter;
		private final boolean lastSpace;

		public WordCounter(int counter, boolean lastSpace) {
			this.counter = counter;
			this.lastSpace = lastSpace;
		}

		public WordCounter accumulate (Character c) {
			// 遍历Character
			if (Character.isWhitespace(c)) {
				return lastSpace ? this : new WordCounter(counter, true);
			} else {
				return lastSpace ? new WordCounter(counter + 1, false) : this;
			}
		}

		public WordCounter combine (WordCounter wordCounter) {
			return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
		}

		public int getCounter () {
			return counter;
		}
	}

	// 实现spliterator的单词计数器，可并行执行
    static class WordCounterSpliterator implements Spliterator<Character> {

		private final String string;
		private int currentChar = 0;

		public WordCounterSpliterator(String string) {
			this.string = string;
		}

		@Override
		public boolean tryAdvance(Consumer<? super Character> action) {
			// 处理当前字符
			action.accept(string.charAt(currentChar++));
			// 如果还有字符要处理，则返回true
			return currentChar < string.length();
		}

		@Override
		public Spliterator<Character> trySplit() {
			int currentSize = string.length() - currentChar;
			if (currentSize < 10) {
				// 返回null表示要解析的String已经足够小，可以顺序处理
				return null;
			}
			// 将拆分位置设定为要解析的string的中间
			for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
				// 将拆分位置前进到下一个空格
				if (Character.isWhitespace(string.charAt(splitPos))) {
					// 创建一个新的WordCounterSpliterator来解析string从开始到拆分位置的部分
					Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
					// 将这个WordCounter的起始位置设置为拆分位置
					currentChar = splitPos;
					return spliterator;
				}
			}
			return null;
		}

		@Override
		public long estimateSize() {
			return string.length() - currentChar;
		}

		@Override
		public int characteristics() {
			return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
		}
	}
}
