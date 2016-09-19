package com.wangdh.bnscalculator.utils;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.wangdh.bnscalculator.AppContext;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/8/22 13:59<br>
 * 描述:  <br>
 */
public class TLog {
    public static void l(String msg){
        d("wdh",msg);
    }
    public static void e(String TAG,String msg){
        Log.e(TAG,msg);
    }
    public static void d(String TAG,String msg){
        Log.d(TAG,msg);
    }

    public static String toString(int id){
        String string = AppContext.getContext().getString(id);
        if(TextUtils.isEmpty(string)){
            return "";
        }
        return string;
    }

    public static void showToast(String msg){
        Toast.makeText(AppContext.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
