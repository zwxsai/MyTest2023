package com.example.mytest2023.widget.other;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mytest2023.R;

import org.w3c.dom.Text;

import androidx.annotation.Nullable;

/**
 * Created by 钟文祥 on 2023-05-26.
 * Describer:
 */
public class CusLinearLayout extends LinearLayout {
    public CusLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.cus_linearlayout, this, true);
        TextView txtA = (TextView) findViewById(R.id.txtA);
        EditText editB = (EditText) findViewById(R.id.editB);
        txtA.setTextColor(Color.RED);
        editB.setText("woshi");

    }
}
