package com.example.giaodien.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.giaodien.Activity.SearchActivity;
import com.example.giaodien.R;
import com.example.giaodien.Models.SanPhamModel;
import com.example.giaodien.Adapter.ViewSP_Main_Adapter;
import com.example.giaodien.Adapter.ViewSP_SUDAI_Main_Adapter;
import com.example.giaodien.Adapter.ViewSP_UDAI_Main_Adapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import book.Book;
import category.category;

public class HomeFragment extends Fragment {

    private ViewFlipper slide;
    //RecyclerView rcvCategory;
    //CatemoryAdapter catemoryAdapter;
    LinearLayout lineSearch;
    TextView test, test1;
    RecyclerView mRecyclerView;


    ViewSP_Main_Adapter mainAdapter;
    ViewSP_UDAI_Main_Adapter main_adapter3;
    ViewSP_SUDAI_Main_Adapter main_adapter2;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = view.findViewById(R.id.idView);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        FirebaseRecyclerOptions<SanPhamModel> options =
                new FirebaseRecyclerOptions.Builder<SanPhamModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SP"),SanPhamModel.class)
                        .build();
        mainAdapter = new ViewSP_Main_Adapter(options);
        mRecyclerView.setAdapter(mainAdapter);
//

       mRecyclerView = view.findViewById(R.id.idView2);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

        FirebaseRecyclerOptions<SanPhamModel> options2 =
                new FirebaseRecyclerOptions.Builder<SanPhamModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SPSUDAI"),SanPhamModel.class)
                        .build();
        main_adapter2 = new ViewSP_SUDAI_Main_Adapter(options2);
        mRecyclerView.setAdapter(main_adapter2);
//
        mRecyclerView = view.findViewById(R.id.idView3);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

        FirebaseRecyclerOptions<SanPhamModel> options3 =
                new FirebaseRecyclerOptions.Builder<SanPhamModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SP"),SanPhamModel.class)
                        .build();
        main_adapter3 = new ViewSP_UDAI_Main_Adapter(options3);
        mRecyclerView.setAdapter(main_adapter3);
        //

       // slide = view.findViewById(R.id.slide);

        //Slide();
       // rcvCategory = view.findViewById(R.id.rev_category);
        lineSearch = view.findViewById(R.id.btnSearch);
        test = view.findViewById(R.id.test);
        test1 = view.findViewById(R.id.test1);

        lineSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        Animation animTranslate = AnimationUtils.loadAnimation(getContext(), R.anim.anim_tramslate);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.startAnimation(animTranslate);
            }
        });

        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test1.startAnimation(animTranslate);
            }
        });

      //  catemoryAdapter = new CatemoryAdapter(getActivity());

        //LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        //rcvCategory.setLayoutManager(linearLayoutManager2);

        //catemoryAdapter.setData(getListCategory());
        return view;

    }//

    @Override
    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
        main_adapter2.startListening();
        main_adapter3.startListening();
    }


    private List<category> getListCategory() {
        List<category> listCategory = new ArrayList<>();

        List<Book> listBook = new ArrayList<>();
        listBook.add(new Book(R.drawable.ic_camera, "Book 1"));
        listBook.add(new Book(R.drawable.ic_search, "Book 2"));
        listBook.add(new Book(R.drawable.ic_camera, "Book 3"));
        listBook.add(new Book(R.drawable.ic_person, "Book 4"));
        listBook.add(new Book(R.drawable.ic_home, "Book 5"));

        listCategory.add(new category("Category 1", listBook));
        return listCategory;
    }
    private void Slide() {
        ArrayList<String> qc = new ArrayList<>();
        qc.add("https://streetstyle.vn/images/news/18/1.unc.jpg");
        qc.add("https://cdn.muabannhanh.com/asset/frontend/img/gallery/2019/03/09/5c835bc1183d6_1552112577.jpg");
        qc.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq11Ep0C6s2z2_P1kZgL5rPLrjD0lCXtNzCg&usqp=CAU");
        for (int i = 0; i < qc.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            Glide.with(getActivity()).load(qc.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            slide.addView(imageView);
        }
        slide.setFlipInterval(5000);
        slide.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getActivity(), R.anim.solide_on);
        Animation slide_out = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_off);
        slide.setInAnimation(slide_in);
        slide.setInAnimation(slide_out);

    }
}