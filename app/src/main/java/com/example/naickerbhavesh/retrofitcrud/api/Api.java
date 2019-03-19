package com.example.naickerbhavesh.retrofitcrud.api;

import com.example.naickerbhavesh.retrofitcrud.models.DefaultResponse;
import com.example.naickerbhavesh.retrofitcrud.models.LoginResponse;
import com.example.naickerbhavesh.retrofitcrud.models.User;
import com.example.naickerbhavesh.retrofitcrud.models.UsersResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded //Compulsory for sending an api call
    @POST("createUser")
        //createUser is the name of Api function

    /* Call method to create an api call the things written in the double quotes denote the column name of the table
    there after the datatype and variable name
    "createUser is the var name for call"
    "ResponseBody is used to bind the HTTP request/response body with a domain object in method parameter or return type. "*/

    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("school") String school
    );

    /*After creating the Api call create the singleton class i.e. a Client Class in this case it is RetrofitClient*/


    @FormUrlEncoded
    @POST("userLogin")
    Call<LoginResponse> userlogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("allusers")
    Call<UsersResponse> getUsers();

    @FormUrlEncoded
    @PUT("updateUser/{id}")
    Call<LoginResponse> updateUser(
            @Path("id") int id,
            @Field("email") String email,
            @Field("name") String name,
            @Field("school") String school

    );

    @FormUrlEncoded
    @PUT("updatePassword")
    Call<DefaultResponse> changePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
    );

    @DELETE("deleteUser/{id}")
    Call<DefaultResponse>deleteUser(
            @Path("id") int id
    );
}
