package com.baorant.layoutdemo.Util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AssetsUtil {
    private static final String TAG = "AssetsUtil";
    /**
     * 将asset里面的文件复制到
     * /data/user/0/报名/files 中
     * @param context
     * @param assertName
     */
    public static void loadAssetsToCache(Context context, String assertName){
        Log.d(TAG, "loadAssetsToCache");
        String filePath = context.getFilesDir().getAbsolutePath();
        Log.d(TAG, "copy到的文件路径filePath： " + filePath);
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(assertName);
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //保存到本地的文件夹下的文件
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + "/" + assertName);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, count);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将asset里面的文件夹复制到 /data/user/0/包名/files 中
     * @param context context
     * @param assetDir assetDir
     * @param dir dir
     */
    public static void copyAssertDirToData(Context context, String assetDir, String dir) {
        String[] files;
        try {
            files = context.getResources().getAssets().list(assetDir);
        } catch (IOException e1) {
            return;
        }
        File mWorkingPath = new File(dir);
        if (!mWorkingPath.exists()) {
            mWorkingPath.mkdirs();
        }
        for (int i = 0; i < files.length; i++) {
            try {
                String fileName = files[i];
                if (!fileName.contains(".")) {
                    if (0 == assetDir.length()) {
                        copyAssertDirToData(context, fileName, dir + fileName + "/");
                    } else {
                        copyAssertDirToData(context, assetDir + "/" + fileName, dir +"/"+fileName);
                    }
                    continue;
                }
                File outFile = new File(mWorkingPath, fileName);
                if (outFile.exists()) {
                    outFile.delete();
                }
                InputStream in = null;
                if (0 != assetDir.length()){
                    in = context.getAssets().open(assetDir + "/" + fileName);
                }else{
                    in = context.getAssets().open(fileName);
                }
                OutputStream out = new FileOutputStream(outFile);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
