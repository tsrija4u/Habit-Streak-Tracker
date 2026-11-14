import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * a demo of where a client program that uses {@code HabitStreakTracker}
 * directly to track a daily reading habit over one month and print a progress
 * report.
 */
public final class ReadingHabitDemo {

    /**
     * Number of days in a week, used for the weekly milestone.
     */
    private static final int DAYS_PER_WEEK = 7;

    /**
     * First day of the first reading run in the demo.
     */
    private static final int FIRST_RUN_START = 1;

    /**
     * Last day of the first reading run in the demo.
     */
    private static final int FIRST_RUN_END = 12;

    /**
     * The day the habit was missed in the demo.
     */
    private static final int MISSED_DAY = 13;

    /**
     * Last day of the second reading run in the demo (also "today").
     */
    private static final int SECOND_RUN_END = 16;

    /**
     * a private constructor.
     */
    private ReadingHabitDemo() {
    }

    /**
     * returns a readable label for a day.
     *
     * @param day
     *            the day of the month
     * @return the label for day
     */
    private static String label(final int day) {
        return "Nov " + day;
    }

    /**
     * logs every day from the start till the end.
     *
     * @param tracker
     *            the tracker to log in
     * @param start
     *            the first day
     * @param end
     *            the last day
     */
    private static void logRange(final HabitStreakTracker tracker,
            final int start, final int end) {
        for (int day = start; day <= end; day++) {
            if (!tracker.hasLogged(day)) {
                tracker.addLogDay(day);
            }
        }
    }

    /**
     * returns a short message for the current streak.
     *
     * @param streak
     *            the length of the current streak
     * @return a message describing about the streak
     */
    private static String getMessage(final int streak) {
        String message;
        if (streak == 0) {
            message = "There is no streak today. Start a new one.";
        } else if (streak < DAYS_PER_WEEK) {
            message = (DAYS_PER_WEEK - streak)
                    + " more day(s) until a one-weekstreak.";
        } else {
            message = "Entire week! Keep it going!";
        }
        return message;
    }

    /**
     * prints a report for the tracker as of today.
     *
     * @param out
     *            the output stream
     * @param habit
     *            the name of the habit
     * @param tracker
     *            the tracker to report on
     * @param today
     *            the day of the report
     */
    private static void printReport(final SimpleWriter out, final String habit,
            final HabitStreakTracker tracker, final int today) {
        int current = tracker.currentStreak(today);
        out.println("=== " + habit + " report for " + label(today) + " ===");
        out.println("Started on:      " + label(tracker.firstDay()));
        out.println("Last logged:     " + label(tracker.lastDay()));
        out.println("Days logged:     " + tracker.size());
        out.println("Current streak:  " + current);
        out.println("Longest streak:  " + tracker.longestStreak());
        out.println(getMessage(current));
        out.println();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments.
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        HabitStreakTracker reading = new HabitStreakTracker1L();

        // reads everyday, but misses a day and starts again.
        logRange(reading, FIRST_RUN_START, FIRST_RUN_END);
        logRange(reading, MISSED_DAY + 1, SECOND_RUN_END);
        int today = SECOND_RUN_END;
        printReport(out, "Read 20 minutes", reading, today);

        //next day, if the streak is still going before reading
        int tomorrow = today + 1;
        out.println("Next morning (" + label(tomorrow) + "):");
        out.println("Logged today yet? " + reading.hasLogged(tomorrow));
        out.println("Streak if I stop now: " + reading.currentStreak(tomorrow));
        out.println("Streak as of yesterday: " + reading.currentStreak(today));
        out.println();

        // reading that day.
        reading.addLogDay(tomorrow);
        reading.addLogDay(MISSED_DAY);
        printReport(out, "Read 20 minutes", reading, tomorrow);

        out.close();
    }

}
