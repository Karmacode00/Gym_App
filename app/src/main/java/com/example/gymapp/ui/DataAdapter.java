package com.example.gymapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gymapp.R;
import com.example.gymapp.model.Data;

import java.util.List;

public class DataAdapter extends BaseAdapter {
    private Context ctx;
    private List<Data> dataList;

    public DataAdapter(Context ctx, List<Data> dataList) {
        this.ctx = ctx;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return dataList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);

        view = inflater.inflate(R.layout.item_data, null);

        Data data = dataList.get(i);

        TextView tvDate = view.findViewById(R.id.item_data_tv_date);
        TextView tvId = view.findViewById(R.id.item_data_tv_id);
        TextView tvWeight = view.findViewById(R.id.item_data_tv_weight);
        TextView tvImc = view.findViewById(R.id.item_data_tv_imc);

        tvId.setText(Long.toString(data.getId()));
        tvDate.setText(data.getDate());
        tvWeight.setText(data.getWeight());
        tvImc.setText(data.getImc());

        return view;
    }

}
