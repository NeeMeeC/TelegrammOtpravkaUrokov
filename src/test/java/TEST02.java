

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


public class TEST02 {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RunApp.class, args);
        TimeUnit.MILLISECONDS.sleep(1000L);
        SpringApplication.run(OneMoreTask.class, args);

    }
}

@EnableScheduling
class RunApp {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(initialDelay = 3000,fixedRate = 10000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}
@EnableScheduling
class OneMoreTask {

    @Scheduled(initialDelay = 3000, fixedRateString = "PT2H")
    public void allIsOk() {
        System.out.println("Пока всё нормально, прошло ещё 25 секунд");
    }
}
