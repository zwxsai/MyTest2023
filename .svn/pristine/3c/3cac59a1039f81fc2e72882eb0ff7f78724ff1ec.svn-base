package com.example.mytest2023.widget.other;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by 钟文祥 on 2023-05-26.
 * Describer:
 */
public class CusText extends AppCompatTextView {
    public CusText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        int width = getWidth();
        int height = getBaseline();
        canvas.drawLine(0, height, width, height, paint);
    }
}
