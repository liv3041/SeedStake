package com.example.main.view;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class RetryInterceptor implements Interceptor {
    private final int maxRetries;

    public RetryInterceptor(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        int retries = 0;
        IOException lastException;

        do {
            try {
                return chain.proceed(chain.request());
            } catch (IOException e) {
                lastException = e;
            }
            retries++;
        } while (retries <= maxRetries);

        throw lastException;
    }
}
