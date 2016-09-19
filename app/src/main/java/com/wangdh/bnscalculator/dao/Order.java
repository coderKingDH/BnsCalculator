package com.wangdh.bnscalculator.dao;

import android.text.TextUtils;

import com.wangdh.bnscalculator.AppContext;
import com.wangdh.bnscalculator.utils.TDevice;
import com.wangdh.bnscalculator.utils.TLog;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/9 18:08<br>
 * 描述: 道具汇总 包括升级的道具和 基本道具 <br>
 */
@Table(name = "Order")
public class Order {
    @Column(name = "id", isId = true)
    private String id;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private boolean isCheck=false;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    public static void save(String name){
        save(TDevice.getId(),name);
    }
    public static void save(String id, String name){
        if(TextUtils.isEmpty(id)){
            id=TDevice.getId();
        }
        Order p = new Order();
        p.setName(name);
        p.setId(id);
        try {
            AppContext.db().saveOrUpdate(p);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public static void save(Order _order){
        if(_order==null){
            TLog.l("_order 为空");
            return;
        }
        try {
            AppContext.db().saveOrUpdate(_order);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public static List<Order> getAll(){
        List<Order> all= null;
        try {
             all = AppContext.db().findAll(Order.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return all;
    }
    public static void deleteById(String _id){
        try {
            AppContext.db().deleteById(Order.class,_id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public static Order getById(String _id){
        Order byId = null;
        try {
            byId = AppContext.db().findById(Order.class, _id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return byId;
    }
    public static Order getByName(String _name){
        Order first = null;
        try {
            first = AppContext.db().selector(Order.class).where("name", "=", _name).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return first;
    }
}
