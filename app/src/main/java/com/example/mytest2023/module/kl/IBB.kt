package com.example.mytest2023.module.kl

/**
 *Created by 钟文祥 on 2023/11/13.
 *Describer: kotlin接口 可有属性，每一一个属性和方法 都要用overwrite实现
 * 一般用于函数的声明
 */
interface IBB {
	val str1: String
		get() = "ttt"
	var num1: Int
		get() = 4
		set(value) = TODO()

	fun funRun(str1: String, num1: Int): Boolean;
}