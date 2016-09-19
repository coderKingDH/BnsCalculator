package com.wangdh.bnscalculator.base;

import android.app.Application;
import android.content.Context;

import com.wangdh.bnscalculator.utils.TLog;


/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/8/22 13:15<br>
 * 描述:  <br>
 */
public class BaseApplication extends Application{
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        TLog.l("BaseApplication");
        context=this.getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
