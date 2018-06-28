package it.fabio.boilerplatejetpack.control;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {
    private String authToken;

    @Inject
    public ApiInterceptor() {}

    public void setCredentials(String username, String password){
        authToken = Credentials.basic(username, password);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if(request.header("No-Authentication") == null && authToken != null) {
            Request.Builder builder = request.newBuilder()
                    .header("Authorization", authToken);

            request = builder.build();
        }
        return chain.proceed(request);
    }
}
