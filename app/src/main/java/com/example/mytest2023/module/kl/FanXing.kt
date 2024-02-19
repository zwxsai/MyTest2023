package com.example.mytest2023.module.kl

/**
 *Created by 钟文祥 on 2023/11/13.
 *Describer: 泛型
 */
class FanXing<T>(_item: T) {
	private var item: T = _item;

	val isGo = false

	private fun testFun1(): T? {
		var tt: T? = null
		return tt;
	}
}