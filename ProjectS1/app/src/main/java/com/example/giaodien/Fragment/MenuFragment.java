package com.example.giaodien.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.giaodien.avtivities.SignInActivity;
import com.example.giaodien.databinding.FragmentMenuBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class MenuFragment extends Fragment {
    FragmentMenuBinding binding;
    FirebaseDatabase database;
    FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater,container,false);
        mAuth = FirebaseAuth.getInstance();

        //1

//        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Users users = snapshot.getValue(Users.class);
//                        Picasso.get()
//                                .load(users.getProfilePic())
//                                .placeholder(R.drawable.avatar)
//                                .into(binding.imgUser);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });


        binding.dangxuat.setText("Đăng xuất");

        binding.dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }
}