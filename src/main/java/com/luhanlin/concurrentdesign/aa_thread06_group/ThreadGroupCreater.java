package com.luhanlin.concurrentdesign.aa_thread06_group;

public class ThreadGroupCreater {

    public static void main(String[] args) {
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup group1 = new ThreadGroup("Group1");

        System.out.println(group1.getParent() == currentGroup);

        ThreadGroup group2 = new ThreadGroup(group1, "Group2");

        System.out.println(group2.getParent() == group1);
    }
}
