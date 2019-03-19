package com.example.naickerbhavesh.retrofitcrud.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naickerbhavesh.retrofitcrud.R;
import com.example.naickerbhavesh.retrofitcrud.SharedPreferences.SharedPrefManager;
import com.example.naickerbhavesh.retrofitcrud.api.RetrofitClient;
import com.example.naickerbhavesh.retrofitcrud.models.DefaultResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            Intent intent = new Intent(MainActivity.this, Profile.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }

    EditText txtEmail, txtPassword, txtName, txtSchool;
    TextView txtLogin;
    Button btnSign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = findViewById(R.id.txtEmail);
        txtName = findViewById(R.id.txtname);
        txtPassword = findViewById(R.id.txtPass);
        txtSchool = findViewById(R.id.txtSchool);

        btnSign = findViewById(R.id.btnSign);

        txtLogin = findViewById(R.id.txtLogin);


        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mail = txtEmail.getText().toString().trim();
                final String name = txtName.getText().toString().trim();
                final String password = txtPassword.getText().toString().trim();
                final String school = txtSchool.getText().toString().trim();
                if (mail.isEmpty()) {
                    txtEmail.setError("Required");
                    txtEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    txtPassword.setError("Required");
                    txtPassword.requestFocus();
                    return;
                }

                if (name.isEmpty()) {
                    txtName.setError("Required");
                    txtName.requestFocus();
                    return;
                }

                if (school.isEmpty()) {
                    txtSchool.setError("Required");
                    txtSchool.requestFocus();
                    return;
                }

                // creating an api call
                Call<DefaultResponse> call = RetrofitClient
                        .getmInstance()
                        .getApi()
                        .createUser(mail, password, name, school);

//               call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        String str=null;
//                        try {
//                            if(response.code() == 201) {
//                                str = response.body().string();
//                            }else {
//                                str = response.errorBody().string();
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        if(str!=null){
//                            try {
//                                JSONObject jsonObject=new JSONObject(str);
//                                Toast.makeText(MainActivity.this,jsonObject.getString("message"), Toast.LENGTH_LONG).show();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if (response.code() == 201) {
                            DefaultResponse defaultResponse = response.body();
                            Toast.makeText(MainActivity.this, defaultResponse.getMsg(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Email Already Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Some Error Occoured", Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
