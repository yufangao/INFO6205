package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Benchmarks;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

public class InsertionSortBenchmark {
    @Test
    public void sort01() throws Exception {
        String description = "Insertion sort for Random";
        Helper<Integer> helper = new BaseHelper<>(description, 1000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,1000);
    }
    @Test
    public void sort02() throws Exception {
        String description = "Insertion sort for Random";
        Helper<Integer> helper = new BaseHelper<>(description, 2000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,2000);
    }
    @Test
    public void sort03() throws Exception {
        String description = "Insertion sort for Random";
        Helper<Integer> helper = new BaseHelper<>(description, 4000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,4000);
    }
    @Test
    public void sort04() throws Exception {
        String description = "Insertion sort for Random";
        Helper<Integer> helper = new BaseHelper<>(description, 8000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,8000);
    }
    @Test
    public void sort05() throws Exception {
        String description = "Insertion sort for Random";
        Helper<Integer> helper = new BaseHelper<>(description, 12000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,12000);
    }
    @Test
    public void sort06() throws Exception {
        String description = "Insertion sort for Ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 1000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,1000);
    }
    @Test
    public void sort07() throws Exception {
        String description = "Insertion sort for Ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 2000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,2000);
    }
    @Test
    public void sort08() throws Exception {
        String description = "Insertion sort for Ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 4000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,4000);
    }
    @Test
    public void sort09() throws Exception {
        String description = "Insertion sort for Ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 8000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,8000);
    }
    @Test
    public void sort10() throws Exception {
        String description = "Insertion sort for Ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 12000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,12000);
    }
    @Test
    public void sort11() throws Exception {
        String description = "Insertion sort for Partially-ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 1000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,1000);
    }
    @Test
    public void sort12() throws Exception {
        String description = "Insertion sort for Partially-ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 2000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,2000);
    }
    @Test
    public void sort13() throws Exception {
        String description = "Insertion sort for Partially-ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 4000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,4000);
    }
    @Test
    public void sort14() throws Exception {
        String description = "Insertion sort for Partially-ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 8000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,8000);
    }
    @Test
    public void sort15() throws Exception {
        String description = "Insertion sort for Partially-ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 12000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,12000);
    }
    @Test
    public void sort16() throws Exception {
        String description = "Insertion sort for Reverse-ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 1000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,1000);
    }
    @Test
    public void sort17() throws Exception {
        String description = "Insertion sort for Reverse-ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 2000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,2000);
    }
    @Test
    public void sort18() throws Exception {
        String description = "Insertion sort for Reverse-ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 4000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,4000);
    }
    @Test
    public void sort19() throws Exception {
        String description = "Insertion sort for Reverse-ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 8000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,8000);
    }
    @Test
    public void sort20() throws Exception {
        String description = "Insertion sort for Reverse-ordered";
        Helper<Integer> helper = new BaseHelper<>(description, 12000, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper,12000);
    }
    public void runBenchmark(String description, GenericSort<Integer> sort, Helper<Integer> helper,int N) {
        helper.init(N);
        Supplier<Integer[]> supplier = null;
        if(description.equals("Insertion sort for Random")){
            supplier = () -> helper.random(Integer.class, Random::nextInt);
        }
        else if(description.equals("Insertion sort for Ordered")){
            Integer[] integers=new Integer[helper.getN()];
            for(int i=0;i<helper.getN();i++){
                integers[i]=i;
            }
            supplier = () -> integers;

        }
        else if(description.equals("Insertion sort for Partially-ordered")){
            Integer[] integers=new Integer[helper.getN()];
            int rd= (int)(Math.random()*helper.getN());
            for(int i=0;i<helper.getN();i++){
                if(i<rd){
                    integers[i]=i;
                }
                else {
                    integers[i]=(int)(Math.random()*helper.getN());
                }
            }
            supplier = () -> integers;
        }
        else if(description.equals("Insertion sort for Reverse-ordered")){
            Integer[] integers=new Integer[helper.getN()];
            for(int i=0;i<helper.getN();i++){
                integers[i]=helper.getN()-i;
            }
            supplier = () -> helper.random(Integer.class, Random::nextInt);

        }
        final Benchmark<Integer[]> benchmark = new Benchmark_Timer<>(
                description + " for " + N + " Integers",
                (xs) -> Arrays.copyOf(xs, xs.length),
                sort::mutatingSort,
                null
        );
        logger.info(Utilities.formatDecimal3Places(benchmark.runFromSupplier(supplier, 100)) + " ms");
    }
    private static Config config;
    final static LazyLogger logger = new LazyLogger(Benchmarks.class);
}
