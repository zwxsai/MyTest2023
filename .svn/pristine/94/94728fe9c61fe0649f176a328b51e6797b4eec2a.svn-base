package com.example.mytest2023.module.Foundation;

import com.example.mytest2023.helper.BaseEventBus;
import com.example.mytest2023.helper.BaseThread;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 钟文祥 on 2023-04-19.
 * Describer:
 */
public class SumThread extends BaseThread {
    private int sum = 0;
    private int i = 0;

    @Override
    public void onRun() {
        sum = sum + i;
        EventBus.getDefault().post(new BaseEventBus<Integer[]>(BaseEventBus.SumI, new Integer[]{sum, i}));
        i++;

    }

    @Override
    public int onSleepTime() {
        return 1000;
    }

    public int getSum() {
        return sum;
    }

    public int getI() {
        return i;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setI(int i) {
        this.i = i;
    }
}
