package com.apptasticsoftware.rssreader.util;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

/**
 * Provides methods for mapping field
 */
public final class Mapper {
    private static final Logger LOGGER = Logger.getLogger("com.apptasticsoftware.rssreader.util");

    private Mapper() { }

    /**
     * Maps a boolean text value (true, false, no or yes) to a boolean field. Text value can be in any casing.
     * @param text text value
     * @param func boolean setter method
     */
    public static void mapBoolean(String text, Consumer<Boolean> func) {
        text = text.toLowerCase(Locale.ENGLISH);
        if ("true".equals(text) || "yes".equals(text)) {
            func.accept(Boolean.TRUE);
        } else if ("false".equals(text) || "no".equals(text)) {
            func.accept(Boolean.FALSE);
        }
    }

    /**
     * Maps a integer text value to a integer field.
     * @param text text value
     * @param func integer setter method
     */
    public static void mapInteger(String text, Consumer<Integer> func) {
        mapNumber(text, func, Integer::valueOf);
    }

    /**
     * Maps a long text value to a long field.
     * @param text text value
     * @param func long setter method
     */
    public static void mapLong(String text, Consumer<Long> func) {
        mapNumber(text, func, Long::valueOf);
    }

    /**
     * Maps a double text value to a double field.
     * @param text text value
     * @param func double setter method
     */
    public static void mapDouble(String text, Consumer<Double> func) {
        mapNumber(text, func, Double::valueOf);
    }

    private static <T> void mapNumber(String text, Consumer<T> func, Function<String, T> convert) {
        if (isNotNullOrEmpty(text)) {
            try {
                func.accept(convert.apply(text));
            } catch (NumberFormatException e) {
                if (LOGGER.isLoggable(Level.WARNING)) {
                    LOGGER.log(Level.WARNING, () -> String.format("Failed to convert %s. Message: %s", text, e.getMessage()));
                }
            }
        }
    }

    /**
     * Split string on comma delimiter.
     * @param string string to split
     * @return list of strings
     */
    public static List<String> split(String string) {
        return Stream.of(string.split(","))
                .map(String::trim)
                .filter(not(String::isBlank))
                .collect(Collectors.toList());
    }

    /**
     * Map value if field has not been mapped before
     * @param text value to map
     * @param getter getter to check if field is empty
     * @param setter setter to set value
     * @param <T> type
     */
    public static <T> void mapIfEmpty(String text, Supplier<T> getter, Consumer<String> setter) {
        if (isNullOrEmpty(getter) && isNotNullOrEmpty(text)) {
            setter.accept(text);
        }
    }

    /**
     * Create a new instance if a getter returns optional empty and assigns the field the new instance.
     * @param getter getter method
     * @param setter setter method
     * @param factory factory for creating a new instance if field is not set before
     * @return existing or new instance
     * @param <T> any class
     */
    public static <T> T createIfNull(Supplier<Optional<T>> getter, Consumer<T> setter, Supplier<T> factory) {
        return createIfNullOptional(getter, setter, factory).orElse(null);
    }

    /**
     * Create a new instance if a getter returns optional empty and assigns the field the new instance.
     * @param getter getter method
     * @param setter setter method
     * @param factory factory for creating a new instance if field is not set before
     * @return existing or new instance
     * @param <T> any class
     */
    public static <T> Optional<T> createIfNullOptional(Supplier<Optional<T>> getter, Consumer<T> setter, Supplier<T> factory) {
        Optional<T> instance = getter.get();
        if (instance.isEmpty()) {
            T newInstance = factory.get();
            setter.accept(newInstance);
            instance = Optional.of(newInstance);
        }
        return instance;
    }

    /**
     * Returns an empty list if the provided list is null.
     * @param list the list to check
     * @param <T> the type of items in the list
     * @return an empty list if the provided list is null, otherwise returns the provided list
     */
    public static <T> List<T> emptyListIfNull(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(list);
    }

    private static <T> boolean isNullOrEmpty(Supplier<T> getter) {
        return getter.get() == null ||
                "".equals(getter.get()) ||
                getter.get() == Optional.empty() ||
                getter.get() instanceof Optional<?> &&
                        ((Optional<?>) getter.get())
                                .filter(String.class::isInstance)
                                .map(String.class::cast)
                                .map(String::isBlank)
                                .orElse(false);
    }

    private static boolean isNullOrEmpty(String text) {
        return text == null || text.isBlank();
    }

    private static boolean isNotNullOrEmpty(String text) {
        return !isNullOrEmpty(text);
    }
}
