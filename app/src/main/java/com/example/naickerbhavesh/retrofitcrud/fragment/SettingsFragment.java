package com.example.naickerbhavesh.retrofitcrud.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.naickerbhavesh.retrofitcrud.R;
import com.example.naickerbhavesh.retrofitcrud.SharedPreferences.SharedPrefManager;
import com.example.naickerbhavesh.retrofitcrud.activities.LoginPage;
import com.example.naickerbhavesh.retrofitcrud.activities.MainActivity;
import com.example.naickerbhavesh.retrofitcrud.api.RetrofitClient;
import com.example.naickerbhavesh.retrofitcrud.models.DefaultResponse;
import com.example.naickerbhavesh.retrofitcrud.models.LoginResponse;
import com.example.naickerbhavesh.retrofitcrud.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment {

    EditText txtEditName, txtEditSchool, txtEditEmail, txtEditCurrPwd, txtEditNewPwd;
    Button btnSave, btnChangePwd, btnDelAccnt, btnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtEditName = view.findViewById(R.id.txtEditName);
        txtEditEmail = view.findViewById(R.id.txtEditEmail);
        txtEditSchool = view.findViewById(R.id.txtEditSchool);
        txtEditCurrPwd = view.findViewById(R.id.txtEditCurrPwd);
        txtEditNewPwd = view.findViewById(R.id.txtEditNewPwd);

        btnSave = view.findViewById(R.id.btnSave);
        btnChangePwd = view.findViewById(R.id.btnChngPwd);
        btnLogout = view.findViewById(R.id.btnLogOut);
        btnDelAccnt = view.findViewById(R.id.btnDelete);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtEditName.getText().toString();
                String email = txtEditEmail.getText().toString();
                String school = txtEditSchool.getText().toString();

                User user = SharedPrefManager.getInstance(getActivity()).getUser();

                Call<LoginResponse> call = RetrofitClient.getmInstance()
                        .getApi().updateUser(user.getId(),
                                email,
                                name, school
                        );

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                        if (!response.body().isError()) {
                            SharedPrefManager.getInstance(getActivity()).saveUser(response.body().getUser());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
        });

        btnChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currPwd=txtEditCurrPwd.getText().toString();
                String newPwd=txtEditNewPwd.getText().toString();


                User user=SharedPrefManager.getInstance(getActivity()).getUser();

                Call<DefaultResponse> call=RetrofitClient.getmInstance().getApi().changePassword(currPwd,newPwd,user.getEmail());

                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        Toast.makeText(getActivity(),response.body().getMsg(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getActivity()).clear();
                Intent intent=new Intent(getActivity(),LoginPage.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnDelAccnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Are You Sure???");
                builder.setMessage("Once you are deleted your account will be permenantly deleted!");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user=SharedPrefManager.getInstance(getActivity()).getUser();
                        Call<DefaultResponse> call=RetrofitClient.getmInstance().getApi().deleteUser(user.getId());
                        call.enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                if(!response.body().isErr()){
                                    SharedPrefManager.getInstance(getActivity()).clear();
                                    SharedPrefManager.getInstance(getActivity()).clear();

                                    Intent intent=new Intent(getActivity(),MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();

                                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                                    //Snackbar.make(view,"Deleted Successfully",Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                            }
                        });
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        });
    }
}
