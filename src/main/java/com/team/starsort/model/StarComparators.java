package com.team.starsort.model;

import java.util.Comparator;

/**
 * Три отдельных компаратора — по одному на каждое поле.
 */
public final class StarComparators {

    public static final Comparator<Star> BY_NAME =
            Comparator.comparing(Star::getName);

    public static final Comparator<Star> BY_MAGNITUDE =
            Comparator.comparingDouble(Star::getApparentMagnitude);

    public static final Comparator<Star> BY_DISTANCE =
            Comparator.comparingLong(Star::getDistanceLightYears);

    private StarComparators() {
        // утилитный класс — не создаём экземпляры
    }
}