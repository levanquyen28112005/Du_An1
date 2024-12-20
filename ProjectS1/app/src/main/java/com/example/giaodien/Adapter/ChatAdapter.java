package com.example.giaodien.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien.Models.MessageModel;
import com.example.giaodien.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MessageModel> messageModels;
    Context context;
    String recId;

    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context, String recId) {
        this.messageModels = messageModels;
        this.context = context;
        this.recId = recId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.gui,parent,false);
            return new SenderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.nhan,parent,false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else{
            return RECEIVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModel = messageModels.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Bạn có chắc chắn muốn xóa tin  này không")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String senderRoom = FirebaseAuth.getInstance().getUid()+recId;
                                database.getReference().child("chats").child(senderRoom)
                                        .child(messageModel.getMessageId())
                                        .setValue(null);
                            }
                        }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                            }
                        }).show();
                return false;
            }
        });

        if (holder.getClass() == SenderViewHolder.class){
            ((SenderViewHolder)holder).senderMsg.setText(messageModel.getMessage());

            Date date = new Date(messageModel.getTimestamp());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm");
            String strDate = simpleDateFormat.format(date);
            ((SenderViewHolder)holder).senderTime.setText(strDate.toString());
        }
        else{
            ((RecieverViewHolder)holder).receiverMsg.setText(messageModel.getMessage());
            Date date = new Date(messageModel.getTimestamp());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm");
            String strDate = simpleDateFormat.format(date);
            ((RecieverViewHolder)holder).receiverTime.setText(strDate.toString());
        }
    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder{
        TextView receiverMsg,receiverTime;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverMsg = itemView.findViewById(R.id.receiveText);
            receiverTime = itemView.findViewById(R.id.receverTime);

        }
    }
    public class SenderViewHolder extends RecyclerView.ViewHolder{

        TextView senderMsg, senderTime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.senderText);
            senderTime = itemView.findViewById(R.id.senderTime);
        }
    }
}
