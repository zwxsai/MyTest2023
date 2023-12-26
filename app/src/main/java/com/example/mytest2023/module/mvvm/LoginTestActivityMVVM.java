package com.example.mytest2023.module.mvvm;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseActivity;
import com.example.mytest2023.helper.ToastUtil;
import com.example.mytest2023.model.design.jianzao.UserItem;
import com.example.mytest2023.databinding.ActivityLoginTestMvvmBinding;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by 钟文祥 on 2023-05-05.
 * Describer:  mvvm参考资料 https://blog.csdn.net/Eqiqi/article/details/121670801
 * https://blog.csdn.net/weixin_53431933/article/details/126372227
 */
public class LoginTestActivityMVVM extends BaseActivity {
    private ActivityLoginTestMvvmBinding binding;
    private UserItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login_test_mvvm);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_test_mvvm);
        item = new UserItem.Builder().name("张三").age(10).id(2).build();
        binding.setUserItem(item);
        binding.setTxtMsg("MVVM Test");
        binding.setLoginTestActivityMVVMHandle(new LoginTestActivityMVVMHandle());
        binding.setIsShow(false);
//        binding.btn1.setText("我是按钮");

    }


    private ReferenceQueue<Object> queue = new ReferenceQueue<>();

    private void test() {
        // 创建一个对象
        Object obj = new Object();
        //  创建一个弱引用，并指向这个对象，并且将引用队列传递给弱引用
        WeakReference<Object> reference = new WeakReference(obj, queue);
        // 打印出这个弱引用，为了跟gc之后queue里面的对比证明是同一个
        System.out.println("这个弱引用是:" + reference);
        //gc一次看看(毛用都没)
        System.gc();
        // 打印队列(应该是空)
        printlnQueue("before");
        //先设置obj为null，obj可以被回收了
        obj = null;
        // 再进行gc，此时obj应该被回收了，那么queue里面应该有这个弱引用了
        System.gc();
        //再打印队列
        printlnQueue("after");
    }

    private void printlnQueue(String tag) {
        System.out.print(tag);
        Object obj;
        // 循环打印引用队列
        while ((obj = queue.poll()) != null) {
            System.out.println(": " + obj);
        }
        System.out.println();
    }


    public class LoginTestActivityMVVMHandle {
        public void OnClick() {
            ToastUtil.showMsg(LoginTestActivityMVVM.this,
                    "用户名：" + binding.getUserItem().getName() + " ,年龄:" + binding.getUserItem().getAge());
        }

        public void OnClick2() {
            item.setName("h");
            item.setAge(12);
            ToastUtil.showMsg(LoginTestActivityMVVM.this,
                    "用户名：" + binding.getUserItem().getName() + " ,年龄:" + binding.getUserItem().getAge());
        }
    }

    public static String init1(String name) {
        return name;
    }
}