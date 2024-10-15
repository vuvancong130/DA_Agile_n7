package com.hrap.app.da_agile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.hrap.app.da_agile.Adapter.Top10AdapterNhap;
import com.hrap.app.da_agile.DAO.ThongKeDAO;
import com.hrap.app.da_agile.DTO.Top10;

import java.util.ArrayList;


public class FragmentTop10Nhap extends Fragment {


   ListView lv_top10_nhap;
    ArrayList<Top10> lisst_top;
    Top10AdapterNhap adt;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_top10_nhap,container,false);
        lv_top10_nhap=v.findViewById(R.id.lv_top_nhap);
        ThongKeDAO tkdao=new ThongKeDAO(getActivity());
        lisst_top=(ArrayList<Top10>) tkdao.getTopNhap();
        adt=new Top10AdapterNhap(getActivity(),this,lisst_top);
        lv_top10_nhap.setAdapter(adt);
        return v;
    }
}