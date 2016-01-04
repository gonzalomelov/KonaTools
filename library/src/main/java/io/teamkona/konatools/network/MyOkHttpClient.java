package io.teamkona.konatools.network;

import com.squareup.okhttp.OkHttpClient;
import io.teamkona.konatools.session.SessionManager;
import java.util.concurrent.TimeUnit;

/**
 * Created by gonzalomelov on 8/19/15.
 **/
public class MyOkHttpClient {
  private static final String TAG = MyOkHttpClient.class.getSimpleName();

  private static final int CONNECT_TIMEOUT_MILLIS = 15 * 1000;
  private static final int READ_TIMEOUT_MILLIS = 180 * 1000;

  private SessionManager sessionManager;

  public MyOkHttpClient(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  public OkHttpClient getOkHttpClient() {
    OkHttpClient httpClient = new OkHttpClient();

    httpClient.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
    httpClient.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);

    httpClient.interceptors().add(new AuthRequestInterceptor(sessionManager));

    return httpClient;
  }
}
