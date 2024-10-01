package com.hrap.app.da_agile.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hrap.app.da_agile.DTO.SanPhamDTO;
import com.hrap.app.da_agile.R;

import java.util.ArrayList;

public class Spinner_SanPham_Adapter extends BaseAdapter {
    Context context;
    ArrayList<SanPhamDTO> listSP;

    public Spinner_SanPham_Adapter(Context context, ArrayList<SanPhamDTO> listSP){
        this.context = context;
        this.listSP = listSP;
    }
    @Override
    public int getCount() {
        return listSP.size();
    }

    @Override
    public Object getItem(int position) {
        return listSP.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(parent.getContext(), R.layout.spinner_san_pham, null);
        TextView txt_maSP = convertView.findViewById(R.id.txt_spn_maSP);
        TextView txt_tenSP = convertView.findViewById(R.id.txt_spn_tenSP);

        txt_maSP.setText(listSP.get(position).getMa_SP() + " ");
        txt_tenSP.setText(listSP.get(position).getTen_SP());
        return convertView;
    }
}
