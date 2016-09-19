package com.wangdh.bnscalculator.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.wangdh.bnscalculator.ui.AddGoodsAty;
import com.wangdh.bnscalculator.ui.AddOrderAty;
import com.wangdh.bnscalculator.ui.AddGoodListAndUpgradeListAty;
import com.wangdh.bnscalculator.ui.AddZBPropAty;
import com.wangdh.bnscalculator.ui.EquipmentAty;
import com.wangdh.bnscalculator.ui.GoodsListAty;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/9 17:09<br>
 * 描述:  <br>
 */
public class UIhelp {

    public static void AddGoodsAty(Context context, String typeid,String id) {
        Intent intent = new Intent(context, AddGoodsAty.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", typeid);
        bundle.putString("id", id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void GoodsListAty(Context context, String typeid) {
        Intent intent = new Intent(context, GoodsListAty.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", typeid);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param upgradeid 装备 表的id
     */
    public static void EquipmentAty(Context context, String upgradeid) {
        Intent intent = new Intent(context, EquipmentAty.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", upgradeid);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param _id     Order 表id
     */
    public static void AddOrderAty(Context context, String _id) {
        Intent intent = new Intent(context, AddOrderAty.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", _id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param _id     道具的id  goodsList 的id
     * @param _mainId goodsList 的主表  主表 id 必须要有
     */
    public static void AddZBPropAty(Context context, String _mainId, String _id) {
        Intent intent = new Intent(context, AddZBPropAty.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", _id);
        bundle.putString("mainId", _mainId);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void AddGoodListAndUpgradeListAty(Context context, String _mainId, String _id) {
        Intent intent = new Intent(context, AddGoodListAndUpgradeListAty.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", _id);
        bundle.putString("mainId", _mainId);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


}
