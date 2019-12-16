package com.cy.job;

import com.cy.domain.ChangeNetMonth;
import com.cy.map.ChangeNetMonthRepository;

import javax.annotation.Resource;
import javax.security.auth.callback.Callback;
import java.util.concurrent.Callable;

public class DemoJob implements Callable<ChangeNetMonth> {

    private ChangeNetMonth changeNetMonth;

    public DemoJob(ChangeNetMonth changeNetMonth) {
        this.changeNetMonth = changeNetMonth;
    }

    @Override
    public ChangeNetMonth call() throws Exception {
        changeNetMonth.setPortoutnetid("00350100");
        return changeNetMonth;
    }
}
