package it.faustino.katas;

import java.time.LocalTime;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BerlinClock {

    static final String OFF_LIGHT = "O";
    static final String YELLOW_LIGHT = "Y";
    static final String RED_LIGHT = "R";

    static final String ONE_MINUTE_ROW = "YYYY";
    static final String FIVE_MINUTE_ROW = "YYRYYRYYRYY";
    static final String SINGLE_HOUR_ROW = "RRRR";
    static final String FIVE_HOUR_ROW = "RRRR";

    public String computeSingleMinute(LocalTime time) {
        int litLamps = time.getMinute() % 5;
        return generateLights(litLamps, ONE_MINUTE_ROW.length(), position -> YELLOW_LIGHT);
    }

    public String computeFiveMinutes(LocalTime time) {
        int litLamps = time.getMinute() / 5;
        return generateLights(litLamps, FIVE_MINUTE_ROW.length(), position -> position % 3 == 0 ? RED_LIGHT : YELLOW_LIGHT);
    }

    public String computeSingleHour(LocalTime time) {
        int litLamps = time.getHour() % 5;
        return generateLights(litLamps, SINGLE_HOUR_ROW.length(), position -> RED_LIGHT);
    }

    public String computeFiveHours(LocalTime time) {
        int litLamps = time.getHour() / 5;
        return generateLights(litLamps, FIVE_HOUR_ROW.length(), position -> RED_LIGHT);
    }

    public String computeSecondLamp(LocalTime time) {
        return generateLights(1, 1, ignored -> time.getSecond() % 2 == 0 ? YELLOW_LIGHT : OFF_LIGHT);
    }

    private String generateOnLights(int litLamps, IntFunction<String> generator) {
        return Stream.iterate(1, current -> current + 1)
                .limit(litLamps)
                .map(generator::apply)
                .collect(Collectors.joining());
    }

    private String generateOffLights(long offLights) {
        return Stream.generate(() -> OFF_LIGHT)
                .limit(offLights)
                .collect(Collectors.joining());
    }

    private String generateLights(int litLamps, int totalLamps, IntFunction<String> generator) {
        String onLights = generateOnLights(litLamps, generator);
        String offLights = generateOffLights(totalLamps - litLamps);

        return onLights + offLights;
    }

    public String compute(LocalTime time) {
        return Stream.<Function<LocalTime, String>>of(
                this::computeSecondLamp,
                this::computeFiveHours,
                this::computeSingleHour,
                this::computeFiveMinutes,
                this::computeSingleMinute
        ).map(f -> f.apply(time))
                .collect(Collectors.joining());
    }
}
