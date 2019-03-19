package com.example.naickerbhavesh.retrofitcrud.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //Base Url is the path before the name of api
    public static final String BaseUrl = "http://172.16.21.100/MyApi/public/";
    //instance of the singleton class i.e. client class
    private static RetrofitClient mInstance;
    //Object of Retrofit
    private Retrofit retrofit;

    //A private constructor of the Client class i.e. Singleton Class
    //Steps IBAB(Initialize Retrofit object, Base URl, Add GSON Convertor Factory, Build
    private RetrofitClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …
// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    //A synchronised class for creating getting the api call
    public static synchronized RetrofitClient getmInstance() {
        /*Two condition if it is null
        and another to get the api call

        null will return the instance of the Retrofit Client
        and the second one is the name of interface in this
        case it is Api it will create the call*/
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
