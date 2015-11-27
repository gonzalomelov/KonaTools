package io.teamkona.konatools.network;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import io.teamkona.konatools.session.session.SessionManager;
import java.io.IOException;

/**
 * Created by gonzalomelov on 8/19/15.
 **/
public class AuthRequestInterceptor implements Interceptor {

  public static final String HEADER_AUTHORIZATION = "Authorization";
  public static final String HEADER_AUTHORIZATION_BEARER = "Bearer";

  private SessionManager sessionManager;

  public AuthRequestInterceptor(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    Request.Builder newRequestbuilder = chain.request().newBuilder();

    if (sessionManager.isSessionAlive()) {
      newRequestbuilder.addHeader(HEADER_AUTHORIZATION,
          String.format("%s %s", HEADER_AUTHORIZATION_BEARER, sessionManager.getSession().getAuthToken()));
    }

    Request newRequest = newRequestbuilder.build();

    return chain.proceed(newRequest);
  }
}