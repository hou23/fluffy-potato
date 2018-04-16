package java_concurrency_in_practice.ch03;


import net.jcip.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 在不可变容器中缓存数字和它的因数
 * @author yaojie.hou
 * @create 2018/3/15
 */
@Immutable
public class OneValueCache {

	private final BigInteger lastNumber;
	private final BigInteger[] lastFactors;

	public OneValueCache(BigInteger i,
						 BigInteger[] factors) {
		lastNumber = i;
		lastFactors = Arrays.copyOf(factors, factors.length);
	}

	public BigInteger[] getFactors(BigInteger i) {
		if (lastNumber == null || !lastNumber.equals(i)) {
			return null;
		} else {
			return Arrays.copyOf(lastFactors, lastFactors.length);
		}
	}

}
