package com.wangdh.bnscalculator.utils;

import com.wangdh.bnscalculator.dao.Goods;
import com.wangdh.bnscalculator.dao.GoodsList;
import com.wangdh.bnscalculator.dao.Order;
import com.wangdh.bnscalculator.dao.Upgrade;
import com.wangdh.bnscalculator.dao.UpgradeList;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/14 15:27<br>
 * 描述: 计算 <br>
 */
public class J {
    public static double getmoney(List<Order> order) {
        double v = 0;
        if (order == null || order.size() <= 0) {
            return v;
        }
        for (int i = 0; i < order.size(); i++) {
            Order o = order.get(i);
            List<GoodsList> allByMainId = GoodsList.getAllByMainId(o.getId());
            if (allByMainId != null && allByMainId.size() > 0) {
                double v1 = goodsList(allByMainId);
                TLog.l("基本材料的价格" + v1);
                v = Arith.add(v1, v);
            }
            List<UpgradeList> allByMainId1 = UpgradeList.getAllByMainId(o.getId());
            if (allByMainId1 != null && allByMainId1.size() > 0) {
                double v2 = UpgradeList(allByMainId1);
                TLog.l("装备树的价格" + v2);
                v = Arith.add(v2, v);
            }
        }

        return v;
    }

    /**
     * @param data
     * @return
     */
    public static double goodsList(List<GoodsList> data) {
        double mul = 0.00;
        if (data == null || data.size() <= 0) {
            return mul;
        }
        ArrayList<Goods> goodses = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            GoodsList goodsList = data.get(i);
            String goodid = goodsList.getGoodid();
            Goods byId = Goods.getById(goodid);
            if (byId != null) {
                double a = Arith.mul(byId.getMoney(), goodsList.getGoodnum() + "");
                mul = Arith.add(mul, a);
            }
        }
        return mul;
    }

    /**
     * @param data
     */
    public static double UpgradeList(List<UpgradeList> data) {
        double mul = 0.00;
        if (data == null || data.size() <= 0) {
            return mul;
        }
        for (int i = 0; i < data.size(); i++) {
            UpgradeList upgradeList = data.get(i);
            String upgradeid = upgradeList.getUpgradeid();
            Upgrade byId = Upgrade.getById(upgradeid);
            if (byId != null) {
                ArrayList<Upgrade> upgrades = new ArrayList<>();
                upgrades.add(byId);
                double upgrade = upgrade(upgrades);
                int upgradenum = upgradeList.getUpgradenum();
                Double aDouble = 0.0;
                try {
                    aDouble = Double.valueOf(upgradenum);
                } catch (Exception e) {
                }

                double mul1 = Arith.mul(upgrade, aDouble);
                mul = Arith.add(mul1, mul);
            }
        }
        return mul;
    }

    public static double upgrade(List<Upgrade> data) {
        double mul = 0.00;
        if (data == null || data.size() <= 0) {
            return mul;
        }
        for (int i = 0; i < data.size(); i++) {
            Upgrade upgrade = data.get(i);
            List<GoodsList> allByMainId = GoodsList.getAllByMainId(upgrade.getId());
            double v = goodsList(allByMainId);
            String fee = upgrade.getFee();
            Double fe = 0.00;
            try {
                fe = Double.valueOf(fee);
            } catch (Exception e) {
            }
            double add = Arith.add(v, fe);
            mul = Arith.add(add, mul);
        }
        return mul;
    }
}
