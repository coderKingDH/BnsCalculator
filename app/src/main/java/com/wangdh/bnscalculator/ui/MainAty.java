package com.wangdh.bnscalculator.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.RadioGroup;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.common.FragmentTabAdapter;
import com.wangdh.bnscalculator.fragment.CombinationFragment;
import com.wangdh.bnscalculator.fragment.CurrenciesFragment;
import com.wangdh.bnscalculator.fragment.DataFragment;
import com.wangdh.bnscalculator.fragment.UpgradeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/8 09:46<br>
 * 描述:  <br>
 */
public class MainAty extends FragmentActivity {
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);
        rg = (RadioGroup) findViewById(R.id.radiogroup);
        rg.check(R.id.t1);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CombinationFragment());
        fragments.add(new UpgradeFragment());
        fragments.add(new DataFragment());
        fragments.add(new CurrenciesFragment());
        new FragmentTabAdapter(this, fragments, R.id.realtabcontent, rg);
    }


}
