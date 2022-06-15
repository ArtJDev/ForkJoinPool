import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final int SIZE = 10000;
    private static final int MAX = 100;

    public static void main(String[] args) {
        long time0, time1, time2;
        int[] array = createArray(SIZE);

        ArraySumTask sumTask = new ArraySumTask(array, 0, SIZE);
        time0 = System.currentTimeMillis();

        System.out.println("Сумма элементов массива размером " + SIZE + " ячеек в один поток");
        sumTask.fork();
        System.out.println(sumTask.join());
        time1 = System.currentTimeMillis();
        System.out.println("Время выполнения " + (time1 - time0) + " миллисекунд");

        ForkJoinPool pool = new ForkJoinPool();
        System.out.println();
        System.out.println("Сумма элементов массива размером " + SIZE + " ячеек в несколько потоков");
        System.out.println(pool.invoke(sumTask));
        time2 = System.currentTimeMillis();
        System.out.println("Время выполнения " + (time2 - time1) + " миллисекунд");

        System.out.println();
        System.out.println("Среднее арифметическое элементов массива - " + pool.invoke(sumTask) / array.length);

    }

    private static int[] createArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(MAX);
        }
        return array;
    }
}