package com.example.naickerbhavesh.retrofitcrud.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.naickerbhavesh.retrofitcrud.R;
import com.example.naickerbhavesh.retrofitcrud.models.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    Context context;
    List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycycler_view, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        User user = userList.get(i);

        userViewHolder.txtVwName.setText(user.getName());
        userViewHolder.txtVwEmail.setText(user.getEmail());
        userViewHolder.txtVwSchool.setText(user.getSchool());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtVwName, txtVwEmail, txtVwSchool;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVwName = itemView.findViewById(R.id.txtViewName);
            txtVwEmail = itemView.findViewById(R.id.txtViewEmail);
            txtVwSchool = itemView.findViewById(R.id.txtViewSchool);

        }
    }
}
