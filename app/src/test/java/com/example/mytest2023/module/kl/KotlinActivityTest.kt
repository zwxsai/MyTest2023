package com.example.mytest2023.module.kl

import android.util.Log
import org.junit.Assert
import org.junit.Test


/**
 * Created by 钟文祥 on 2026/1/23.
 * Describer:
 */
class KotlinActivityTest {

    @Test
    fun onClick2() {
        Log.e("lgddfg", "onClick2: 33")
    }


    @Test
    fun onClick() {
        val sdf = 3
        val dfgdf = 5
        val dtr = true
        Assert.assertEquals(false, sdf + dfgdf != 9) //断言验证结果是否正确 是否等于8
//        Assert.assertEquals(true, sdf + dfgdf > 9)

    }

    data class TestA(
        val a: Int,
        val b: Boolean
    )
}