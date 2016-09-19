package com.wangdh.bnscalculator.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.wangdh.bnscalculator.adapter.PropAdapter;
import com.wangdh.bnscalculator.dao.Goods;
import com.wangdh.bnscalculator.dao.GoodsList;
import com.wangdh.bnscalculator.dao.Upgrade;
import com.wangdh.bnscalculator.dao.UpgradeList;
import com.wangdh.bnscalculator.dao.ZBType;
import com.wangdh.bnscalculator.utils.TDevice;
import com.wangdh.bnscalculator.utils.TLog;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/13 14:28<br>
 * 描述: 这是道具list和成长树list <br>
 */
public class AddGoodListAndUpgradeListAty extends AddPropAty {
    private String mainid;
    private String id;
    private int ddd=0; // 0: 增加数据 1：修改 goodlist 数据  2修改upgradelist 数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainid = getIntent().getStringExtra("mainId");
        id = getIntent().getStringExtra("id");
        if (TextUtils.isEmpty(mainid)) {
            TLog.l("主表id为null");
            finish();
            return;
        }
        if (TextUtils.isEmpty(id)) {
            id = TDevice.getId();
        }
        initSP();
        setTitle("选择道具");
    }

    Goods _good = null;
    Upgrade _Upgrade = null;

    @Override
    protected void initSP() {
        List<ZBType> all = ZBType.getAll();
        if (all == null || all.size() <= 0) {
            return;
        }
        ZBType zbType = new ZBType();
        zbType.setId("0");
        zbType.setName("装备成长树");
        all.add(zbType);
        final PropAdapter propAdapter = new PropAdapter(this, all, android.R.layout.simple_list_item_1);
        sp_type.setAdapter(propAdapter);
        //goodslist 表中看又没有
        GoodsList allById = GoodsList.getAllById(id);
        String type = "";

        if (allById != null) {
            ddd=1;
            _good = Goods.getById(allById.getGoodid());
            if (_good != null) {
                type = _good.getType();
            }
        } else {
            //goodslist 没有 就查Upgradelist
            UpgradeList byId = UpgradeList.getById(id);
            if (byId != null) {
                type = "装备成长树";
                _Upgrade = Upgrade.getById(byId.getUpgradeid());
                ddd=2;
            }
        }
        int j = 0;
        if (!TextUtils.isEmpty(type)) {
            for (int i = 0; i < all.size(); i++) {
                if (type.equals(all.get(i).getId())) {
                    j = i;
                }
            }
        }
        sp_type.setSelection(j);
        if(all==null || all.size()<=0){return;}
        if (j < all.size()-1) {
            sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    item = propAdapter.getItem(position);
                    TLog.l(item.getName());
                    String id1 = item.getId();
                    if (id1.equals("0")) {
                        initZBCZS(item.getId(), _Upgrade);
                    } else {
                        initGood(id1, _good);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    item = propAdapter.getItem(position);
                    TLog.l(item.getName());
                    initZBCZS(item.getId(), _Upgrade);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }

    @Override
    protected void submit() {
        super.submit();
        if (isGood) {
            if (goods == null) {
                TLog.showToast("请选择道具！");
                return;
            }
            if(ddd==2){
                UpgradeList.deleteById(id);
            }
            GoodsList goodsList = new GoodsList();
            goodsList.setId(id);
            goodsList.setGoodid(goods.getId());
            goodsList.setGoodnum(getNumber());
            goodsList.setMainid(mainid);
            GoodsList.save(goodsList);
            TLog.e(this.getClass().getName(),goodsList.toString());
            finish();
        } else {
            if (zbczs == null) {
                TLog.showToast("请选择道具！");
                return;
            }
            if(ddd==1){
                GoodsList.deleteById(id);
            }
            UpgradeList u = new UpgradeList();
            u.setId(id);
            u.setMainid(mainid);
            u.setUpgradeid(zbczs.getId());
            u.setUpgradenum(getNumber());
            UpgradeList.save(u);
            TLog.e(this.getClass().getName(),u.toString());
            finish();
        }
    }
}

