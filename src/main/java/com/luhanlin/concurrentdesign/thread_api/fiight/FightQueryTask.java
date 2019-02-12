package com.luhanlin.concurrentdesign.thread_api.fiight;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FightQueryTask extends Thread implements FightQuery {

    private final String origin;
    private final String destination;

    private final List<String> resultList = new ArrayList<>();

    public FightQueryTask(String name, String origin, String destination){
        super("[" + name + "]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public List<String> get() {
        return resultList;
    }

    @Override
    public void run() {
        System.out.printf("%s_query from %s to %s . \n", getName(), origin, destination);
        int random = ThreadLocalRandom.current().nextInt(10);

        try {
            TimeUnit.SECONDS.sleep(random);
            resultList.add(getName() + "-" + random);
            System.out.printf("%s_query list query successfully. \n", getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
