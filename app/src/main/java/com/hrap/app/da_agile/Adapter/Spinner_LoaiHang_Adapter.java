package com.hrap.app.da_agile.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hrap.app.da_agile.DTO.LoaiHangDTO;
import com.hrap.app.da_agile.R;

import java.util.ArrayList;

public class Spinner_LoaiHang_Adapter extends BaseAdapter {
    Context context;
    ArrayList<LoaiHangDTO> list;

    public Spinner_LoaiHang_Adapter(Context context, ArrayList<LoaiHangDTO> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(parent.getContext(), R.layout.spinner_loai_hang, null);
        TextView txt_maLS = convertView.findViewById(R.id.txt_maLH);
        TextView txt_tenLS = convertView.findViewById(R.id.txt_tenLH);


        txt_maLS.setText(list.get(position).getMa_loai_hang() + " ");
        txt_tenLS.setText(list.get(position).getTen_loai_hang());
        return convertView;
    }
}
