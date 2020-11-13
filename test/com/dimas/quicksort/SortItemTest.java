package com.dimas.quicksort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SortItemTest {
    @ParameterizedTest
    @MethodSource("sortItemsProvider")
    void testSetter(List<Integer> list, int lo, int hi) {
        SortItem item = new SortItem();

        item.setList(list);
        item.setLo(lo);
        item.setHi(hi);

        assertEquals(item.getList(), list);
        assertEquals(item.getLo(), lo);
        assertEquals(item.getHi(), hi);
    }

    @ParameterizedTest
    @MethodSource("sortItemsProvider")
    void testConstructor(List<Integer> list, int lo, int hi) {
        SortItem item = new SortItem(list, lo, hi);

        assertEquals(item.getList(), list);
        assertEquals(item.getLo(), lo);
        assertEquals(item.getHi(), hi);
    }

    static Stream<Arguments> sortItemsProvider() {
        Stream<Arguments> streamArgs = Stream.of(
            Arguments.of(new ArrayList<Integer>(Arrays.asList(1, 3)), 0, 1),
            Arguments.of(new ArrayList<Integer>(Arrays.asList(1, 2)), 1, 2)
        );
        return streamArgs;
    }
}
