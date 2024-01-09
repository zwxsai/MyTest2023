package com.example.mytest2023.module.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.example.mytest2023.module.Foundation.FoundationActivity
import com.example.mytest2023.module.kl.IRemoteService

/**
 *Created by 钟文祥 on 2023/12/29.
 *Describer: * Describer: aidl的服务
 * https://blog.csdn.net/hello_1995/article/details/122094512?ops_request_misc=%257B%2522request
 * %255Fid%2522%253A%2522170349611916800185811505%2522%252C%2522scm%2522%253A%252220140713
 * .130102334..%2522%257D&request_id=170349611916800185811505&biz_id=0&utm_medium=distribute
 * .pc_search_result.none-task-blog-2~all~top_positive~default-1-122094512-null-null
 * .142^v96^pc_search_result_base8&utm_term=aidl&spm=1018.2226.3001.4187
 * https://blog.csdn.net/jdfkldjlkjdl/article/details/115012307
 * https://www.jianshu.com/p/1140b90d8807
 *
 * 服务与Messenger
 * https://blog.csdn.net/javazejian/article/details/52709857
 */
class AIDLActivity : FoundationActivity() {


	override fun initView() {
		txt1.text = "AIDL"
		txt2.text = data


	}

	//activity里面
	private var iRemoteService: IRemoteService? = null

	private var mConnection = object : ServiceConnection {
		override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

			Log.e("aidl测试", "onServiceConnected: 绑定成功");
			iRemoteService =
				IRemoteService.Stub.asInterface(service); //通过binder返回服务 ，aidl特有方法： binder.asInterface（）：server
			Thread(object : Runnable {
				override fun run() {
					try {
						var newname = iRemoteService?.getName1("钟文祥");
						Log.e("aidl测试", "onServiceConnected: " + newname);
					} catch (e: Exception) {

					}
				}
			}).start();
		}

		override fun onServiceDisconnected(name: ComponentName?) {
			Log.e("aidl测试", "onServiceConnected: 绑定中断");
			iRemoteService = null;
		}

	}

	private fun bindService() {
		var intent: Intent = Intent();
		intent.setAction("MyTestService.action");
		intent.setPackage("com.example.mytest2023");
		var df = bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
		var er = 3
	}


}