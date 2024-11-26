package com.example.giaodien.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.giaodien.databinding.ActivityDangnhapAdminBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DangnhapAdmin extends AppCompatActivity {

    ActivityDangnhapAdminBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangnhapAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(DangnhapAdmin.this);
        progressDialog.setTitle("Đăng nhập");
        progressDialog.setMessage("Vui lòng chờ.");
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.txtEmail.getText().toString().isEmpty()
                        && !binding.txtMatKhau.getText().toString().isEmpty()){

                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(binding.txtEmail.getText().toString(),binding.txtMatKhau.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(DangnhapAdmin.this, AdminActivity.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(DangnhapAdmin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(DangnhapAdmin.this, "Nhập thông tin đăng nhập.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        if (mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(DangnhapAdmin.this,AdminActivity.class);
            startActivity(intent);
        }
    }
}