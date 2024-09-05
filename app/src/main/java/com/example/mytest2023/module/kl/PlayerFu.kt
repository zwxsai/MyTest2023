package com.example.mytest2023.module.kl

import android.util.Log

/**
 *Created by 钟文祥 on 2023/10/31.
 *Describer:
 */
open class PlayerFu( //类默认都是不能被继承的，加上open就能被继承
	_id: Int, _name: String, var age: Int = 32
) {
	//operator 运算符 可以对对象用 +号了
	operator fun component1() = age + 45

	var id = _id
		//		get() {
//			var str: String = "ABC"
//			return str.length
//		}
		set(value) {
			var str: String = "ABC"
			field = str.length + value //field就是本身
		}

	var name = _name
		//		get() {
//			return name + "56"
//		}
		set(value) {
			name = value + "567"
		}



	init {
		var tr = 4
		var name2 = name
	}

	//次构造函数
	constructor(id2: Int) : this(id2, _name = "zhong") {
		this.id = id2 + 10
	}


//	override fun toString(): String {
//		return "Player2(id=$id, name='$name',age=$age, )"
//	}

	override fun toString(): String {
		return "Player2(id=$id, name='$name',age=$age, )"
	}

	open fun dodo() {
		if (::work.isInitialized) {////函数引用
			Log.e("kotlin", "dodo: " + work)
		}

	}

	open fun getStr(): String {
		return "AAB"
	}

	lateinit var work: String  //延时初始化

	val config by lazy { test1() } //懒惰加载 几秒后才会执行   lazy只适用于val对象，对于var对象，需要使用lateinit
	fun test1(): String {
		Log.e("kotlin", "test1: loading")
		if (age == 3) {

		}
		return "ABC"
	}
}

//调用顺序
//1、主构造函数的参数声明
//2、类级别的参数赋值
//3、init()方法的赋值和函数调用  ，注init()方法需要写在参数后面
//4、次构造函数的赋值和函数调用