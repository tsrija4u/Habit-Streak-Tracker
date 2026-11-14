import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 *
 * HabitStreakTrackerTest is a JUnit test for {@code HabitStreakTracker}'s
 * secondary methods and the other methods like ({@code equals},
 * {@code hashCode}, {@code toString}) implemented in
 * {@code HabitStreakTrackerSecondary}.
 *
 * @author Srijani Tirungiri
 */
public class HabitStreakTrackerTest {

    /**
     * The value of five, used in tests.
     */
    private static final int FIVE = 5;
    /**
     * The value of three, used in tests.
     */
    private static final int THREE = 3;
    /**
     * The value of ten, used in tests.
     */
    private static final int TEN = 10;
    /**
     * The value of four, used in tests.
     */
    private static final int FOUR = 4;
    /**
     * The value of six, used in tests.
     */
    private static final int SIX = 6;
    /**
     * The value of seven, used in tests.
     */
    private static final int SEVEN = 7;
    /**
     * The value of eight, used in tests.
     */
    private static final int EIGHT = 8;
    /**
     * The value of nine, used in tests.
     */
    private static final int NINE = 9;
    /**
     * The value of twelve, used in tests.
     */
    private static final int TWELVE = 12;

    /**
     * Creates and returns a {@code HabitStreakTracker} with the logged days
     * that are given.
     *
     * @param days
     *            the days to log in
     * @return a new tracker with exactly the given days logged
     * @requires the given days are all different
     * @ensures createFromArgs = {days}
     */
    private HabitStreakTracker createFromArgs(int... days) {
        HabitStreakTracker tracker = new HabitStreakTracker1L();
        for (int day : days) {
            tracker.addLogDay(day);
        }
        return tracker;
    }

    /* currentStreak tests */

    /**
     * tests on an empty tracker.
     */
    @Test
    public void testCurrentStreakEmpty() {
        HabitStreakTracker tracker = this.createFromArgs();
        HabitStreakTracker expected = this.createFromArgs();
        assertEquals(0, tracker.currentStreak(TEN));
        assertEquals(expected, tracker);
    }

    /**
     * tests when today is not logged.
     */
    @Test
    public void testCurrentStreakTodayNotLogged() {
        HabitStreakTracker tracker = this.createFromArgs(THREE, FOUR, FIVE);
        HabitStreakTracker expected = this.createFromArgs(THREE, FOUR, FIVE);
        assertEquals(0, tracker.currentStreak(TEN));
        assertEquals(expected, tracker);
    }

    /**
     * tests when only today is logged.
     */
    @Test
    public void testCurrentStreakOnlyToday() {
        HabitStreakTracker tracker = this.createFromArgs(TEN);
        HabitStreakTracker expected = this.createFromArgs(TEN);
        assertEquals(1, tracker.currentStreak(TEN));
        assertEquals(expected, tracker);
    }

    /**
     * tests when the current streak stops at a gap.
     */
    @Test
    public void testCurrentStreakStopsAtGap() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE, FIVE, SIX,
                SEVEN);
        HabitStreakTracker expected = this.createFromArgs(1, 2, THREE, FIVE,
                SIX, SEVEN);
        assertEquals(THREE, tracker.currentStreak(SEVEN));
        assertEquals(expected, tracker);
    }

    /* longestStreak */

    /**
     * tests on an empty tracker.
     */
    @Test
    public void testLongestStreakEmpty() {
        HabitStreakTracker tracker = this.createFromArgs();
        HabitStreakTracker expected = this.createFromArgs();
        assertEquals(0, tracker.longestStreak());
        assertEquals(expected, tracker);
    }

    /**
     * tests with a single day.
     */
    @Test
    public void testLongestStreakOneDay() {
        HabitStreakTracker tracker = this.createFromArgs(FOUR);
        HabitStreakTracker expected = this.createFromArgs(FOUR);
        assertEquals(1, tracker.longestStreak());
        assertEquals(expected, tracker);
    }

    /**
     * tests when two days in a row are empty.
     */
    @Test
    public void testLongestStreakNoConsecutiveDays() {
        HabitStreakTracker tracker = this.createFromArgs(1, THREE, FIVE, SEVEN);
        HabitStreakTracker expected = this.createFromArgs(1, THREE, FIVE,
                SEVEN);
        assertEquals(1, tracker.longestStreak());
        assertEquals(expected, tracker);
    }

    /**
     * tests when the days were logged out of order.
     */
    @Test
    public void testLongestStreakOutOfOrder() {
        HabitStreakTracker tracker = this.createFromArgs(NINE, THREE, 1, EIGHT,
                2, TEN);
        HabitStreakTracker expected = this.createFromArgs(1, 2, THREE, EIGHT,
                NINE, TEN);
        assertEquals(THREE, tracker.longestStreak());
        assertEquals(expected, tracker);
    }

    /* firstDay */

    /**
     * tests with one day.
     */
    @Test
    public void testFirstDayOneDay() {
        HabitStreakTracker tracker = this.createFromArgs(SIX);
        HabitStreakTracker expected = this.createFromArgs(SIX);
        assertEquals(SIX, tracker.firstDay());
        assertEquals(expected, tracker);
    }

    /**
     * tests with multiple days.
     */
    @Test
    public void testFirstDaySeveral() {
        HabitStreakTracker tracker = this.createFromArgs(FIVE, -THREE, TWELVE,
                0);
        HabitStreakTracker expected = this.createFromArgs(FIVE, -THREE, TWELVE,
                0);
        assertEquals(-THREE, tracker.firstDay());
        assertEquals(expected, tracker);
    }

    /* lastDay */

    /**
     * tests with one day.
     */
    @Test
    public void testLastDayOneDay() {
        HabitStreakTracker tracker = this.createFromArgs(SIX);
        HabitStreakTracker expected = this.createFromArgs(SIX);
        assertEquals(SIX, tracker.lastDay());
        assertEquals(expected, tracker);
    }

    /**
     * tests with multiple days.
     */
    @Test
    public void testLastDaySeveral() {
        HabitStreakTracker tracker = this.createFromArgs(FIVE, -THREE, TWELVE,
                0);
        HabitStreakTracker expected = this.createFromArgs(FIVE, -THREE, TWELVE,
                0);
        assertEquals(TWELVE, tracker.lastDay());
        assertEquals(expected, tracker);
    }

    /* equals */

    /**
     * tests that the two empty trackers are equal to each other.
     */
    @Test
    public void testEqualsBothEmpty() {
        HabitStreakTracker tracker = this.createFromArgs();
        HabitStreakTracker other = this.createFromArgs();
        assertEquals(true, tracker.equals(other));
    }

    /**
     * tests that the trackers with different sizes are not equal to each other.
     */
    @Test
    public void testEqualsDifferentSize() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker other = this.createFromArgs(1, 2);
        assertEquals(false, tracker.equals(other));
    }

    /**
     * tests that if the tracker is equal to itself.
     */
    @Test
    public void testEqualsSelf() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2);
        assertEquals(true, tracker.equals(tracker));
    }

    /**
     * tests if the tracker does not equal to null.
     */
    @Test
    public void testEqualsNull() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2);
        assertEquals(false, tracker.equals(null));
    }

    /**
     * tests that the tracker does not equal an object of a different type.
     */
    @Test
    public void testEqualsDifferentType() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2);
        assertNotEquals(tracker, "{1, 2}");
    }

    /* hashCode */

    /**
     * tests that the trackers have equal hash codes.
     */
    @Test
    public void testHashCodeEqualTrackers() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker other = this.createFromArgs(THREE, 2, 1);
        assertEquals(tracker.hashCode(), other.hashCode());
    }

    /**
     * tests that the hashCode doesn't change the tracker.
     */
    @Test
    public void testHashCodeRestores() {
        HabitStreakTracker tracker = this.createFromArgs(FOUR, FIVE);
        HabitStreakTracker expected = this.createFromArgs(FOUR, FIVE);
        tracker.hashCode();
        assertEquals(expected, tracker);
    }

    /* toString */

    /**
     * tests on an empty tracker.
     */
    @Test
    public void testToStringEmpty() {
        HabitStreakTracker tracker = this.createFromArgs();
        HabitStreakTracker expected = this.createFromArgs();
        assertEquals("{}", tracker.toString());
        assertEquals(expected, tracker);
    }

    /**
     * tests on a tracker with a single day.
     */
    @Test
    public void testToStringOneDay() {
        HabitStreakTracker tracker = this.createFromArgs(FIVE);
        HabitStreakTracker expected = this.createFromArgs(FIVE);
        assertEquals("{5}", tracker.toString());
        assertEquals(expected, tracker);
    }

    /**
     * tests on a tracker with multiple days.
     */
    @Test
    public void testToStringSeveralDays() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker expected = this.createFromArgs(1, 2, THREE);
        String result = tracker.toString();
        assertEquals("{1, 2, 3}".length(), result.length());
        assertEquals(true, result.contains("1") && result.contains("2")
                && result.contains("3"));
        assertEquals(expected, tracker);
    }
}
