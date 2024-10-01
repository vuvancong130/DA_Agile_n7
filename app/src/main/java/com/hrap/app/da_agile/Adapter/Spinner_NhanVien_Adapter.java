package com.hrap.app.da_agile.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hrap.app.da_agile.DTO.NhanVienDTO;
import com.hrap.app.da_agile.R;

import java.util.ArrayList;

public class Spinner_NhanVien_Adapter extends BaseAdapter {
    Context context;
    ArrayList<NhanVienDTO> list;

    public Spinner_NhanVien_Adapter(Context context, ArrayList<NhanVienDTO> list){
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
        convertView = View.inflate(parent.getContext(), R.layout.spinner_nhan_vien, null);

        TextView txt_tenNV = convertView.findViewById(R.id.txt_spn_tenNV);


        txt_tenNV.setText(list.get(position).getHo_ten());
        return convertView;
    }
}
