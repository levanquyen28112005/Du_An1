package com.example.giaodien.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giaodien.Models.SanPhamModel;
import com.example.giaodien.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

;

public class SPSieuUdaiAdapter extends FirebaseRecyclerAdapter<SanPhamModel, SPSieuUdaiAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SPSieuUdaiAdapter(@NonNull FirebaseRecyclerOptions<SanPhamModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull SanPhamModel model) {

        holder.tensp.setText(model.getTensp());
       // holder.mota.setText(model.getMota());
        holder.soluongSP.setText(model.getSpkho());


        Glide.with(holder.img.getContext())
                .load(model.getTurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_spsudai_admin))
                        .setExpanded(true,2200)
                        .create();
                //
                View view = dialogPlus.getHolderView();
                EditText tensp = view.findViewById(R.id.txt_tensp_up);
                EditText hinh1 = view.findViewById(R.id.txt_img_up);
//                EditText email = view.findViewById(R.id.txtEmail);
//                EditText turl = view.findViewById(R.id.txtImageUrl);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);


                tensp.setText(model.getTensp());
               hinh1.setText(model.getTurl());
//                email.setText(model.getEmail());
//                turl.setText(model.getTurl());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String ,Object> map = new HashMap<>();

                        map.put("tensp",tensp.getText().toString());
                        map.put("turl",hinh1.getText().toString());
//                        map.put("email",email.getText().toString());
//                        map.put("turl",turl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("SPSUDAI")

                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.tensp.getContext(),"Data update success",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.tensp.getContext(),"Data update Faild (Error while Update)",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });
                    }
                });
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.tensp.getContext());
                builder.setTitle("Are you sure");
                builder.setMessage("Delete data can't Undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("SPSUDAI")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.tensp.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view_spsudai, parent, false);


        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView tensp,mota,soluongSP;
        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            soluongSP = itemView.findViewById(R.id.txt_soluongSP);
            img = itemView.findViewById(R.id.img_admin_hinh);
            tensp = itemView.findViewById(R.id.txt_admin_tensp);
            mota = itemView.findViewById(R.id.txt_admin_mota);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);



        }
    }

}