package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Stopwatch;
import edu.neu.coe.info6205.util.TimeLogger;
import edu.neu.coe.info6205.util.Utilities;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ThreeSumBenchmark {
    public ThreeSumBenchmark(int runs, int n, int m) {
        this.runs = runs;
        this.supplier = new Source(n, m).intsSupplier(10);
        this.n = n;
    }

    public void runBenchmarks() {
        System.out.println("ThreeSumBenchmark: N=" + n);
        benchmarkThreeSum("ThreeSumQuadratic", (xs) -> new ThreeSumQuadratic(xs).getTriples(), n, timeLoggersQuadratic);
        benchmarkThreeSum("ThreeSumQuadrithmic", (xs) -> new ThreeSumQuadrithmic(xs).getTriples(), n, timeLoggersQuadrithmic);
        benchmarkThreeSum("ThreeSumCubic", (xs) -> new ThreeSumCubic(xs).getTriples(), n, timeLoggersCubic);
    }

    public static void main(String[] args) {
        new ThreeSumBenchmark(100, 10, 250).runBenchmarks();
        new ThreeSumBenchmark(50, 20, 500).runBenchmarks();
        new ThreeSumBenchmark(20, 40, 1000).runBenchmarks();
        new ThreeSumBenchmark(10, 80, 2000).runBenchmarks();
        new ThreeSumBenchmark(5, 160, 4000).runBenchmarks();
        new ThreeSumBenchmark(3, 320, 8000).runBenchmarks();
        new ThreeSumBenchmark(2, 640, 16000).runBenchmarks();
//        new ThreeSumBenchmark(100, 250, 250).runBenchmarks();
//        new ThreeSumBenchmark(50, 500, 500).runBenchmarks();
//        new ThreeSumBenchmark(20, 1000, 1000).runBenchmarks();
//        new ThreeSumBenchmark(10, 2000, 2000).runBenchmarks();
//        new ThreeSumBenchmark(5, 4000, 4000).runBenchmarks();
//        new ThreeSumBenchmark(3, 8000, 8000).runBenchmarks();
//        new ThreeSumBenchmark(2, 16000, 16000).runBenchmarks();
    }

    private void benchmarkThreeSum(final String description, final Consumer<int[]> function, int n, final TimeLogger[] timeLoggers) {
        if (description.equals("ThreeSumCubic") && n > 4000) return;
        // FIXME

        Stopwatch stopwatch = new Stopwatch();
        for(int i = 0; i < n; i++){
            function.accept(supplier.get());
        }
        if (description.equals("ThreeSumQuadratic")){
            System.out.println("Quadratic Time: " + stopwatch.lap() + "ms");
        }else if(description.equals("ThreeSumQuadrithmic")){
            System.out.println("Quadrithmic Time: " + stopwatch.lap() + "ms");
        }else if(description.equals("ThreeSumCubic")){
            System.out.println("Cubic Time: " + stopwatch.lap() + "ms");
        }else{
            System.out.println("error!");
        }
        // System.out.println("time: " + stopwatch.lap() + "ms");
        stopwatch.close();

        // END 
    }

    private final static TimeLogger[] timeLoggersCubic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^3): ", (time, n) -> time / n / n / n * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadrithmic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2 log n): ", (time, n) -> time / n / n / Utilities.lg(n) * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadratic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2): ", (time, n) -> time / n / n * 1e6)
    };

    private final int runs;
    private final Supplier<int[]> supplier;
    private final int n;
}
