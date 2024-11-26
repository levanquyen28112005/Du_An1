package com.example.giaodien.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giaodien.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Them_Km_Admin extends AppCompatActivity {
    EditText tenkm,hsd,loaikh,sotiengiam;
    Button btnAdd,btnBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_km_admin);

        tenkm = findViewById(R.id.txt_tenkm_add);
        hsd = findViewById(R.id.txt_hsd_add);
        loaikh = findViewById(R.id.txt_loaikh_add);
        sotiengiam = findViewById(R.id.txt_sotiengiam_add);




        btnAdd = findViewById(R.id.btnAdd_add);
        btnBack = findViewById(R.id.btnBack_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void insertData(){

        Map<String, Object> map = new HashMap<>();
        map.put("tenkm",tenkm.getText().toString());
        map.put("hansudung",hsd.getText().toString());
        map.put("loaikhachhang",loaikh.getText().toString());
        map.put("sotiengiam",sotiengiam.getText().toString());



        if (TextUtils.isEmpty(tenkm.getText().toString())
                ||TextUtils.isEmpty(hsd.getText().toString())
        ){
            Toast.makeText(Them_Km_Admin.this,"Bạn phải nhập đủ dữ liệu",Toast.LENGTH_SHORT).show();
            return;
        }else {
            FirebaseDatabase.getInstance().getReference().child("KM").push()
                    .setValue(map)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Them_Km_Admin.this,"Data In Success",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(Them_Km_Admin.this,"Data In Faild",Toast.LENGTH_SHORT).show();

                        }
                    });
            clearAll();
        }



    }
    private void clearAll(){
        tenkm.setText("");
        sotiengiam.setText("");
        loaikh.setText("");
        hsd.setText("");


    }
}