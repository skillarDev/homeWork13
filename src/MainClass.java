import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static boolean win = false;
    public static final int CARS_COUNT = 4;
    public static CyclicBarrier startBarrier = new CyclicBarrier(CARS_COUNT);
    public static Semaphore tunnelSemaphore = new Semaphore(CARS_COUNT / 2);
    public static CountDownLatch countDownLatchLatch = new CountDownLatch(CARS_COUNT);
    public static CountDownLatch countDownLatchLatch2 = new CountDownLatch(CARS_COUNT);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        countDownLatchLatch.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        countDownLatchLatch2.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}




