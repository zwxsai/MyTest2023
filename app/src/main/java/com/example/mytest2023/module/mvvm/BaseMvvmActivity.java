package com.example.mytest2023.module.mvvm;

import android.os.Bundle;
import android.view.View;

import com.example.mytest2023.Api.Utils.BaseResource;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by 钟文祥 on 2023/6/15.
 * Describer:mvvm基类
 * https://codeleading.com/article/13872669987/
 * https://blog.csdn.net/dageliuqing/article/details/127094021
 * https://juejin.cn/post/6844903955693043725
 * https://juejin.cn/post/6844903968884146184
 * <p>
 * //内存泄露解决方法
 * //<a href="https://blog.csdn.net/qq_48435252/article/details/127540216">...</a>
 * //https://zhuanlan.zhihu.com/p/608780834?utm_id=0
 * <p>
 * monkey测试 <a href="https://blog.csdn.net/supernova_TOP/article/details/125970648">...</a>
 * adb shell monkey -p com.dfl.video --throttle 500 -v -v -v 1000
 * <p>
 * adb shell 命令行
 * <a href="https://zhuanlan.zhihu.com/p/589447707">...</a>
 * https://www.jianshu.com/p/62bbf31cf1a0
 * <p>
 * handle
 * <a href="https://blog.csdn.net/ly0724ok/article/details/117324053/">...</a>
 * <p>
 * 性能优化
 * <a href="https://zhuanlan.zhihu.com/p/639316383">...</a>
 *
 * Android ANR详解
 * https://blog.csdn.net/qq_34519487/article/details/113030181
 */
public abstract class BaseMvvmActivity<VM extends BaseViewModel, VDB extends ViewDataBinding> extends AppCompatActivity {

    protected VM mViewModel;
    protected VDB mViewDataBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(getLayoutId());
        //初始化我们的binging
        mViewDataBind = DataBindingUtil.setContentView(this, getLayoutId());
        //给binding加上感知生命周期，AppCompatActivity就是lifeOwner
        mViewDataBind.setLifecycleOwner(this);

//        mViewDataBind = AppCompatActivity.inflateBindingWithGeneric();
//        View root = mViewDataBind.getRoot();
//        setContentView(root);


        //获得泛型参数的实际类型
//        Class<VM> vmClass =
//                (Class<VM>) ((ParameterizedType) this.getClass().getGenericSuperclass())
//                .getActualTypeArguments()[0];
//        mViewModel = new ViewModelProvider(this).get(vmClass);
        //        mViewDataBind.setVariable(getBRVariableId(), mViewModel);
        createViewModel();
        afterCreate();
    }

    //创建ViewModel
    public void createViewModel() {
        if (mViewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            mViewModel = (VM) ViewModelProviders.of(this).get(modelClass);
        }
    }

    /** 界面id */
    protected abstract @LayoutRes int getLayoutId();

    /** 处理业务逻辑 */
    protected abstract void afterCreate();


    public abstract class OnMyCallback<T> implements BaseResource.OnHandleCallback<T> {
        @Override
        public void onLoading(String msg) {
            //统一操作 showLoading
        }

        @Override
        public void onError(Throwable throwable) {
            //统一操作联网失败
        }

        @Override
        public void onFailure(String msg) {
            //接口走通了，但是code 不等于0
        }

        @Override
        public void onCompleted() {
            //统一关闭 hideLoading
        }

        @Override
        public void onProgress(int precent, long total) {
            //这是上传图片和下载文件才需要的。
        }
    }


}
