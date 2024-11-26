package com.example.giaodien.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.giaodien.Fragment.GioHangFragment;
import com.example.giaodien.Fragment.HomeFragment;
import com.example.giaodien.Fragment.MenuFragment;
import com.example.giaodien.Fragment.MessFragment;
import com.example.giaodien.Fragment.TinTucFragment;
import com.example.giaodien.R;
import com.example.giaodien.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.tintuc:
                    replaceFragment(new TinTucFragment());
                    break;
                case R.id.mess:
                    replaceFragment(new MessFragment());
                    break;
                case R.id.gh:
                    replaceFragment(new GioHangFragment());
                    break;
                case R.id.account:
                    replaceFragment(new MenuFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout,fragment);
        fragmentTransaction.commit();
    }
}