package java_8_in_action.stream;


import java_8_in_action.domain.Trader;
import java_8_in_action.domain.Transaction;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Created by yaojie.hou on 2017/3/8.
 * <p>
 * 5.5实践
 */
public class StreamInAction {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1.找出2011年发生的所有交易，并按交易额正序排序
        List<Transaction> l1 = transactions.stream().filter(t -> 2011 == t.getYear()).sorted(comparing(Transaction::getValue)).collect(toList());
        // [{Trader:Brian in Cambridge, year: 2011, value:300}, {Trader:Raoul in Cambridge, year: 2011, value:400}]
        System.out.println(l1);

        // 2.交易员都在哪些城市工作过
        List<String> l2 = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(toList());
        Set<String> s2 = transactions.stream().map(t -> t.getTrader().getCity()).collect(toSet());
        // [Cambridge, Milan]
        System.out.println(l2);
        System.out.println(s2);

        // 3.查找所有来自剑桥的交易员，并按姓名排序
        List<Trader> l3 = transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity().equals("Cambridge")).distinct().sorted(comparing(Trader::getName)).collect(toList());
        // [Trader:Alan in Cambridge, Trader:Brian in Cambridge, Trader:Raoul in Cambridge]
        System.out.println(l3);

        // 4.返回所有交易员的姓名字符串，按字母顺序排序
        String s4 = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().reduce("", (n1, n2) -> n1 + n2);
        //字符串相加的效率不高，用joining比较好
        String s4_1 = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().collect(joining());
        // AlanBrianMarioRaoul
        System.out.println(s4);
        System.out.println(s4_1);

        // 5.有没有交易员是在米兰工作的
        boolean b5 = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        // true
        System.out.println(b5);

        // 6.打印生活在剑桥的交易员的所有交易额
        // 300 1000 400 950
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);

        // 7.所有交易中最高的交易额是多少
        Optional<Integer> max7 = transactions.stream().map(Transaction::getValue).reduce(Integer::min);
        // Optional[1000]
        System.out.println(max7);

        // 8.找到交易额最小的交易
        Optional<Transaction> min8 = transactions.stream().min(comparing(Transaction::getValue));
        System.out.println(min8);
    }
}
