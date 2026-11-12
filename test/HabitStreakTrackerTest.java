import static org.junit.Assert.assertEquals;

import org.junit.Test;

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

    private HabitStreakTracker createFromArgs(int... days) {
        HabitStreakTracker tracker = new HabitStreakTracker1L();
        for (int day : days) {
            tracker.addLogDay(day);
        }
        return tracker;
    }

    /* currentStreak tests */
    @Test
    public void testCurrentStreakEmpty() {
        HabitStreakTracker tracker = this.createFromArgs();
        HabitStreakTracker expected = this.createFromArgs();
        assertEquals(0, tracker.currentStreak(TEN));
        assertEquals(expected, tracker);
    }

    @Test
    public void testCurrentStreakTodayNotLogged() {
        HabitStreakTracker tracker = this.createFromArgs(THREE, FOUR, FIVE);
        HabitStreakTracker expected = this.createFromArgs(THREE, FOUR, FIVE);
        assertEquals(0, tracker.currentStreak(TEN));
        assertEquals(expected, tracker);
    }

    @Test
    public void testCurrentStreakOnlyToday() {
        HabitStreakTracker tracker = this.createFromArgs(TEN);
        HabitStreakTracker expected = this.createFromArgs(TEN);
        assertEquals(1, tracker.currentStreak(TEN));
        assertEquals(expected, tracker);
    }

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

    @Test
    public void testLongest

}
