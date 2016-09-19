package com.wangdh.bnscalculator.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.adapter.OrderAdapter;
import com.wangdh.bnscalculator.base.BaseTabFragment;
import com.wangdh.bnscalculator.dao.Order;
import com.wangdh.bnscalculator.ui.AddPropAty;
import com.wangdh.bnscalculator.ui.AddZBPropAty;
import com.wangdh.bnscalculator.utils.Arith;
import com.wangdh.bnscalculator.utils.J;
import com.wangdh.bnscalculator.utils.TLog;
import com.wangdh.bnscalculator.utils.UIhelp;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/8 10:09<br>
 * 描述: 组合方案 <br>
 */
public class CombinationFragment extends BaseTabFragment{
    private Button btn_main;
    private ListView lv_main;
    private TextView tv_com_jb;
    private TextView tv_com_rmb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_combination,
                container, false);
        btn_main=(Button)view.findViewById(R.id.btn_main);
        lv_main=(ListView)view.findViewById(R.id.lv_main);
        tv_com_jb=(TextView)view.findViewById(R.id.tv_com_jb);
        tv_com_rmb=(TextView)view.findViewById(R.id.tv_com_rmb);
        init();
        return view;
    }
    private void setjb(Double s){
        tv_com_jb.setText("金币(单位/金)："+s);
        Double bl = CurrenciesFragment.getBL();
        double mul = Arith.mul(bl, s);
        setrmb(mul);
    }
    private void setrmb(Double s){
        tv_com_rmb.setText("软妹币(单位/元)："+s);
    }

    private void init() {
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIhelp.AddOrderAty(CombinationFragment.this.getContext(),"");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tv_com_jb.setText("金币(单位/金)："+"0.00");
        tv_com_rmb.setText("软妹币(单位/元)："+"0.00");
        initList();
    }

    private void initList() {
        List<Order> all = Order.getAll();
        final OrderAdapter orderAdapter = new OrderAdapter(this.getContext(), all, R.layout.item_text);
        orderAdapter.setC(new OrderAdapter.Choice() {
            @Override
            public void check(List<Order> order) {
                TLog.e("notifyDataSetChanged","12");
                ArrayList<Order> upgrades = new ArrayList<>();
                for(Order o:order){
                    TLog.e("notifyDataSetChanged",o.toString());
                    if(o.isCheck()){
                        upgrades.add(o);
                    }
                }
                setjb(0.00);
                double getmoney = J.getmoney(upgrades);
                setjb(getmoney);
            }
        });
        lv_main.setAdapter(orderAdapter);
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UIhelp.AddOrderAty(CombinationFragment.this.getContext(),orderAdapter.getItem(position).getId());
            }
        });
    }
}
