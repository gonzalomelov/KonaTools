package io.teamkona.konatools.network;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import io.teamkona.konatools.BuildConfig;
import io.teamkona.konatools.session.SessionManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import timber.log.Timber;

/**
 * Created by gonzalomelov on 8/19/15.
 **/
public class MyOkHttpClient {
  private static final String TAG = MyOkHttpClient.class.getSimpleName();

  private static final int CONNECT_TIMEOUT_MILLIS = 15 * 1000;
  private static final int READ_TIMEOUT_MILLIS = 180 * 1000;

  public OkHttpClient getOkHttpClient(Interceptor interceptor) {
    OkHttpClient httpClient = new OkHttpClient();

    httpClient.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
    httpClient.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);

    //if (BuildConfig.DEBUG) {
    //  httpClient.setProxy(new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("192.168.56.1", 8888)););
    //}

    httpClient.interceptors().add(interceptor);

    return httpClient;
  }
}
