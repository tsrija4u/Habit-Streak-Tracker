/**
 * {@code HabitStreakTrackerKernel} enhanced with secondary methods.
 */
public interface HabitStreakTracker extends HabitStreakTrackerKernel {

    /**
     * Returns the current streak of completed habit days, given {@code today}.
     *
     * @param today
     * @return the lenghth of the current streak
     */
    int currentStreak(int today);

    /**
     * Returns the longest streak of completed habit days.
     *
     * @return the length of the longest streak
     * @ensures longestStreak = the length of the longest streak of completed
     *          habit days
     */
    int longestStreak();

    /**
     * Returns the first day of the habit tracking period.
     *
     * @return the first day
     */
    int firstDay();

    /**
     * Returns the last day of the habit tracking period.
     *
     * @return the last day
     */
    int lastDay();

}
