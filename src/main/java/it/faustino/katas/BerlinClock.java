package it.faustino.katas;

import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BerlinClock {


    static final String ONE_MINUTE_ROW = "YYYY";
    static final String FIVE_MINUTE_ROW = "YYRYYRYYRYY";
    static final String SINGLE_HOUR_ROW = "RRRR";
    static final String FIVE_HOUR_ROW = "RRRR";

    public String computeSingleMinute(String input) {
        LocalTime time = LocalTime.parse(input);
        int litLamps = time.getMinute() % 5;
        return getOnLights(ONE_MINUTE_ROW, litLamps) + getOffLights(ONE_MINUTE_ROW, litLamps);
    }

    public String computeFiveMinutes(String input) {
        LocalTime time = LocalTime.parse(input);
        int litLamps = time.getMinute() / 5;
        return getOnLights(FIVE_MINUTE_ROW, litLamps) + getOffLights(FIVE_MINUTE_ROW, litLamps);
    }

    public String computeSingleHour(String input) {
        LocalTime time = LocalTime.parse(input);
        int litLamps = time.getHour() % 5;
        return getOnLights(SINGLE_HOUR_ROW, litLamps) + getOffLights(SINGLE_HOUR_ROW, litLamps);
    }

    private String getOnLights(String lights, int litLamps) {
        return lights.chars()
                .mapToObj(p -> (char) p)
                .limit(litLamps)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private String getOffLights(String lights, int litLamps) {
        return Stream.generate(() -> "O")
                .limit(lights.length() - litLamps)
                .collect(Collectors.joining());
    }
}
