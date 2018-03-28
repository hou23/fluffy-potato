package mooc_zju_algorithm;

/**
 * Created by marshall.houz on 2017/4/19.
 *
 * 两种方式实现计算给定多项式在x处的值
 */
public class Polynomial {
    public static void main(String[] args) {
        //测试两种求多项式和的性能
        //多项式最大项数
        int max = 10;
        double[] a = new double[max];
        for (int i = 0; i < max; i++) {
            a[i] = (double)i;
        }
        //函数调用次数, 由于函数执行太快, 所以重复执行函数最后求平均时间来计算单次时间
        int times = (int) Math.pow(10, 7);
        long start = System.currentTimeMillis();
        for (int j = 0; j < times; j++) {
            func1(max - 1, a, 1.1);
        }
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("func1用时: " + duration + "ms");

        start = System.currentTimeMillis();
        for (int j = 0; j < times; j++) {
            func2(max - 1, a, 1.1);
        }
        end = System.currentTimeMillis();
        duration = end - start;
        System.out.println("func2用时: " + duration + "ms");
    }

    //多项式求和, 简单方法
    private static double func1(int n, double[] a, double x) {
        double p = a[0];
        for (int i = 1; i < n; i++) {
            p += a[i] * Math.pow(x, i);
        }
        return p;
    }

    //多项式求和, 提取公因子
    private static double func2(int n, double[] a, double x) {
        double p = a[n];
        for (int i = n - 1; i > 0; i--) {
            p += a[i] + x*p;
        }
        return p;
    }
}
