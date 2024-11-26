package com.example.giaodien.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien.Adapter.KhuyenMaiAdapter;
import com.example.giaodien.Models.KhuyenMaiModel;
import com.example.giaodien.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class List_km_admin extends AppCompatActivity {

    RecyclerView mRecyclerView;
    KhuyenMaiAdapter mainAdapter;
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_km_admin);


        mRecyclerView = findViewById(R.id.rv_km);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<KhuyenMaiModel> options =
                new FirebaseRecyclerOptions.Builder<KhuyenMaiModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("KM"),KhuyenMaiModel.class)
                        .build();

        mainAdapter = new KhuyenMaiAdapter(options);
        mRecyclerView.setAdapter(mainAdapter);

        mFloatingActionButton = findViewById(R.id.floatingActionButton);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Them_Km_Admin.class));

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

}