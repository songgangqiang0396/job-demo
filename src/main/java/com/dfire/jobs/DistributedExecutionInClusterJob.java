package com.dfire.jobs;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dfire.utils.PrintContext;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by eric on 16/7/11.
 */
public class DistributedExecutionInClusterJob extends AbstractSimpleElasticJob {
    private PrintContext printContext = new PrintContext(DistributedExecutionInClusterJob.class);
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void process(JobExecutionMultipleShardingContext shardingContext) {
        System.out.println("Start-------------------------------");
        System.out.println("当前Node连续第" + counter.addAndGet(1) + "次执行。");
        printContext.printProcessJobMessage(shardingContext.getShardingItems());

        doExecuteCurrentShardings(shardingContext.getShardingItemParameters());

        System.out.println("End---------------------------------");
        System.out.println();
    }

    private void doExecuteCurrentShardings(Map<Integer, String> shardingParams) {
        try {
            System.out.println("执行当前分片内容:" + shardingParams);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
