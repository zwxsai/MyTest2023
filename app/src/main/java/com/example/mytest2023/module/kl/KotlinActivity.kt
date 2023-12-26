package com.example.mytest2023.module.kl

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.mytest2023.R
import com.example.mytest2023.helper.ToastUtil
import kotlin.time.seconds

//函数当参数
class KotlinActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_kotlin)
		R.id.txt1.OnClick(this, action = { //只有一个参数的时候 自带一个it 代表参数
			it.setText("AAA")
		})
		R.id.txt1.OnClick(this, {
			it.setText("AAA")
		})
		R.id.txt1.OnClick(this) {
			it.setText("AAA")
		}


		R.id.txt2.OnClick2(this) { txt, isOpen ->
			txt.setText("BBB")
			6
		}
		R.id.txt2.OnClick2(this) { txt: TextView, isOpen: Boolean ->
			txt.setText("CC")
			7
		}


		//匿名函数 ,前后至少有一个是 声明变量类型
		var action: (txt: TextView, isOpen: Boolean) -> Int = { txt: TextView, isOpen: Boolean ->
			txt.setText("DD")
			7
		}
		R.id.txt2.OnClick2(this, action);

		var action1: (TextView, Boolean) -> Int = { a1, a2 ->
			5
		}
		R.id.txt2.OnClick2(this, action1);

		var action12 = { txt: TextView, isOpen: Boolean ->
			5
		}
		R.id.txt2.OnClick2(this, action12);

		var action3: () -> Unit = {
			val e3 = 2
		}

		R.id.txt2.OnClick3(this, action3);

		var action4 = {
			val e3 = 2
		}
		R.id.txt2.OnClick3(this, action4);

		R.id.txt2.OnClick3(this, ::test1)
	}

	//添加int的其他方法
	fun Int.OnClick(context: Context, action: (txt: TextView) -> Unit) {
		val view: TextView = findViewById<TextView>(this)
		view.setOnClickListener(View.OnClickListener {
			ToastUtil.showMsg(context, "AA")
			action(view)
		})
	}

	//带参函数当参数，自己做不了的事情交给别人做
	fun Int.OnClick2(context: Context, action: (txt: TextView, isOpen: Boolean) -> Int) {
		val view: TextView = findViewById<TextView>(this)
		view.setOnClickListener(View.OnClickListener {
			ToastUtil.showMsg(context, "AA")
			action(view, true)
		})
	}

	fun Int.OnClick3(context: Context, action: () -> Unit) {
		val view: TextView = findViewById<TextView>(this)
		view.setOnClickListener(View.OnClickListener {
			ToastUtil.showMsg(context, "AA")
			action()
		})
	}

	fun test1() {

	}
}


