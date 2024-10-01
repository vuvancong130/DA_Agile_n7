package com.hrap.app.da_agile;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hrap.app.da_agile.Adapter.NhanVienAdapter;
import com.hrap.app.da_agile.DAO.NhanVienDAO;
import com.hrap.app.da_agile.DTO.NhanVienDTO;

import java.util.ArrayList;


public class Fragment_QL_NhanVien extends Fragment {

    RecyclerView rc_nhan_vien;
    FloatingActionButton fab_nhan_vien;
    ArrayList<NhanVienDTO> list;
    Dialog dialog;
    EditText tiedt_add_maNV, tiedt_add_tenNV, tiedt_add_sodienthoai, tiedt_add_matKhau;
    Button btn_addTV, btn_huy_addTV;
    static NhanVienDAO nhanVienDAO;
    NhanVienAdapter nhanVienAdapter;
    NhanVienDTO nhanVienDTO;
    LinearLayoutManager linearLayoutManager;
    SearchView searchView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment__q_l__nhan_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rc_nhan_vien = view.findViewById(R.id.rc_nhan_vien);
        fab_nhan_vien = view.findViewById(R.id.fab_nhan_vien);
        nhanVienDAO =  new NhanVienDAO(getActivity());
        capNhatLv();
        fab_nhan_vien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
    }

    public void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_nhan_vien);
        tiedt_add_maNV = dialog.findViewById(R.id.tiedt_add_maNV);
        tiedt_add_tenNV = dialog.findViewById(R.id.tiedt_add_tenNV);
        tiedt_add_sodienthoai = dialog.findViewById(R.id.tiedt_add_sodienthoai);
        tiedt_add_matKhau = dialog.findViewById(R.id.tiedt_add_matKhau);
        btn_addTV = dialog.findViewById(R.id.btn_addTV);
        btn_huy_addTV = dialog.findViewById(R.id.btn_huy_addTV);

        if (type != 0) {
            tiedt_add_maNV.setText(String.valueOf(nhanVienDTO.getMaNV()));
            tiedt_add_tenNV.setText(nhanVienDTO.getHo_ten());
            tiedt_add_sodienthoai.setText(nhanVienDTO.getSdt());
            tiedt_add_matKhau.setText(nhanVienDTO.getMat_khau());
        }
        btn_huy_addTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btn_addTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhanVienDTO = new NhanVienDTO();
                nhanVienDTO.setMaNV(tiedt_add_maNV.getText().toString());
                nhanVienDTO.setHo_ten(tiedt_add_tenNV.getText().toString());
                nhanVienDTO.setSdt(tiedt_add_sodienthoai.getText().toString());
                nhanVienDTO.setMat_khau(tiedt_add_matKhau.getText().toString());

                if (validate() > 0){
                    if (type == 0) {
                        if (nhanVienDAO.insert(nhanVienDTO) > 0) {
                            Toast.makeText(context, "Thêm thành công.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại.", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        nhanVienDTO.setMaNV(String.valueOf(Integer.parseInt(tiedt_add_maNV.getText().toString())));
                        if (nhanVienDAO.update(nhanVienDTO) > 0) {
                            Toast.makeText(context, "Sửa thành công.", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Sửa thất bại.", Toast.LENGTH_SHORT).show();

                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private int validate() {
        int check = 1;

        String ma = tiedt_add_maNV.getText().toString();
        String ten = tiedt_add_tenNV.getText().toString();
        String matKhau = tiedt_add_matKhau.getText().toString();
        String sodienThoai = tiedt_add_sodienthoai.getText().toString();

        if (ma.isEmpty()) {
            tiedt_add_maNV.setError("Vui lòng nhập mã nhân viên!");
            check = -1;
        } else if (matKhau.isEmpty()) {
            tiedt_add_matKhau.setError("Vui lòng nhập mật khẩu!");
            check = -1;
        } else if (ten.isEmpty()) {
            tiedt_add_tenNV.setError("Vui lòng nhập tên!");
            check = -1;
        } else if (sodienThoai.isEmpty()) {
            tiedt_add_sodienthoai.setError("Vui lòng nhập số điện thoại!");
            check = -1;
        } else {
            try {
                // Kiểm tra năm sinh có đúng định dạng số không
                Integer.parseInt(sodienThoai);
            } catch (NumberFormatException e) {
                tiedt_add_sodienthoai.setError("Sai định dạng, phải nhập số!");
                check = -1;
            }
        }

        return check;


    }

    public void capNhatLv(){
        list = (ArrayList<NhanVienDTO>) nhanVienDAO.getAll();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rc_nhan_vien.setLayoutManager(linearLayoutManager);
        nhanVienAdapter = new NhanVienAdapter(getActivity(), list);
        rc_nhan_vien.setAdapter(nhanVienAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.search_action).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nhập mã hoặc tên nhân viên");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                nhanVienAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                nhanVienAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}