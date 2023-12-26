package com.example.mytest2023.module.kl

/**
 *Created by 钟文祥 on 2023/11/22.
 *Describer: in 逆变 泛型作为函数的输入
 *  * 如果又有out 和 in 则不需要加
 */
interface IDDD<in T> {
	fun test1(t: T)
}

