package com.luhanlin.concurrentdesign.aa_thread_api03.fiight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FightQueryExample {

    // 定义需要查询的航空公司的名称集合
    private static final List<String> fightCompany = Arrays.asList("boo", "Jso", "Isjj");

    public static void main(String[] args) {

        // 查询所有航班信息
        List<String> result = search("bj", "sh");
        System.out.println("-------------- result ----------------");
        result.forEach(System.out::println);

        /**
         * [boo]_query from bj to sh .
         * [Isjj]_query from bj to sh .
         * [Jso]_query from bj to sh .
         * [boo]_query list query successfully.
         * [Isjj]_query list query successfully.
         * [Jso]_query list query successfully.
         * -------------- result ----------------
         * [boo]-2
         * [Jso]-4
         * [Isjj]-3
         */
    }

    private static List<String> search(String origin, String destination) {

        final List<String> resultTotal = new ArrayList<>();

        // 1. 创建各个航空公司的task
        List<FightQueryTask> taskLists = fightCompany.stream()
                .map(str -> createFightQueryTask(str, origin, destination))
                .collect(Collectors.toList());

        // 2. 启动线程
        taskLists.forEach(task -> task.start());

        // 3. 主线程等待
        taskLists.forEach(task -> {
            try {
                task.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 4. 收集结果
        taskLists.stream().map(FightQuery::get).forEach(resultTotal::addAll);

        return resultTotal;
    }

    private static FightQueryTask createFightQueryTask(String companyName, String origin, String destination) {
        return new FightQueryTask(companyName, origin, destination);
    }
}
