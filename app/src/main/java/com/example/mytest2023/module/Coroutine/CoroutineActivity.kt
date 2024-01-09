package com.example.mytest2023.module.Coroutine

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.mytest2023.helper.TimeHelper
import com.example.mytest2023.module.Foundation.FoundationActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext

/**
 *Created by 钟文祥 on 2023/12/27.
 *Describer:协程
 * https://zhuanlan.zhihu.com/p/427092689
 * https://blog.csdn.net/android_jianbo/article/details/128190986
 */
class CoroutineActivity : FoundationActivity() {
	private val TAG = "CoroutineActivity"

	override fun initView() {
		txt1.text = "协程2"
		txt2.text = data

		test0()
		//两者各开了一条线程
		test1()
		test2()
		test3()
		test4()
		test5()
		test6()
	}

	private fun test0() {
		runBlocking {
			coroutineScope {
				Log.e(TAG, "test0 开始: " + TimeHelper.currentTime()) //第49秒
				launch {  //协程开启，没返回值
					delay(2000)
					Log.e(TAG, "test0 结束1: " + TimeHelper.currentTime())//第51秒
				}
				Log.e(TAG, "test0 结束2: " + TimeHelper.currentTime())//第49秒
			}
		}
	}

	fun test1() {
		GlobalScope.launch {
			Log.e(TAG, "test1开始: " + TimeHelper.currentTime()) //第46秒
			val arg1 = sunpendF1()     //第49秒  suspend立即挂起，等完成再恢复 执行下面的
			var arg2 = sunpendF2()     //第53秒
			Log.e(
				TAG,
				"test1结束1 ${TimeHelper.currentTime()}:"
			)//第53秒

			Log.e(
				TAG,
				"test1结束2 ${TimeHelper.currentTime()}: $arg1  arg2:$arg2  result:${arg1 + arg2}"
			) //第53秒
		}
	}

	private suspend fun sunpendF1(): Int {
		Log.e(TAG, "suspend fun 1-1 " + TimeHelper.currentTime()) //第46秒
		delay(3000)
		Log.e(TAG, "suspend fun 1 " + TimeHelper.currentTime()) //第49秒
		return 2
	}

	private suspend fun sunpendF2(): Int {
		Log.e(TAG, "suspend fun 2-1 " + TimeHelper.currentTime()) //第49秒
		delay(4000)
		Log.e(TAG, "suspend fun 2 " + TimeHelper.currentTime()) //第53秒
		return 4
	}

	private fun test2() {
//		runBlocking
//		常规函数，阻塞线程
//		CoroutineScope
//		挂起函数，不阻塞线程。内部一个协程失败了，其它的协程也会被取消
//		supervisorScope
//		挂起函数，不阻塞线程。内部一个协程失败了，不影响其它协程

		MainScope().launch(Dispatchers.IO) {
			Log.e(TAG, "test2开始: " + TimeHelper.currentTime()) //第46秒
			val userInfoname = getUserName() //挂起，等执行完 ，恢复执行下面
			Log.e(TAG, "test2结束1: ${userInfoname} :" + TimeHelper.currentTime()) //第48秒
			withContext(Dispatchers.Main) { //切线程
				val sdf = userInfoname
				Log.e(TAG, "test2结束2: ${userInfoname} :" + TimeHelper.currentTime()) //第48秒
			}
		}
	}

	//耗时操作 返回值  suspend挂起不是阻塞，等执行完才往下执行
	private suspend fun getUserName(): String {
		delay(2000)
		return "AAA"
	}

	private fun test3() {
		MainScope().launch {
			Log.e(TAG, "test3开始 :" + TimeHelper.currentTime()) //第46秒
			var async = async {
				delay(2000)
				"AA"
			}
			val de = 45
			Log.e(TAG, "test3结束1 :${de}:" + TimeHelper.currentTime()) //第46秒
			val date = async.await() //开启新的协程 并挂起，不往下执行，等到返回后，再往下执行 async需要与await结合使用
			Log.e(TAG, "test3结束2 :${date}:" + TimeHelper.currentTime()) //第48秒

		}
	}

	fun test4() {
		MainScope().launch {
			Log.e(TAG, "test4开始 :" + TimeHelper.currentTime()) //第46秒
			// 此处有一个需求  同时请求5个接口  并且将返回值拼接起来
			val job1 = async {//协程开启，有返回值，最后一行或return
				// 请求1
				delay(1000)
				"1"
				Log.e(TAG, "test4结束-1: ${TimeHelper.currentTime()} ") //第47秒

			}
			val job2 = async {//协程开启，有返回值
				// 请求2
				delay(2000)
				"2"
				Log.e(TAG, "test4结束-2: ${TimeHelper.currentTime()} ") //第48秒
			}
			val job3 = async {
				// 请求3
				delay(3000)
				"3"
				Log.e(TAG, "test4结束-3: ${TimeHelper.currentTime()} ") //第49秒
			}
			val job4 = async {
				// 请求4
				delay(4000)
				"4"
				Log.e(TAG, "test4结束-4: ${TimeHelper.currentTime()} ") //第50秒
			}
			val job5 = async {
				// 请求5
				delay(5000)
				"5"
				Log.e(TAG, "test4结束-5: ${TimeHelper.currentTime()} ") //第51秒
			}
			Log.e(TAG, "test4结束1: ${TimeHelper.currentTime()} ") //第46秒
			// 代码执行到此处时  5个请求已经同时开始执行了
			// await等待各job执行完 将结果合并，挂起等待完成再恢复下面
			Log.e(
				TAG,
				"test4结束2: ${TimeHelper.currentTime()}  ${job1.await()} ${job2.await()} ${job3.await()} ${job4.await()} ${job5.await()}"
			) //显示46秒 但在第51秒 输出
			Log.e(
				TAG, "test4结束3: ${TimeHelper.currentTime()} }" //第51秒
			)
			// 因为我们设置的模拟时间都是5000毫秒  所以当job1执行完时  其他job也均执行完成
		}
	}


	private fun test5() { //join() 可以按顺序执行，123，不然的话312
		CoroutineScope(Dispatchers.IO).launch {
			val job1 = CoroutineScope(Dispatchers.IO).launch {
				delay(1000)
				Log.e(TAG, "test5----第 1 个任务")
			}
			job1.join() //挂起函数
			val job2 = CoroutineScope(Dispatchers.IO).launch {
				delay(2000)
				Log.e(TAG, "test5----第 2 个任务")
			}
			job2.join()//挂起函数
			Log.e(TAG, "test5----第 3个任务")
		}
	}

	private fun test6() {
		CoroutineScope(Dispatchers.IO).launch {
			Log.e(TAG, "test6: 开始：${TimeHelper.currentTime()}")
			val job1 = async {
				delay(4000)
				Log.e(TAG, "test6: 第一个：${TimeHelper.currentTime()}")
				3
			}
			val job2 = async {
				delay(2000)
				Log.e(TAG, "test6: 第二个：${TimeHelper.currentTime()}")
				4
			} //job1,job2 并行
			val num1 = job1.await() //挂起，注意不要写在上面，否则是变为串行了
			Log.e(TAG, "test6: 结束1：${TimeHelper.currentTime()}")
			val num2 = job2.await() //挂起
			Log.e(TAG, "test6: 结束2：${TimeHelper.currentTime()}")
			val sdf = withContext(Dispatchers.Main) {
				Log.e(TAG, "test6: 第三个：${num1 + num2} ${TimeHelper.currentTime()}")
				"fg"
			}

			//注意：注意：大多数await做串行时 可用withContext代替


			//协程的调度器 (3个)
			// Dispatchers.Main  主线程   CoroutineScope(Dispatchers.IO)
			//主要是处理：UI的更新操作，调用suspend挂起函数
			// Dispatchers.Default  默认 非主线程
			//主要是处理：CPU密集型操作(数据解析，数据计算等)
			// Dispatchers.IO  非主线程
			//主要是处理：IO操作(缓存，文件，数据库等数据)及网络数据

//			协程的启动模式(CoroutineStart)  launch(CoroutineStart.DEFAULT){}
//			CoroutineStart.DEFAULT 创建协程后，立即开始调度。在调度之前如果协程被取消，那么它就不会执行，而是以抛出异常来结束
//			CoroutineStart.ATOMIC
//			CoroutineStart.LAZY
//			CoroutineStart.UNDISPATCHED

		}
	}

}