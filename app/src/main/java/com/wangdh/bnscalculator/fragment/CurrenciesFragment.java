package com.wangdh.bnscalculator.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.base.BaseTabFragment;
import com.wangdh.bnscalculator.utils.Arith;
import com.wangdh.bnscalculator.utils.SPTools;
import com.wangdh.bnscalculator.utils.TLog;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/8 10:09<br>
 * 描述: 汇率<br>
 */
public class CurrenciesFragment extends BaseTabFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private EditText et_11;
    private EditText et_12;
    private EditText et_21;
    private EditText et_22;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_currencies,
                container, false);
        et_11 = (EditText) view.findViewById(R.id.et_11);
        et_12 = (EditText) view.findViewById(R.id.et_12);
        et_21 = (EditText) view.findViewById(R.id.et_21);
        et_22 = (EditText) view.findViewById(R.id.et_22);
        init();
        return view;
    }

    private void init() {
        et_11.setText(getrmb() + "");
        et_12.setText(getj() + "");
        et_21.setText(getj2() + "");
        et_22.setText(getrmb2() + "");


        et_11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                TLog.l("et_11beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TLog.l("et_11onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_11.isFocused()) {
                    TLog.l("et_11afterTextChanged");
                    gx1();
                }
                TLog.l(et_11.isEnabled() + "");
                TLog.l(et_11.isFocusable() + "");
                TLog.l(et_11.isFocused() + "");
                TLog.l(et_11.isInTouchMode() + "");
//                TLog.l(et_11.isTextSelectable()+"");
                TLog.l(et_11.isFocusableInTouchMode() + "");
//                TLog.l(et_11.isInEditMode()+"");
            }
        });

        et_12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                TLog.l("et_12beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TLog.l("et_12onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_12.isFocused()) {
                    TLog.l("et_12afterTextChanged");
                    gx1();
                }
            }
        });
        et_21.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                TLog.l("et_21beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TLog.l("et_21onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_21.isFocused()) {
                    TLog.l("et_21afterTextChanged");
                    gx2();
                }
            }
        });

        et_22.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                TLog.l("et_22beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TLog.l("et_22onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_22.isFocused()) {
                    TLog.l("et_22afterTextChanged");
                    gx2();
                }
            }
        });

    }

    private void gx1() {
        String t1 = et_11.getText().toString().trim();
        t1 = TextUtils.isEmpty(t1) ? "0" : t1;
        double v = Double.parseDouble(t1);
        String t2 = et_12.getText().toString().trim();
        t2 = TextUtils.isEmpty(t2) ? "0" : t2;
        double v1 = Double.parseDouble(t2);
        if (v == 0 || v1 == 0) {
            return;
        }
        double div = Arith.div(v, v1);
        setBL(div+"");
        if (div <= 0) {
            return;
        }
        et_21.setText("1");
        et_22.setText(div + "");
        saveAll();
    }

    private void gx2() {
        String t1 = et_21.getText().toString().trim();
        t1 = TextUtils.isEmpty(t1) ? "0" : t1;
        double v = Double.parseDouble(t1);
        String t2 = et_22.getText().toString().trim();
        t2 = TextUtils.isEmpty(t2) ? "0" : t2;
        double v1 = Double.parseDouble(t2);
        if (v == 0 || v1 == 0) {
            return;
        }
        double div = Arith.div(v, v1);
        setBL(Arith.div(1, div)+"");
        if (div <= 0) {
            return;
        }
        et_11.setText("1");
        et_12.setText(div + "");
        saveAll();
    }

    /**
     * 设置比率
     *
     * @param l
     */
    public static void setBL(String l) {
        TLog.e("1金的比率",l);
        SPTools.set("bl", l);
    }

    public static Double getBL() {
        String bl = SPTools.get("bl", "0");
        return Double.valueOf(bl);
    }

    private void saveAll() {
        setrmb(et_11.getText().toString().trim());
        setj(et_12.getText().toString().trim());
        setrmb2(et_22.getText().toString().trim());
        setj2(et_21.getText().toString().trim());
    }

    public static Double getrmb() {
        String rmb1 = SPTools.get("rmb1", "0");
        return Double.valueOf(rmb1);
    }

    public static Double getj() {
        String rmb1 = SPTools.get("j1", "0");
        return Double.valueOf(rmb1);
    }

    public static Double getrmb2() {
        String rmb1 = SPTools.get("rmb2", "0");
        return Double.valueOf(rmb1);
    }

    public static Double getj2() {
        String j1 = SPTools.get("j2", "0");
        return Double.valueOf(j1);
    }

    public static void setrmb(String r) {
        SPTools.set("rmb1", r);
    }

    public static void setj(String r) {
        SPTools.set("j1", r);
    }

    public static void setrmb2(String r) {
        SPTools.set("rmb2", r);
    }

    public static void setj2(String r) {
        SPTools.set("j2", r);
    }
}
