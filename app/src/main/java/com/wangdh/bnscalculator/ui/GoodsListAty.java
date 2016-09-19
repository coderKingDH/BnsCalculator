package com.wangdh.bnscalculator.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.adapter.GoodsAdapter;
import com.wangdh.bnscalculator.base.BaseActivity;
import com.wangdh.bnscalculator.dao.Goods;
import com.wangdh.bnscalculator.dao.ZBType;
import com.wangdh.bnscalculator.utils.TLog;
import com.wangdh.bnscalculator.utils.UIhelp;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/9 17:30<br>
 * 描述: 类型 子界面 <br>
 */
public class GoodsListAty extends BaseActivity {
    private ListView lv;
    private Button btn_data_add;
    private String typeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_data);
        typeid = getIntent().getStringExtra("type");
        if (TextUtils.isEmpty(typeid)) {
            TLog.showToast("数据异常");
            finish();
            return;
        }
        ZBType byId = ZBType.getById(typeid);
        setTitle(byId.getName());

        lv = (ListView) findViewById(R.id.lv_data);
        btn_data_add = (Button) findViewById(R.id.btn_data_add);
        btn_data_add.setText("添加物品");
        init();
    }

    private void init() {
        btn_data_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIhelp.AddGoodsAty(GoodsListAty.this, typeid,"");
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        List<Goods> all = Goods.getAllByType(typeid);
        if (all != null) {
            lv.setAdapter(new GoodsAdapter(this, all, R.layout.item_text));
        }

    }
}
