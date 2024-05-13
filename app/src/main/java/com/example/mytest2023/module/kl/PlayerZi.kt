package com.example.mytest2023.module.kl

/**
 *Created by 钟文祥 on 2023/10/31.
 *Describer:
 */
class PlayerZi : PlayerFu(30) { //类和方法需要加open

	var i: Int = 0

	override fun dodo() {
		super.dodo()
		var dfsd = age
	}

	fun dodo2() {
		var index = ENUMClass.AA
		val df = ENUMClass.BB.value
	}

	override fun toString(): String {

		return "PlayerZi(i=${i})"
	}


	//kotlin的对比
	//一、java
	//  1、==     基本数据类型比较值，引用类型比较地址
	//  2、equals string比较值，  引用类型比较地址

	//二、kotlin
	//  1、== 和 equal 基本数据和数据类比较值  (其中==在非数据类 比较地址， equal 在float和double比较地址)
	//  2、===    基本数据类型比较值，引用类型比较是否指向同一个对象
}