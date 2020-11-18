package com.example.fmovil.adaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fmovil.R;
import com.example.fmovil.models.MovilModels;

import java.util.ArrayList;

public class MovilAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MovilModels> modelsArrayList;

    public MovilAdapter(Context context, ArrayList<MovilModels> modelsArrayList) {
        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @Override
    public int getCount() {
        return modelsArrayList.size();
    }

    @Override
    public MovilModels getItem(int i) {
        return modelsArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view=layoutInflater.inflate(R.layout.movil_list_item,viewGroup,false);
        }
        TextView tv_movil_list_item_description = view.findViewById(R.id.tv_movil_list_item_description);
        TextView tv_movil_list_item_serial = view.findViewById(R.id.tv_movil_list_item_serial);

        tv_movil_list_item_description.setText(getItem(i).getDescripcion());
        tv_movil_list_item_serial.setText(getItem(i).getSerial());

        return view;
    }
}
