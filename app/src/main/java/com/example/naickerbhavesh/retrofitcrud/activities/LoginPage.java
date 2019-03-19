package com.example.naickerbhavesh.retrofitcrud.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.naickerbhavesh.retrofitcrud.R;
import com.example.naickerbhavesh.retrofitcrud.SharedPreferences.SharedPrefManager;
import com.example.naickerbhavesh.retrofitcrud.api.RetrofitClient;
import com.example.naickerbhavesh.retrofitcrud.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            Intent intent = new Intent(LoginPage.this, Profile.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }

    EditText txtEmail,txtPassword;
    Button btnLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        txtEmail=findViewById(R.id.txtEmail);
        txtPassword=findViewById(R.id.txtPass);

        btnLogin=findViewById(R.id.btnLogin);




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mail=txtEmail.getText().toString().trim();
                final String password=txtPassword.getText().toString().trim();

                if(mail.isEmpty()){
                    txtEmail.setError("Required");
                    txtEmail.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    txtPassword.setError("Required");
                    txtPassword.requestFocus();
                    return;
                }

                Call<LoginResponse>call=RetrofitClient
                        .getmInstance().getApi().userlogin(mail,password);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse=response.body();

                        if(!loginResponse.isError()){
                            SharedPrefManager.getInstance(LoginPage.this).saveUser(loginResponse.getUser());
                            Intent intent=new Intent(LoginPage.this,Profile.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                            Toast.makeText(LoginPage.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginPage.this,loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}
