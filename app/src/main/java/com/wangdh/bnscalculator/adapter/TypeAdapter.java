package com.wangdh.bnscalculator.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.common.CommonAdapter;
import com.wangdh.bnscalculator.common.ViewHolder;
import com.wangdh.bnscalculator.dao.ZBType;
import com.wangdh.bnscalculator.utils.TLog;
import com.wangdh.bnscalculator.utils.UIhelp;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/9 14:11<br>
 * 描述:  <br>
 */
public class TypeAdapter extends CommonAdapter<ZBType> {

    public TypeAdapter(Context context, List<ZBType> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    private TextView view;
    private TextView tv_item_text_money;
    private Button btn_item_delete;
    @Override
    public void convert(ViewHolder holder, final ZBType zbType) {
        view = holder.getView(R.id.tv_item_text);
        tv_item_text_money = holder.getView(R.id.tv_item_text_money);
        tv_item_text_money.setText("");
        btn_item_delete = holder.getView(R.id.btn_item_delete);
        view.setText(zbType.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIhelp.GoodsListAty(mContext,zbType.getId());
            }
        });
        btn_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZBType.deleteById(zbType.getId());
                getDatas().remove(zbType);
                notifyDataSetChanged();
            }
        });
    }
}
