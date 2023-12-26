package com.example.mytest2023.module.kl

/**
 *Created by 钟文祥 on 2023/11/13.
 *Describer:
 */
class IbbImpl : IBB {

	override val str1: String
		get() = super.str1
//		set(value) {}

	override var num1: Int
		get() = super.num1
		set(value) {}

	override fun funRun(str1: String, num1: Int): Boolean {
		TODO("Not yet implemented")
	}
}