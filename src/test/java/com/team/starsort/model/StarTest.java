package com.team.starsort.model;

public class StarTest {

    public static void main(String[] args) {
        testBuildValid();
        testBuildNullName();
        testBuildEmptyName();
        testBuildBlankName();
        testBuildName50CharsOk();
        testBuildName51CharsThrows();
        testBuildMagnitudeNaN();
        testBuildMagnitudeTooLow();
        testBuildMagnitudeTooHigh();
        testBuildMagnitudeAtLowerBoundaryOk();
        testBuildMagnitudeAtUpperBoundaryOk();
        testBuildDistanceZero();
        testBuildDistanceNegative();
        testBuildDistanceOneOk();
        testEqualsSameFields();
        testEqualsDifferentFields();
        testHashCodeEqualObjects();
        testToStringFormat();
        System.out.println("All StarTest passed");
    }

    // ---------- Builder: happy path ----------

    static void testBuildValid() {
        Star s = new Star.Builder()
                .name("Сириус")
                .apparentMagnitude(-1.46)
                .distanceLightYears(9)
                .build();
        assertEq("Сириус", s.getName(), "name");
        assertEqDouble(-1.46, s.getApparentMagnitude(), "magnitude");
        assertEq(9L, s.getDistanceLightYears(), "distance");
    }

    // ---------- name: границы и ошибки ----------

    static void testBuildNullName() {
        assertThrows(() -> new Star.Builder()
                        .name(null).apparentMagnitude(1).distanceLightYears(1).build(),
                "null name");
    }

    static void testBuildEmptyName() {
        assertThrows(() -> new Star.Builder()
                        .name("").apparentMagnitude(1).distanceLightYears(1).build(),
                "empty name");
    }

    static void testBuildBlankName() {
        assertThrows(() -> new Star.Builder()
                        .name("   ").apparentMagnitude(1).distanceLightYears(1).build(),
                "blank name");
    }

    static void testBuildName50CharsOk() {
        String name50 = "a".repeat(50);
        Star s = new Star.Builder()
                .name(name50).apparentMagnitude(1).distanceLightYears(1).build();
        assertEq(name50, s.getName(), "50 chars ok");
    }

    static void testBuildName51CharsThrows() {
        String name51 = "a".repeat(51);
        assertThrows(() -> new Star.Builder()
                        .name(name51).apparentMagnitude(1).distanceLightYears(1).build(),
                "51 chars throws");
    }

    // ---------- apparentMagnitude: границы и ошибки ----------

    static void testBuildMagnitudeNaN() {
        assertThrows(() -> new Star.Builder()
                        .name("A").apparentMagnitude(Double.NaN).distanceLightYears(1).build(),
                "NaN magnitude");
    }

    static void testBuildMagnitudeTooLow() {
        assertThrows(() -> new Star.Builder()
                        .name("A").apparentMagnitude(-26.75).distanceLightYears(1).build(),
                "magnitude < -26.74");
    }

    static void testBuildMagnitudeTooHigh() {
        assertThrows(() -> new Star.Builder()
                        .name("A").apparentMagnitude(15.01).distanceLightYears(1).build(),
                "magnitude > 15.0");
    }

    static void testBuildMagnitudeAtLowerBoundaryOk() {
        Star s = new Star.Builder()
                .name("A").apparentMagnitude(-26.74).distanceLightYears(1).build();
        assertEqDouble(-26.74, s.getApparentMagnitude(), "magnitude at -26.74");
    }

    static void testBuildMagnitudeAtUpperBoundaryOk() {
        Star s = new Star.Builder()
                .name("A").apparentMagnitude(15.0).distanceLightYears(1).build();
        assertEqDouble(15.0, s.getApparentMagnitude(), "magnitude at 15.0");
    }

    // ---------- distanceLightYears: границы и ошибки ----------

    static void testBuildDistanceZero() {
        assertThrows(() -> new Star.Builder()
                        .name("A").apparentMagnitude(1).distanceLightYears(0).build(),
                "distance = 0");
    }

    static void testBuildDistanceNegative() {
        assertThrows(() -> new Star.Builder()
                        .name("A").apparentMagnitude(1).distanceLightYears(-5).build(),
                "distance < 0");
    }

    static void testBuildDistanceOneOk() {
        Star s = new Star.Builder()
                .name("A").apparentMagnitude(1).distanceLightYears(1).build();
        assertEq(1L, s.getDistanceLightYears(), "distance = 1");
    }

    // ---------- equals / hashCode ----------

    static void testEqualsSameFields() {
        Star a = new Star.Builder().name("A").apparentMagnitude(1.0).distanceLightYears(10).build();
        Star b = new Star.Builder().name("A").apparentMagnitude(1.0).distanceLightYears(10).build();
        if (!a.equals(b)) throw new AssertionError("expected equals for same fields");
    }

    static void testEqualsDifferentFields() {
        Star a = new Star.Builder().name("A").apparentMagnitude(1.0).distanceLightYears(10).build();
        Star b = new Star.Builder().name("B").apparentMagnitude(1.0).distanceLightYears(10).build();
        if (a.equals(b)) throw new AssertionError("expected NOT equals for different fields");
    }

    static void testHashCodeEqualObjects() {
        Star a = new Star.Builder().name("A").apparentMagnitude(1.0).distanceLightYears(10).build();
        Star b = new Star.Builder().name("A").apparentMagnitude(1.0).distanceLightYears(10).build();
        if (a.hashCode() != b.hashCode()) throw new AssertionError("hashCode must match for equal objects");
    }

    // ---------- toString ----------

    static void testToStringFormat() {
        Star s = new Star.Builder().name("Vega").apparentMagnitude(0.03).distanceLightYears(25).build();
        assertEq("Vega;0.03;25", s.toString(), "toString format");
    }

    // ---------- хелперы ----------

    static void assertEq(Object expected, Object actual, String ctx) {
        if (expected == null && actual == null) return;
        if (expected == null || !expected.equals(actual)) {
            throw new AssertionError("[" + ctx + "] expected=" + expected + " actual=" + actual);
        }
    }

    static void assertEqDouble(double expected, double actual, String ctx) {
        double eps = 1e-9;
        if (Math.abs(expected - actual) > eps) {
            throw new AssertionError("[" + ctx + "] expected=" + expected + " actual=" + actual);
        }
    }

    static void assertThrows(Runnable action, String ctx) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("[" + ctx + "] expected IllegalArgumentException, none thrown");
    }
}