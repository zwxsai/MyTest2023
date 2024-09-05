package com.example.mytest2023.module.Foundation;

import android.content.Intent;
import android.widget.Toast;

import com.example.mytest2023.helper.OnItemRecyclerListener;

/**
 * Created by 钟文祥 on 2023/4/12.
 * Describer：启动activity
 * <p>
 * 参考 https://blog.csdn.net/qq_38350635/article/details/88878310   singleTop以这个url内容为准
 * https://blog.csdn.net/xiajun2356033/article/details/78741121   这个urlsingleTop内容不准
 */
public class Open2Activity extends FoundationActivity {

    @Override
    public void initView() {
        txt1.setText("第一页面:");
        txt2.setText(data);
        listener = new OnItemRecyclerListener() {
            @Override
            public void onClick(int viewId, Object object) {
//                Intent intent = new Intent(Open2Activity.this, ServiceActivity.class);
//                intent.putExtra("data", "第二页面的内容");
//                startActivity(intent);

                Toast.makeText(Open2Activity.this, "dfg", Toast.LENGTH_SHORT).show();
            }
        };

    }
}