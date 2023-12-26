package com.example.mytest2023.module.mvp.presenter;

import com.example.mytest2023.model.design.jianzao.UserItem;
import com.example.mytest2023.module.mvp.view.IUserView;

/**
 * Created by 钟文祥 on 2023-04-26.
 * Describer:
 */
public class UserPresenter implements BaseMVPPresenter<IUserView> {

    private IUserView userView;
    private UserItem item;

    @Override
    public void rigerView(IUserView userView) {
        this.userView = userView;
    }

    public void rigerView(UserItem item, IUserView userView) {
        this.item = item;
        this.userView = userView;
    }

    @Override
    public void unRigerView() {
        this.userView = null;
    }


    public void login(UserItem item) {
        if (item == null) return;
        if (item.getName() == null || item.getName().equals("")) return;
        if (item.getAge() == 0) return;

        if (item.getName().equals("张三") && item.getAge() == 2) {
            userView.loginSuccess("登录成功");
        } else {
            userView.loginFail("登录失败");
        }
    }
}
