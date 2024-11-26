package com.example.giaodien.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien.Models.KhuyenMaiModel;
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

public class KhuyenMaiAdapter extends FirebaseRecyclerAdapter<KhuyenMaiModel, KhuyenMaiAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public KhuyenMaiAdapter(@NonNull FirebaseRecyclerOptions<KhuyenMaiModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull KhuyenMaiModel model) {

        holder.tenkm.setText(model.getTenkm());
        holder.hansudung.setText(model.getHansudung());
        holder.sotiengiam.setText(model.getSotiengiam());
        holder.loaikhachhang.setText(model.getLoaikhachhang());


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.tenkm.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_km_admin))
                        .setExpanded(true,2200)
                        .create();
                //
                View view = dialogPlus.getHolderView();
                EditText tenkm = view.findViewById(R.id.txt_tenkm_up);
                EditText hsd = view.findViewById(R.id.txt_hsd_up);
                EditText sotiengiam = view.findViewById(R.id.txt_sotiengiam_up);
                EditText loaikh = view.findViewById(R.id.txt_loaikh_up);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);


                tenkm.setText(model.getTenkm());
               hsd.setText(model.getHansudung());
                sotiengiam.setText(model.getSotiengiam());
                loaikh.setText(model.getLoaikhachhang());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String ,Object> map = new HashMap<>();

                        map.put("tenkm",tenkm.getText().toString());
                        map.put("hansudung",hsd.getText().toString());
                        map.put("sotiengiam",sotiengiam.getText().toString());
                        map.put("loaikhachhang",loaikh.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("KM")

                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.tenkm.getContext(),"Data update success",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.tenkm.getContext(),"Data update Faild (Error while Update)",Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.tenkm.getContext());
                builder.setTitle("Are you sure");
                builder.setMessage("Delete data can't Undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("KM")
                                .child(getRef(position).getKey()).removeValue();




                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.tenkm.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view_km, parent, false);


        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView tenkm,hansudung,loaikhachhang,sotiengiam;
        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            tenkm =itemView.findViewById(R.id.txt_admin_tenkm);
            hansudung =itemView.findViewById(R.id.txt_hsd);
            loaikhachhang =itemView.findViewById(R.id.txt_loaikh);
            sotiengiam =itemView.findViewById(R.id.txt_sotiengiam);


            btnEdit = itemView.findViewById(R.id.btnEdit_km);
            btnDelete = itemView.findViewById(R.id.btnDelete_km);



        }
    }

}