package com.wangdh.bnscalculator.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.base.BaseActivity;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/13 12:16<br>
 * 描述:  <br>
 */
public class AddUIAty extends BaseActivity {
    protected EditText edit_equipment_name;
    protected EditText edit_eq_sxf;
    protected Button btn_eq_add;
    protected Button btn_eq_submit;
    protected ListView lv_eq_data;
    protected TextView tv_eq_hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_equipment);
        edit_equipment_name = (EditText) findViewById(R.id.edit_equipment_name);
        btn_eq_add = (Button) findViewById(R.id.btn_eq_add);
        lv_eq_data = (ListView) findViewById(R.id.lv_eq_data);
        edit_eq_sxf = (EditText) findViewById(R.id.edit_eq_sxf);
        btn_eq_submit = (Button) findViewById(R.id.btn_eq_submit);
        tv_eq_hint = (TextView) findViewById(R.id.tv_eq_hint);
        edit_eq_sxf.setText("0");
        btn_eq_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        btn_eq_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAdd();
            }
        });
    }

    public void submit() {
    }

    public void clickAdd() {
    }

    //获取名字
    public String getName() {
        return edit_equipment_name.getText().toString().trim();
    }

    //获手续费
    public String getSXF() {
        String trim = edit_eq_sxf.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            trim = "0";
        }
        return trim;
    }
}
