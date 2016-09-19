package com.wangdh.bnscalculator.dao;

import com.wangdh.bnscalculator.AppContext;
import com.wangdh.bnscalculator.utils.TDevice;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/9 15:58<br>
 * 描述: 物品 <br>
 */
@Table(name = "Goods")
public class Goods {
    @Column(name = "id", isId = true)
    private String id;
    @Column(name = "money")
    private String money;
    @Column(name = "type")
    private String type;//type id
    @Column(name = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Goods() {
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", money='" + money + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Goods(String id, String money, String type, String name) {
        this.id = id;
        this.money = money;
        this.type = type;
        this.name = name;
    }
    public static List<Goods> getAll(){
        List<Goods> all = null;
        try {
            all = AppContext.db().findAll(Goods.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return all;
    }
    public static List<Goods> getAllByType(String typeid){
        List<Goods> all = null;
        try {
            all = AppContext.db().selector(Goods.class).where("type", "=", typeid).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return all;
    }
    public static void deleteById(String id){
        try {
            AppContext.db().deleteById(Goods.class,id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void deleteByType(String _type){
        try {
            AppContext.db().delete(Goods.class, WhereBuilder.b().b("type", "=", _type));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public static void save(String name ,String m ,String typeid){
        Goods goods = new Goods();
        goods.setId(TDevice.getId());
        goods.setName(name);
        goods.setMoney(m);
        goods.setType(typeid);
        try {
            AppContext.db().saveOrUpdate(goods);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public static void save(Goods g){
        try {
            AppContext.db().saveOrUpdate(g);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static Goods getByName(String name){
        Goods name1 = null;
        try {
            name1 = AppContext.db().selector(Goods.class).where("name", "=", name).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return name1;
    }
    public static Goods getById(String _id){
        Goods byId = null;
        try {
            byId = AppContext.db().findById(Goods.class, _id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return byId;
    }
}
