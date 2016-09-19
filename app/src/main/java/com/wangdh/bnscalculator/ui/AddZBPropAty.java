package com.wangdh.bnscalculator.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.wangdh.bnscalculator.adapter.PropAdapter;
import com.wangdh.bnscalculator.dao.Goods;
import com.wangdh.bnscalculator.dao.GoodsList;
import com.wangdh.bnscalculator.dao.ZBType;
import com.wangdh.bnscalculator.utils.TDevice;
import com.wangdh.bnscalculator.utils.TLog;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/12 15:18<br>
 * 描述:  <br>
 */
public class AddZBPropAty extends AddPropAty {
    private String mainId; //主表id
    private String id;  //goodsList 自己的id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getIntent().getStringExtra("id");
        if (TextUtils.isEmpty(id)) {
            id = TDevice.getId();
        }
        //主表 id 必须要有
        mainId = getIntent().getStringExtra("mainId");
        if (TextUtils.isEmpty(mainId)) {
            mainId = TDevice.getId();
            finish();
            return;
        }
        setTitle("选择材料吧");
        init();
    }

    private void init() {
        initSP();
    }
    Goods byId;
    @Override
    protected void initSP() {
        List<ZBType> all = ZBType.getAll();
        if (all == null || all.size() <= 0) {
            return;
        }
        GoodsList allById = GoodsList.getAllById(id);
        String type = "";
        if (allById != null) {
             byId = Goods.getById(allById.getGoodid());
            if (byId != null) {
                type = byId.getType();
            }
        }
        final PropAdapter propAdapter = new PropAdapter(this, all, android.R.layout.simple_list_item_1);
        sp_type.setAdapter(propAdapter);
        int j = 0;
        if (!TextUtils.isEmpty(type)) {
            for (int i = 0; i < all.size(); i++) {
                if (type.equals(all.get(i).getId())) {
                    j = 0;
                }
            }
        }
        sp_type.setSelection(j);
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = propAdapter.getItem(position);
                TLog.l(item.getName());
                initGood(item.getId(),byId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void submit() {
        super.submit();
        if (goods == null) {
            TLog.showToast("您没有选择道具！");
            return;
        }
        GoodsList goodsList = new GoodsList();
        goodsList.setId(id);
        goodsList.setGoodid(goods.getId());
        goodsList.setGoodnum(getNumber());
        goodsList.setMainid(mainId);
        GoodsList.save(goodsList);
        finish();
    }
}
