import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            MainClass.countDownLatchLatch.countDown(); // "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"
            MainClass.startBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
            Object o = race.getStages().get(i);
            if(race.getStages().indexOf(o) == race.getStages().size() - 1 && !MainClass.win){
                MainClass.win = true;
                System.out.println(name + " WIN");
            }
        }
        MainClass.countDownLatchLatch2.countDown();
    }
}