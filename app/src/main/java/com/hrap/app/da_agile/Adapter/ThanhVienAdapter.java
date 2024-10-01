package com.hrap.app.da_agile.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hrap.app.da_agile.DAO.ThanhVienDAO;
import com.hrap.app.da_agile.DTO.ThanhVienDTO;
import com.hrap.app.da_agile.Fragment_QL_ThanhVien;
import com.hrap.app.da_agile.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.TViewHolder> implements Filterable {

    private Context context;
    ArrayList<ThanhVienDTO> list;
    ArrayList<ThanhVienDTO> list_search;
    TextInputEditText tiedt_add_maTV,tiedt_add_tenTV,tiedt_add_namSinh,tiedt_add_gioiTinh,tiedt_add_sodienthoai;
    Button btn_addTV,btn_huy_addTV;
    RadioGroup rdo_gr;
    RadioButton rdo_nam,rdo_nu;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVienDTO> list){
        this.context = context;
        this.list_search=list;
        this.list = list;
    }
    @NonNull
    @Override
    public TViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_thanh_vien, parent, false);
        return new TViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ThanhVienDTO thanhVienDTO = list.get(position);
        holder.txt_maTV.setText("Mã thành viên: " + thanhVienDTO.getMaTV());
        holder.txt_tenTV.setText("Tên thành viên: " + thanhVienDTO.getHo_ten());
        holder.txt_namSinh.setText("Năm sinh: " + thanhVienDTO.getNam_sinh());

        if(thanhVienDTO.getGioi_tinh()==0){
            holder.txt_gioiTinh.setText("Giới tính: Nữ");
        }else{
            holder.txt_gioiTinh.setText("Giới tính: Nam");
        }
        holder.txt_sodienThoai.setText("Số điện thoại: " + thanhVienDTO.getSo_dien_thoai());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_thanh_vien, null);
                builder.setView(view);
                Dialog dialog = builder.create();


                tiedt_add_maTV = view.findViewById(R.id.tiedt_add_maTV);
                tiedt_add_tenTV = view.findViewById(R.id.tiedt_add_tenTV);
                tiedt_add_namSinh = view.findViewById(R.id.tiedt_add_namSinh);
                rdo_gr=view.findViewById(R.id.rdo_gr_tv);
                rdo_nam=view.findViewById(R.id.rdo_nam);
                rdo_nu=view.findViewById(R.id.rdo_nu);
                tiedt_add_sodienthoai = view.findViewById(R.id.tiedt_add_sodienthoai);
                btn_addTV = view.findViewById(R.id.btn_addTV);
                btn_huy_addTV = view.findViewById(R.id.btn_huy_addTV);

                tiedt_add_maTV.setText(String.valueOf(list.get(position).getMaTV()));
                tiedt_add_tenTV.setText(list.get(position).getHo_ten());
                tiedt_add_namSinh.setText(list.get(position).getNam_sinh());


                if(list.get(position).getGioi_tinh()==0){
                    rdo_nu.setChecked(true);
                }else if(list.get(position).getGioi_tinh()==1){
                    rdo_nam.setChecked(true);

                }

                tiedt_add_sodienthoai.setText(list.get(position).getSo_dien_thoai());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                btn_huy_addTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btn_addTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenTV = tiedt_add_tenTV.getText().toString();
                        String NS = tiedt_add_namSinh.getText().toString();
                        String SDT = tiedt_add_sodienthoai.getText().toString();
                        thanhVienDTO.setHo_ten(tenTV);
                        thanhVienDTO.setNam_sinh(NS);

                        if(rdo_nam.isChecked()){
                            thanhVienDTO.setGioi_tinh(1);
                        }else if(rdo_nu.isChecked()){
                            thanhVienDTO.setGioi_tinh(0);
                        }


                        thanhVienDTO.setSo_dien_thoai(SDT);
                        boolean err = false;
                        if (tenTV.isEmpty()) {
                            tiedt_add_tenTV.setError("Vui lòng nhập tên thành viên!");
                            err = true;
                        }
                        if (NS.isEmpty()) {
                            tiedt_add_namSinh.setError("Vui lòng nhập năm sinh thành viên!");
                            err = true;
                        }

                        if (SDT.isEmpty()) {
                            tiedt_add_sodienthoai.setError("Vui lòng nhập số điện thoại thành viên!");
                            err = true;
                        }

                        if(!err){
                            int kq = holder.thanhVienDAO.update(thanhVienDTO);
                            if (kq > 0) {
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }
                    }
                });
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty()) {
                    list = list_search;
                } else {
                    List<ThanhVienDTO> list_tv = new ArrayList<ThanhVienDTO>() {
                    };
                    for (ThanhVienDTO thanhVienDTO : list) {
                        if (thanhVienDTO.getHo_ten().toLowerCase().contains(strSearch.toLowerCase())) {
                            list_tv.add(thanhVienDTO);
                        }
                        try {
                            int maSPSearch = Integer.parseInt(strSearch);
                            if (thanhVienDTO.getMaTV() == maSPSearch) {
                                list_tv.add(thanhVienDTO);
                            }
                        } catch (NumberFormatException e) {

                        }
                    }
                    list = (ArrayList<ThanhVienDTO>) list_tv;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (ArrayList<ThanhVienDTO>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class TViewHolder extends RecyclerView.ViewHolder {
        TextView txt_maTV, txt_tenTV, txt_namSinh, txt_gioiTinh, txt_sodienThoai;

        ThanhVienDTO thanhVienDTO;
        ThanhVienDAO thanhVienDAO;
        ThanhVienAdapter thanhVienAdapter;
        Fragment_QL_ThanhVien FragmentQLThanhVien;

        public TViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_maTV = itemView.findViewById(R.id.txt_maTV);
            txt_tenTV = itemView.findViewById(R.id.txt_tenTV);
            txt_namSinh = itemView.findViewById(R.id.txt_namSinh);
            txt_gioiTinh = itemView.findViewById(R.id.txt_gioiTinh);
            txt_sodienThoai = itemView.findViewById(R.id.txt_sodienthoai);


            thanhVienDTO = new ThanhVienDTO();
            thanhVienDAO = new ThanhVienDAO(context);
            thanhVienAdapter = new ThanhVienAdapter(context, list);
            FragmentQLThanhVien = new Fragment_QL_ThanhVien();
        }
    }
}
