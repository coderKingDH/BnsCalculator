package com.wangdh.bnscalculator;

import android.os.Environment;

import java.io.File;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/8 18:21<br>
 * 描述:  <br>
 */
public class AppConfig {
    public static final String POS_NAME = "bnsjishuanqi"; //这个pos 的数据保存的一级文件路径 每个pos app 必须自己更换这个名字
    public static final String DB_NAME = "posDB.db";//数据库名称
    public static final int Db_Version = 1;//数据库名称
    public static final int number = 10;//db 分页
    // 默认存放数据库的路径
    public final static String DEFAULT_SAVE_DB_PATH = Environment
            .getExternalStorageDirectory()
            + File.separator
            + POS_NAME
            + File.separator + "db" + File.separator;
}
