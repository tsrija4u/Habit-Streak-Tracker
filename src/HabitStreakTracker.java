import java.util.Set;

import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class HabitStreakTracker {

    /** A set to store the days when the habit was completed. */
    private Set<Integer> days;

    /** Initializes a new instance of the HabitStreakTracker class. */
    public HabitStreakTracker() {
        this.days = new Set1L<>();
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
     * Converts a string representation of a day to an integer.
     * 
     * @param date
     * @return the day number as an integer
     */
    private static int toDay(final String date) {
        return Integer.parseInt(date);
    }

    /**
     * Converts an integer representation of a day to a string.
     * 
     * @param day
     * @return the day number as a string
     */
    private static String toDate(final int day) {
        return Integer.toString(day);
    }

    private static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        HabitStreakTracker tracker = new HabitStreakTracker();
        int today = toDay("2024-06-01"); // Example: June 1, 2024
        int missedDay = toDay("2024-05-30"); // Example: May 30, 2024

        for (int d = today; d >= missedDay; d--) {
            tracker.addLogDay(d);
        }

        out.println("habit: read for 20 minutes");
        out.println("Logged days:    " + tracker);
        out.println("Days logged:    " + tracker.size());
        out.println("Started on:     " + toDate(tracker.firstDay()));
        out.println("Last logged:    " + toDate(tracker.lastDay()));
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
    }

}
