package com.luhanlin.concurrentdesign.observer;

/**
 * @description: Task任务监控的主要执行监控线程
 * @author: Mr.Lu
 * @create: 2019-02-27 16:49
 **/
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifeCycle<T> taskLifeCycle;

    private final Task<T> task;

    private volatile ThreadCycle cycle;

    public ObservableThread(Task task) {
        this(new TaskLifeCycle.EmptyLisfeCycle(), task);
    }

    public ObservableThread(TaskLifeCycle taskLifeCycle, Task task) {
        super();
        if (task == null){
            throw new IllegalArgumentException("Task can`t be null.");
        }
        this.taskLifeCycle = taskLifeCycle;
        this.task = task;
    }

    @Override
    public void run() {
        this.update(ThreadCycle.STARTED, null, null);

        try {
            this.update(ThreadCycle.RUNNING, null, null);
//            TimeUnit.SECONDS.sleep(10);
            T result = this.task.call();

            this.update(ThreadCycle.DONE, result, null);
        } catch (Exception e) {
            this.update(ThreadCycle.ERROR, null, e);
        }

    }

    private void update(ThreadCycle cycle, T result, Exception e) {
        this.cycle = cycle;

        if (cycle == null){
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.taskLifeCycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.taskLifeCycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.taskLifeCycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.taskLifeCycle.onError(currentThread(), e);
            }
        } catch (Exception e1) {
            if (cycle == ThreadCycle.ERROR){
                throw e1;
            }
        }

    }

    @Override
    public ThreadCycle getCycle() {
        return this.cycle;
    }
}
