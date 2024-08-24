package com.apptasticsoftware.rssreader.internal;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Basic RSS server from testing
 */
public class RssServer {
    private static final Logger LOGGER = Logger.getLogger("RssServer");
    private final HttpServer server;

    private RssServer(int port, String endpointPath, File file, Duration writeBodyPause) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext(endpointPath, new FileRssHandler(file, writeBodyPause));
        server.setExecutor(null);
    }

    /**
     * RSS server that publish the given file content as an RSS/Atom feed.
     * @param file content to publish
     * @return RSS server
     */
    public static RssServerBuilder with(File file) {
        Objects.requireNonNull(file, "File must not be null");
        if (!file.isFile()) {
            throw new IllegalArgumentException("File must exist");
        }
        return new RssServerBuilder(file, Duration.ZERO);
    }

    /**
     * RSS server that publish the given file content as an RSS/Atom feed.
     * Server will publish 90% of the data and then wait the given amount of time before publish the rest of the data.
     * @param file content to publish
     * @param writeBodyPause time to wait before publishing the last data
     * @return RSS server
     */
    public static RssServerBuilder withWritePause(File file, Duration writeBodyPause) {
        Objects.requireNonNull(file, "File must not be null");
        if (!file.isFile()) {
            throw new IllegalArgumentException("File must exist");
        }
        Objects.requireNonNull(writeBodyPause, "Write body pause must not be null");
        if (writeBodyPause.isNegative()) {
            throw new IllegalArgumentException("Write body pause must not be negative");
        }
        return new RssServerBuilder(file, writeBodyPause);
    }

    /**
     * Start RSS server
     */
    public void start() {
        server.start();
    }

    /**
     * Stop RSS server
     */
    public void stop() {
        server.stop(1);
    }

    private static class FileRssHandler implements HttpHandler {
        private final File file;
        private final Duration writeBodyPause;

        public FileRssHandler(File file, Duration writeBodyPause) {
            this.file = file;
            this.writeBodyPause = writeBodyPause;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            LOGGER.info("New connection " + Instant.now());
            var responseBodyLength = Files.size(file.toPath());
            exchange.sendResponseHeaders(200, responseBodyLength);

            try (var os = exchange.getResponseBody()) {
                writeResponseBody(os, responseBodyLength);
            }

            LOGGER.info("Connection closed " + Instant.now());
        }

        private void writeResponseBody(OutputStream os, long responseBodyLength) throws IOException {
            byte[] buffer = new byte[128];
            int readLength;
            int totalReadLength = 0;
            boolean hasPaused = false;

            try (var is = new FileInputStream(file)){
                while ((readLength = is.read(buffer)) != -1) {
                    totalReadLength += readLength;
                    os.write(buffer, 0, readLength);
                    if (isWritePause(totalReadLength, responseBodyLength) && !hasPaused) {
                        pause(writeBodyPause);
                        hasPaused = true;
                        LOGGER.info("Continue to write " + Instant.now());
                    }
                }
            }

            os.flush();
        }

        private boolean isWritePause(int length, long totalLength) {
            return writeBodyPause.toMillis() > 0 && length >= totalLength * 0.90;
        }

        @SuppressWarnings("java:S2925")
        private void pause(Duration duration) {
            try {
                TimeUnit.MILLISECONDS.sleep(duration.toMillis());
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }

    }

    /**
     * Builder for RSS server
     */
    public static class RssServerBuilder {
        private int port = 8080;
        private String endpointPath = "/rss";
        private final File file;
        private final Duration writeBodyPause;

        RssServerBuilder(File file, Duration writeBodyPause) {
            this.file = file;
            this.writeBodyPause = writeBodyPause;
        }

        /**
         * Port number to use. Default: 8080
         * @param port port number
         * @return builder
         */
        public RssServerBuilder port(int port) {
            this.port = port;
            return this;
        }

        /**
         * The endpoint path to use. Default: /rss
         * @param endpointPath endpoint path
         * @return builder
         */
        public RssServerBuilder endpointPath(String endpointPath) {
            this.endpointPath = endpointPath;
            return this;
        }

        /**
         * Builds and configures the RSS server
         * @return RSS server
         * @throws IOException if an I/O error occurs
         */
        public RssServer build() throws IOException {
            return new RssServer(port, endpointPath, file, writeBodyPause);
        }
    }

}
