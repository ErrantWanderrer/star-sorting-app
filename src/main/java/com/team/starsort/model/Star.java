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
     * Проверяет, что имя звезды валидно:
     *  - не null, не пустое, не из пробелов;
     *  - не длиннее 50 символов;
     *  - содержит только буквы и цифры, разделённые одиночными дефисами.
     */
    public static boolean isValidName(String name) {
        return name != null
                && !name.isBlank()
                && name.length() <= 50
                && name.matches("[\\p{L}\\p{N}]+(-[\\p{L}\\p{N}]+)*");
    }

    public static boolean isValidMagnitude(double magnitude) {
        return !Double.isNaN(magnitude)
                && magnitude >= -26.74
                && magnitude <= 15.0;
    }

    public static boolean isValidDistance(long distance) {
        return distance > 0;
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
            if (!isValidName(name)) {
                throw new IllegalArgumentException(
                        "Название звезды: буквы и цифры, разделённые одиночным дефисом, " + "до 50 символов, не пустое");
            }
            if (!isValidMagnitude(apparentMagnitude)) {
                throw new IllegalArgumentException(
                        "Видимая величина должна быть в диапазоне [-26.74, 15.0] (NaN запрещён)");
            }
            if (!isValidDistance(distanceLightYears)) {
                throw new IllegalArgumentException(
                        "Расстояние до Земли должно быть положительным (> 0)");
            }
            return new Star(this);
        }
    }
}