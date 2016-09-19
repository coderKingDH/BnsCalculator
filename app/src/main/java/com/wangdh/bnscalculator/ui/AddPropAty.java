package com.wangdh.bnscalculator.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.adapter.PropAdapter;
import com.wangdh.bnscalculator.adapter.PropGoodsAdapter;
import com.wangdh.bnscalculator.adapter.UpgradeSPAdapter;
import com.wangdh.bnscalculator.base.BaseActivity;
import com.wangdh.bnscalculator.dao.Goods;
import com.wangdh.bnscalculator.dao.Upgrade;
import com.wangdh.bnscalculator.dao.ZBType;
import com.wangdh.bnscalculator.utils.TLog;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/12 15:03<br>
 * 描述: 添加道具 极 数量 得到一个goodsLIst 对象 主表id 需要在设置 <br>
 */
public class AddPropAty extends BaseActivity {
    public Spinner sp_type;
    public Spinner sp_good;
    public EditText edit_num;
    public Button btn_prop_submit;


    protected ZBType item;
    protected Goods goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_prop);
        sp_type = (Spinner) findViewById(R.id.sp_type);
        sp_good = (Spinner) findViewById(R.id.sp_good);
        edit_num = (EditText) findViewById(R.id.edit_num);
        edit_num.setText("1");
        btn_prop_submit = (Button) findViewById(R.id.btn_prop_submit);
        btn_prop_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }

        });

    }

    protected int getNumber() {
        String num = edit_num.getText().toString().trim();
        if (TextUtils.isEmpty(num)) {
            num = "1";
        }
        int i = Integer.parseInt(num);
        return i;
    }

    protected void submit() {
    }

    protected void initSP() {
        List<ZBType> all = ZBType.getAll();
        if (all == null || all.size() <= 0) {
            return;
        }
        final PropAdapter propAdapter = new PropAdapter(this, all, android.R.layout.simple_list_item_1);
        sp_type.setAdapter(propAdapter);
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = propAdapter.getItem(position);
                TLog.l(item.getName());
                initGood(item.getId(),null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    protected boolean isGood=true;
    protected void initGood(String typeid,Goods good) {
        isGood=true;
        List<Goods> allByType = Goods.getAllByType(typeid);
        goods = null;
        final PropGoodsAdapter propAdapter = new PropGoodsAdapter(this, allByType, android.R.layout.simple_list_item_1);
        sp_good.setAdapter(propAdapter);
        int j=0;
        if(good!=null && allByType!=null){
            for(int i=0;i<allByType.size();i++){
                if(good.getId().equals(allByType.get(i).getId())){
                    j=i;
                }
            }
        }
        sp_good.setSelection(j);

        sp_good.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                goods = propAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    protected Upgrade zbczs;

    /**
     *
     * @param _id  道具类型的id  如果是成长树 默认id为0
     * @param _Upgrade
     */
    protected void initZBCZS(String _id,Upgrade _Upgrade) {
        isGood=false;
        List<Upgrade> all = Upgrade.getAll();

        final UpgradeSPAdapter upgradeSPAdapter = new UpgradeSPAdapter(this, all, android.R.layout.simple_list_item_1);
        sp_good.setAdapter(upgradeSPAdapter);
        int j=0;
        if(_Upgrade!=null &&all !=null){
            for(int i=0;i<all.size();i++){
                Upgrade upgrade = all.get(i);
                if(upgrade.getId().equals(_Upgrade.getId())){
                    j=i;
                }
            }
        }
        sp_good.setSelection(j);
        sp_good.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 zbczs = upgradeSPAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
