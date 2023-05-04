package com.example.chat_llamada;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.MyViewHolder> {
    private Context context;
    private List<userModel> userModelList;

    public userAdapter(Context context) {
        this.context = context;
        userModelList = new ArrayList<>();
    }

    public void add(userModel userModel){
        userModelList.add(userModel);
        notifyDataSetChanged();
    }
    public  void clear(){
        userModelList.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    userModel userModel = userModelList.get(position);
    holder.nombre.setText(userModel.getUserName());
    holder.correo.setText(userModel.getUserEmail());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context,chatActivity.class);
            intent.putExtra("id",userModel.getUserID());
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nombre,correo;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            nombre = itemView.findViewById(R.id.userNombre);
            correo = itemView.findViewById(R.id.userCorreo);
        }
    }
}
