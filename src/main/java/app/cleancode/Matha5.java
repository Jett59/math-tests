package app.cleancode;

public class Matha5 implements Runnable {
    public static final int WORKER_THREADS = Runtime.getRuntime().availableProcessors();

    long startPoint = 0;
    long numSums = 0;

    public Matha5(long startPoint, long numSums) {
        this.startPoint = startPoint;
        this.numSums = numSums;
    }

    boolean done = false;
    long result = 0;

    public static void main(String[] args) throws Throwable {
        long precision = 10000000000l;
        long highestOdd = (precision - 1) * 2 + 1;
        long startPointMultiplier = precision / WORKER_THREADS;
        Matha5[] workers = new Matha5[WORKER_THREADS];
        for (int i = 0; i < WORKER_THREADS; i++) {
            Matha5 worker = new Matha5(startPointMultiplier * i,
                    i < WORKER_THREADS - 1 ? startPointMultiplier
                            : precision - startPointMultiplier * i);
            workers[i] = worker;
            Thread workerThread = new Thread(worker, "Matha5 worker " + i);
            workerThread.start();
            System.out.println("Started worker thread " + i);
        }
        long started = System.nanoTime();
        long sum = 0;
        for (int i = 0; i < workers.length; i++) {
            if (!workers[i].done) {
                synchronized (workers[i]) {
                    workers[i].wait();
                }
            }
            System.out.println("worker " + i + " completed");
            sum += workers[i].result;
        }
        long finished = System.nanoTime();
        System.out.printf("Summing complete: time taken: %.3fs\n",
                (finished - started) / 1000000000d);
        double result = (double) sum / (double) (sum + highestOdd);
        System.out.println(result);
    }

    @Override
    public void run() {
        for (long i = 0; i < numSums; i++) {
            long n = i + startPoint;
            result += n * 2;
            result += (n * 2 + 1) * 2;
        }
        System.out.println("completed sums for thread " + Thread.currentThread().getName());
        done = true;
        synchronized (this) {
            this.notifyAll();
        }
    }
}
