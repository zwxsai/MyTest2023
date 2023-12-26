package com.example.mytest2023.module.Foundation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseActivity;
import com.example.mytest2023.databinding.ActivityThreadBinding;
import com.example.mytest2023.model.design.jianzao.UserItem;

/**
 * Created by 钟文祥 on 2023/5/7.
 * Describer：多线程与异步
 * <p>
 * https://www.bilibili.com/video/BV19U4y1G7mk/?spm_id_from=333.337.search-card.all
 * .click&vd_source=b2ee647f609e36dfd2254ad7fffd79d9
 * <p>
 * ui线程就是主线程 不能做耗时操作，子线程不能操作ui
 * <p>
 * https://www.jianshu.com/p/7a8cb20cfd80
 */
public class ThreadActivity extends BaseActivity {
    private ActivityThreadBinding binding;

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                binding.getUserItem().setAge(msg.arg1); //要注意第二个输入框是age
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_thread);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_thread);
        binding.setUserItem(new UserItem.Builder().name("张三").age(102).id(3).build());


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    int finalI = i;

                    Message message = new Message();
                    message.what = 0;
                    message.arg1 = finalI;
                    handler.sendMessage(message);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.edit1.setText(finalI + "个苹果");
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}