import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * HabitStreakTracker1LTest is a JUnit test fixture for
 * {@code HabitStreakTracker1L}'s constructor, kernel methods, and Standard
 * methods.
 */
public class HabitStreakTracker1LTest {

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
     * Creates and returns a new {@code HabitStreakTracker} with the given days
     * logged.
     *
     * @param days
     * @return a new {@code HabitStreakTracker} with the given days logged
     */
    private HabitStreakTracker createFromArgs(int... days) {
        HabitStreakTracker tracker = new HabitStreakTracker1L();
        for (int day : days) {
            tracker.addLogDay(day);
        }
        return tracker;
    }

    /* Constructor tests */

    /**
     * Tests the no-argument constructor.
     */
    @Test
    public void testNoArgumentConstructor() {
        HabitStreakTracker tracker = new HabitStreakTracker1L();
        HabitStreakTracker expected = this.createFromArgs();
        assertEquals(0, tracker.size());
        assertEquals(expected, tracker);
    }

    /* addLogDay tests */

    /**
     * Tests adding a log day to an empty tracker.
     */
    @Test
    public void testAddLogDayToEmpty() {
        HabitStreakTracker tracker = this.createFromArgs();
        HabitStreakTracker expected = this.createFromArgs(FIVE);
        tracker.addLogDay(FIVE);
        assertEquals(expected, tracker);
    }

    /**
     * Tests adding a log day to a non-empty tracker.
     */
    @Test
    public void testAddLogDayToNonEmpty() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker expected = this.createFromArgs(1, 2, THREE, TEN);
        tracker.addLogDay(TEN);
        assertEquals(expected, tracker);
    }

    /**
     * Tests adding extreme values as log days to a tracker.
     */
    @Test
    public void testAddLogDayExtremeValues() {
        HabitStreakTracker tracker = this.createFromArgs();
        HabitStreakTracker expected = this.createFromArgs(Integer.MIN_VALUE,
                Integer.MAX_VALUE);
        tracker.addLogDay(Integer.MIN_VALUE);
        tracker.addLogDay(Integer.MAX_VALUE);
        assertEquals(expected, tracker);
    }

    /* removeLogDay tests */

    /**
     * Tests removing a log day from an empty tracker.
     */
    @Test
    public void testRemoveLogDayFromEmpty() {
        HabitStreakTracker tracker = this.createFromArgs(FIVE);
        HabitStreakTracker expected = this.createFromArgs();
        tracker.removeLogDay(FIVE);
        assertEquals(expected, tracker);
    }

    /**
     * Tests removing a log day from a non-empty tracker.
     */
    @Test
    public void testRemoveLogDayFromNonEmpty() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE, FOUR);
        HabitStreakTracker expected = this.createFromArgs(1, 2, FOUR);
        tracker.removeLogDay(THREE);
        assertEquals(expected, tracker);
    }

    /* removeAny */

    /**
     * Tests on a tracker with a single day.
     */
    @Test
    public void testRemoveAnyOneDay() {
        HabitStreakTracker tracker = this.createFromArgs(FOUR);
        HabitStreakTracker expected = this.createFromArgs();
        int removed = tracker.removeAny();
        assertEquals(FOUR, removed);
        assertEquals(expected, tracker);
    }

    /**
     * Tests on a tracker with multiple days.
     */
    @Test
    public void testRemoveAnyMultipleDays() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker expected = this.createFromArgs(1, 2, THREE);
        int removed = tracker.removeAny();
        assertEquals(THREE, removed);
        assertEquals(expected, tracker);
    }

    /* hasLogged tests */

    /**
     * Tests on an empty tracker.
     */
    @Test
    public void testHasLoggedEmpty() {
        HabitStreakTracker tracker = this.createFromArgs();
        HabitStreakTracker expected = this.createFromArgs();
        assertEquals(false, tracker.hasLogged(FOUR));
        assertEquals(expected, tracker);
    }

    /**
     * Tests if a day is logged.
     */
    @Test
    public void testHasLoggedTrue() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker expected = this.createFromArgs(1, 2, THREE);
        assertEquals(true, tracker.hasLogged(THREE));
        assertEquals(expected, tracker);
    }

    /**
     * Tests if a day is not logged.
     */
    @Test
    public void testHasLoggedFalse() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker expected = this.createFromArgs(1, 2, THREE);
        assertEquals(false, tracker.hasLogged(FOUR));
        assertEquals(expected, tracker);
    }

    /* size tests */

    /**
     * Tests on an empty tracker.
     */
    @Test
    public void testSizeEmpty() {
        HabitStreakTracker tracker = this.createFromArgs();
        HabitStreakTracker expected = this.createFromArgs();
        assertEquals(0, tracker.size());
        assertEquals(expected, tracker);
    }

    /**
     * Tests on a tracker with multiple days.
     */
    @Test
    public void testSizeNonEmpty() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker expected = this.createFromArgs(1, 2, THREE);
        assertEquals(THREE, tracker.size());
        assertEquals(expected, tracker);
    }

    /* Standard tests */

    /**
     * Tests on an empty tracker.
     */
    @Test
    public void testClearEmpty() {
        HabitStreakTracker tracker = this.createFromArgs();
        HabitStreakTracker expected = this.createFromArgs();
        tracker.clear();
        assertEquals(expected, tracker);
    }

    /**
     * Tests on a tracker with multiple days.
     */
    @Test
    public void testClearNonEmpty() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker expected = this.createFromArgs();
        tracker.clear();
        assertEquals(expected, tracker);
    }

    /**
     * Tests that the newInstance returns a new, empty tracker of the same class
     * and leaves the original unchanged.
     */
    @Test
    public void testNewInstance() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker expected = this.createFromArgs();
        HabitStreakTracker actual = tracker.newInstance();
        assertEquals(expected, actual);
    }

    /**
     * Tests on an empty tracker.
     */
    @Test
    public void testTransferFromIntoEmpty() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker expected = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker actual = this.createFromArgs();
        actual.transferFrom(tracker);
        assertEquals(expected, actual);
    }

    /**
     * Tests on a tracker with multiple days.
     */
    @Test
    public void testTransferFromIntoNonEmpty() {
        HabitStreakTracker tracker = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker expected = this.createFromArgs(1, 2, THREE);
        HabitStreakTracker actual = this.createFromArgs(FOUR);
        actual.transferFrom(tracker);
        assertEquals(expected, actual);
    }
}
