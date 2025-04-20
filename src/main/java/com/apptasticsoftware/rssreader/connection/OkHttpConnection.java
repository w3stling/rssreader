package com.apptasticsoftware.rssreader.connection;


import com.apptasticsoftware.rssreader.Connection;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

// Separate project and dependency???
public class OkHttpConnection implements Connection {
    private final OkHttpClient client;

    public OkHttpConnection() {
        client = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(1))
                .followRedirects(true)
                .followSslRedirects(true)
                .build();
    }

    public OkHttpConnection(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public CompletableFuture<Response> sendAsync(Request request) {
        okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder()
                .url(request.url());

        if (request.userAgent() != null && !request.userAgent().isEmpty()) {
            requestBuilder.addHeader("User-Agent", request.userAgent());
        }
        request.headers().forEach(requestBuilder::addHeader);

        OkHttpResponseFuture callback = new OkHttpResponseFuture();
        client.newCall(requestBuilder.build()).enqueue(callback);
        return callback.future.thenApply(response -> {

            if (!response.isSuccessful()) {
                throw new CompletionException(new IOException("HTTP error code: " + response.code()));
            }

            return new Response() {
                @Override
                public String url() {
                    if (response.networkResponse() != null) {
                        return response.networkResponse().request().url().uri().toString();
                    } else {
                        return request.url();
                    }
                }

                @Override
                public InputStream inputStream() {
                    if (response.body() != null) {
                        return new ResponseInputStream(response, response.body().byteStream());
                    } else {
                        return InputStream.nullInputStream();
                    }
                }
            };
        });
    }

    static class ResponseInputStream extends InputStream {
        private final okhttp3.Response response;
        private final InputStream inputStream;

        public ResponseInputStream(okhttp3.Response response, InputStream inputStream) {
            this.response = response;
            this.inputStream = inputStream;
        }

        @Override
        public int read() throws IOException {
            return response.body().byteStream().read();
        }

        @Override
        public void close() throws IOException {
            super.close();
            inputStream.close();
            response.close();
        }
    }

    static class OkHttpResponseFuture implements Callback {
        public final CompletableFuture<okhttp3.Response> future = new CompletableFuture<>();

        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            future.completeExceptionally(e);
        }


        @Override
        public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
            future.complete(response);
        }
    }
}
