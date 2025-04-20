package com.apptasticsoftware.rssreader;

import java.io.InputStream;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

// See jsoup.Connection for inspiration
// implements closable ???
public interface Connection {

    CompletableFuture<Response> sendAsync(Request request);

    interface Request {
        String userAgent();
        Map<String, String> headers();
        Duration timeout(); // request timeout && read timeout ???
        String url();
    }

    // implements closable interface or close any resources when closing the input stream
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
