package io.teamkona.konatools.network;

import io.teamkona.konatools.gson.MyGson;
import io.teamkona.konatools.log.LogHelper;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by gmelo on 12/06/14.
 */
public class RetrofitHelper {

  private static final String TAG = LogHelper.makeLogTag(RetrofitHelper.class);

  private MyOkHttpClient myOkHttpClient;
  private MyGson myGson;
  private String baseUrl;

  public RetrofitHelper(MyOkHttpClient myOkHttpClient, MyGson myGson, String baseUrl) {
    this.myOkHttpClient = myOkHttpClient;
    this.myGson = myGson;
    this.baseUrl = baseUrl;
  }

  private Retrofit getRetrofit() {
    Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl);

    retrofitBuilder.client(myOkHttpClient.getOkHttpClient());

    retrofitBuilder.addConverterFactory(GsonConverterFactory.create(myGson.getGson()));
    retrofitBuilder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    return retrofitBuilder.build();
  }

  // Encapsulate the RestAdapter
  public <T> T createService(Class<T> service) {
    return getRetrofit().create(service);
  }
}
