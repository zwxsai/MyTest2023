package com.example.mytest2023.module.mvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytest2023.Api.Utils.BaseResource;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by 钟文祥 on 2023/6/15.
 * Describer:mvvm基类
 * https://codeleading.com/article/13872669987/
 * https://blog.csdn.net/dageliuqing/article/details/127094021
 * https://juejin.cn/post/6844903955693043725
 * https://juejin.cn/post/6844903968884146184
 */
public abstract class BaseMvvmFragment<VM extends BaseViewModel, VDB extends ViewDataBinding> extends Fragment {
    protected VM mViewModel;
    protected VDB mViewDataBind;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        //初始化我们的binging
        mViewDataBind = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        //给binding加上感知生命周期，AppCompatActivity就是lifeOwner
        mViewDataBind.setLifecycleOwner(getActivity());

//        //获得泛型参数的实际类型
//        Class<VM> vmClass =
//                (Class<VM>) ((ParameterizedType) this.getClass().getGenericSuperclass())
//                .getActualTypeArguments()[0];
////        mViewModel = ViewModelProviders.of(this).get(vmClass);
//        mViewModel = new ViewModelProvider(getActivity()).get(vmClass);
        //        mViewDataBind.setVariable(getBRVariableId(), mViewModel);
        createViewModel();
        return mViewDataBind.getRoot();
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
            mViewModel = (VM) ViewModelProviders.of(getActivity()).get(modelClass);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        afterCreate();
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

