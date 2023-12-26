package com.example.mytest2023.module.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseActivity;
import com.example.mytest2023.helper.ToastUtil;
import com.example.mytest2023.model.design.jianzao.UserItem;
import com.example.mytest2023.module.mvp.presenter.UserPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 钟文祥 on 2023-05-05.
 * Describer:  mvp参考资料 https://blog.csdn.net/aishang5wpj/article/details/51384371
 */
public class LoginTestActivityMVP extends BaseActivity {

    @BindView(R.id.edit1) EditText edit1;
    @BindView(R.id.edit2) EditText edit2;
    @BindView(R.id.btn1) Button btn1;
    @BindView(R.id.txt1) TextView txt1;

    private UserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpactivity);
        ButterKnife.bind(this);

        presenter = new UserPresenter();
        presenter.rigerView(new IUserView() {
            @Override
            public void showToast(String msg) {
                ToastUtil.showMsg(LoginTestActivityMVP.this, msg);
            }

            @Override
            public void loginSuccess(String msg) {
                txt1.setText(msg);
            }

            @Override
            public void loginFail(String msg) {
                txt1.setText(msg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unRigerView();
    }

    @OnClick({R.id.btn1})
    public void onBindClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                UserItem item = new UserItem.Builder().id(2).name("张三").age(2).build();
                presenter.login(item);

                break;
        }
    }
}