package com.example.mytest2023.module.kl

/**
 *Created by 钟文祥 on 2023/11/22.
 *Describer: 将泛型类作为 函数返回的 ，用out协变
 * 如果又有out 和 in 则不需要加
 */
interface ICCC<out T> {
	fun test1(): T
}



