package com.example.mytest2023.module.mvp.view;

/**
 * Created by 钟文祥 on 2023-05-05.
 * Describer:
 */
public interface IUserView extends BaseMVPView {

    public void loginSuccess(String msg);

    public void loginFail(String msg);
}
