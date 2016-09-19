package com.wangdh.bnscalculator.dao;

import com.wangdh.bnscalculator.AppContext;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/13 11:10<br>
 * 描述: Order 的从表 <br>
 */
@Table(name = "UpgradeList")
public class UpgradeList {
    @Column(name = "id", isId = true)
    private String id;
    @Column(name = "mainid")
    private String mainid = "";//主表id
    @Column(name = "upgradeid")
    private String upgradeid;//
    @Column(name = "upgradenum")
    private int upgradenum;//个数

    public UpgradeList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainid() {
        return mainid;
    }

    public void setMainid(String mainid) {
        this.mainid = mainid;
    }

    public String getUpgradeid() {
        return upgradeid;
    }

    public void setUpgradeid(String upgradeid) {
        this.upgradeid = upgradeid;
    }

    public int getUpgradenum() {
        return upgradenum;
    }

    public void setUpgradenum(int upgradenum) {
        this.upgradenum = upgradenum;
    }

    @Override
    public String toString() {
        return "UpgradeList{" +
                "id='" + id + '\'' +
                ", mainid='" + mainid + '\'' +
                ", upgradeid='" + upgradeid + '\'' +
                ", upgradenum=" + upgradenum +
                '}';
    }
    public static void deleteById(String _id){
        try {
            AppContext.db().deleteById(UpgradeList.class,_id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public static void deleteByMainId(String _id){
        try {
            AppContext.db().delete(UpgradeList.class, WhereBuilder.b().b("mainid", "=", _id));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public static UpgradeList getById(String _id){
        UpgradeList byId = null;
        try {
            byId = AppContext.db().findById(UpgradeList.class, _id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return byId;
    }
    public static List<UpgradeList> getAllByMainId(String _id){
        List<UpgradeList> mainid = null;
        try {
            mainid = AppContext.db().selector(UpgradeList.class).where("mainid", "=", _id).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return mainid;
    }
    public static void save(UpgradeList _UpgradeList){
        try {
            AppContext.db().saveOrUpdate(_UpgradeList);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
