package com.dimas.quicksort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class QuicksortTest {

    private Quicksort quicksort;

    @BeforeEach
    void setUp() throws Exception {
        quicksort = new Quicksort();
    }

    @ParameterizedTest
    @MethodSource("quicksortProvider")
    void testSort(List<Integer> sortedList, List<Integer> rawList) {
        System.out.println("------- ");
        try {
            quicksort.sort(rawList);
            assertEquals(sortedList, rawList);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception was thrown!");
        }
    }

    static Stream<Arguments> quicksortProvider() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)),
                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4))
            ),
            Arguments.of(
                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)),
                new ArrayList<Integer>(Arrays.asList(4, 2, 1, 3))
            )
        );
    }
}
