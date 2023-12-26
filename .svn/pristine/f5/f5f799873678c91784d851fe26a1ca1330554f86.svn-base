package com.example.mytest2023.helper;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * android退出程序的工具类，使用单例模式
 * 1.在Activity的onCreate()的方法中调用addActivity()方法添加到mActivityList
 * 2.你可以在Activity的onDestroy()的方法中调用delActivity()来删除已经销毁的Activity实例
 * 这样避免了mActivityList容器中有多余的实例而影响程序退出速度
 *
 * @author KY
 */
public class ExitAppUtils {
	/**
	 * 转载Activity的容器
	 */
	private List<Activity> mActivityList = new LinkedList<Activity>();
	private static ExitAppUtils instance = new ExitAppUtils();

	/**
	 * 将构造函数私有化
	 */
	private ExitAppUtils() {
	}

	;

	/**
	 * 获取ExitAppUtils的实例，保证只有一个ExitAppUtils实例存在
	 *
	 * @return
	 */
	public static ExitAppUtils getInstance() {
		return instance;
	}

	public List<Activity> getActivityList() {
		return mActivityList;
	}

	/**
	 * 添加Activity实例到mActivityList中，在onCreate()中调用
	 *
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		mActivityList.add(activity);
	}

	/**
	 * 从容器中删除多余的Activity实例，在onDestroy()中调用
	 *
	 * @param activity
	 */
	public void removeActivity(Activity activity) {
		mActivityList.remove(activity);
	}

	/**
	 * 退出程序的方法
	 */
	public void exit() {
		for (Activity activity : mActivityList) {
			activity.finish();
		}

//		System.exit(0);

		int pid = android.os.Process.myPid(); //获得自己的pid
		android.os.Process.killProcess(pid);//通过pid自杀
	}
}
