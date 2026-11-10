import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A class to track the streak of a habit over time. HabitStreakTracker
 */
public class HabitStreakTracker {

    /** A set to store the days when the habit was completed. */
    private Set<Integer> days;

    /** Example "today" for the demo (November 10). */
    private static final int TODAY = 10;

    /** Example day the habit was missed in the demo (November 6). */
    private static final int MISSED_DAY = 6;

    /**
     * Creates a empty representation.
     */
    private void createNewRep() {
        this.days = new Set1L<>();
    }

    /**
     * Initializes a new HabitStreakTracker object with an empty set of
     * completed habit days.
     */
    public HabitStreakTracker() {
        this.createNewRep();
    }

    /**
     * Clears the set of completed habit days, effectively resetting the habit
     * tracker.
     */
    public final void clear() {
        this.createNewRep();
    }

    /**
     * Transfers the contents of another HabitStreakTracker to this one.
     *
     * @param source
     */
    public final void transferFrom(HabitStreakTracker source) {
        this.days = source.days;
        source.createNewRep();
    }

    /* Kernel Methods */

    /**
     * Adds a day to the set of completed habit days.
     *
     * @param day
     */
    public void addLogDay(int day) {
        this.days.add(day);
    }

    /**
     * Removes a day from the set of completed habit days.
     *
     * @param day
     */
    public void removeLogDay(int day) {
        this.days.remove(day);
    }

    /**
     * Removes and returns any day from the set of completed habit days.
     *
     * @return the removed day
     */
    public final int removeAny() {
        return this.days.removeAny();
    }

    /**
     * Returns true if the specified day is in the set of completed habit days,
     * false otherwise.
     *
     * @param day
     *            the day to check
     * @return true if the specified day is in the set of completed habit days,
     *         false otherwise
     */
    public final boolean hasLogged(final int day) {
        return this.days.contains(day);
    }

    /**
     * Returns the number of days in the set of completed habit days.
     *
     * @return the number of days in the set of completed habit days
     */
    public final int size() {
        return this.days.size();
    }

    /**
     * Returns the current streak of consecutive days for the habit, starting
     * from the specified day.
     *
     * @param today
     * @return the current streak of consecutive days for the habit, starting
     *         from the specified day
     */
    public final int currentStreak(final int today) {
        int streak = 0;
        while (this.days.contains(today - streak)) {
            streak++;
        }
        return streak;
    }

    /**
     * Returns the first day in the set of completed habit days.
     *
     * @return the first logged day
     */
    public final int firstDay() {
        HabitStreakTracker temp = new HabitStreakTracker();
        int min = Integer.MAX_VALUE;
        while (this.size() > 0) {
            int day = this.removeAny();
            min = Math.min(min, day);
            temp.addLogDay(day);
        }
        this.transferFrom(temp);
        return min;
    }

    /**
     * Returns the last day in the set of completed habit days.
     *
     * @return the last logged day
     */
    public final int lastDay() {
        HabitStreakTracker temp = new HabitStreakTracker();
        int max = Integer.MIN_VALUE;
        while (this.size() > 0) {
            int day = this.removeAny();
            max = Math.max(max, day);
            temp.addLogDay(day);
        }
        this.transferFrom(temp);
        return max;
    }

    /**
     * Transfers the contents of another HabitStreakTracker to this one.
     *
     * @return the length of the longest streak
     */
    public final int longestStreak() {
        int longest = 0;
        if (this.size() > 0) {
            int first = this.firstDay();
            int last = this.lastDay();
            int currentStreak = 0;
            for (int day = first; day <= last; day++) {
                if (this.hasLogged(day)) {
                    currentStreak++;
                } else {
                    longest = Math.max(longest, currentStreak);
                    currentStreak = 0;
                }
            }
            longest = Math.max(longest, currentStreak);
        }
        return longest;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Logged days: ");
        for (int day : this.days) {
            sb.append(day).append(" ");
        }
        return sb.toString();
    }

    /* Main Method */

    /**
     * The main method to demonstrate the functionality of the
     * HabitStreakTracker class.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        HabitStreakTracker tracker = new HabitStreakTracker();
        int today = TODAY;
        int missedDay = MISSED_DAY;

        for (int d = 1; d <= today; d++) {
            if (d != missedDay) {
                tracker.addLogDay(d);
            }
        }

        out.println("habit: read for 20 minutes");
        out.println("Logged days:    " + tracker);
        out.println("Days logged:    " + tracker.size());
        out.println("Started on:     Nov " + tracker.firstDay());
        out.println("Last logged:    Nov " + tracker.firstDay());
        out.println("Read on Nov 6?  " + tracker.hasLogged(missedDay));
        out.println("Current streak: " + tracker.currentStreak(today));
        out.println("Longest streak: " + tracker.longestStreak());

        /* Fix the missed day */
        out.println();
        out.println("Oops, I forgot to log Nov 6. Fixing it...");
        tracker.addLogDay(missedDay);
        out.println("Current streak: " + tracker.currentStreak(today));
        out.println("Longest streak: " + tracker.longestStreak());

        /* Remove a log day */
        out.println();
        out.println("Actually, I haven't read yet today. Undoing...");
        tracker.removeLogDay(today);
        out.println("Streak ending today:     " + tracker.currentStreak(today));
        out.println(
                "Streak ending yesterday: " + tracker.currentStreak(today - 1));
        out.println("Longest streak:          " + tracker.longestStreak());

        /* Start a new habit tracker */
        out.println();
        out.println("Starting a new habit tracker from scratch...");
        tracker.clear();
        out.println("Days logged:    " + tracker.size());
        out.println("Longest streak: " + tracker.longestStreak());

        out.close();
    }

}
