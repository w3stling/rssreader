package com.apptasticsoftware.integrationtest;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.*;

class CloseTest {
    private static final String FILE_NAME = "rss-feed.xml";
    private int closeCounter;

    @BeforeEach
    void setup() {
        closeCounter = 0;
    }

    @Test
    void testCount() {
        long count = getReader().count();
        assertEquals(1, closeCounter);
        assertEquals(19, count);
    }

    @Test
    void testUrl() throws IOException {
        long count = new RssReader().read("https://gizmodo.com/feed").onClose(this::close).count();
        assertEquals(1, closeCounter);
        assertTrue(count > 0);
    }

    @Test
    void testTwoUrls() {
        var urls = List.of("https://gizmodo.com/feed", "https://feeds.a.dj.com/rss/RSSMarketsMain.xml");
        long count = new RssReader().read(urls).onClose(this::close).count();
        assertEquals(1, closeCounter);
        assertTrue(count > 0);
    }

    @Test
    void testLimit() {
        long count = getReader().limit(3).count();
        assertEquals(1, closeCounter);
        assertEquals(3, count);
    }

    @Test
    void testSkip() {
        long count = getReader().skip(3).count();
        assertEquals(1, closeCounter);
        assertEquals(16, count);
    }

    @Test
    void testFilter() {
        long count = getReader().filter(i -> i.getCategories().contains("AI")).count();
        assertEquals(1, closeCounter);
        assertEquals(6, count);
    }

    @Test
    void testMap() {
        var titles = getReader().map(Item::getTitle).collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, titles.size());
    }

    @Test
    void testMapToInt() {
        int sum = getReader().map(i -> i.getCategories().size()).mapToInt(Integer::intValue).sum();
        assertEquals(1, closeCounter);
        assertEquals(102, sum);
    }

    @Test
    void testMapToLong() {
        long sum = getReader().map(i -> i.getCategories().size()).mapToLong(Integer::longValue).sum();
        assertEquals(1, closeCounter);
        assertEquals(102, sum);
    }

    @Test
    void testMapToDouble() {
        double sum = getReader().map(i -> i.getCategories().size()).mapToDouble(Integer::doubleValue).sum();
        assertEquals(1, closeCounter);
        assertEquals(102.0, sum);
    }

    @Test
    void testFlatMap() {
        long count = getReader().flatMap(i -> i.getCategories().stream()).limit(100).count();
        assertEquals(1, closeCounter);
        assertEquals(100, count);
    }

    @Test
    void testFlatMapToInt() {
        int count = getReader().flatMapToInt(i -> i.getCategories().stream().mapToInt(String::length)).sum();
        assertEquals(1, closeCounter);
        assertEquals(903, count);
    }

    @Test
    void testFlatMapToLong() {
        long count = getReader().flatMapToLong(i -> i.getCategories().stream().mapToLong(String::length)).sum();
        assertEquals(1, closeCounter);
        assertEquals(903, count);
    }

    @Test
    void testFlatMapToDouble() {
        double count = getReader().flatMapToDouble(i -> i.getCategories().stream().mapToDouble(String::length)).sum();
        assertEquals(1, closeCounter);
        assertEquals(903, count);
    }

    @Test
    void testDistinct() {
        long count = getReader().flatMap(i -> i.getCategories().stream()).distinct().count();
        assertEquals(1, closeCounter);
        assertEquals(72, count);
    }

    @Test
    void testSorted() {
        var items = getReader().flatMap(i -> i.getCategories().stream()).sorted().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(102, items.size());
    }

    @Test
    void testSortedWithComparator() {
        var items = getReader().flatMap(i -> i.getCategories().stream()).sorted(String::compareToIgnoreCase).collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(102, items.size());
    }

    @Test
    void testPeek() {
        final AtomicInteger counter = new AtomicInteger();
        var items = getReader().peek(i -> counter.incrementAndGet()).collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, counter.intValue());
        assertEquals(19, items.size());
    }

    @Test
    void testForEach() {
        final AtomicInteger counter = new AtomicInteger();
        getReader().forEach(i -> counter.incrementAndGet());
        assertEquals(1, closeCounter);
        assertEquals(19, counter.intValue());
    }

    @Test
    void testForEachOrdered() {
        final AtomicInteger counter = new AtomicInteger();
        getReader().forEachOrdered(i -> counter.incrementAndGet());
        assertEquals(1, closeCounter);
        assertEquals(19, counter.intValue());
    }

    @Test
    void testToArray() {
        var items = getReader().toArray();
        assertEquals(1, closeCounter);
        assertEquals(19, items.length);
    }

    @Test
    void testToArray2() {
        String[] items = getReader().map(i -> i.getTitle().orElse("")).toArray(String[]::new);
        assertEquals(1, closeCounter);
        assertEquals(19, items.length);
    }

    @Test
    void testReduce() {
        var count = getReader().map(i -> i.getCategories().size()).reduce(Integer::sum).orElse(0);
        assertEquals(1, closeCounter);
        assertEquals(102, count);
    }

    @Test
    void testReduce2() {
        var count = getReader().map(i -> i.getCategories().size()).reduce(0, Integer::sum);
        assertEquals(1, closeCounter);
        assertEquals(102, count);
    }

    @Test
    void testReduce3() {
        var count = getReader().map(i -> i.getCategories().size()).reduce(0, Integer::sum, Integer::sum);
        assertEquals(1, closeCounter);
        assertEquals(102, count);
    }

    @Test
    void testCollect() {
        var items = getReader().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, items.size());
    }

    @Test
    void testCollect2() {
        var items = getReader().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        assertEquals(1, closeCounter);
        assertEquals(19, items.size());
    }

    @Test
    void testMin() {
        var count = getReader().map(i -> i.getTitle().map(String::length).orElse(0)).min(Integer::compare).orElse(-1);
        assertEquals(1, closeCounter);
        assertEquals(34, count);
    }

    @Test
    void testMax() {
        var count = getReader().map(i -> i.getTitle().map(String::length).orElse(0)).max(Integer::compare).orElse(-1);
        assertEquals(1, closeCounter);
        assertEquals(109, count);
    }

    @Test
    void testAnyMatch() {
        var isMatch = getReader().anyMatch(i -> i.getCategories().contains("AI"));
        assertEquals(1, closeCounter);
        assertTrue(isMatch);
    }

    @Test
    void testAllMatch() {
        var allMatch = getReader().allMatch(i -> i.getCategories().contains("AI"));
        assertEquals(1, closeCounter);
        assertFalse(allMatch);
    }

    @Test
    void testNoneMatch() {
        var noMatch = getReader().noneMatch(i -> i.getCategories().contains("AI"));
        assertEquals(1, closeCounter);
        assertFalse(noMatch);
    }

    @Test
    void testFindFirst() {
        var item = getReader().findFirst();
        assertEquals(1, closeCounter);
        assertNotNull(item);
    }

    @Test
    void testFindAny() {
        var item = getReader().findAny();
        assertEquals(1, closeCounter);
        assertNotNull(item);
    }

    @Test
    void testIsParallel() {
        var stream = getReader();
        assertFalse(stream.isParallel());
        stream.close();
        assertEquals(1, closeCounter);
    }

    @Test
    void testParallel() {
        var stream = getReader().parallel();
        assertTrue(stream.isParallel());
        assertEquals(19, stream.count());
        assertEquals(1, closeCounter);
    }

    @Test
    void testSequential() {
        long count = getReader().sequential().count();
        assertEquals(1, closeCounter);
        assertEquals(19, count);
    }

    @Test
    void testUnordered() {
        long count = getReader().unordered().count();
        assertEquals(1, closeCounter);
        assertEquals(19, count);
    }

    @Test
    void testIterator() {
        var stream = getReader();
        var iterator = stream.iterator();
        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());
        stream.close();
        assertEquals(1, closeCounter);
    }

    @Test
    void testSpliterator() {
        var stream = getReader();
        var spliterator = stream.spliterator();
        assertNotNull(spliterator);
        stream.close();
        assertEquals(1, closeCounter);
    }



    @Test
    void testIntStream() {
        AtomicInteger counter = new AtomicInteger();
        long sum = getReaderIntStream().peek(i -> counter.incrementAndGet()).skip(1).limit(4).distinct().sorted().filter(i -> i > 5).map(i -> i * 2).sum();
        assertEquals(1, closeCounter);
        assertEquals(5, counter.intValue());
        assertEquals(20, sum);
    }

    @Test
    void testIntStream2() {
        int length = getReaderIntStream().mapToObj(String::valueOf).reduce("", String::concat).length();
        assertEquals(1, closeCounter);
        assertEquals(20, length);
    }

    @Test
    void testIntStreamMapToLong() {
        long sum = getReaderIntStream().mapToLong(Long::valueOf).sum();
        assertEquals(1, closeCounter);
        assertEquals(102, sum);
    }

    @Test
    void testIntStreamMapToDouble() {
        double sum = getReaderIntStream().mapToDouble(Double::valueOf).sum();
        assertEquals(1, closeCounter);
        assertEquals(102.0, sum);
    }

    @Test
    void testIntStreamFlatMap() {
        var sumX2 = getReaderIntStream().flatMap(i -> IntStream.of(i + i)).sum();
        assertEquals(1, closeCounter);
        assertEquals(204, sumX2);
    }

    @Test
    void testIntStreamForEach() {
        AtomicInteger counter = new AtomicInteger();
        getReaderIntStream().forEach(i -> counter.incrementAndGet());
        assertEquals(1, closeCounter);
        assertEquals(19, counter.intValue());
    }

    @Test
    void testIntStreamForEachOrdered() {
        AtomicInteger counter = new AtomicInteger();
        getReaderIntStream().forEachOrdered(i -> counter.incrementAndGet());
        assertEquals(1, closeCounter);
        assertEquals(19, counter.intValue());
    }

    @Test
    void testIntStreamToArray() {
        var array = getReaderIntStream().toArray();
        assertEquals(1, closeCounter);
        assertEquals(19, array.length);
    }

    @Test
    void testIntStreamReduce() {
        int sum = getReaderIntStream().reduce(Integer::sum).orElse(0);
        assertEquals(1, closeCounter);
        assertEquals(102, sum);
    }

    @Test
    void testIntStreamReduce2() {
        int sum = getReaderIntStream().reduce(0, Integer::sum);
        assertEquals(1, closeCounter);
        assertEquals(102, sum);
    }

    @Test
    void testIntStreamCollect() {
        var array = getReaderIntStream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        assertEquals(1, closeCounter);
        assertEquals(19, array.size());
    }

    @Test
    void testIntStreamMin() {
        var value = getReaderIntStream().min().orElse(Integer.MAX_VALUE);
        assertEquals(1, closeCounter);
        assertEquals(2, value);
    }

    @Test
    void testIntStreamMax() {
        var value = getReaderIntStream().max().orElse(Integer.MIN_VALUE);
        assertEquals(1, closeCounter);
        assertEquals(10, value);
    }

    @Test
    void testIntStreamCount() {
        var value = getReaderIntStream().count();
        assertEquals(1, closeCounter);
        assertEquals(19, value);
    }

    @Test
    void testIntStreamAverage() {
        double value = getReaderIntStream().average().orElse(0.0);
        assertEquals(1, closeCounter);
        assertEquals(5.368421052631579, value);
    }

    @Test
    void testIntStreamSummaryStatistics() {
        double value = getReaderIntStream().summaryStatistics().getAverage();
        assertEquals(1, closeCounter);
        assertEquals(5.368421052631579, value);
    }

    @Test
    void testIntStreamAnyMatch() {
        boolean anyMatch = getReaderIntStream().anyMatch(i -> i > 5);
        assertEquals(1, closeCounter);
        assertTrue(anyMatch);
    }

    @Test
    void testIntStreamAllMatch() {
        boolean anyMatch = getReaderIntStream().allMatch(i -> i > 0);
        assertEquals(1, closeCounter);
        assertTrue(anyMatch);
    }

    @Test
    void testIntStreamNoneMatch() {
        boolean anyMatch = getReaderIntStream().noneMatch(i -> i == 0);
        assertEquals(1, closeCounter);
        assertTrue(anyMatch);
    }

    @Test
    void testIntStreamFindFirst() {
        int value = getReaderIntStream().findFirst().orElse(0);
        assertEquals(1, closeCounter);
        assertEquals(6, value);
    }

    @Test
    void testIntStreamFindAny() {
        int value = getReaderIntStream().findAny().orElse(0);
        assertEquals(1, closeCounter);
        assertEquals(6, value);
    }

    @Test
    void testIntStreamAsLongStream() {
        var values = getReaderIntStream().asLongStream().boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testIntStreamAsDoubleStream() {
        var values = getReaderIntStream().asDoubleStream().boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testIntStreamBoxed() {
        var values = getReaderIntStream().boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testIntStreamSequential() {
        var stream = getReaderIntStream().sequential();
        assertFalse(stream.isParallel());
        var values = stream.boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testIntStreamParallel() {
        var stream = getReaderIntStream().parallel();
        assertTrue(stream.isParallel());
        var values = stream.boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testIntStreamUnordered() {
        var values = getReaderIntStream().unordered().boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testIntStreamIterator() {
        var stream = getReaderIntStream();
        var iterator = stream.iterator();
        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());
        stream.close();
        assertEquals(1, closeCounter);
    }

    @Test
    void testIntStreamSpliterator() {
        var stream = getReaderIntStream();
        var spliterator = stream.spliterator();
        assertNotNull(spliterator);
        stream.close();
        assertEquals(1, closeCounter);
    }



    @Test
    void testLongStream() {
        AtomicInteger counter = new AtomicInteger();
        long sum = getReaderLongStream().peek(i -> counter.incrementAndGet()).skip(1).limit(4).distinct().sorted().filter(i -> i > 5).map(i -> i * 2).sum();
        assertEquals(1, closeCounter);
        assertEquals(5, counter.intValue());
        assertEquals(20, sum);
    }

    @Test
    void testLongStream2() {
        int length = getReaderLongStream().mapToObj(String::valueOf).reduce("", String::concat).length();
        assertEquals(1, closeCounter);
        assertEquals(20, length);
    }

    @Test
    void testLongStreamMapToLong() {
        int sum = getReaderLongStream().mapToInt(i -> (int) i).sum();
        assertEquals(1, closeCounter);
        assertEquals(102, sum);
    }

    @Test
    void testLongStreamMapToDouble() {
        double sum = getReaderLongStream().mapToDouble(Double::valueOf).sum();
        assertEquals(1, closeCounter);
        assertEquals(102.0, sum);
    }

    @Test
    void testLongStreamFlatMap() {
        var sumX2 = getReaderLongStream().flatMap(i -> LongStream.of(i + i)).sum();
        assertEquals(1, closeCounter);
        assertEquals(204, sumX2);
    }

    @Test
    void testLongStreamForEach() {
        AtomicInteger counter = new AtomicInteger();
        getReaderLongStream().forEach(i -> counter.incrementAndGet());
        assertEquals(1, closeCounter);
        assertEquals(19, counter.intValue());
    }

    @Test
    void testLongStreamForEachOrdered() {
        AtomicInteger counter = new AtomicInteger();
        getReaderLongStream().forEachOrdered(i -> counter.incrementAndGet());
        assertEquals(1, closeCounter);
        assertEquals(19, counter.intValue());
    }

    @Test
    void testLongStreamToArray() {
        var array = getReaderLongStream().toArray();
        assertEquals(1, closeCounter);
        assertEquals(19, array.length);
    }

    @Test
    void testLongStreamReduce() {
        long sum = getReaderLongStream().reduce(Long::sum).orElse(0);
        assertEquals(1, closeCounter);
        assertEquals(102, sum);
    }

    @Test
    void testLongStreamReduce2() {
        long sum = getReaderLongStream().reduce(0, Long::sum);
        assertEquals(1, closeCounter);
        assertEquals(102, sum);
    }

    @Test
    void testLongStreamCollect() {
        var array = getReaderLongStream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        assertEquals(1, closeCounter);
        assertEquals(19, array.size());
    }

    @Test
    void testLongStreamMin() {
        var value = getReaderLongStream().min().orElse(Integer.MAX_VALUE);
        assertEquals(1, closeCounter);
        assertEquals(2, value);
    }

    @Test
    void testLongStreamMax() {
        var value = getReaderLongStream().max().orElse(Integer.MIN_VALUE);
        assertEquals(1, closeCounter);
        assertEquals(10, value);
    }

    @Test
    void testLongStreamCount() {
        var value = getReaderLongStream().count();
        assertEquals(1, closeCounter);
        assertEquals(19, value);
    }

    @Test
    void testLongStreamAverage() {
        double value = getReaderLongStream().average().orElse(0.0);
        assertEquals(1, closeCounter);
        assertEquals(5.368421052631579, value);
    }

    @Test
    void testLongStreamSummaryStatistics() {
        double value = getReaderLongStream().summaryStatistics().getAverage();
        assertEquals(1, closeCounter);
        assertEquals(5.368421052631579, value);
    }

    @Test
    void testLongStreamAnyMatch() {
        boolean anyMatch = getReaderLongStream().anyMatch(i -> i > 5);
        assertEquals(1, closeCounter);
        assertTrue(anyMatch);
    }

    @Test
    void testLongStreamAllMatch() {
        boolean anyMatch = getReaderLongStream().allMatch(i -> i > 0);
        assertEquals(1, closeCounter);
        assertTrue(anyMatch);
    }

    @Test
    void testLongStreamNoneMatch() {
        boolean anyMatch = getReaderLongStream().noneMatch(i -> i == 0);
        assertEquals(1, closeCounter);
        assertTrue(anyMatch);
    }

    @Test
    void testLongStreamFindFirst() {
        long value = getReaderLongStream().findFirst().orElse(0);
        assertEquals(1, closeCounter);
        assertEquals(6, value);
    }

    @Test
    void testLongStreamFindAny() {
        long value = getReaderLongStream().findAny().orElse(0);
        assertEquals(1, closeCounter);
        assertEquals(6, value);
    }

    @Test
    void testLongStreamAsDoubleStream() {
        var values = getReaderLongStream().asDoubleStream().boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testLongStreamBoxed() {
        var values = getReaderLongStream().boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testLongStreamSequential() {
        var stream = getReaderLongStream().sequential();
        assertFalse(stream.isParallel());
        var values = stream.boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testLongStreamParallel() {
        var stream = getReaderLongStream().parallel();
        assertTrue(stream.isParallel());
        var values = stream.boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testLongStreamUnordered() {
        var values = getReaderLongStream().unordered().boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testLongStreamIterator() {
        var stream = getReaderLongStream();
        var iterator = stream.iterator();
        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());
        stream.close();
        assertEquals(1, closeCounter);
    }

    @Test
    void testLongStreamSpliterator() {
        var stream = getReaderLongStream();
        var spliterator = stream.spliterator();
        assertNotNull(spliterator);
        stream.close();
        assertEquals(1, closeCounter);
    }



    @Test
    void testDoubleStream() {
        AtomicInteger counter = new AtomicInteger();
        double sum = getReaderDoubleStream().peek(i -> counter.incrementAndGet()).skip(1).limit(4).distinct().sorted().filter(i -> i > 5.0).map(i -> i * 2.0).sum();
        assertEquals(1, closeCounter);
        assertEquals(5, counter.intValue());
        assertEquals(20, sum);
    }

    @Test
    void testDoubleStream2() {
        int length = getReaderDoubleStream().mapToObj(String::valueOf).reduce("", String::concat).length();
        assertEquals(1, closeCounter);
        assertEquals(58, length);
    }

    @Test
    void testDoubleStreamMapToInt() {
        int sum = getReaderDoubleStream().mapToInt(i -> Double.valueOf(i).intValue()).sum();
        assertEquals(1, closeCounter);
        assertEquals(102.0, sum);
    }

    @Test
    void testDoubleStreamMapToLong() {
        long sum = getReaderDoubleStream().mapToLong(i -> Double.valueOf(i).longValue()).sum();
        assertEquals(1, closeCounter);
        assertEquals(102, sum);
    }

    @Test
    void testDoubleStreamFlatMap() {
        var sumX2 = getReaderDoubleStream().flatMap(i -> DoubleStream.of(i + i)).sum();
        assertEquals(1, closeCounter);
        assertEquals(204.0, sumX2);
    }

    @Test
    void testDoubleStreamForEach() {
        AtomicInteger counter = new AtomicInteger();
        getReaderDoubleStream().forEach(i -> counter.incrementAndGet());
        assertEquals(1, closeCounter);
        assertEquals(19, counter.intValue());
    }

    @Test
    void testDoubleStreamForEachOrdered() {
        AtomicInteger counter = new AtomicInteger();
        getReaderDoubleStream().forEachOrdered(i -> counter.incrementAndGet());
        assertEquals(1, closeCounter);
        assertEquals(19, counter.intValue());
    }

    @Test
    void testDoubleStreamToArray() {
        var array = getReaderDoubleStream().toArray();
        assertEquals(1, closeCounter);
        assertEquals(19, array.length);
    }

    @Test
    void testDoubleStreamReduce() {
        double sum = getReaderDoubleStream().reduce(Double::sum).orElse(0);
        assertEquals(1, closeCounter);
        assertEquals(102.0, sum);
    }

    @Test
    void testDoubleStreamReduce2() {
        double sum = getReaderDoubleStream().reduce(0, Double::sum);
        assertEquals(1, closeCounter);
        assertEquals(102.0, sum);
    }

    @Test
    void testDoubleStreamCollect() {
        var array = getReaderDoubleStream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        assertEquals(1, closeCounter);
        assertEquals(19, array.size());
    }

    @Test
    void testDoubleStreamMin() {
        var value = getReaderDoubleStream().min().orElse(Integer.MAX_VALUE);
        assertEquals(1, closeCounter);
        assertEquals(2, value);
    }

    @Test
    void testDoubleStreamMax() {
        var value = getReaderDoubleStream().max().orElse(Integer.MIN_VALUE);
        assertEquals(1, closeCounter);
        assertEquals(10, value);
    }

    @Test
    void testDoubleStreamCount() {
        var value = getReaderDoubleStream().count();
        assertEquals(1, closeCounter);
        assertEquals(19, value);
    }

    @Test
    void testDoubleStreamAverage() {
        double value = getReaderDoubleStream().average().orElse(0.0);
        assertEquals(1, closeCounter);
        assertEquals(5.368421052631579, value);
    }

    @Test
    void testDoubleStreamSummaryStatistics() {
        double value = getReaderDoubleStream().summaryStatistics().getAverage();
        assertEquals(1, closeCounter);
        assertEquals(5.368421052631579, value);
    }

    @Test
    void testDoubleStreamAnyMatch() {
        boolean anyMatch = getReaderDoubleStream().anyMatch(i -> i > 5);
        assertEquals(1, closeCounter);
        assertTrue(anyMatch);
    }

    @Test
    void testDoubleStreamAllMatch() {
        boolean anyMatch = getReaderDoubleStream().allMatch(i -> i > 0);
        assertEquals(1, closeCounter);
        assertTrue(anyMatch);
    }

    @Test
    void testDoubleStreamNoneMatch() {
        boolean anyMatch = getReaderDoubleStream().noneMatch(i -> i == 0);
        assertEquals(1, closeCounter);
        assertTrue(anyMatch);
    }

    @Test
    void testDoubleStreamFindFirst() {
        double value = getReaderDoubleStream().findFirst().orElse(0);
        assertEquals(1, closeCounter);
        assertEquals(6.0, value);
    }

    @Test
    void testDoubleStreamFindAny() {
        double value = getReaderDoubleStream().findAny().orElse(0);
        assertEquals(1, closeCounter);
        assertEquals(6.0, value);
    }

    @Test
    void testDoubleStreamBoxed() {
        var values = getReaderDoubleStream().boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testDoubleStreamSequential() {
        var stream = getReaderDoubleStream().sequential();
        assertFalse(stream.isParallel());
        var values = stream.boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testDoubleStreamParallel() {
        var stream = getReaderDoubleStream().parallel();
        assertTrue(stream.isParallel());
        var values = stream.boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testDoubleStreamUnordered() {
        var values = getReaderDoubleStream().unordered().boxed().collect(Collectors.toList());
        assertEquals(1, closeCounter);
        assertEquals(19, values.size());
    }

    @Test
    void testDoubleStreamIterator() {
        var stream = getReaderDoubleStream();
        var iterator = stream.iterator();
        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());
        stream.close();
        assertEquals(1, closeCounter);
    }

    @Test
    void testDoubleStreamSpliterator() {
        var stream = getReaderDoubleStream();
        var spliterator = stream.spliterator();
        assertNotNull(spliterator);
        stream.close();
        assertEquals(1, closeCounter);
    }



    void close() {
        closeCounter++;
    }

    private Stream<Item> getReader() {
        return new RssReader().read(fromFile(FILE_NAME)).onClose(this::close).limit(19);
    }

    private IntStream getReaderIntStream() {
        return new RssReader().read(fromFile(FILE_NAME)).mapToInt(i -> i.getCategories().size()).onClose(this::close).limit(19);
    }

    private LongStream getReaderLongStream() {
        return new RssReader().read(fromFile(FILE_NAME)).mapToLong(i -> i.getCategories().size()).onClose(this::close).limit(19);
    }

    private DoubleStream getReaderDoubleStream() {
        return new RssReader().read(fromFile(FILE_NAME)).mapToDouble(i -> i.getCategories().size()).onClose(this::close).limit(19);
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
