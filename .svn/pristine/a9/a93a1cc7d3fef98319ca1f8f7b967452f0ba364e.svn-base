package com.example.mytest2023.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.SeekBar;
import android.widget.TextView;


import com.example.mytest2023.R;

import java.lang.reflect.Field;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * Created by 钟文祥 on 2019/2/15.
 * Describer:
 */
public class UIHelper {
    /**
     * 隐藏状态栏和导航栏
     * https://www.jianshu.com/p/557eac857afd
     *
     * @param context
     * @param show
     * @param type 0 状态栏  1导航栏   2所有
     */
    public static void setSystemUIBarVisible(Activity context, boolean show, int type) {
        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        if (!show) {
            if (type == 0) {
                uiFlags |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN;//状态栏
            } else if (type == 1) {
                uiFlags |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;//导航栏
            } else if (type == 2) {
                uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN//状态栏
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;//导航栏
            }
        }
        uiFlags |= 0x00001000;
        context.getWindow().getDecorView().setSystemUiVisibility(uiFlags);
    }

    /** 隐藏软键盘 */
    public static void hideSoftKeyboard(Activity activity, List<View> viewList) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService
                    (Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                if (viewList != null && viewList.size() > 0) {
                    for (View v : viewList) {
                        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                } else {
                    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
                            .getWindowToken(), 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 初始化SeekBar */
    public static void initSeekBar(Context context, SeekBar seekBarRoundAngle, SeekBar
            .OnSeekBarChangeListener listener) {
        LayerDrawable layerDrawable = (LayerDrawable) seekBarRoundAngle.getProgressDrawable();
        Drawable dra = layerDrawable.getDrawable(2);
        dra.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode
                .SRC);
        seekBarRoundAngle.getThumb().setColorFilter(ContextCompat.getColor(context, R.color
                .colorPrimary), PorterDuff.Mode.SRC_ATOP);
        seekBarRoundAngle.invalidate();
        seekBarRoundAngle.setOnSeekBarChangeListener(listener);
    }


    public static boolean isTxtEmpty(TextView txt) {
        return TextUtils.isEmpty(txt.getText().toString());
    }

    public static boolean isTxtEmptyOrDef(TextView txt, String def) {
        return isTxtEmpty(txt) || txt.getText().toString().equals(def);
    }


    public static int getStatusBarHeight(Context context) {
        int statusbarheight = 0;
        if (statusbarheight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusbarheight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (statusbarheight == 0) {
            statusbarheight = dip2px(context, 25);

        }
        return statusbarheight;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp2pX
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static float getTextWidth(Paint paint, String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }
        return paint.measureText(string);
    }

    public static float getTextHeight(Paint paint, String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }
        Rect bonuds = new Rect();
        paint.getTextBounds(string, 0, string.length(), bonuds);
        return bonuds.height();
    }


    public static float getTextHeight(Paint paint) {
        Rect bonuds = new Rect();
        paint.getTextBounds("Q", 0, 1, bonuds);
        return bonuds.height();
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

}
