package com.wangdh.bnscalculator.adapter;

import android.content.Context;
import android.widget.TextView;

import com.wangdh.bnscalculator.common.CommonAdapter;
import com.wangdh.bnscalculator.common.ViewHolder;
import com.wangdh.bnscalculator.dao.Upgrade;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/13 18:22<br>
 * 描述:  <br>
 */
public class UpgradeSPAdapter extends CommonAdapter<Upgrade> {
    public UpgradeSPAdapter(Context context, List<Upgrade> datas, int layoutId) {
        super(context, datas, layoutId);
    }
    private TextView text1 ;
    @Override
    public void convert(ViewHolder holder, Upgrade upgrade) {
        text1=holder.getView(android.R.id.text1);
        text1.setText(upgrade.getName());
    }
}
