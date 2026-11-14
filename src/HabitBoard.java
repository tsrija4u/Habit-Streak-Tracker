import components.map.Map;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * a habit board that tracks several named habits at once. Unlike
 * {@code ReadingHabitDemo}, this class uses {@code HabitStreakTracker} as part
 * of its own representation: each habit name maps to its own tracker.
 */
public class HabitBoard {
    /**
     * Example "today" for the demo.
     */
    private static final int TODAY = 30;

    /**
     * Example number of days to simulate in the demo.
     */
    private static final int DAYS_TRACKED = 30;

    /**
     * Example interval for a habit done every few days in the demo.
     */
    private static final int EVERY_OTHER_DAY = 2;

    /**
     * Example day a streak was broken in the demo.
     */
    private static final int MISSED_DAY = 20;

    /**
     * Map from each habit's name to the tracker of days it was completed.
     */
    private Map<String, HabitStreakTracker> habits;

    /**
     * adds a new habit.
     *
     * @param name
     *            the name of the habit.
     */
    public void addHabit(final String name) {
        this.habits.add(name, new HabitStreakTracker1L());
    }

    /**
     * returns if name is a habit on the board.
     *
     * @param name
     *            the name of the habit
     * @return true if the name is a habit on the board.
     */
    public boolean hasHabit(final String name) {
        return this.habits.hasKey(name);
    }

    /**
     * checks off the habit name for the day.
     *
     * @param name
     *            the name of the habit
     * @param day
     *            the day it was completed
     */
    public void checkOff(final String name, final int day) {
        HabitStreakTracker tracker = this.habits.value(name);
        if (!tracker.hasLogged(day)) {
            tracker.addLogDay(day);
        }
    }

    /**
     * returns how many habits are there that were completed.
     *
     * @param day
     *            the day to check
     * @return the number of habits completed on day.
     */
    public int completedOn(final int day) {
        int count = 0;
        for (Map.Pair<String, HabitStreakTracker> habit : this.habits) {
            if (habit.value().hasLogged(day)) {
                count++;
            }
        }
        return count;
    }

    /**
     * returns the name of the habit with the longest current streak.
     *
     * @param today
     *            the day the streaks end on
     * @return the name of the habit with the longest current streak
     */
    public String bestHabit(final int today) {
        assert this.habits.size() > 0 : "Violation of: board is not empty";
        String best = "";
        int bestStreak = -1;
        for (Map.Pair<String, HabitStreakTracker> habit : this.habits) {
            int streak = habit.value().currentStreak(today);
            if (streak > bestStreak) {
                best = habit.key();
                bestStreak = streak;
            }
        }
        return best;
    }

    /**
     * prints a summary of the streaks as of today.
     *
     * @param out
     *            the output stream
     * @param today
     *            the day of the summary
     */
    public void printSummary(final SimpleWriter out, final int today) {
        for (Map.Pair<String, HabitStreakTracker> habit : this.habits) {
            HabitStreakTracker tracker = habit.value();
            out.println(habit.key() + ": current streak "
                    + tracker.currentStreak(today) + ", longest streak "
                    + tracker.longestStreak() + ", days done "
                    + tracker.size());
        }
    }

    /**
     * Main Method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        HabitBoard board = new HabitBoard();
        board.addHabit("Drink water");
        board.addHabit("Read");
        board.addHabit("Walk");

        /*
         * Simulate a month: water every day, reading every day except one
         * missed day, and walking every other day
         */
        for (int day = 1; day <= DAYS_TRACKED; day++) {
            board.checkOff("Drink water", day);
            if (day != MISSED_DAY) {
                board.checkOff("Read", day);
            }
            if (day % EVERY_OTHER_DAY == 0) {
                board.checkOff("Walk", day);
            }
        }

        out.println("=== Morning routine, day " + TODAY + " ===");
        board.printSummary(out, TODAY);
        out.println();
        out.println("Habits done on day " + MISSED_DAY + ": "
                + board.completedOn(MISSED_DAY) + " of 3");
        out.println("Habits done today: " + board.completedOn(TODAY) + " of 3");
        out.println("Best current streak: " + board.bestHabit(TODAY));

        /*
         * Add a new habit half way through
         */
        out.println();
        out.println("Adding a new habit: Stretch");
        board.addHabit("Stretch");
        board.checkOff("Stretch", TODAY);
        board.printSummary(out, TODAY);

        out.close();
    }
}
