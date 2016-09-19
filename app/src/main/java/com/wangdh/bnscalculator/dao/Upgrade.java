package com.wangdh.bnscalculator.dao;

import com.wangdh.bnscalculator.AppContext;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/12 10:52<br>
 * 描述: 这是装备的升级类 <br>
 */
@Table(name = "Upgrade")
public class Upgrade {
    @Column(name = "id", isId = true)
    private String id;
    @Column(name = "name")
    private String name="";//方案名
    @Column(name = "fee")
    private String fee="0";//手续费
    private boolean isCheck=false;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public Upgrade() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Upgrade{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fee='" + fee + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }

    public static Upgrade getById(String tid){
        Upgrade byId = null;
        try {
            byId = AppContext.db().findById(Upgrade.class, tid);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return byId;
    }
    public static void save(Upgrade _data){
        try {
            AppContext.db().saveOrUpdate(_data);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static List<Upgrade> getAll(){
        List<Upgrade> all = null;
        try {
            all = AppContext.db().findAll(Upgrade.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return all;
    }
    public static void deleteById(String _id){
        try {
            AppContext.db().deleteById(Upgrade.class,_id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
