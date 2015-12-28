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

    //if (sessionManager.isSessionAlive()) {
    //  newRequestbuilder.addHeader(HEADER_AUTHORIZATION,
    //      String.format("%s %s", HEADER_AUTHORIZATION_BEARER, sessionManager.getSession().getAuthToken()));
    //}

    // FIXME Remove this
    String authToken ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfX3YiOjAsIl9jcmVhdGVkQXQiOiIyMDE1LTEyLTIyVDIxOjIxOjE0LjQyNVoiLCJfdXBkYXRlZEF0IjoiMjAxNS0xMi0yMlQyMToyMToxNC40MjVaIiwiZW1haWwiOiJnb256YWxvbWVsb3ZAZ21haWwuY29tIiwiX2lkIjoiNTY3OWJlY2FlZjI0N2FiZDAzNzZjODE1IiwiZnJpZW5kcyI6W10sImZhdm91cml0ZVJlY29tbWVuZGVkIjpbXSwiZmF2b3VyaXRlRXZlbnRzIjpbXSwiaW50ZXJlc3RzIjpbXSwiY291bnRyaWVzVmlzaXRlZCI6W10sImNvdW50cmllc1RvVmlzaXQiOltdLCJjdXJyZW50UG9zaXRpb24iOltdLCJwcm9maWxlUGhvdG9zIjpbXX0.MZmG5xqXda_tgHZdHeU36GrAZbOAYlGm_HGTfbyfPe4";
    newRequestbuilder.addHeader(HEADER_AUTHORIZATION,
        String.format("%s %s", HEADER_AUTHORIZATION_BEARER, authToken));


    Request newRequest = newRequestbuilder.build();

    return chain.proceed(newRequest);
  }
}