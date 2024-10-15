package com.hrap.app.da_agile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hrap.app.da_agile.DTO.Top10;
import com.hrap.app.da_agile.FragmentTop10Nhap;
import com.hrap.app.da_agile.R;

import java.util.ArrayList;

public class Top10AdapterNhap extends ArrayAdapter<Top10> {
    private Context context;
    private ArrayList<Top10> list_top;
    TextView txt_tenSP,txt_soluong;
    FragmentTop10Nhap frtop10;
    public Top10AdapterNhap(@NonNull Context context, FragmentTop10Nhap frtop10nhap, ArrayList<Top10> list_top) {
        super(context, 0, list_top);

        this.context=context;
        this.frtop10=frtop10nhap;
        this.list_top=list_top;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_top10nhap,null);
        }
        final Top10 top=list_top.get(position);
        if(top!=null){
            txt_tenSP=v.findViewById(R.id.txt_top10_tenSP);
            txt_soluong=v.findViewById(R.id.txt_top10_soluong);
            txt_tenSP.setText("Tên sản phẩm: "+top.getTenSP());
            txt_soluong.setText("Số lượng: "+top.getSoLuongTOP());
        }
        return v;
    }
}
