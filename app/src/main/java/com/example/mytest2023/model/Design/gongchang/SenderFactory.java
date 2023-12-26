package com.example.mytest2023.model.design.gongchang;

/**
 * Created by 钟文祥 on 2023-04-21.
 * Describer: 工厂
 * https://blog.csdn.net/qq_14931305/article/details/81296329
 */
public class SenderFactory {
    public static Sender getASender() {
        return new ASender();
    }

    public static Sender getBSender() {
        return new BSender();
    }
}
