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


/**
 * Created by 钟文祥 on 2023-05-05.
 * Describer:  mvp参考资料 https://blog.csdn.net/aishang5wpj/article/details/51384371
 */
public class LoginTestActivityMVP extends BaseActivity implements View.OnClickListener {

    EditText edit1;
    EditText edit2;
    Button btn1;
    TextView txt1;

    private UserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpactivity);
        findView();

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                UserItem item = new UserItem.Builder().id(2).name("张三").age(2).build();
                presenter.login(item);

                break;
        }
    }

    private void findView() {
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        btn1 = findViewById(R.id.btn1);
        txt1 = findViewById(R.id.txt1);
        btn1.setOnClickListener(this);
    }
}