package com.team.starsort.model;

import com.team.starsort.collection.CustomArrayList;
import com.team.starsort.collection.CustomList;
import com.team.starsort.sorting.BubbleSort;

public class StarComparatorsTest {

    public static void main(String[] args) {
        testByName();
        testByMagnitude();
        testByDistance();
        testByNameDescending();
        testComparatorOnSingleElement();
        testComparatorOnEmptyList();
        System.out.println("All StarComparatorsTest passed");
    }

    static CustomList<Star> makeUnsorted() {
        // порядок добавления: B, A, C — намеренно не отсортирован
        CustomList<Star> l = new CustomArrayList<>();
        l.add(new Star.Builder().name("B").apparentMagnitude(2.0).distanceLightYears(30).build());
        l.add(new Star.Builder().name("A").apparentMagnitude(3.0).distanceLightYears(10).build());
        l.add(new Star.Builder().name("C").apparentMagnitude(1.0).distanceLightYears(20).build());
        return l;
    }

    // ---------- по имени ----------

    static void testByName() {
        CustomList<Star> l = makeUnsorted();
        new BubbleSort<Star>().sort(l, StarComparators.BY_NAME);
        assertEq("A", l.get(0).getName(), "BY_NAME[0]");
        assertEq("B", l.get(1).getName(), "BY_NAME[1]");
        assertEq("C", l.get(2).getName(), "BY_NAME[2]");
    }

    // ---------- по величине ----------

    static void testByMagnitude() {
        CustomList<Star> l = makeUnsorted();
        new BubbleSort<Star>().sort(l, StarComparators.BY_MAGNITUDE);
        assertEqDouble(1.0, l.get(0).getApparentMagnitude(), "BY_MAGNITUDE[0]");
        assertEqDouble(2.0, l.get(1).getApparentMagnitude(), "BY_MAGNITUDE[1]");
        assertEqDouble(3.0, l.get(2).getApparentMagnitude(), "BY_MAGNITUDE[2]");
    }

    // ---------- по расстоянию ----------

    static void testByDistance() {
        CustomList<Star> l = makeUnsorted();
        new BubbleSort<Star>().sort(l, StarComparators.BY_DISTANCE);
        assertEq(10L, l.get(0).getDistanceLightYears(), "BY_DISTANCE[0]");
        assertEq(20L, l.get(1).getDistanceLightYears(), "BY_DISTANCE[1]");
        assertEq(30L, l.get(2).getDistanceLightYears(), "BY_DISTANCE[2]");
    }

    // ---------- реверс-компаратор (проверка, что это действительно Comparator) ----------

    static void testByNameDescending() {
        CustomList<Star> l = makeUnsorted();
        new BubbleSort<Star>().sort(l, StarComparators.BY_NAME.reversed());
        assertEq("C", l.get(0).getName(), "BY_NAME.reversed()[0]");
        assertEq("B", l.get(1).getName(), "BY_NAME.reversed()[1]");
        assertEq("A", l.get(2).getName(), "BY_NAME.reversed()[2]");
    }

    // ---------- границы ----------

    static void testComparatorOnSingleElement() {
        CustomList<Star> l = new CustomArrayList<>();
        l.add(new Star.Builder().name("A").apparentMagnitude(1).distanceLightYears(1).build());
        new BubbleSort<Star>().sort(l, StarComparators.BY_NAME);
        assertEq("A", l.get(0).getName(), "single element untouched");
    }

    static void testComparatorOnEmptyList() {
        CustomList<Star> l = new CustomArrayList<>();
        new BubbleSort<Star>().sort(l, StarComparators.BY_NAME);
        assertEq(0, l.size(), "empty list stays empty");
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
}