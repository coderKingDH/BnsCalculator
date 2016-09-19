package com.wangdh.bnscalculator.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.adapter.GoodsListAdapter;
import com.wangdh.bnscalculator.dao.GoodsList;
import com.wangdh.bnscalculator.dao.Order;
import com.wangdh.bnscalculator.dao.UpgradeList;
import com.wangdh.bnscalculator.utils.TDevice;
import com.wangdh.bnscalculator.utils.TLog;
import com.wangdh.bnscalculator.utils.UIhelp;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/13 11:25<br>
 * 描述: 添加搭配装备的主表 从表为 道具list表 和 成长树list表 <br>
 */
public class AddOrderAty extends AddUIAty {
    private String id;
    private boolean hasId=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edit_eq_sxf.setVisibility(View.GONE);
        tv_eq_hint.setVisibility(View.GONE);
        init();
        setTitle("搭配装备");
    }

    private Order order;

    private void init() {
        id = getIntent().getStringExtra("id");
        if (TextUtils.isEmpty(id)) {
            hasId=false;
            id = TDevice.getId();
        }
        TLog.e(this.getName(), "id" + id);
        order = Order.getById(id);
        if (order == null) {
            order = new Order();
            order.setId(id);
            order.setName("");
        }
        edit_equipment_name.setText(order.getName());

    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();
    }

    private void initList() {
        List<UpgradeList> q = UpgradeList.getAllByMainId(id);
        List<GoodsList> w = GoodsList.getAllByMainId(id);
        List<GoodsList> e = new ArrayList<>();
        if (q != null) {
            for (UpgradeList up : q) {
                GoodsList goodsList = new GoodsList();
                goodsList.setId(up.getId());
                goodsList.setMainid(up.getMainid());
                goodsList.setGoodid(up.getUpgradeid());
                goodsList.setGoodnum(up.getUpgradenum());
                goodsList.setType(1);
                e.add(goodsList);
                TLog.e("goodsList",goodsList.getGoodid());
            }
        }
        if (w != null && w.size() > 0) {
            e.addAll(w);
        }

        final GoodsListAdapter goodsListAdapter = new GoodsListAdapter(this, e, R.layout.item_text);
        lv_eq_data.setAdapter(goodsListAdapter);
        lv_eq_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GoodsList item = goodsListAdapter.getItem(position);
                UIhelp.AddGoodListAndUpgradeListAty(AddOrderAty.this, item.getMainid(), item.getId());
            }
        });
    }

    @Override
    public void clickAdd() {
        super.clickAdd();
        UIhelp.AddGoodListAndUpgradeListAty(this, id, "");
    }

    @Override
    public void submit() {
        super.submit();
        String trim = edit_equipment_name.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            TLog.showToast("请输入方案名");
            return;
        }
        if(!hasId){
            Order byName = Order.getByName(trim);
            if(byName!=null){
                TLog.showToast("该方案以存在");
                return;
            }
        }
        order.setName(trim);
        Order.save(order);
        finish();
    }
}
