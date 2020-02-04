package afniramadania.tech.submisson3app;

import android.app.Application;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import okhttp3.OkHttpClient;

public class MovieMain extends Application {

    public static final String MOVIE_DB_API_KEY = "a410dfa9397c1a21f5a4fd5e284e699f";

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("API", message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);

        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
    }
}
