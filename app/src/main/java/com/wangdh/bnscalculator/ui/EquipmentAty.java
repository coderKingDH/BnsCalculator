package com.wangdh.bnscalculator.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.adapter.GoodsListAdapter;
import com.wangdh.bnscalculator.dao.GoodsList;
import com.wangdh.bnscalculator.dao.Upgrade;
import com.wangdh.bnscalculator.utils.TDevice;
import com.wangdh.bnscalculator.utils.UIhelp;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/12 14:41<br>
 * 描述: 升级装备 添加界面 <br>
 */
public class EquipmentAty extends AddUIAty {
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        if (TextUtils.isEmpty(id)) {
            id = TDevice.getId();
        }
        init();
        setTitle("装备成长树");
    }

    protected Upgrade _Upgrade;

    private void init() {
        _Upgrade = Upgrade.getById(id);
        if (_Upgrade == null) {
            _Upgrade = new Upgrade();
            _Upgrade.setId(id);
        }
        edit_equipment_name.setText(_Upgrade.getName());
        edit_eq_sxf.setText(_Upgrade.getFee());

    }

    private boolean isSubmit = false;

    //提交
    @Override
    public void submit() {
        _Upgrade.setFee(getSXF());
        _Upgrade.setName(getName());
        Upgrade.save(_Upgrade);
        isSubmit = true;
        finish();
    }

    //点击添加
    @Override
    public void clickAdd() {
        UIhelp.AddZBPropAty(this, id, "");
    }

    List<GoodsList> datas = null;
    List<String> yId = new ArrayList<>();
    private int i = 0;

    private void initList() {
        datas = GoodsList.getAllByMainId(id);
//        if(i==0){
//            for(GoodsList s:datas){
//                yId.add(s.getId());
//            }
//        }
        final GoodsListAdapter goodsListAdapter = new GoodsListAdapter(this, datas, R.layout.item_text);
        lv_eq_data.setAdapter(goodsListAdapter);
        lv_eq_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GoodsList item = goodsListAdapter.getItem(position);
                UIhelp.AddZBPropAty(EquipmentAty.this, item.getMainid(), item.getId());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(!isSubmit){
//            for(String g:yId){
//                GoodsList.deleteById(g);
//            }
//        }
    }
}
