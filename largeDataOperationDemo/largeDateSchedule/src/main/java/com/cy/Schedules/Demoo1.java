package com.cy.Schedules;


import com.cy.domain.ChangeNetMonth;
import com.cy.job.DemoJob;
import com.cy.map.ChangeNetMonthRepository;
import com.github.wenhao.jpa.Specifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

@Component
@Slf4j
public class Demoo1 {

    @Resource
    private ChangeNetMonthRepository changeNetMonthRepository;

    //创建一个线程池
    private static final ExecutorService SERVICE = new ThreadPoolExecutor(
            15,
            20,
            20,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            r -> {
                SecurityManager s = System.getSecurityManager();
                ThreadGroup group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
                Thread thread = new Thread(group, r, "test-thread", 0);
                thread.setDaemon(true);
                return thread;
            }
    );

    //执行线程任务
    private ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(SERVICE);

    //单线程太慢，使用多线程来做这件事
    @Scheduled(fixedDelay = 1000)
    public void timer() {
        Specification<ChangeNetMonth> spec = Specifications.<ChangeNetMonth>and()
                .ne("portoutnetid", "00350100")
                .build();
        Page<ChangeNetMonth> changeNetMonthPage = changeNetMonthRepository.findAll(spec, PageRequest.of(0, 2000));
        int size = changeNetMonthPage.getContent().size();
        //线程执行
        if (size > 0) {
            List<ChangeNetMonth> content = changeNetMonthPage.getContent();
            for (ChangeNetMonth changeNetMonth : content) {
                executorCompletionService.submit(new DemoJob(changeNetMonth));
            }
        }
        //获取线程执行后的结果
        for (int i = 0; i < size; i++) {
            try {
                ChangeNetMonth changeNetMonth = (ChangeNetMonth) executorCompletionService.take().get();
                changeNetMonthRepository.save(changeNetMonth);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
