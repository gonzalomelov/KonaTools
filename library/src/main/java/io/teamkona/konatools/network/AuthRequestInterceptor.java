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
    String authToken ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NjY3YjliMGJjNGI1ZDQ3MDI2MTcyYWIiLCJfY3JlYXRlZEF0IjoiMjAxNS0xMi0wOVQwNToxODo0MC41MTlaIiwiX3VwZGF0ZWRBdCI6IjIwMTUtMTItMDlUMDU6MTg6NDAuNTE5WiIsImVtYWlsIjoiaGVybmFuQHRlYW1rb25hLmlvIiwiX192IjowLCJmcmllbmRzIjpbXSwiZmF2b3VyaXRlUmVjb21tZW5kZWQiOltdLCJmYXZvdXJpdGVFdmVudHMiOltdLCJpbnRlcmVzdHMiOltdLCJjb3VudHJpZXNWaXNpdGVkIjpbXSwiY291bnRyaWVzVG9WaXNpdCI6W10sImRlc2NyaXB0aW9uIjpbXSwiY3VycmVudFBvc2l0aW9uIjpbXSwicHJvZmlsZVBob3RvcyI6W119.PNlxXUXcH09Jzhox-tG73xVDIExOZeHV2zvU0oyYKvA";
    newRequestbuilder.addHeader(HEADER_AUTHORIZATION,
        String.format("%s %s", HEADER_AUTHORIZATION_BEARER, authToken));


    Request newRequest = newRequestbuilder.build();

    return chain.proceed(newRequest);
  }
}