package com.example.giaodien.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.giaodien.R;

public class AdminActivity extends AppCompatActivity {
    private CardView btn_sp,chatuser,km_sp,sp_sudai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        inView();//ánh xạ
        setOnClickCardView();



    }
    private void setOnClickCardView() {

        btn_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, List_sp_admin.class));
            }
        });
        chatuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, Userchat.class);
                startActivity(intent);
            }
        });
        km_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, List_km_admin.class);
                startActivity(intent);
            }
        });
        sp_sudai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, List_spsudai_admin.class);
                startActivity(intent);
            }
        });

    }

    private void inView() {
        btn_sp = findViewById(R.id.sp_admin);
        chatuser = findViewById(R.id.chatuser);
        km_sp = findViewById(R.id.km_sp);
        sp_sudai = findViewById(R.id.sp_sudai_admin);

    }
}