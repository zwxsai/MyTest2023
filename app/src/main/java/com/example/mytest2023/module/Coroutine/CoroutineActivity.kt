package com.example.mytest2023.module.Coroutine

import android.util.Log
import com.example.mytest2023.helper.TimeHelper
import com.example.mytest2023.module.Foundation.FoundationActivity
import kotlinx.coroutines.*

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
//		//两者各开了一条线程
//		test1()
//		test2()
//		test3()
//		test4()
//		test5()
//		test6()
//		coroutineStartOne()
//		coroutineStartATOMIC()
//		CoroutineScope(Dispatchers.IO).launch {
//			testCoroutineContext2()
//		}
//		coroutineScopeCancel()
	}

	private fun test0() {
		runBlocking {//开启一个协程，（阻塞线程 ，等执行完才往下执行） 常规函数
			coroutineScope { //挂起函数
				Log.e(TAG, "test0 开始0: " + TimeHelper.currentTime()) //第49秒
				launch {  //协程开启，没返回值
					delay(2000)
					Log.e(TAG, "test0 结束1: " + TimeHelper.currentTime())//第51秒
				}
				Log.e(TAG, "test0 结束2: " + TimeHelper.currentTime())//第49秒
			}
		}
		Log.e(TAG, "test0 开始1: " + TimeHelper.currentTime()) //第51秒
	}

	fun test1() {
		GlobalScope.launch { //开启一个协程，（不阻塞线程，可往下执行）
			Log.e(TAG, "test1开始: " + TimeHelper.currentTime()) //第46秒
			val arg1 = sunpendF1()     //第49秒  （suspend立即挂起，不阻塞线程，等完成再恢复 执行下面的）
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
			var async = async { //async 不阻塞不挂起
				delay(2000)
				"AA"
			}
			val de = 45
			Log.e(TAG, "test3结束1 :${de}:" + TimeHelper.currentTime()) //第46秒
			val date = async.await() //挂起，不往下执行，等到返回后，再往下执行 async需要与await结合使用
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
			// await等待各job执行完 将结果合并，挂起等待完成再恢复下面，挂起
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
			val job1 = launch(start = CoroutineStart.LAZY) {
				delay(1000)
				Log.e(TAG, "test5----第 1 个任务")
			}
			job1.join() //挂起函数
			val job2 = launch {
				delay(2000)
				Log.e(TAG, "test5----第 2 个任务")
			}
			job2.join()//挂起函数
			Log.e(TAG, "test5----第 3个任务")
		}
	}

	private fun test6() {
		CoroutineScope(Dispatchers.IO).launch {//开启协程，不阻塞，不挂起
			Log.e(TAG, "test6: 开始：${TimeHelper.currentTime()}") //40秒
			val job1 = async { //子协程，不阻塞，不挂起
				delay(4000)
				Log.e(TAG, "test6: 第一个：${TimeHelper.currentTime()}")//44秒
				3
			}
			val job2 = async {//子协程，不阻塞，不挂起
				delay(2000)
				Log.e(TAG, "test6: 第二个：${TimeHelper.currentTime()}") //42秒
				4
			} //job1,job2 并行
			val num1 = job1.await() //挂起，注意不要写在上面，否则是变为串行了 ，阻塞，执行完再往下执行
			Log.e(TAG, "test6: 结束1：${TimeHelper.currentTime()}") //44秒
			val num2 = job2.await() //挂起
			Log.e(TAG, "test6: 结束2：${TimeHelper.currentTime()}")//44秒
			val sdf = withContext(Dispatchers.Main) {
				Log.e(TAG, "test6: 第三个：${num1 + num2} ${TimeHelper.currentTime()}") //44秒
				"fg"
			}

			//注意：注意：大多数await做串行时 可用withContext代替


			//协程的调度器 (3个)
			// Dispatchers.Main  主线程   CoroutineScope(Dispatchers.Main)
			//主要是处理：UI的更新操作，调用suspend挂起函数
			// Dispatchers.Default  默认 非主线程
			//主要是处理：CPU密集型操作(数据解析，数据计算等)
			// Dispatchers.IO  非主线程
			//主要是处理：IO操作(缓存，文件，数据库等数据)及网络数据

//			协程的启动模式(CoroutineStart)  launch(CoroutineStart.DEFAULT){}
//			CoroutineStart.DEFAULT 创建协程后，立即开始调度。在调度之前如果协程被取消，那么它就不会执行，而是以抛出异常来结束
//			CoroutineStart.ATOMIC 创建协程后，立即开始调度。 在执行到第一个挂起函数前 不会取消
//			CoroutineStart.LAZY 创建协程后，不立即开始调度。只有协程被需要时，包括主动调用协程的start()/join()/await()等函数时，才开始。
//			在调度之前如果协程被取消，那么它就不会执行，而是以抛出异常来结束
//			CoroutineStart.UNDISPATCHED
//			协程创建后，立即在当前函数调用栈执行(在哪个线程创建，在哪个线程执行)。
//			在哪个函数创建，就在哪个线程执行，从名字可以看出，它不接受Dispatchers指定线程的调度
//			在执行到第一个挂起函数前 不会取消

//			1，DEFAULT，ATOMIC创建后，会立即调度（并不是立即执行）；LAZY是只有触发了，才会执行；UNDISPATCHED会立即执行
//			2，UNDISPATCHED执行的线程是创建它的函数所在线程，哪怕指定线程，也无效
//			3，DEFAULT取消时，会被立即取消
		}
	}


	fun coroutineStartDEFAULT() {
		runBlocking {
			//启动协程
			val job = launch(start = CoroutineStart.DEFAULT) {
				Log.d("liu", "default  start")
				delay(3000)
				Log.d("liu", "default  end")
			}
			delay(1000)
			//取消
			job.cancel()
		}
	}

	fun coroutineStartATOMIC() {
		runBlocking {
			//启动协程
			val job = launch(start = CoroutineStart.ATOMIC) {
				Log.d("liu", "default  start")
				delay(3000)
				Log.d("liu", "default  end")
			}
			//取消
			job.cancel()
		}
	}

	fun coroutineStartLAZY() {
		runBlocking {
			val job = launch(start = CoroutineStart.LAZY) {
				Log.d("liu", "LAZY  start")
			}
			Log.d("liu", "开始一些计算")
			delay(3000)
			Log.d("liu", "耗时操作完成")
			job.start()
		}

		//打印结果
//		开始一些计算
//		耗时操作完成
//		LAZY  start
	}

	fun coroutineStartUNDISPATCHED() {
		runBlocking {
			val job = async(context = Dispatchers.IO, start = CoroutineStart.UNDISPATCHED) {
				Log.d("liu", "线程IO:${Thread.currentThread().name}")
			}
			val job1 = async(context = Dispatchers.IO, start = CoroutineStart.DEFAULT) {
				Log.d("liu", "线程:${Thread.currentThread().name}")
			}
		}
		//打印结果
//		线程IO:main
//		线程:DefaultDispatcher-worker-1
	}

	private suspend fun teeee() {
		CoroutineScope(Dispatchers.IO).async(
			context = Dispatchers.IO, start = CoroutineStart.DEFAULT
		) {
			launch {

			}
		}
		//作用域
		runBlocking { //常规函数 阻塞线程
			coroutineScope {
				launch {

				}
			}
			launch { }
		}

		coroutineScope {//挂起函数 不阻塞线程 需要suspend ，内部一个协程失败了，其它的协程也会被取消

		}
		supervisorScope {//挂起函数 不阻塞线程 需要suspend ，内部一个协程失败了，不影响其它协程

		}
	}

	fun coroutineScopeCancel() {
		//等待子协程执行完
		runBlocking<Unit> {
			//CoroutineScope不会继承runBlocking的属性。需要delay或者join
			val scope = CoroutineScope(Dispatchers.Default)
			val job1 = scope.launch {
				delay(1000)
				Log.d("liu", "启动 job 1")
			}
			val job2 = scope.launch {
				delay(1000)
				Log.d("liu", "启动 job 2")
			}

//			delay(200)
			delay(2000)
//			scope.cancel() //一个协程作用域 取消
			job1.cancel()
			job2.cancel()
		}
	}


	private suspend fun testCoroutineContext2() {

		val exceptionHandler = CoroutineExceptionHandler { _, exception ->
			Log.d("liu", "exception: $exception")
		}
		Log.d("liu", "1 Top Job exceptionHandler: $exceptionHandler")
		//创建Job
		val topJob = Job()
		//创建一个新的协程作用域
		val scope =
			CoroutineScope(topJob + Dispatchers.Default + CoroutineName("coroutine new Name") + exceptionHandler)
		//打印顶层Job
		Log.d("liu", "2 Top Job Info: $topJob")
		val job = scope.launch() {
			//打印协程相关信息
			Log.d(
				"liu",
				"3 Job Info: ${coroutineContext[Job]}  ${coroutineContext[CoroutineName]}" +
						" ${coroutineContext[CoroutineExceptionHandler]} " +
						", Thread info: ${Thread.currentThread().name}"
			)
			val job2 = async {
				Log.d(
					"liu",
					"4 Job Info: ${coroutineContext[Job]} ${coroutineContext[CoroutineName]}" +
							" ${coroutineContext[CoroutineExceptionHandler]} " +
							", Thread info: ${Thread.currentThread().name}"
				)
			}
			job2.await()
		}
		job.join()
	}

////打印结果
////异常Handler
//	Top Job exceptionHandler: cn.edu.coroutine.CoroutineTestActivity$testCoroutineContext2$$inlined$CoroutineExceptionHandler$1@6c29d69
//
////Job名字
//	Top Job Info: JobImpl{Active}@50933ee
//
////launch打印 job  协程名字  exceptionHandler  线程信息
//	Job Info: StandaloneCoroutine{Active}@ad1d08f  CoroutineName(coroutine new Name) cn.edu.coroutine.CoroutineTestActivity$testCoroutineContext2$$inlined$CoroutineExceptionHandler$1@6c29d69 , Thread info: DefaultDispatcher-worker-2
//
////launch打印 job  协程名字  exceptionHandler  线程信息
//	Job Info: DeferredCoroutine{Active}@67b8f1c CoroutineName(coroutine new Name) cn.edu.coroutine.CoroutineTestActivity$testCoroutineContext2$$inlined$CoroutineExceptionHandler$1@6c29d69 , Thread info: DefaultDispatcher-worker-1

//	通过launch 或 async启动的协程，系统始终会向新协程分配 Job 的新实例。所以，Job是不会传递到子协程的，但是，其它的属性，都可以被继承下来

}