package com.example.mytest2023.Api.RequestModel;

/**
 * Created by 钟文祥 on 2023/6/16.
 * Describer:搜索推荐词 请求类
 */
public class HotwordsRequest {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public HotwordsRequest() {
    }

    public HotwordsRequest(int count) {
        this.count = count;
    }
}
