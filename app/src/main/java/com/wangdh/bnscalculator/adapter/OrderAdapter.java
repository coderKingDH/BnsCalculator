package com.wangdh.bnscalculator.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.common.CommonAdapter;
import com.wangdh.bnscalculator.common.ViewHolder;
import com.wangdh.bnscalculator.dao.GoodsList;
import com.wangdh.bnscalculator.dao.Order;
import com.wangdh.bnscalculator.dao.UpgradeList;
import com.wangdh.bnscalculator.utils.TLog;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/9 14:11<br>
 * 描述:  <br>
 */
public class OrderAdapter extends CommonAdapter<Order> {

    public OrderAdapter(Context context, List<Order> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    private TextView view;
    private TextView tv_item_text_money;
    private Button btn_item_delete;
    private RadioButton tbtn_item;

    @Override
    public void convert(ViewHolder holder, final Order goods) {
        view = holder.getView(R.id.tv_item_text);
        tbtn_item = holder.getView(R.id.tbtn_item);
        tv_item_text_money = holder.getView(R.id.tv_item_text_money);
        tbtn_item.setVisibility(View.VISIBLE);
        TLog.e("notifyDataSetChanged01",goods.isCheck()+"");
        tbtn_item.setChecked(goods.isCheck());
        TLog.e("notifyDataSetChanged02",goods.isCheck()+"");

        tv_item_text_money.setText("");
        btn_item_delete = holder.getView(R.id.btn_item_delete);
        view.setText(goods.getName());
        btn_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order.deleteById(goods.getId());
                getDatas().remove(goods);
                notifyDataSetChanged();
                GoodsList.deleteByMainId(goods.getId());
                UpgradeList.deleteByMainId(goods.getId());
            }
        });
        tbtn_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goods.setCheck(!goods.isCheck());

                if(c!=null){
                    TLog.e("notifyDataSetChanged","11");
                    c.check(OrderAdapter.this.getDatas());
                }
                OrderAdapter.this.notifyDataSetChanged();
            }
        });
    }
    private Choice c;

    public Choice getC() {
        return c;
    }

    public void setC(Choice c) {
        this.c = c;
    }

    public interface Choice{
        void check(List<Order> order);
    }

}
