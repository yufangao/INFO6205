package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.*;
import edu.neu.coe.info6205.sort.elementary.HeapSort;
import edu.neu.coe.info6205.sort.linearithmic.MergeSort;
import edu.neu.coe.info6205.sort.linearithmic.QuickSort_DualPivot;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertTrue;
public class TimePredictor {

    @BeforeClass
    public static void beforeClass() throws IOException {
        config = Config.load(TimePredictor.class);
    }

    @Test
    public void MergeInstrumentsTest() {
        Config config1 = config.copy("helper", "instrument", "true");
        Config config2 = config1.copy(MergeSort.MERGESORT, MergeSort.INSURANCE, "true");
        Config config3 = config2.copy(MergeSort.MERGESORT, MergeSort.NOCOPY, "true");
        for(int n = 10000; n <= 256000; n *= 2) {
            System.out.println("MergeSort instrument variables of " + n + "-sized array");
            final Helper<Integer> helper = HelperFactory.create("MergeSort", n, config3);
            Sort<Integer> s = new MergeSort<>(helper);
            s.init(n);
            int finalN = n;
            final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(finalN));
            helper.preProcess(xs);
            Integer[] ys = s.sort(xs);
            helper.postProcess(ys);
            final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
            final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");

            final long compares = (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean();
            final long inversions = (int) statPack.getStatistics(InstrumentedHelper.INVERSIONS).mean();
            final long fixes = (int) statPack.getStatistics(InstrumentedHelper.FIXES).mean();
            final long swaps = (int) statPack.getStatistics(InstrumentedHelper.SWAPS).mean();
            final long copies = (int) statPack.getStatistics(InstrumentedHelper.COPIES).mean();

            System.out.println("Compares: " + compares);
            System.out.println("Inversions : " + inversions);
            System.out.println("Fixes: " + fixes);
            System.out.println("Swaps: " + swaps);
            System.out.println("Copies: " + copies);


            if(n == 160000) n = 128000;
        }
    }

    @Test
    public void MergeTimeTest() {
        Config config1 = config.copy(MergeSort.MERGESORT, MergeSort.INSURANCE, "true");
        Config config2 = config1.copy(MergeSort.MERGESORT, MergeSort.NOCOPY, "true");

        for(int n = 10000, m = 100; n <= 256000; n *= 2) {
            Benchmark_Timer<Integer> bm = new Benchmark_Timer<>(
                    "MergeSort",
                    null,
                    (Integer N) -> {
                        MergeSort<Integer> sorter = new MergeSort<>(N, config2);
                        Helper<Integer> helper = sorter.getHelper();
                        Integer[] ints = helper.random(Integer.class, r -> r.nextInt(N));
                        Integer[] sorted = sorter.sort(ints);
                        assertTrue(helper.sorted(sorted));
                    },
                    null
            );

            double x = bm.run(n, m);
            System.out.println("Array size: " + n + " - " + x + "ms.");

            if(n == 160000) n = 128000;
        }

        System.out.println();
    }
    @Test
    public void QuickInstrumentsTest() {

        Config config1 = Config.setupConfig("true", "0", "1", "", "");
        for(int n = 10000; n <= 256000; n *= 2) {
            System.out.println("QuickSort instrument variables of " + n + "-sized array");
            final Helper<Integer> helper = HelperFactory.create("QuickSort", n, config1);
            Sort<Integer> sort = new QuickSort_DualPivot<>(helper);
            sort.init(n);
            int finalN = n;
            final Integer[] xs = helper.random(Integer.class, r -> r.nextInt(finalN));
            helper.preProcess(xs);
            Integer[] ys = sort.sort(xs);
            helper.postProcess(ys);
            final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
            final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");

            final long compares = (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean();
            final long inversions = (int) statPack.getStatistics(InstrumentedHelper.INVERSIONS).mean();
            final long fixes = (int) statPack.getStatistics(InstrumentedHelper.FIXES).mean();
            final long swaps = (int) statPack.getStatistics(InstrumentedHelper.SWAPS).mean();
            final long copies = (int) statPack.getStatistics(InstrumentedHelper.COPIES).mean();

            System.out.println("Compares: " + compares);
            System.out.println("Inversions : " + inversions);
            System.out.println("Fixes: " + fixes);
            System.out.println("Swaps: " + swaps);
            System.out.println("Copies: " + copies);


            if(n == 160000) n = 128000;
        }
    }

    @Test
    public void QuickTimeTest() {
        Config config1 = Config.setupConfig("true", "0", "1", "", "");
        for(int n = 10000, m = 100; n <= 256000; n *= 2) {
            Benchmark_Timer<Integer> benchmark_timer = new Benchmark_Timer<>(
                    "QuickSort",
                    null,
                    (Integer N) -> {
                        QuickSort_DualPivot<Integer> sorter = new QuickSort_DualPivot<>(N, config);
                        Helper<Integer> helper = sorter.getHelper();
                        Integer[] ints = helper.random(Integer.class, r -> r.nextInt(N));
                        Integer[] sorted = sorter.sort(ints);
                        assertTrue(helper.sorted(sorted));
                    },
                    null
            );

            double x = benchmark_timer.run(n, m);
            System.out.println("Array size: " + n + " - " + x + "ms.");

            if(n == 160000) n = 128000;
        }

        System.out.println();
    }
    @Test
    public void HeapInstrumentsTest() {
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        for(int n = 10000; n <= 256000; n *= 2) {
            System.out.println("HeapSort instrument variables of " + n + "-sized array");
            Helper<Integer> helper = HelperFactory.create("HeapSort", n, config);
            helper.init(n);
            final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
            final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
            Random rd = new Random();
            Integer[] res = new Integer[n];
            for(int i = 0; i < n; i++) {
                res[i] = rd.nextInt(n);
            }
            SortWithHelper<Integer> sorter = new HeapSort<Integer>(helper);
            sorter.preProcess(res);
            Integer[] ys = sorter.sort(res);
            sorter.postProcess(ys);

            final int compares = (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean();
            final int inversions = (int) statPack.getStatistics(InstrumentedHelper.INVERSIONS).mean();
            final int fixes = (int) statPack.getStatistics(InstrumentedHelper.FIXES).mean();
            final int swaps = (int) statPack.getStatistics(InstrumentedHelper.SWAPS).mean();
            final int copies = (int) statPack.getStatistics(InstrumentedHelper.COPIES).mean();

            System.out.println("Compares: " + compares);
            System.out.println("Inversions : " + inversions);
            System.out.println("Fixes: " + fixes);
            System.out.println("Swaps: " + swaps);
            System.out.println("Copies: " + copies);

            if(n == 160000) n = 128000;
        }
    }

    @Test
    public void HeapTimeTest() {
        Config config = Config.setupConfig("false", "", "0", "", "");

        for(int n = 10000, m = 100; n <= 256000; n *= 2) {
            Random rd = new Random();
            Integer[] res = new Integer[n];
            for(int i = 0; i < n; i++) {
                res[i] = rd.nextInt(n);
            }

            Benchmark_Timer<Integer[]> benchmark_timer = new Benchmark_Timer<>(
                    "HeapSort",
                    null,
                    (Integer[] arr) -> {
                        BaseHelper<Integer> helper = new BaseHelper<>("HeapSort", arr.length, config);
                        GenericSort<Integer> sorter = new HeapSort<>(helper);
                        Integer[] ys = sorter.sort(arr);
                        assertTrue(helper.sorted(ys));
                    },
                    null
            );

            double x = benchmark_timer.run(res, m);
            System.out.println("Array size: " + n + " - " + x + "ms.");

            if(n == 160000) n = 128000;
        }

        System.out.println();
    }

    private static Config config;
}