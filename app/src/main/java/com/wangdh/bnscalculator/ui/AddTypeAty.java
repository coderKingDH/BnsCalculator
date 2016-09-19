package com.wangdh.bnscalculator.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.base.BaseActivity;
import com.wangdh.bnscalculator.dao.ZBType;
import com.wangdh.bnscalculator.utils.TLog;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/8 18:32<br>
 * 描述:  <br>
 */
public class AddTypeAty extends BaseActivity {
    private EditText edit_add_name;
    private Button btn_add_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("添加类型");
        setContentView(R.layout.aty_add_type);
        edit_add_name= (EditText)findViewById(R.id.edit_add_name);
        btn_add_submit= (Button)findViewById(R.id.btn_add_submit);
        btn_add_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = edit_add_name.getText().toString().trim();
                if(TextUtils.isEmpty(trim)){
                    return;
                }
                if(ZBType.getByName(trim)==null){
                    ZBType.save(trim);
                    finish();
                }else{
                    TLog.showToast("类型相同！");
                }
            }
        });
    }
}
