package it.faustino.katas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BerlinClockTest {
    private BerlinClock sut;

    @BeforeEach
    void setUp() {
        sut = new BerlinClock();

    }

    @ParameterizedTest
    @CsvSource({
            "00:00:00, OOOO",
            "23:59:59, YYYY",
            "12:32:00, YYOO",
            "12:34:00, YYYY",
            "12:35:00, OOOO",
    })
    void singleMinuteRow(String input, String expected) {
        String actual = sut.computeSingleMinute(input);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "00:00:00, OOOOOOOOOOO",
            "23:59:59, YYRYYRYYRYY",
            "12:04:00, OOOOOOOOOOO",
            "12:23:00, YYRYOOOOOOO",
            "12:35:00, YYRYYRYOOOO",
    })
    void fiveMinuteRow(String input, String expected) {
        String actual = sut.computeFiveMinutes(input);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "00:00:00, OOOO",
            "23:59:59, RRRO",
            "02:04:00, RROO",
            "08:23:00, RRRO",
            "14:35:00, RRRR",
    })
    void singleHoursRow(String input, String expected) {
        String actual = sut.computeSingleHour(input);
        assertEquals(expected, actual);
    }


    @ParameterizedTest
    @CsvSource({
            "00:00:00, OOOO",
            "23:59:59, RRRR",
            "02:04:00, OOOO",
            "08:23:00, ROOO",
            "16:35:00, RRRO",
    })
    void fiveHoursRow(LocalTime time, String expected) {
        String actual = sut.computeFiveHours(time);
        assertEquals(expected, actual);
    }











}