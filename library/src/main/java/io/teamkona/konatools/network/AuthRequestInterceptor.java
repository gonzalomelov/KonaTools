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
    String authToken ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NjcyZmYwNzgxYzExYTE3NTRkZWJiMmUiLCJfY3JlYXRlZEF0IjoiMjAxNS0xMi0xN1QxODoyOToyNy43MDNaIiwiX3VwZGF0ZWRBdCI6IjIwMTUtMTItMTdUMTg6Mjk6MjcuNzAzWiIsImVtYWlsIjoiZ29uemFsb0B0ZWFta29uYS5pbyIsIl9fdiI6MCwiaG93VHJhdmVsIjoiRkFNSUxZIiwiYmlydGhQbGFjZSI6eyJjb3VudHJ5Q29kZSI6IlVZIiwibmFtZSI6Ik1lbG8sIENlcnJvIExhcmdvLCBVcnVndWF5In0sInByb2ZpbGVJbWFnZVVybCI6ImJ1Y2tldC81NjcyZmYzMTgxYzExYTE3NTRkZWJiMmYiLCJnZW5kZXIiOiJNIiwiYmlydGhkYXkiOiIxOTg5LTA4LTE3VDAwOjAwOjAwLjAwMFoiLCJuYW1lIjoiR01FTE8iLCJzdGF0dXMiOiJXT1JLX0FORF9UUkFWRUwiLCJjdXJyZW50UGxhY2UiOiJNb250ZXZpZGVvLCBNb250ZXZpZGVvIERlcGFydG1lbnQsIFVydWd1YXkiLCJkZXNjcmlwdGlvbiI6IkJlYXV0aWZ1bCIsImN1cnJlbnRIb3N0ZWwiOiI1NjU2YjU0OWJlZTg4MjE4M2MxNzUwNzYiLCJmcmllbmRzIjpbXSwiZmF2b3VyaXRlUmVjb21tZW5kZWQiOltdLCJmYXZvdXJpdGVFdmVudHMiOltdLCJpbnRlcmVzdHMiOlsiNTY1NjZlNjZiZWU4ODIxODNjMTc1MDY4IiwiNTY1NjZlYjliZWU4ODIxODNjMTc1MDcwIiwiNTY1NjZlNWJiZWU4ODIxODNjMTc1MDY2Il0sImNvdW50cmllc1Zpc2l0ZWQiOlsiNTY3MGNmZTczZWJjMGFhMDA1NzlmZWZlIl0sImNvdW50cmllc1RvVmlzaXQiOlsiNTY3MGNmZTczZWJjMGFhMDA1NzlmZWU4Il0sImN1cnJlbnRQb3NpdGlvbiI6WzM3LjMzMjMzMSwtNjAuMDMxMjE5XSwicHJvZmlsZVBob3RvcyI6W119.4xioNUj2WcARVGd3RHu---76sy6oMn_eP1YinPpT5Bk";
    newRequestbuilder.addHeader(HEADER_AUTHORIZATION,
        String.format("%s %s", HEADER_AUTHORIZATION_BEARER, authToken));


    Request newRequest = newRequestbuilder.build();

    return chain.proceed(newRequest);
  }
}