package com.example.giaodien.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giaodien.Models.SanPhamModel;
import com.example.giaodien.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

;

public class ViewSP_Main_Adapter extends FirebaseRecyclerAdapter<SanPhamModel, ViewSP_Main_Adapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ViewSP_Main_Adapter(@NonNull FirebaseRecyclerOptions<SanPhamModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull SanPhamModel model) {

        holder.tensp.setText(model.getTensp());
        holder.giatiengoc.setText(model.getGiatiengoc());
        holder.giatienkm.setText(model.getGiatienkm());

        Glide.with(holder.img.getContext())
                .load(model.getTurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dssp_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView tensp,giatiengoc,giatienkm;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            img = itemView.findViewById(R.id.img_grid);
            tensp = itemView.findViewById(R.id.title_grid);
            giatiengoc = itemView.findViewById(R.id.gia_goc);
            giatienkm = itemView.findViewById(R.id.gia_km);





        }
    }

}