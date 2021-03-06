package com.dfire.jobs;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dfire.utils.PrintContext;

/**
 * Created by eric on 16/7/11.
 */
@Component
public class OnceExecutionInClusterJob extends AbstractSimpleElasticJob {
    private PrintContext printContext = new PrintContext(OnceExecutionInClusterJob.class);
    private AtomicInteger counter = new AtomicInteger(0);
    
    //测试属性注入
    @Autowired
    private Sample sample;

    @Override
    public void process(JobExecutionMultipleShardingContext shardingContext) {
        System.out.println("Start------------------------------");
        System.out.println("当前Node连续第" + counter.addAndGet(1) + "次执行。");
        // 分片总数为1,这边打印的分片index一定是0
        printContext.printProcessJobMessage(shardingContext.getShardingItems());

        // 真实任务模拟
        doExecute();

        System.out.println("End---------------------------------");
        System.out.println();
    }

    private void doExecute() {
        System.out.println("执行任务。。。sample="+sample);
        sample.print();
        System.out.println("结束任务。。。");
    }
}
