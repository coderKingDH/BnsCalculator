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
 * 创建时间:  2016/9/12 11:27<br>
 * 描述: 这个表为 其他表的子表   <br>
 */
@Table(name = "GoodsList")
public class GoodsList {
    @Column(name = "id", isId = true)
    private String id;
    @Column(name = "mainid")
    private String mainid;// 归属表的id       也就是主表id
    @Column(name = "goodid")
    private String goodid;//
    @Column(name = "goodnum")
    private int goodnum;//个数

    private int type=0;// 0 自己的类型   1 别人的类型

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getGoodid() {
        return goodid;
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public int getGoodnum() {
        return goodnum;
    }

    public void setGoodnum(int goodnum) {
        this.goodnum = goodnum;
    }

    @Override
    public String toString() {
        return "GoodsList{" +
                "id='" + id + '\'' +
                ", mainid='" + mainid + '\'' +
                ", goodid='" + goodid + '\'' +
                ", goodnum=" + goodnum +
                '}';
    }

    public GoodsList() {
    }
    //这个id为主表id
    public static List<GoodsList> getAllByMainId(String mainid){
        List<GoodsList> ddd=null;
        try {
            ddd = AppContext.db().selector(GoodsList.class).where("mainid", "=", mainid).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return ddd;
    }

    public static GoodsList getAllById(String _id){
        GoodsList byId = null;
        try {
            byId = AppContext.db().findById(GoodsList.class, _id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return byId;
    }
    public static void deleteByMainId(String mainid){
        try {
            AppContext.db().delete(GoodsList.class, WhereBuilder.b().b("mainid", "=", mainid));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public static void deleteById(String id){
        try {
            AppContext.db().deleteById(GoodsList.class,id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public static void save(GoodsList gl){
        try {
            AppContext.db().saveOrUpdate(gl);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
