package com.example.mytest2023.module.kl

/**
 *Created by 钟文祥 on 2023/11/13.
 *Describer: 抽象类 ,要被继承，有些方法必须要实现， 有些方法可以不实现
 * */
abstract class AbsFu<in T>(var str1: String, var num1: Int) {
	abstract fun absFun1(isGo: Boolean): Int
	protected fun fun0(): String {
		return ""
	}
}