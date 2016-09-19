package com.wangdh.bnscalculator.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.wangdh.bnscalculator.AppContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/6/26 17:09<br>
 * 描述:android 系统的工具类<br>
 */
public class TDevice {
    private static final String TAG = TDevice.class.getName();
    private static Integer _loadFactor = null;
    public static float displayDensity = 0.0F;

    public static Context context() {
        return AppContext.getContext().getApplicationContext();
    }

    public static String getId() {
        UUID uuid = UUID.randomUUID();
        String s = UUID.randomUUID().toString();
        s.replace("-", "");
        return s;
    }

    public static int getDefaultLoadFactor() {
        if (_loadFactor == null) {
            Integer integer = Integer.valueOf(0xf & context()
                    .getResources().getConfiguration().screenLayout);
            _loadFactor = integer;
            _loadFactor = Integer.valueOf(Math.max(integer.intValue(), 1));
        }
        return _loadFactor.intValue();
    }

    /**
     * 获取密度
     *
     * @return
     */
    public static float getDensity() {
        if (displayDensity == 0.0)
            displayDensity = getDisplayMetrics().density;
        return displayDensity;
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) context().getSystemService(
                Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
                displaymetrics);
        return displaymetrics;
    }

    public static float getScreenHeight() {
        TLog.e(TAG, "屏幕高：" + getDisplayMetrics().heightPixels);
        return getDisplayMetrics().heightPixels;
    }

    public static float getScreenWidth() {
        TLog.e(TAG, "屏幕宽：" + getDisplayMetrics().widthPixels);
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 震动 p2000没硬件
     */
    public static void zd() {
        Vibrator vibrator = (Vibrator) context().getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {100, 100};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern, -1);           //重复两次上面的pattern 如果只想震动一次，index设为-1
    }

    //
    public static boolean isZh() {
        Locale locale = context().getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }


    //网络
    public static boolean isNetworkConnected() {
        // 判断网络是否连接
        ConnectivityManager connectivityManager = (ConnectivityManager) context().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mobNetInfo.isConnected() || wifiNetInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static String getTime() {
        SimpleDateFormat time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return time.format(new Date());
    }

    //sd卡是否挂载
    public static boolean isHasSdcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取年份
     *
     * @return
     */
    public static String getYear() {
        return getNowTimeByFormat("yyyy");
    }

    /**
     * 获取日期
     *
     * @return MMDD
     */
    public static String getSysDate() {
        return getNowTimeByFormat("MMdd");
    }

    /**
     * 获取时间
     *
     * @return hhmmss
     */
    public static String getSysTime() {
        return getNowTimeByFormat("HHmmss");
    }

    /**
     * pos  显示 格式化时间
     *
     * @param MMddHHmmss
     * @return yyyy/MM/dd HH:mm:ss
     */
    public static String formatDate(String MMddHHmmss) {
        return formatDateYDT(getYear() + MMddHHmmss);
    }

    public static String formatDateYDT(String yyyyMMddHHmmss) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            return sdf2.format(sdf1.parse(yyyyMMddHHmmss));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getNowTimeByFormat(String f) {
        SimpleDateFormat sdf = new SimpleDateFormat(f);
        return sdf.format(new Date());
    }

    /**
     * 通过广播设置系统的时间,如果不设置年份，yyyy为null，其他类似
     *
     * @param mContext
     * @param yyyy
     * @param MMdd
     * @param HHmmss
     */
    public static void setSystemTime(@NonNull Context mContext, String yyyy, String MMdd, String HHmmss) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d = null;
        if (yyyy == null) {
            yyyy = getYear();
        }
        if (MMdd == null) {
            MMdd = getSysDate();
        }
        if (HHmmss == null) {
            HHmmss = getSysTime();
        }
        String dateString = yyyy + MMdd + HHmmss;
        try {
            d = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG, "时间设置失败");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.sfexpress.setSystemTime");
        long datetime = d.getTime();
        intent.putExtra("system_time", datetime);
        Log.i(TAG, "设置时间前 date=" + getNowTimeByFormat("yyyy-MM-dd HH:mm:ss SSS"));
        mContext.sendBroadcast(intent);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "设置时间完成(发送的广播,0.5s后查询) date=" + getNowTimeByFormat("yyyy-MM-dd HH:mm:ss SSS"));
            }
        }).start();
    }


}