package com.luhanlin.concurrentdesign.thread_block05;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class EventQueue {

    private static final int DEFAULT_MAX_SIZE = 10;

    private final int max;

    private final LinkedList<Event> queue = new LinkedList<>();

    static class Event{}

    public EventQueue() {
        this.max = DEFAULT_MAX_SIZE;
    }

    public EventQueue(int max) {
        this.max = max;
    }

    // 向队列添加 Event
    public void addEvent(Event event){

        synchronized (queue){
            while (queue.size() >= max){
                try {
                    console(" the queue is full . ");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            console(" the new event is submitted .");
            queue.addLast(event);
            queue.notifyAll();
        }
    }

    // 从队列中取数据
    public Event takeEvent(){
        synchronized (queue){
            while (queue.isEmpty()){
                try {
                    console(" the queue is empty . ");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Event event = queue.removeFirst();
            queue.notifyAll();
            console(" the event("+ event +") is handled .");
            return event;
        }
    }

    private void console(String msg){
        System.out.printf(" %s : %s \n", Thread.currentThread().getName(), msg);
    }

    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();

        IntStream.range(0,5).mapToObj(i -> new Thread(() -> {
            for ( ; ; ) {
                eventQueue.addEvent(new EventQueue.Event());
            }
        },"producer_" + i)).forEach(Thread::start);


        IntStream.range(0,20).mapToObj(i -> new Thread(() -> {
            for ( ; ; ) {
                eventQueue.takeEvent();
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"consumer_" + i)).forEach(Thread::start);
    }

}
