package com.example.giaodien.avtivities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.giaodien.Models.Users;
import com.example.giaodien.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ActivitySignUpBinding binding;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Tạo tài khoản");
        progressDialog.setMessage("Đang tạo tài khoản.");

        binding.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.txtTen.getText().toString().isEmpty()
                && !binding.txtEmail.getText().toString().isEmpty()
                        && !binding.txtDiachi.getText().toString().isEmpty()
                        && !binding.txtSDT.getText().toString().isEmpty()
                && !binding.txtMatKhau.getText().toString().isEmpty()){

                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(binding.txtEmail.getText().toString(),binding.txtMatKhau.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()){
                                        Users user = new Users(binding.txtTen.getText().toString()
                                                ,binding.txtEmail.getText().toString()
                                                ,binding.txtMatKhau.getText().toString()
                                                ,binding.txtDiachi.getText().toString()
                                                ,binding.txtSDT.getText().toString());
                                        String id = task.getResult().getUser().getUid();
                                        database.getReference().child("Users").child(id).setValue(user);
                                        Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(SignUpActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
                else{
                    Toast.makeText(SignUpActivity.this, "Nhập thông tin đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });
        setListeners();
    }
    private void setListeners(){
        binding.tvDangNhap.setOnClickListener(view -> onBackPressed());
    }
}