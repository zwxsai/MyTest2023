package com.example.mytest2023.helper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mytest2023.BuildConfig;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import androidx.core.app.NotificationManagerCompat;


/**
 * Created by 钟文祥 on 2018/6/9.
 * Describer:
 */
public class AppHelper {
    /** 判断是否重打包 ,是退出程序 */
    public static int isReDaBao(Context context, String packName) {
        PackageManager pm = context.getPackageManager();
        int sig = 0;
        try {
            PackageInfo pi = pm.getPackageInfo(packName, PackageManager.GET_SIGNATURES);
            sig = pi.signatures[0].hashCode();
            if (sig != -2052954174 && !BuildConfig.DEBUG) {
                exitProgram(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sig;
    }

    /** 退出程序 */
    public static void exitProgram(Context context) {
        // 清除所有Notification
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.cancelAll();

        ExitAppUtils.getInstance().exit();
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public static void requestPermission(final Context context, Action granted, String...
            permissions) {
        AndPermission.with(context)
                .permission(permissions)
                // 准备方法，和 okhttp 的拦截器一样，在请求权限之前先运行改方法，已经拥有权限不会触发该方法
                .rationale(new Rationale() {
                    @Override
                    public void showRationale(Context context, List<String> permissions,
                                              RequestExecutor executor) {
                        // 此处可以选择显示提示弹窗
                        executor.execute();
                    }
                })
                // 用户给权限了
                .onGranted(granted)
                // 用户拒绝权限，包括不再显示权限弹窗也在此列
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        // 判断用户是不是不再显示权限弹窗了，若不再显示的话进入权限设置页
                        if (AndPermission.hasAlwaysDeniedPermission(context,
                                permissions)) {
                            Toast.makeText(context, "打开权限设置页" + permissions.toString(), Toast
                                    .LENGTH_LONG).show();
                            // 打开权限设置页
                            AndPermission.permissionSetting(context).execute();
                            return;
                        }
                        ToastUtil.showMsg(context, "用户拒绝权限");
                    }
                })
                .start();
    }

    public static String getVersionName(Context context, String packageName) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            if (info != null) {
                String versionName = TextUtils.isEmpty(info.versionName) ? "" : info
                        .versionName;
                return versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 检测辅助功能是否开启
     *
     * @param mContext
     * @return boolean
     */
    public static boolean isAccessibilitySettingsOn(Context mContext, String serviceName) {
        int accessibilityEnabled = 0;
        // 对应的服务
        final String service = mContext.getPackageName() + "/" + serviceName;
        try {
            accessibilityEnabled = Settings.Secure.getInt(mContext.getApplicationContext()
                            .getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter
                (':');
        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(mContext.getApplicationContext()
                            .getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 执行shell 命令， 命令中不必再带 adb shell
     *
     * @param cmd
     * @return Sting  命令执行在控制台输出的结果
     */
    public static String execByRuntime(String cmd) {
        Process process = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            inputStreamReader = new InputStreamReader(process.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);

            int read;
            char[] buffer = new char[4096];
            StringBuilder output = new StringBuilder();
            while ((read = bufferedReader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != inputStreamReader) {
                try {
                    inputStreamReader.close();
                } catch (Throwable t) {
                    //
                }
            }
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (Throwable t) {
                    //
                }
            }
            if (null != process) {
                try {
                    process.destroy();
                } catch (Throwable t) {
                    //
                }
            }
        }
    }
}
