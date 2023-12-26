package com.example.mytest2023.module.mvvm;

import android.view.View;

import com.example.mytest2023.Api.Utils.BaseResource;
import com.example.mytest2023.Api.Utils.BaseResponse;
import com.example.mytest2023.R;
import com.example.mytest2023.databinding.ActivityUserItemBinding;
import com.example.mytest2023.model.home.ARSceneResponse;

import androidx.lifecycle.Observer;

/**
 * Created by 钟文祥 on 2023/6/15.
 * Describer: 继承BaseMvvmActivity
 * * https://juejin.cn/post/6844903968884146184
 */
public class UserItemActivity extends BaseMvvmActivity<UserItemViewModel, ActivityUserItemBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_item;
    }


    @Override
    protected void afterCreate() {

        mViewDataBind.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testgetARSceneResponse();
                testgetARSceneResponse2();
            }
        });
    }

    private void testgetARSceneResponse2() {
        mViewModel.testgetARSceneResponse2().observe(this,
                new Observer<BaseResource<ARSceneResponse>>() {
                    @Override
                    public void onChanged(BaseResource<ARSceneResponse> arSceneResponseBaseResource) {
                        arSceneResponseBaseResource.handler(new OnMyCallback<ARSceneResponse>() {
                            @Override
                            public void onSuccess(ARSceneResponse data) {
                                updateView(data);
                            }
                        });
                    }
                });
    }

    private void testgetARSceneResponse() {
        mViewModel.testgetARSceneResponse0().observe(this, new Observer<ARSceneResponse>() {
            @Override
            public void onChanged(ARSceneResponse arSceneResponse) {

            }
        });
    }

    private void updateView(ARSceneResponse arSceneResponse) {
        mViewDataBind.button.setText(arSceneResponse.getCategoryList().get(0).getName());
    }
}