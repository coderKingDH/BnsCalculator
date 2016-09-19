package com.wangdh.bnscalculator.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.common.CommonAdapter;
import com.wangdh.bnscalculator.common.ViewHolder;
import com.wangdh.bnscalculator.dao.Goods;
import com.wangdh.bnscalculator.dao.ZBType;
import com.wangdh.bnscalculator.utils.UIhelp;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/9 14:11<br>
 * 描述:  <br>
 */
public class GoodsAdapter extends CommonAdapter<Goods> {

    public GoodsAdapter(Context context, List<Goods> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    private TextView view;
    private TextView tv_item_text_money;
    private Button btn_item_delete;
    @Override
    public void convert(ViewHolder holder, final Goods goods) {
        view = holder.getView(R.id.tv_item_text);
        tv_item_text_money = holder.getView(R.id.tv_item_text_money);
        tv_item_text_money.setText(goods.getMoney()+" 金 ");
        btn_item_delete = holder.getView(R.id.btn_item_delete);
        view.setText(goods.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIhelp.AddGoodsAty(mContext,goods.getType(),goods.getId());
            }
        });
        btn_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Goods.deleteById(goods.getId());
                getDatas().remove(goods);
                notifyDataSetChanged();
            }
        });
    }
}
