package com.example.mytest2023.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mytest2023.R;
import com.example.mytest2023.widget.alertview.OnItemClickListener;
import com.example.mytest2023.widget.other.IOSAlertView;


/**
 * Created by 钟文祥 on 2018/6/5.
 * Describer:
 */
public class DialogHelper {
    /** 通用Dialog */
    // 因为本类不是activity所以通过继承接口的方法获取到点击的事件
    public interface OnAlertClickListener {
        void onOkClick();

        void onCancelClick();

        void onOtherClick();
    }

    private static AlertDialog alertDialog;

    public static void showAlertDialog(Context context, String title, String
            content, String cancel, String ok, final OnAlertClickListener listener) {
        showAlertDialog(context, title, content, null, cancel, ok, listener);
    }

    public static void showAlertDialog(Context context, String title, String
            content, String other, String cancel, String ok, final OnAlertClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(content);
        // 设置title
        builder.setTitle(title);
        // 设置确定按钮，固定用法声明一个按钮用这个setPositiveButton
        builder.setPositiveButton(ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 如果确定被电击
                        if (listener != null) {
                            listener.onOkClick();
                        }
                        alertDialog = null;
                    }
                });
        if (!TextUtils.isEmpty(cancel)) {
            // 设置取消按钮，固定用法声明第二个按钮要用setNegativeButton
            builder.setNegativeButton(cancel,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // 如果取消被点击
                            if (listener != null) {
                                listener.onCancelClick();
                            }
                            alertDialog = null;
                        }
                    });
        }

        if (!TextUtils.isEmpty(other)) {
            builder.setNeutralButton(other, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null) {
                        listener.onOtherClick();
                    }
                    alertDialog = null;
                }
            });
        }
        // 控制这个dialog可不可以按返回键，true为可以，false为不可以
        builder.setCancelable(false);
        // 显示dialog
        alertDialog = builder.create();
        if (!alertDialog.isShowing())
            alertDialog.show();
    }

    /** 自定义的加载Dialog */
    private static Dialog loadingDialog;
    private static TextView loadingText;


    public static void showNetWebDialog(Context context, String msg, boolean cancelable) {
        if (loadingDialog == null) {
            if (null == context) return;
            loadingDialog = new Dialog(context, R.style.loadingDialog);
            View view = LayoutInflater.from(context).inflate(R.layout.widget_netweb_loading_dialog,
                    null);
            loadingText = (TextView) view.findViewById(R.id.MKLoader_Text);

            loadingDialog.setContentView(view, new LinearLayout.LayoutParams(context.getResources
                    ().getDimensionPixelSize(R.dimen.loading_width), context.getResources()
                    .getDimensionPixelSize(R.dimen.loading_width)));
        }

        if (!TextUtils.isEmpty(msg)) {
            loadingText.setVisibility(View.VISIBLE);
            loadingText.setText(msg);
        } else {
            loadingText.setVisibility(View.GONE);
        }
        loadingDialog.setCancelable(cancelable);
        loadingDialog.setCanceledOnTouchOutside(cancelable);

        Activity activity = (Activity) context;
        if (activity.isFinishing()) return;
        try {
            loadingDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 结束进度条 */
    public static void stopProgressDlg() {
        try {
            if (null != loadingDialog && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** 仿IOS弹出框 */
    public static void showIOSAlerView(Context context, String title, String msg, String redMsg,
                                       boolean isShowBtnCancel, OnItemClickListener listener) {
        showIOSAlerView(context, title, msg, redMsg, "取消", "确定", isShowBtnCancel, listener);
    }

    /**
     * 仿IOS弹出框提示框
     *
     * @param context
     * @param title
     * @param msg 黑色描述
     * @param redMsg 红色描述
     * @param leftStr 左边按钮名称
     * @param rightStr 右边按钮名称
     * @param isShowBtnCancel 是否显示取消按钮
     * @param listener 事件
     */
    public static void showIOSAlerView(Context context, String title, String msg, String redMsg,
                                       String leftStr,
                                       String rightStr, boolean isShowBtnCancel,
                                       OnItemClickListener listener) {
        IOSAlertView al = null;
        if (redMsg != null && !redMsg.equals("")) {
            al = new IOSAlertView(title, msg, isShowBtnCancel ? leftStr : null, null, new
                    String[]{rightStr}, context,
                    IOSAlertView.Style.Alert, listener);
            ViewGroup extView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout
                    .widget_ios_alertext_form, null);
            TextView etName = extView.findViewById(R.id.wight_alerttext_form);
            etName.setText(redMsg + "\n   ");
            al.addExtView(extView);
        } else {
            al = new IOSAlertView(title, msg, isShowBtnCancel ? leftStr : null, new
                    String[]{rightStr}, null, context,
                    IOSAlertView.Style.Alert, listener);
        }
        al.setCancelable(true);
        try {
            al.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 仿IOS弹出框 编辑框
     * @param context
     * @param title
     * @param editStr
     * @param hintStr
     * @param inputType 默认InputType.TYPE_NULL
     * @param listener
     */
    public static void showIOSEditView(Context context, String title, String editStr, String
            hintStr, int inputType, OnItemClickListener listener) {
        IOSAlertView al = new IOSAlertView(title, null, "取消", new String[]{"确定"}, null,
                context, IOSAlertView.Style.Alert, listener);

        ViewGroup extView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout
                .widget_ios_alertext_edit, null);
        EditText etName = extView.findViewById(R.id.wight_alerttext_EditText);
        etName.setText(editStr);
        etName.setHint(hintStr);
        etName.setInputType(inputType);
        al.addExtView(extView);

        al.setCancelable(true);
        try {
            al.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final int IOS_CANCEL = -1;
    public static final int IOS_OK = 0;


}
