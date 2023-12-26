package com.example.mytest2023.module.Foundation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mytest2023.R;
import com.example.mytest2023.helper.OnItemRecyclerListener;

/**
 * Created by 钟文祥 on 2023/4/24.
 * Describer：算法
 * <p>
 * https://blog.csdn.net/chiqi4262/article/details/100606802
 */
public class SuanFaActivity extends FoundationActivity {

    private static final String TAG = "SuanFaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_su_fa);
    }

    @Override
    public void initView() {
        txt1.setText("算法");
        txt2.setText(data);
        btn1.setVisibility(View.VISIBLE);
        btn1.setText("插入排序");

        btn1_2.setVisibility(View.VISIBLE);
        btn1_2.setText("选择排序");

        btn2.setVisibility(View.VISIBLE);
        btn2.setText("冒泡排序");

        btn2_2.setVisibility(View.VISIBLE);
        btn2_2.setText("快速排序");

        listener = new OnItemRecyclerListener() {
            @Override
            public void onClick(int position, Object object) {
                int[] nums = new int[]{9, 4, 2, 7, 8, 1, 3, 0};
                switch (position) {
                    case R.id.btn1:
                        nums = sortInsert(nums);
                        break;
                    case R.id.btn1_2:
                        nums = sortSelect(nums);
                        break;
                    case R.id.btn2:
                        nums = sortBubble(nums);
                        break;
                    case R.id.btn2_2:
                        sortQuick(nums, 1, 2);
                        break;
                }
                Log.e(TAG, "得出结论:" + nums.toString());
                txt2.setText(nums.toString());
            }
        };
    }


    /**
     * 插入排序
     *
     * @param array 没有排序的数组
     * @return 从小到大排好序的数组
     * <p>
     * 插入排序思路: 首先默认数组中第一个( [0] )是排好序的,从第二位( [1] )开始进行判断,
     * 如果第二位大于第一位的话,就插入到第二位,不大于的话,就将第一位往后移动一位,变成第二位,原先的第二位插入到第一位
     * <p>
     * 我的理解:第一个与第二个比 大了调换，第二个与第三个比又调换
     */
    public int[] sortInsert(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j;
            for (j = i - 1; j >= 0 && temp < array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;//插入进数组
        }
        return array;
    }

    /**
     * 选择排序
     *
     * @param arr 没有排序的数组
     * @return 从小到大排好序的数组
     * <p>
     * 选择排序的思路是先从未排序的数组中找到最小数值的元素,
     * 放到新数组第一位上,然后从数组中剩下未排序部分的元素中再选择最小值的元素,并将此值放到新数组的结束位上,以此类推,
     */
    public int[] sortSelect(int[] arr) {
        for (int i = 0; i < arr.length; i++) { //循环轮数
            int min = i; //假设最小数值对应的角标
            for (int j = i + 1; j < arr.length; j++) { //每轮循环次数
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            //找出最小值后,进行交换
            if (arr[i] > arr[min]) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        return arr;
    }

    /**
     * 冒泡排序
     *
     * @param nums
     * @return 从小到大排好序的数组
     * 冒泡排序的思路是:从左往右两两进行比较,较大的值靠右边放,以此类推,直到结束
     */
    public int[] sortBubble(int[] nums) {
        int temp = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }


    /**
     * 快速排序 (冒泡的进阶版)
     * 取第一个元素为基数，循环比较最小位置值与最大位置值，较大的往后排，较小的往前排，
     * 然后并较小的位数加一，较大的位数减一，循环结果就是中间位数，
     * 最后就是以中间两部分分开做递归运算再次比较排列，直到正确排序为止
     */
    public void sortQuick(int[] arr, int start, int end) {
        if (start > end) return;
        int i, j, temp, t;
        i = start; //起始位 , 0
        j = end; //结束位,数组长度-1
        temp = arr[start]; // 选择数组第一位作为基准
        while (i < j) {
            while (i < j && temp <= arr[j]) { //先从数组最后往前检查,当基准数小于时,j--,继续往前走,一旦大于了,就停下,执行下一个while循环
                j--;
            }

            while (i < j && temp >= arr[i]) { //从数组开始位,往后检查,当基准数大于时,i++,接着往前走,直到基准小于时候,停下, 然后执行下面交换逻辑
                i++;
            }

            //两个while循环执行完,交换两个循环停下位置的数值,交换完毕后,再接着走最外侧的大while循环,直到i>j时,跳转整个while循环,执行下面基准位的赋值逻辑
            if (i < j) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        //将基准位的数值赋值给中间i和j相遇的位置
        arr[start] = arr[i];
        arr[i] = temp;
        //以上是第一轮检查,并将基准位确定出来,此时,基准位左侧都比基准数值小,右侧都比基准数大

        //递归遍历基准位置左侧的数组
        sortQuick(arr, start, j - 1);
        //递归遍历基准位置右侧的数组
        sortQuick(arr, i + 1, end);
    }

}