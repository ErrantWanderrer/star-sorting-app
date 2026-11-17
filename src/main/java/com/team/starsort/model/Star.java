package com.team.starsort.model;

import java.util.Objects;

/**
 * Класс Star (Звезда).
 * Поля:
 *  - name (String) - название
 *  - apparentMagnitude (double) - видимая звёздная величина
 *  - distanceLightYears (long) - расстояние до Земли в световых годах
 */
public final class Star {

    private final String name;
    private final double apparentMagnitude;
    private final long distanceLightYears;

    private Star(Builder builder) {
        this.name = builder.name;
        this.apparentMagnitude = builder.apparentMagnitude;
        this.distanceLightYears = builder.distanceLightYears;
    }

    public String getName() {
        return name;
    }

    public double getApparentMagnitude() {
        return apparentMagnitude;
    }

    public long getDistanceLightYears() {
        return distanceLightYears;
    }

    /**
     * Формат сериализации: {@code name;apparentMagnitude;distanceLightYears}.
     */
    @Override
    public String toString() {
        return name + ";" + apparentMagnitude + ";" + distanceLightYears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return Double.compare(star.apparentMagnitude, apparentMagnitude) == 0
                && distanceLightYears == star.distanceLightYears
                && Objects.equals(name, star.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, apparentMagnitude, distanceLightYears);
    }

    /**
     * Ручной Builder с валидацией в build().
     */
    public static final class Builder {

        private String name;
        private double apparentMagnitude;
        private long distanceLightYears;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder apparentMagnitude(double apparentMagnitude) {
            this.apparentMagnitude = apparentMagnitude;
            return this;
        }

        public Builder distanceLightYears(long distanceLightYears) {
            this.distanceLightYears = distanceLightYears;
            return this;
        }

        /**
         * Создаёт Star с валидацией.
         * @throws IllegalArgumentException если данные невалидны
         */
        public Star build() {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Название звезды не может быть пустым");
            }
            if (name.length() > 50) {
                throw new IllegalArgumentException("Название звезды не должно превышать 50 символов");
            }
            if (Double.isNaN(apparentMagnitude)
                    || apparentMagnitude < -26.74
                    || apparentMagnitude > 15.0) {
                throw new IllegalArgumentException(
                        "Видимая величина должна быть в диапазоне [-26.74, 15.0] (NaN запрещён)");
            }
            if (distanceLightYears <= 0) {
                throw new IllegalArgumentException(
                        "Расстояние до Земли должно быть положительным (> 0)");
            }
            return new Star(this);
        }
    }
}