package com.example.naickerbhavesh.retrofitcrud.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.naickerbhavesh.retrofitcrud.R;
import com.example.naickerbhavesh.retrofitcrud.SharedPreferences.SharedPrefManager;

public class HomeFragment extends Fragment {
    TextView txtVwEmail,txtVwSchool,txtVwName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtVwEmail=view.findViewById(R.id.txtVwEmail);
        txtVwSchool=view.findViewById(R.id.txtVwSchool);
        txtVwName=view.findViewById(R.id.txtVwName);

        txtVwEmail.setText(SharedPrefManager.getInstance(getActivity()).getUser().getEmail());
        txtVwName.setText(SharedPrefManager.getInstance(getActivity()).getUser().getName());
        txtVwSchool.setText(SharedPrefManager.getInstance(getActivity()).getUser().getSchool());

    }
}
