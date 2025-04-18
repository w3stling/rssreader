package com.apptasticsoftware.rssreader;

import java.io.InputStream;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

// See jsoup.Connection for inspiration
public interface Connection {

    CompletableFuture<Response> sendAsync(Request request);

    interface Request {
        String userAgent();
        Map<String, String> headers();
        Duration timeout(); // request timeout && read timeout ???
        String url();
    }

    interface Response {
        String url();
        InputStream inputStream();
    }

    class Config {
        // connectTimeout or connectionTimeout???
        public Duration connectionTimeout() {
            return Duration.ofSeconds(0);
        }
    }
}
