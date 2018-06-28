package it.fabio.boilerplatejetpack.utils;

public interface Constants {

    final class CONFIG {
        public static final long RETROFIT_API_CONNECTION_TIMEOUT_SECONDS = 30;
    }

    final class DATABASE {
        public static final String DB_NAME = "app.db";
        public static final int DB_VERSION = 2;
    }
}
