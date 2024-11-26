package com.example.giaodien.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien.Adapter.SanPhamAdapter;
import com.example.giaodien.Models.SanPhamModel;
import com.example.giaodien.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class List_sp_admin extends AppCompatActivity {
    RecyclerView mRecyclerView;
    SanPhamAdapter mainAdapter;
    FloatingActionButton mFloatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sp_admin);


        mRecyclerView = findViewById(R.id.rv_sanpham);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<SanPhamModel> options =
                new FirebaseRecyclerOptions.Builder<SanPhamModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SP"),SanPhamModel.class)
                        .build();

        mainAdapter = new SanPhamAdapter(options);
        mRecyclerView.setAdapter(mainAdapter);
        mFloatingActionButton = findViewById(R.id.floatingActionButton);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Them_SP_Admin.class));

            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }



}