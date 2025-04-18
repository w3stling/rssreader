package com.apptasticsoftware.rssreader.connection;

import com.apptasticsoftware.rssreader.Connection;

import javax.net.ssl.SSLContext;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;
import java.util.zip.GZIPInputStream;

public class HttpClientConnection implements Connection {
    HttpClient httpClient;
    private String userAgent;
    private int connectTimeout;
    private int readTimeout;

    public HttpClientConnection() {
        httpClient = createHttpClient(Duration.ofMillis(300));
    }

    public HttpClientConnection(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpClientConnection(Connection.Config config) {
        httpClient = createHttpClient(config.connectionTimeout());
    }

    @Override
    public CompletableFuture<Response> sendAsync(Request request) {
            return sendAsyncRequest(request).thenApply(processResponse()).thenApply(response -> new Response() {
                @Override
                public String url() {
                    return request.url();
                }

                @Override
                public InputStream inputStream() {
                    return response.inputStream();
                }
            });
    }

    protected CompletableFuture<HttpResponse<InputStream>> sendAsyncRequest(Request request) {
        var builder = HttpRequest.newBuilder(URI.create(request.url()))
                .GET()
                .header("Accept-Encoding", "gzip");
        if (request.timeout().toMillis() > 0) {
            builder.timeout(request.timeout());
        }

        if (!request.userAgent().isBlank()) {
            builder.header("User-Agent", request.userAgent());
        }

        request.headers().forEach(builder::header);
        return httpClient.sendAsync(builder.GET().build(), HttpResponse.BodyHandlers.ofInputStream());
    }

    private Function<HttpResponse<InputStream>, Response> processResponse() {
        return response -> {
            try {
                if (response.statusCode() >= 400 && response.statusCode() < 600) {
                    throw new IOException(String.format("Response HTTP status code: %d", response.statusCode()));
                }

                InputStream inputStream = response.body();
                if ("gzip".equals(response.headers().firstValue("Content-Encoding").orElse(null))) {
                    inputStream = new GZIPInputStream(inputStream);
                }

                InputStream finalInputStream = inputStream;
                return new Response() {
                    @Override
                    public String url() {
                        return response.request().uri().toString();
                    }

                    @Override
                    public InputStream inputStream() {
                        return new BufferedInputStream(finalInputStream);
                    }
                };
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        };
    }

    private static HttpClient createHttpClient(Duration connectionTimeout) {
        HttpClient client;
        try {
            var context = SSLContext.getInstance("TLSv1.3");
            context.init(null, null, null);

            var builder = HttpClient.newBuilder()
                    .sslContext(context)
                    .followRedirects(HttpClient.Redirect.ALWAYS);
            if (connectionTimeout.toMillis() > 0) {
                builder.connectTimeout(connectionTimeout);
            }
            client = builder.build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            var builder = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS);
            if (connectionTimeout.toMillis() > 0) {
                builder.connectTimeout(connectionTimeout);
            }
            client = builder.build();
        }
        return client;
    }
}
