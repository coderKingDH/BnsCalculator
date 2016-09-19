package com.wangdh.bnscalculator.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.base.BaseActivity;
import com.wangdh.bnscalculator.dao.Goods;
import com.wangdh.bnscalculator.dao.ZBType;
import com.wangdh.bnscalculator.utils.TDevice;
import com.wangdh.bnscalculator.utils.TLog;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/9 16:07<br>
 * 描述: 添加  道具 到数据库 <br>
 */
public class AddGoodsAty extends BaseActivity {
    private String typeid;
    private EditText edit_addgood_name;
    private EditText edit_addgood_money;
    private Button btn_add_submit;
    private String id;
    private Goods good;
    private boolean hasId=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeid = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        if (TextUtils.isEmpty(typeid)) {
            TLog.showToast("数据异常");
            finish();
            return;
        }
        setContentView(R.layout.aty_add_goods);

        edit_addgood_name = (EditText) findViewById(R.id.edit_addgood_name);
        edit_addgood_money = (EditText) findViewById(R.id.edit_addgood_money);
        btn_add_submit = (Button) findViewById(R.id.btn_add_submit);

        ZBType byId = ZBType.getById(typeid);
        setTitle("添加"+byId.getName());
        if(TextUtils.isEmpty(id)){
            hasId=false;
            id= TDevice.getId();
        }
        good= Goods.getById(id);
        if(good!=null){
            edit_addgood_name.setText(good.getName());
            edit_addgood_money.setText(good.getMoney());
        }else{
            good=new Goods();
            good.setId(id);
            good.setType(typeid);
        }

        btn_add_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_addgood_name.getText().toString().trim();
                String money = edit_addgood_money.getText().toString().trim();
                if(!hasId){
                    Goods byName = Goods.getByName(name);
                    if (byName != null) {
                        TLog.l("物品以存在！");
                        return;
                    }
                }
                good.setMoney(money);
                good.setName(name);
                Goods.save(good);
                AddGoodsAty.this.finish();
            }
        });




    }
}
