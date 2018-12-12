package me.test.springboottryit.task.schedule;

import me.test.springboottryit.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author paranoidq
 * @since 1.0.0
 */
// @EnableScheduling标注main boot class
@Component
public class ScheduleTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    @Autowired
    private AppProperties appProperties;


    @Scheduled(fixedRate = 2000)
    private void reportCurrentTime() {
        System.out.println("*** [" + appProperties.getTest() + "] Now time: " + dateFormat.format(new Date()));
        /*
        result:
            *** Now time: 2018-22-12 10:22:05
            *** Now time: 2018-22-12 10:22:07
            *** Now time: 2018-22-12 10:22:11
         */
    }

}
