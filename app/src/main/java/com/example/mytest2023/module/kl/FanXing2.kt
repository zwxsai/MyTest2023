package com.example.mytest2023.module.kl

/**
 *Created by 钟文祥 on 2023/11/21.
 *Describer:
 */
class FanXing2<T>(
	vararg _items: T
) {
	private var items: Array<out T> = _items
	private var items2 = _items
}