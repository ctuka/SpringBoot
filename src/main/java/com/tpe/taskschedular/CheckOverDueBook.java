package com.tpe.taskschedular;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CheckOverDueBook {

    @Scheduled(fixedRate = 6000)
    public void checkOverBooks() {
        System.out.println("Checkin over due books.....");
    }
}
