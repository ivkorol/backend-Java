package retrofit.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import utils.PropertyScanner;

import java.io.IOException;

public class RetrofitGetter<PropertyScanner> {

    private HttpLoggingInterceptor logger;
    private OkHttpClient client;
    private PropertyScanner ps;
    private String baseUrl;

    public RetrofitGetter() throws IOException {
        ps = new PropertyScanner();
        baseUrl = ps.getProperty("mini.market.url");
        logger = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(logger)
                .build();
    }


    public Retrofit getInstance() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();

    }


}
