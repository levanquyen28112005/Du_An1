package com.example.giaodien.admin;

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

public class Them_SPSUDAI_Admin extends AppCompatActivity {
    EditText tensp,kichthuoc,giatiengoc,giatienkm,hinh1,mota,chatlieu,spkho,xuatsu;
    Button btnAdd,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_spsudai_admin);
        tensp = findViewById(R.id.txt_tensp);
        kichthuoc = findViewById(R.id.txt_kichthuoc);
        giatiengoc = findViewById(R.id.txt_giatiengoc);
        giatienkm = findViewById(R.id.txt_giatienkm);
        mota = findViewById(R.id.txt_mota);
        hinh1 = findViewById(R.id.txt_hinh1);
        chatlieu = findViewById(R.id.txt_chatlieu);
        spkho = findViewById(R.id.txt_spkho);
        xuatsu = findViewById(R.id.txt_xuatsu);



        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
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
        map.put("tensp",tensp.getText().toString());
        map.put("kichthuoc",kichthuoc.getText().toString());
        map.put("turl",hinh1.getText().toString());
        map.put("giatiengoc",giatiengoc.getText().toString());
        map.put("giatienkm",giatienkm.getText().toString());
        map.put("mota",mota.getText().toString());
        map.put("xuatsu",xuatsu.getText().toString());
        map.put("spkho",spkho.getText().toString());
        map.put("chatlieu",chatlieu.getText().toString());


        if (TextUtils.isEmpty(tensp.getText().toString())
                ||TextUtils.isEmpty(hinh1.getText().toString())
        ){
            Toast.makeText(Them_SPSUDAI_Admin.this,"Bạn phải nhập đủ dữ liệu",Toast.LENGTH_SHORT).show();
            return;
        }else {
            FirebaseDatabase.getInstance().getReference().child("SPSUDAI").push()
                    .setValue(map)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Them_SPSUDAI_Admin.this,"Data In Success",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(Them_SPSUDAI_Admin.this,"Data In Faild",Toast.LENGTH_SHORT).show();

                        }
                    });
            clearAll();
        }



    }
    private void clearAll(){
        tensp.setText("");
        kichthuoc.setText("");
        hinh1.setText("");
        mota.setText("");
        giatiengoc.setText("");
        giatienkm.setText("");
        spkho.setText("");
        xuatsu.setText("");
        chatlieu.setText("");

    }
}