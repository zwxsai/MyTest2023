package com.example.mytest2023.module.kl

import android.content.Context
import android.util.ArrayMap
import com.example.mytest2023.module.Foundation.Note
import com.google.gson.JsonObject
import kotlin.math.roundToInt

/**
 *Created by 钟文祥 on 2023/10/27.
 *Describer:
 */
class KotlinDemoTest() {

	fun test30() {
		if (AAADataInfo(10, 20) == AAADataInfo(10, 20))//=====>true
		{

		}
		if (AAADataInfo2(10, 20) == AAADataInfo2(10, 20))  //====>false
		{

		}

	}

	//kotlin嵌套类 相当于java静态内部类
	class KotlinDemoTest2() {

	}

	private var context: Context? = null
	private var strNull: String? = "4g" //安全调用符 加上？ 可空。可以赋空值，kotlin默认不给赋空值

	//变量的类型为一个无参返回string的函数 等于 一个匿名函数 （不需要return 默认最后一行返回值）,其变量已变为一个函数
	private var actionStr: () -> String = {
		KotlinDemoTest.KotlinDemoTest2()//静态内部类
		val tr = "43dfg"
		//1 安全调用符
		strNull = null
		strNull?.capitalize() //安全调用符，加上？. 非空执行，为空不执行
		//变为三目运算了 
		val df = strNull?.let {
			if (it.isNotBlank()) { //不为空时 ，执行capitalize
				it.capitalize()
			} else { //为空时，默认赋值
				"dfd"
			}
		}
		//2 断言操作符
		strNull!!.capitalize() //非空断言符  。是不是空都能调用，为空就报错

		try {
			//3 空合并操作符
			// TODO:  
			strNull ?: "4gfdg" //不为空时返回本身，为空时赋值
			strNull = strNull ?: "dddd";

			//等价于
			if (strNull == null) strNull = "4gfdg"
			else strNull
		} catch (e: Exception) {
			e.printStackTrace()
		}
		var tt = 4
		strNull?.substring(0, tt)

		var list = mutableListOf<String>()
		var list2 = arrayListOf<String>();
		list.add("dd")
		list2.add("4")
		list[0] = ""
		list2[0] = ""

		//结构语法
		var (a1, a2, a3) = "AA".split(",")
		val sdt = a2
		var a11 = "AA".split(",")
		"AA".replace(Regex("ABCD")) {
			when (it.value) {
				"A" -> "P"
				else -> it.value
			}
		}//regex 正则表达式 里面的替换


		val te1: String = "AAA"
		val te2: String = "AAA"
		if (te1 == te2) { //kotlin内容比较

		} else if (te1 === te2) { //kotlin两个变量是否指向内存堆上同一个对象

		}
		var number1: Int? = "5.45".toIntOrNull() //非空 取整
		var number2: Int = 5.67.roundToInt() //4舍5入
		var number3 = "%.2f".format(5.67685) //double类型格式化

		tr
	}

	//带参数名的函数,匿名函数
	private var actionStr2: (a1: Int, b1: String) -> String = { a1, b1 ->
		"${a1}  ++++  ${b1}"
	}

	//不带参数名的函数，匿名函数
	private var actionStr3: (Int, String) -> String = { aa1, bb1 ->
		val di1: Int = aa1
		val di2: String = bb1
		"dfd"
	}

	//只有一个参数的时候 自带一个it 代表参数
	private var actionStr4: (String) -> String = {
		val di1: String = it
		"${di1}   AAA   ${it}"
	}

	//等于一个大括号 等于一个无参无返回值的函数
	private var actionStr5 = {
		val di1 = 4
	}

	//自动类型推断
	private var actionStr6 = { name: String, age: Int ->
		45
	}


	fun test1() {

		var list10 = ArrayList<PlayerZi>()


//		val 只读变量（常量）  var 可修改变量
		//可变 有排序，有重复
		val list1 = mutableListOf<String>()
		list1.add("AA")
		list1.add(0, "BB")
		//list的get和set
		var str1 = list1[0]
		str1 = list1.get(0)
		list1.set(0, "CC")
		list1[0] = "DD"
		list1.clear()
		list1.sort()//从小到大
		list1.sortDescending()//从大到小
		list1.forEach { s: String -> s + "11" }
		list1.forEachIndexed { index, s -> "${index}" + s }
		for ((index, item) in list1.withIndex()) {

		}
		for (i in 0 until 5) {

		}
		for (i in 0..5) {

		}

		var list111 = arrayListOf(1, 1)
		var list222 = mutableListOf<Int>(1, 2)


		val arr1 = arrayOf("aaa")
		for (index in arr1.indices) {

		}
		arr1[0]

		//初始化 并赋值
		val list2 = mutableListOf<Int>(0, 1, 2)
		list2.add(4)
		list2.set(1, 4)
		list2[1] = 4 //推荐

		val list324 = mutableListOf<Int>()
		val sdf = arrayListOf<Int>() //推荐
		val mResBitList: List<String> = ArrayList<String>()
		val map: Map<Int, String> = HashMap<Int, String>()


		//不可变
		val list3 = listOf<String>("AA", "BB")

		//list 有排序，有重复
		//set  无排序，无重复

		//可变 无排序，无重复
		val set1 = mutableSetOf<String>();
		set1.add("BB")
		set1.add("BB") //不会重复添加了


		val map1 = mutableMapOf<String, String>(Pair("key1", "value1"))
		map1["key2"] = "value2"
		map1["key2"] = "value3"
		map1.put("key3", "value3")
		var str2 = map1["key2"]
		str2 = map1.get("key2")

		val map2 = mutableMapOf<Int, String>()
		map2.put(0, "value1")
		map2.set(0, "value1")
		map2[0] = "value1"//推荐
		map2.containsKey(2)
		map2.remove(0)
		var size = map2.size
		size = list1.size

		val map32 = mutableMapOf<String, String>()
		map32.remove("5")

		var map3 = HashMap<Int, String>()
		map3.put(4, "5")
		map3.get(4)
		map3[4] = "5"
		var sd = map[4]

		map2.forEach {
			it.key + it.value.toInt()
		}
		map2.iterator().forEach { mutableEntry: MutableMap.MutableEntry<Int, String> ->
			val fgf = "${mutableEntry.key}" + mutableEntry.value
			val sdf = mutableEntry.key.toString() + mutableEntry.value
		}

		var strtest = test1(2, "2")
		MyClass1().test1() //new出来 点
		Myclass2.test2() //object 单例类 静态方法
		MyClass1.test3() //companion object 将静态方法写在一起
		MyClass1.test4()
		strtest = test5("cc")
		strtest = test5("dd", 3)
		test6(a2 = "rt")
		test6(3, "cc")

		test7(2, "ee", {
			var ew = 3
		})
		test8(65, "ff", {
			var sd = 4
			return@test8 43
		})
		//或者 当最后一个参数为方法时 可以
		test8(435, "gg", action = {
			var dfg = 43
			76 //最后一行为返回值
		})
		//或者 当最后一个参数为方法时 可以
		test8(435, "gg", {
			var dfg = 43
			return@test8 76 //最后一行为返回值
		})
		//或者   当最后一个参数为方法时 可以
		test8(534, "hh") {
			var dfh = 4
			return@test8 30
		}

		test9(54, "aa", "bb")
		//可以加一个数组 前面用 *号
		val arrel = arrayOf("cc", "dd")
		val list333 = mutableListOf<String>("aa", "bb")
		test9(64, "aa", "bb", *arrel)

		test10("aa", "bb", *arrel, a1 = 43)



		test11(list1 = list1, action = { index, value ->
			var sdf = "${index} 东方闪电 ：${value}"
			return@test11 4
		})

		val list6 = mutableListOf<String>()
		list6.forEach { s: String -> var sdfgsfd = s }
		list6.forEach {
			var gdfg = it
		} //当高阶lambde函数只有一个参数时，可以用it来代替
		list6.forEach { sere: String -> var sd = sere }

		//.class  ->> ::class.java
		var obj: JsonObject? = fromjson<JsonObject>("json", JsonObject::class.java)

		MyClass1().test2(4)//扩展方法

		test19("100", action1)
		var sddfgsdff = action1("4")


	}

	fun test1(a1: Int, a2: String): String {
		return ""
	}

	fun test5(a2: String, a1: Int = 45): String {
		return "${a1} ：${a2}"
	}

	//默认参数 和 具名参数
	fun test6(a1: Int = 45, a2: String): String {
		return "${a1} ：${a2}"
	}


	//action为方法名=参数名，（）就为方法   -> 返回值 无返回值用unit，有返回值用int
	fun test7(a1: Int = 34, a2: String, action: () -> Unit): String {
		return "${a1} ：${a2}：${action()}"
	}

	fun test8(a1: Int = 54, a2: String, action: () -> Int): String {
		return "${a1} ：${a2}：${action()}"
	}

	//不定长参数 vararg 就是一个数组，一般放在最后一个参数
	fun test9(a1: Int = 65, vararg a2: String): Unit {
		var gf = 4
	}

	//如果不定长参数 不在最后一位 ，后面的参数要用具名参数
	fun test10(vararg a2: String, a1: Int): Unit {

	}

	fun test11(list1: MutableList<String>, action: (index: Int, value: String) -> Int) {
		for ((index1, item) in list1.withIndex()) {
			val wer: Int = action(index1, list1[index1])
			val wer2: Int = action(index1, item)
			val sd = 5 * wer
		}
		list1.forEachIndexed { index, s ->

		}
	}

	fun test11(a: Int, b: Int): Int {
//		return a>b?a:b //没有3目运算符的,只能用if else
		return if (a > b) a else b
	}

	//注意 if和when都是一个表达式 有返回值,返回值一般是最后一行 或return
	fun test12(a: Int, b: Int): Int {
		var ddd: Int = if (a > b) return 4 else if (a == 5) {
			var fg = 5
			6
		} else if (b == 4) 9 else 5

		return if (a > b) {
			return 5
		} else if (a == 3) {
			5
		} else {
			5
		}

	}

	fun test13(a: Number): Int {
		val rt: Int = when (a) {
			is Int -> {
				var f3 = 4
				return f3
			}
			is Double -> {
				var geomagneticF = 3
				return geomagneticF
			}
			else -> {
				return 3
			}
		}
		return rt
	}

	fun test14(a: Int): Int {
		return when (a) {
			220 -> {
				val er = 4
				56
			}
			else -> {
				3
			}
		}
	}

	fun test15(a: Int): Int = when (a) {
		200 -> a * 4
		else -> 400
	}

	//方法泛型 ？值可为空
	fun <T : JsonObject> fromjson(json: String, tClass: Class<T>): T? {
		val t: T = tClass.newInstance()
		return t
	}

	//为已有的类 扩展方法
	fun MyClass1.test2(a: Int): String? {
		return a.toString()
	}

	fun test14() {
		var str1: String? = null
		val str2 = str1?.let {
			val sd4 = 3
			"4t"
		}
		val str3 = str1.run {
			toString()
			4
		}
		val str4 = str1.apply {  //初始化
			4
		}
		val str5 = str1.also { //更新
			5
		}
	}

	//let 函数适合对一个对象进行一系列处理，并返回处理结果的场景,返回值表达式的执行结果作为返回值，it为对象，要用it点出来方法
	//run 函数适合对一个对象进行一系列处理，并返回处理结果的场景,返回值表达式的执行结果作为返回值，this类似java的this，可以直接用this本身的方法
	//with 等价于run 返回最后一行和return
	//apply 返回对象本身 this  用于初始化
	//also 等价于apply  返回对象本身 用于执行对象操作 可以链式调用
	//takeif函数 判断是否为true，不为空返回对象本身  为空返回null
	//takeunless 与takeif相反


	//let扩展  ?可能为空，方法体里需要判空处理,  作用：形成一个作用域，有返回值，return值，定义变量，做判空操作，可以用公有方法和属性
	fun test16(str: String?) {
//		str.let {
//			val str2 = "43dfg" + it
//		}
		//做判空处理 ，不为空时，执行let方法
		var tmep = str?.let {
			"43dfg" + it
			5
		}
//		val str3 = str2 //不能使用作用域的变量

		var str1 = "fdsfg"
		var temp = str1.let {
			it.length
		}.let {
			(it + 3).toString()
		}.let {
			it.toInt()
		}

		//返回对象本身
		var dg = str1.also {
			it.length
		}.also {
			it.toIntOrNull()
		}.also {
			it + 567
		}
	}

	//run扩展 形成一个作用域 ，有返回值，return值，里面的隐藏属性this，可调用公有，用来做对象的设置set
	fun test17(note: Note?) {
		var tmep = note?.run {
			val der = this.anInt1
			this.test1()
			test1() //直接用对象里面的方法
		}
		var str1 = "ABCf"
		var dfgf = str1.run {
			length > 3
		}

		with("ABCf") {
			length > 3
		} //与run相等


		//takeif函数 判断{}最后一行为true，返回自身it，为false，返回null
		var dgf = str1.takeIf { it.length > 3 }?.toString()

	}

	//apply扩展 形成一个作用域，返回对象本身，里面的隐藏属性this，可调用公有
	private fun test18(note: Note?) {  //默认是public
		var fgdf = note?.apply {
			this.test1()
			var str: String = actionStr()
			actionStr2(4, "fg")
		}
		val age = 4;
		if (age in (0..3)) {  // in 闭区间

		} else if (age in (3..6)) {
			TODO("5g")
		}


	}


	//-----------------函数作为参数的函数的 写法  start-----------------
	/**自己解决不了的事 交给别人做*/
	private fun test19(a1: String, action1: (str1: String) -> Int): Boolean {
		return a1.equals(action1("100").toString())
	}

	private fun test21(action1: (str1: String) -> Int): Boolean {
		return "a1".equals(action1("100").toString())
	}

	private fun yinyong(str1: String): Int {
		return when (str1) {
			"100" -> 100
			else -> 50
		}
	}

	private val action1 = { str1: String ->
		when (str1) {
			"100" -> {
				100
			}
			else -> 50
		}
	}

	private fun test20() {
		test19("100") {
			//匿名函数lambda函数参数排在最后，则lambda可以写在括号后面。
			// 或只有一个lambda函数参数，则可把括号去掉，再写在后面
			when (it) {
				"100" -> 100
				else -> 50
			}
		}
		test19("100", action1) //参数指向的函数

		test19("100", ::yinyong) //函数引用

		test21 {
			when (it) {
				"100" -> 100
				else -> 50
			}
		}
		test21(action1)

		var playerFu = PlayerFu(3)
		Thread.sleep(3000)
		playerFu.config

		var playerFu2: PlayerFu = PlayerZi()
		if (playerFu2 is PlayerZi) {
			(playerFu2 as PlayerZi).dodo2() //转一次就成功了 ，后面不用转
			playerFu2.dodo2()
		}

	}
	//-----------------函数作为参数的函数的 写法  end-----------------


	private fun test22() {
		//变换过滤合并

		//1map函数 副本 遍历每一一个元素，进行改造 输入一个新list，原来的list不变
		val list1 = listOf<String>("abbb", "b")
		val list2 = list1.map { "cc$it" }
		val list3 = list2.map { it.length }

		//1flatmap函数 操作一个集合的集合，将所有元素 拆开再合并为一个集合
		val list4 = listOf(listOf("a", "b"), listOf("a", "b"))
		val list5 = list4.flatMap { it }

		//2filter 过滤保留 符合条件的 元素
		val list6 = list1.filter { it.contains("a") }

		//none1没有**条件的返回true ，2没有元素的返回true
		val list62 = list1.none { it.contains("a") }

		//3 zip合并成一个map键值对
		val map = list1.zip(list3)

		//惰性集合
		//take函数是根据传入的参数挑出该集合【前n个元素】的子集合 //有667个，够数就不往下执行了
		//take满足条件的前多少个
		val list7 = (0..5000).toList().filter { it % 2 == 0 }.take(1000)

		//有1000 个  generateSequence（）无线加载
		val list8 = generateSequence(2) {
			it + 1
		}.filter { it % 2 == 0 }.take(1000)
		val len1 = list8.toList().size

	}
}

//普通类
class MyClass1 {
	fun test1() {
		var tempClass = object : PlayerFu(45) { //object表达式，相当于实现一个接口
			override fun getStr(): String {
				return "123"
			}
		}
		var a1 = tempClass.getStr()
		var a2 = Myclass2.test2()
		var a3 = Myclass2.str
	}

	//伴生对象  只有一份 静态单例的 ，里面的方法都为静态  。类似于static写在一起
	companion object {
		fun test3() {}
		fun test4() {}

		//内联函数 ,避免函数参数出现在循环里面的时间开支
		inline fun test5(block: (Int) -> Int) {
			for (i in 0 until 10) {
				var item = block(i)
			}
		}
	}
}

//单例对象 静态类 里面默认都是静态方法
object Myclass2 {
	const val str: String = "54gh" //静态变量
	var str2 = "ghfg"

	fun test2(): Int {
		return 45;
	}
}

class MyClass3(a: Int) : IClickListener<String>, MyAbs<String>(a) {
	override fun test3(a: Int, t: String): Int {
		TODO("Not yet implemented")
	}

	override fun test1(b: Int, t: String) {
		var dd: String = "${a}gdf..${b}...${t}"
//		var ee:String="$a ghgfgh$b dfg"
	}
}

interface IClickListener<T> {
	fun test3(a: Int, t: T): Int
}

abstract class MyAbs<T>(val a: Int) {
	abstract fun test1(b: Int, t: T)
}