package com.example.mytest2023.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwx on 2018/5/16.
 * Describer:
 */
public class FileHelper {

    /** 截图 地址 */
    public static final String IMG_PATH = Environment.getExternalStorageDirectory()
            .getPath() + "/iqiyi/img/";

    public static boolean isFileExists(String dirPath, String fileName) {
        return isFileExists(dirPath + fileName);
    }

    public static boolean isFileExists(String filePath) {
        File myCaptureFile = new File(filePath);
        return myCaptureFile.exists();
    }

    /***
     * //创建文件夹
     * @param dirPath 文件夹路径
     */
    public static void createDir(String dirPath) {
        if (!dirPath.endsWith(File.separator)) {
            dirPath = dirPath + File.separator;
        }
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            if (!dirFile.mkdir()) {
                dirFile.mkdirs();
            }
        }
    }

    /***
     * 创建文件
     * @param dirPath   文件夹路径
     * @param fileName  文件名
     */
    public static void createFile(String dirPath, String fileName) {
        if (!dirPath.endsWith(File.separator)) {
            dirPath = dirPath + File.separator;
        }
        createDir(dirPath);
        File file = new File(dirPath + fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 删除 */
    public static boolean delete(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + filePath + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(filePath);
            else
                return deleteDirectory(filePath);
        }
    }

    /**
     * 删除单个文件
     *
     * @param filePath 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + filePath + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + filePath + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + filePath + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
//            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
//            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
//            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    /** 返回正规的文件名 */
    public static String toRegularFileName(String fileName) {
        return fileName.replace(" ", "_").replace(":", "");
    }

    /** 获取某个文件夹中的图片地址 */
    public static List<String> getAllImgByDirPath(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {//判断路径是否存在
            return new ArrayList<>();
        }
        if (dir.isDirectory()) {
            List<String> filePaths = new ArrayList<>();
            String path = "";
            for (File file : dir.listFiles()) {
                path = file.getAbsolutePath();
                if (path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".png")) {
                    filePaths.add(path);
                }
            }
            return filePaths;
        }
        return new ArrayList<>();

    }

    /**
     * 重命名文件
     *
     * @param oldName 原来的文件地址
     * @param newName 新的文件地址
     */
    public static boolean renameFile(String dirPath, String oldName, String newName) {

        File oleFile = new File(dirPath + oldName);
        File newFile = new File(dirPath + newName);
        //执行重命名
        return oleFile.renameTo(newFile);
    }

    /** 获取Asset里的图片 */
    public static Bitmap getAssetImg(Context context, String imgName) {
        Bitmap bitmap = null;
        try {
            InputStream inputStream = context.getAssets().open(imgName);
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /** 复制Asset图片到指定目录 */
    public static void copyAssetImgAndSave(final Context context, final String imgName, final
    String dirPath) {
        if (isFileExists(dirPath, imgName))
            return;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap = getAssetImg(context, imgName);
                BitmapHelper.createJPEGFileByBitmap(bitmap, dirPath, imgName, 100);
            }
        });


    }

    public static boolean copyFile(String dirPath, String oldName, String newName) {
        return copyFile(dirPath + oldName, dirPath + newName);
    }

    /**
     * 复制单个文件
     *
     * @param oldPath$Name String 原文件路径+文件名 如：data/user/0/com.test/files/abc.txt
     * @param newPath$Name String 复制后路径+文件名 如：data/user/0/com.test/cache/abc.txt
     * @return <code>true</code> if and only if the file was copied;
     * <code>false</code> otherwise
     */
    public static boolean copyFile(String oldPath$Name, String newPath$Name) {
        try {
            File oldFile = new File(oldPath$Name);
            if (!oldFile.exists() || !oldFile.isFile() || !oldFile.canRead()) {
                return false;
            }

            FileInputStream fileInputStream = new FileInputStream(oldPath$Name);
            FileOutputStream fileOutputStream = new FileOutputStream(newPath$Name);
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = fileInputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 复制文件夹及其中的文件
     *
     * @param oldPath String 原文件夹路径 如：data/user/0/com.test/files
     * @param newPath String 复制后的路径 如：data/user/0/com.test/cache
     * @return <code>true</code> if and only if the directory and files were copied;
     * <code>false</code> otherwise
     */
    public static boolean copyFolder(String oldPath, String newPath) {
        try {
            File newFile = new File(newPath);
            if (!newFile.exists()) {
                if (!newFile.mkdirs()) {
                    return false;
                }
            }
            File oldFile = new File(oldPath);
            String[] files = oldFile.list();
            File temp;
            for (String file : files) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file);
                } else {
                    temp = new File(oldPath + File.separator + file);
                }

                if (temp.isDirectory()) {   //如果是子文件夹
                    copyFolder(oldPath + "/" + file, newPath + "/" + file);
                } else if (temp.exists() && temp.isFile() && temp.canRead()) {
                    FileInputStream fileInputStream = new FileInputStream(temp);
                    FileOutputStream fileOutputStream = new FileOutputStream(newPath + "/" + temp
                            .getName());
                    byte[] buffer = new byte[1024];
                    int byteRead;
                    while ((byteRead = fileInputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, byteRead);
                    }
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
