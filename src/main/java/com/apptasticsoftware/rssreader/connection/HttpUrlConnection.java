package com.apptasticsoftware.rssreader.connection;

import com.apptasticsoftware.rssreader.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.zip.GZIPInputStream;

public class HttpUrlConnection implements Connection {

    @Override
    public CompletableFuture<Response> sendAsync(Request request) {
        return CompletableFuture.supplyAsync(() -> {
            HttpURLConnection connection;
            try {
                URL url = new URL(request.url());
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                throw new CompletionException(e);
            }
            if (request.userAgent() != null && !request.userAgent().isEmpty()) {
                connection.setRequestProperty("User-Agent", request.userAgent());
            }
            connection.setRequestProperty("Accept-Encoding", "gzip");
            connection.setConnectTimeout((int) request.timeout().toMillis());
            connection.setReadTimeout((int) request.timeout().toMillis());
            connection.setInstanceFollowRedirects(true);
            request.headers().forEach(connection::addRequestProperty);

            return new Response() {
                @Override
                public String url() {
                    return request.url();
                }

                @Override
                public InputStream inputStream() {
                        try {
                            InputStream inputStream = connection.getInputStream();
                            if ("gzip".equalsIgnoreCase(connection.getContentEncoding())) {
                                inputStream = new GZIPInputStream(connection.getInputStream());
                            }
                            return new HttpClientInputStream(connection, inputStream);
                        } catch (IOException e) {
                            throw new CompletionException(e);
                        }
                }
            };
        });
    }


    class HttpClientInputStream extends InputStream {
        private final HttpURLConnection httpConnection;
        private final InputStream inputStream;

        public HttpClientInputStream(HttpURLConnection httpConnection, InputStream inputStream) {
            this.httpConnection = httpConnection;
            this.inputStream = inputStream;
        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        @Override
        public void close() throws IOException {
            super.close();
            inputStream.close();
            httpConnection.disconnect();
        }
    }
}
