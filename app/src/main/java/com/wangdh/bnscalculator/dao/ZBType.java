package com.wangdh.bnscalculator.dao;

import com.wangdh.bnscalculator.AppContext;
import com.wangdh.bnscalculator.utils.TDevice;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/8 18:22<br>
 * 描述:  <br>
 */
@Table(name = "ZBType")
public class ZBType {
    @Column(name = "id", isId = true)
    private String id;

    @Column(name = "name")
    private String name;

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

    @Override
    public String toString() {
        return "ZBType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    public static ZBType getById(String id){
        ZBType byId=null;
        try {
            byId = AppContext.db().findById(ZBType.class, id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return byId;
    }
    public static ZBType getByName(String name) {
        ZBType name1 = null;
        try {
            name1 = AppContext.db().selector(ZBType.class).where("name", "=", name).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return name1;
    }
    public static List<ZBType> getAll(){
        List<ZBType> all = null;
        try {
            all = AppContext.db().findAll(ZBType.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return all;
    }
    public static void deleteById(String id){
        try {
            AppContext.db().deleteById(ZBType.class,id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        Goods.deleteByType(id);
    }
    public static void save(String name) {
        ZBType type = new ZBType();
        type.setId(TDevice.getId());
        type.setName(name);
        try {
            AppContext.db().save(type);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
