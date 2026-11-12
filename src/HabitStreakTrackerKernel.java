import components.standard.Standard;

/**
 *
 * HabitStreakTrackerKernel.
 */
public interface HabitStreakTrackerKernel extends Standard<HabitStreakTracker> {

    /**
     * Adds {@code day} to the set of completed habit days.
     *
     * @param day
     *            the day to log
     * @updates this
     * @requires day is not in this
     * @ensures this = #this union {day}
     */
    void addLogDay(int day);

    /**
     * Removes {@code day} from the set of completed habit days.
     *
     * @param day
     *            the day to remove
     * @updates this
     * @requires day is in this
     * @ensures this = #this \ {day}
     */
    void removeLogDay(int day);

    /**
     * Removes and returns any day from the set of completed habit days.
     *
     * @return the removed day
     * @updates this
     * @requires |this| > 0
     * @ensures removeAny is in #this and this = #this \ {removeAny}
     */
    int removeAny();

    /**
     * Returns whether {@code day} is in the set of completed habit days.
     *
     * @param day
     *            the day to check
     * @return true if {@code day} is logged, false otherwise
     * @ensures hasLogged = (day is in this)
     */
    boolean hasLogged(int day);

    /**
     * Returns the number of days in the set of completed habit days.
     *
     * @return the number of logged days
     * @ensures size = |this|
     */
    int size();

}
