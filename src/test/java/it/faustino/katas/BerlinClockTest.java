package it.faustino.katas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    void singleMinuteRow(LocalTime time, String expected) {
        String actual = sut.computeSingleMinute(time);
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
    void fiveMinuteRow(LocalTime time, String expected) {
        String actual = sut.computeFiveMinutes(time);
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
    void singleHoursRow(LocalTime time, String expected) {
        String actual = sut.computeSingleHour(time);
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


    @ParameterizedTest
    @CsvSource({
            "00:00:00, Y",
            "23:59:59, O"
    })
    void secondsLamp(LocalTime time, String expected) {
        String actual = sut.computeSecondLamp(time);
        assertEquals(expected, actual);
    }


    @ParameterizedTest
    @CsvSource({
            "00:00:00, YOOOOOOOOOOOOOOOOOOOOOOO",
            "23:59:59, ORRRRRRROYYRYYRYYRYYYYYY",
            "16:50:06, YRRROROOOYYRYYRYYRYOOOOO",
            "11:37:01, ORROOROOOYYRYYRYOOOOYYOO"
    })
    void completeBerlinClock(LocalTime time, String expected) {
        String actual = sut.compute(time);
        assertEquals(expected, actual);
    }

    @Disabled("impossbbru")
    @ParameterizedTest
    @CsvSource({
            "YOOOOOOOOOOOOOOOOOOOOOOO, 00:00:00",
            "ORRRRRRROYYRYYRYYRYYYYYY, 23:59:59",
            "YRRROROOOYYRYYRYYRYOOOOO, 16:50:06",
            "ORROOROOOYYRYYRYOOOOYYOO, 11:37:01"
    })
    void completeBerlinClockToDigital(String berlin, LocalTime expected) {
//        LocalTime actual = sut.compute(berlin);
//        assertEquals(expected, actual);
    }


}