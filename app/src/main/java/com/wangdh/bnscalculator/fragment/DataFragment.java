package com.wangdh.bnscalculator.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.wangdh.bnscalculator.R;
import com.wangdh.bnscalculator.adapter.TypeAdapter;
import com.wangdh.bnscalculator.base.BaseTabFragment;
import com.wangdh.bnscalculator.dao.ZBType;
import com.wangdh.bnscalculator.ui.AddTypeAty;

import java.util.List;

/**
 * 作者: wdh <br>
 * 内容摘要: <br>
 * 创建时间:  2016/9/8 10:09<br>
 * 描述: 数据 <br>
 */
public class DataFragment extends BaseTabFragment {
    private ListView lv;
    private Button btn_data_add;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_data,
                container, false);
        lv = (ListView) view.findViewById(R.id.lv_data);
        btn_data_add = (Button) view.findViewById(R.id.btn_data_add);
        init();
        return view;
    }

    private void init() {
        btn_data_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataFragment.this.getContext(), AddTypeAty.class);
                DataFragment.this.startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        List<ZBType> all = ZBType.getAll();
        if (all != null) {
            lv.setAdapter(new TypeAdapter(this.getActivity(),all,R.layout.item_text));
        }

    }
}
