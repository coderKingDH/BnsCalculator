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

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.adapter.OrderAdapter;
import com.wangdh.bnscalculator.adapter.UpgradeAdapter;
import com.wangdh.bnscalculator.base.BaseTabFragment;
import com.wangdh.bnscalculator.dao.Order;
import com.wangdh.bnscalculator.dao.Upgrade;
import com.wangdh.bnscalculator.ui.EquipmentAty;
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
 * 描述: 升级 <br>
 */
public class UpgradeFragment extends BaseTabFragment {
    private Button btn_upgrade_add;
    private ListView lv_upgrade;
    private TextView tv_com_jb;
    private TextView tv_com_rmb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_upgrade,
                container, false);
        btn_upgrade_add = (Button) view.findViewById(R.id.btn_upgrade_add);
        lv_upgrade = (ListView) view.findViewById(R.id.lv_upgrade);
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
        btn_upgrade_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIhelp.EquipmentAty(UpgradeFragment.this.getContext(),"");
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
        List<Upgrade> all = Upgrade.getAll();
        final UpgradeAdapter upgradeAdapter = new UpgradeAdapter(this.getContext(), all, R.layout.item_text);
        upgradeAdapter.setC(new UpgradeAdapter.Choice() {
            @Override
            public void check(List<Upgrade> order) {
                TLog.e("notifyDataSetChanged","12");
                setjb(0.00);
                ArrayList<Upgrade> upgrades = new ArrayList<>();
                for(Upgrade o:order){
                    TLog.e("notifyDataSetChanged",o.toString());
                    if(o.isCheck()){
                        upgrades.add(o);
                    }
                }
                double getmoney = J.upgrade(upgrades);
                TLog.e("notifyDataSetChanged",getmoney+"");
                setjb(getmoney);
            }
        });

        lv_upgrade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TLog.l("___________________________");
                Upgrade item = upgradeAdapter.getItem(position);
                UIhelp.EquipmentAty(UpgradeFragment.this.getContext(),item.getId());
            }
        });
        lv_upgrade.setAdapter(upgradeAdapter);
    }
}
