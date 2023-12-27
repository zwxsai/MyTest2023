package com.example.mytest2023.module.Coroutine

import android.util.Log
import com.example.mytest2023.module.Foundation.FoundationActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *Created by 钟文祥 on 2023/12/27.
 *Describer:协程
 * https://zhuanlan.zhihu.com/p/427092689
 */
class CoroutineActivity : FoundationActivity() {
	private val TAG = "CoroutineActivity"

	override fun initView() {
		txt1.text = "协程2"
		txt2.text = data
	}

	fun test1() {
		GlobalScope.launch {
			val arg1 = sunpendF1()
			var arg2 = sunpendF2()
			Log.e(TAG, "suspend finish arg1:$arg1  arg2:$arg2  result:${arg1 + arg2}")
		}
	}

	private suspend fun sunpendF1(): Int {
		delay(1000)
		Log.e(TAG, "suspend fun 1")
		return 2
	}

	private suspend fun sunpendF2(): Int {
		delay(2000)
		Log.e(TAG, "suspend fun 2")
		return 4
	}
}