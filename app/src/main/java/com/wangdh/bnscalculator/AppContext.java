package com.wangdh.bnscalculator;

import com.wangdh.bnscalculator.base.BaseApplication;
import com.wangdh.bnscalculator.utils.TLog;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/8/22 13:15<br>
 * 描述:  <br>
 */
public class AppContext extends BaseApplication {
    private static final String TAG = AppContext.class.getName();
    private static DbManager xdb = null;
    @Override
    public void onCreate() {
        super.onCreate();
        TLog.l("AppContext");
    }
    //初始化xutil3 数据库
    private DbManager initDB() {
        TLog.e(TAG, "initDB()");
        xdb = x.getDb(getDBConfig());
        return xdb;
    }
    public static DbManager db() {
        if (xdb == null) {
            xdb = x.getDb(getDBConfig());
        }
        return xdb;
    }

    private static DbManager.DaoConfig getDBConfig() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName(AppConfig.DB_NAME)
                // 不设置dbDir时, 默认存储在app的私有目录.
                .setDbDir(new File(AppConfig.DEFAULT_SAVE_DB_PATH)) //
                .setDbVersion(AppConfig.Db_Version)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        // 开启WAL, 对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                });
        return daoConfig;
    }
}
