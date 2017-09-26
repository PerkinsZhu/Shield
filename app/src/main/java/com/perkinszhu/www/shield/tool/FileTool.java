package com.perkinszhu.www.shield.tool;

import android.os.Environment;

import org.apache.http.util.EncodingUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-25
 * Time: 17:27
 */
public class FileTool {
    //写数据到SD中的文件
    public static void writeFileSdcardFile(String fileName, String write_str,boolean isAppend) throws IOException {
        try {
            if (isBlank(fileName)) {
                throw new RuntimeException("fileName 为空！");
            }
            File file = new File(fileName);
            if (!file.exists()) {
                File parentFile =file.getParentFile();
                if(!parentFile.exists()){
                    parentFile.mkdirs();
                }
                file.createNewFile();
            }

            FileOutputStream fout = new FileOutputStream(file,isAppend);
            byte[] bytes = write_str.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //读SD中的文件
    public static String readFileSdcardFile(String fileName) throws IOException {
        String res = "";
        try {
            if (isBlank(fileName)) return res;
            File file = new File(fileName);
            if (!file.exists()) {
                return res;
            }

            FileInputStream fin = new FileInputStream(file);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            res = EncodingUtils.getString(buffer, "UTF-8");
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private static boolean isBlank(String fileName) {
        if (fileName == null || fileName.length() < 1) {
            return true;
        }
        return false;
    }

    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static String getSdCardPath() {
        boolean exist = isSdCardExist();
        String sdpath = "";
        if (exist) {
            sdpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            sdpath = "不适用";
        }
        return sdpath;

    }

}
