package com.wangdh.bnscalculator.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.common.CommonAdapter;
import com.wangdh.bnscalculator.common.ViewHolder;
import com.wangdh.bnscalculator.dao.Goods;
import com.wangdh.bnscalculator.dao.GoodsList;
import com.wangdh.bnscalculator.dao.Upgrade;
import com.wangdh.bnscalculator.dao.UpgradeList;
import com.wangdh.bnscalculator.utils.TLog;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/9 14:11<br>
 * 描述:  <br>
 */
public class GoodsListAdapter extends CommonAdapter<GoodsList> {

    public GoodsListAdapter(Context context, List<GoodsList> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    private TextView view;
    private TextView tv_item_text_money;
    private Button btn_item_delete;

    @Override
    public void convert(ViewHolder holder, final GoodsList goods) {
        view = holder.getView(R.id.tv_item_text);
        tv_item_text_money = holder.getView(R.id.tv_item_text_money);
        tv_item_text_money.setText(goods.getGoodnum() + " 个 ");
        btn_item_delete = holder.getView(R.id.btn_item_delete);
        TLog.e("byId1",goods.getType()+"");
        if (goods.getType() == 1) {
            Upgrade byId1 = Upgrade.getById(goods.getGoodid());
            TLog.e("byId1",goods.getGoodid());
            TLog.e("byId1",byId1==null?".":byId1.toString());
            if (byId1 == null) {
                view.setText("该物品已被删除！");
            } else {
                view.setText(byId1.getName());
            }

            btn_item_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDatas().remove(goods);
                    UpgradeList.deleteById(goods.getId());
                    notifyDataSetChanged();
                }
            });
        } else {
            Goods byId = Goods.getById(goods.getGoodid());
            if (byId != null) {
                view.setText(byId.getName());
            } else {
                view.setText("该物品已被删除！");
            }


            btn_item_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDatas().remove(goods);
                    GoodsList.deleteById(goods.getId());
                    notifyDataSetChanged();
                }
            });
        }



    }
}
