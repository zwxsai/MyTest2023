package com.example.mytest2023.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by 钟文祥 on 2019/2/19.
 * Describer: Bitmap 辅助类
 */
public class BitmapHelper {
    /** 创建jpeg图片文件 */
    public static void createJPEGFileByBitmap(Bitmap bitmap, String dirPath, String fileName, int
            quality) {
        createImgFileByBitmap(bitmap, dirPath, fileName, Bitmap.CompressFormat.JPEG, quality);
    }

    /** 创建png图片文件 */
    public static void createPNGFileByBitmap(Bitmap bitmap, String dirPath, String fileName, int
            quality) {
        createImgFileByBitmap(bitmap, dirPath, fileName, Bitmap.CompressFormat.PNG, quality);
    }

    /** 通过Bitmap 创建图片文件 quality默认90 */
    private static void createImgFileByBitmap(Bitmap bitmap, String dirPath, String fileName, Bitmap
            .CompressFormat format, int quality) {
        FileHelper.createDir(dirPath);

        File myCaptureFile = new File(dirPath + fileName);
        if (myCaptureFile.exists()) {
            myCaptureFile.delete();
        }
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream
                    (myCaptureFile));
            bitmap.compress(format, quality, bos);

        } catch (Exception e) {
            e.printStackTrace();
            myCaptureFile.delete();
        } finally {
            try {
                bos.flush();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static Bitmap getImgBitMap(String filePath) {
        return BitmapFactory.decodeFile(filePath);
    }

    /** 获取文字的bitmap */
//    public static Bitmap getTextViewBitmap(Context context, String msg, int dimen) {
//        LinearLayout view = (LinearLayout) LayoutInflater.from(context)
//                .inflate(R.layout.view_text_watermark, null);
//        TextView txtMsg = (TextView) view.findViewById(R.id.water_msg);
//        txtMsg.setText(msg);
////        txtMsg.setTextColor(context.getResources().getColor(resId));
//        int dp = 14;
//        switch (dimen) {
//            case R.dimen.large:
//                dp = 30;
//                break;
//            case R.dimen.medium:
//                dp = 28;
//                break;
//            case R.dimen.small:
//                dp = 26;
//                break;
//            case R.dimen.Xsmall:
//                dp = 24;
//                break;
//            case R.dimen.XXsmall:
//                dp = 22;
//                break;
//            case R.dimen.XXXsmall:
//                dp = 20;
//                break;
//        }
////        dp = dp2px(context, dp);
//        txtMsg.setTextSize(dp);
//        return view2Bitmap(view);
//    }

    private static Bitmap view2Bitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View
                .MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /** 添加水印图到左下 */
    public static Bitmap createWaterMaskLeftBottom(
            Context context, Bitmap src, Bitmap watermark) {
        return createWaterMaskBitmap(src, watermark, dp2px(context, 30),
                src.getHeight() - watermark.getHeight() - dp2px(context, 50));
    }

    private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark,
                                                int paddingLeft, int paddingTop) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        //创建一个bitmap
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//
        // 创建一个新的和SRC长度宽度一样的位图
        //将该图片作为画布
        Canvas canvas = new Canvas(newBitmap);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, null);
        //在画布上绘制水印图片
        canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
        // 保存
//        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.save();
        // 存储
        canvas.restore();
        return newBitmap;
    }

    private static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
