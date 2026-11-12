/**
 * HabitStreakTrackerSecondary.
 *
 * <p>
 * Every method in this class is implemented using only the kernel methods
 * ({@code addLogDay}, {@code removeLogDay}, {@code removeAny},
 * {@code hasLogged}, {@code size}) and the {@code Standard} methods
 * ({@code clear}, {@code newInstance}, {@code transferFrom}).
 * </p>
 *
 * @author Srijani Tirunagiri
 */
public abstract class HabitStreakTrackerSecondary
        implements HabitStreakTracker {

    /**
     * Two HabitStreakTracker objects are equal if they have the same size and
     * contain the same logged days.
     */
    @Override
    public final boolean equals(Object obj) {
        boolean result;
        if (obj == this) {
            result = true;
        } else if (!(obj instanceof HabitStreakTracker)) {
            result = false;
        } else {
            HabitStreakTracker other = (HabitStreakTracker) obj;
            result = this.size() == other.size();

            if (result) {
                HabitStreakTracker temp = this.newInstance();
                while (this.size() > 0) {
                    int day = this.removeAny();
                    result = result && other.hasLogged(day);
                    /*
                     * addLogDay requires day is not in temp: holds because each
                     * day is removed from this exactly once, and this is a set
                     */
                    temp.addLogDay(day);
                }
                this.transferFrom(temp);
            }
        }
        return result;
    }

    /**
     * The hash code of a HabitStreakTracker is the sum of the logged days.
     */
    @Override
    public final int hashCode() {
        int result = 0;
        HabitStreakTracker temp = this.newInstance();
        while (this.size() > 0) {
            int day = this.removeAny();
            result += day;
            temp.addLogDay(day);
        }
        this.transferFrom(temp);
        return result;
    }

    /**
     * Returns the logged days in increasing order, e.g., "{1, 2, 3, 5}".
     */
    @Override
    public final String toString() {
        String result = "{";
        HabitStreakTracker temp = this.newInstance();
        while (this.size() > 0) {
            int day = this.removeAny();
            result += day;
            if (this.size() > 0) {
                result += ", ";
            }
            temp.addLogDay(day);
        }
        this.transferFrom(temp);
        result += "}";
        return result;
    }

    /* Secondary methods */

    @Override
    public final int currentStreak(int today) {
        int streak = 0;
        while (this.hasLogged(today - streak)) {
            streak++;
        }
        return streak;
    }

    @Override
    public final int longestStreak() {
        int longest = 0;
        /*
         * firstDay and lastDay require |this| > 0, so check before calling
         */
        if (this.size() > 0) {
            int first = this.firstDay();
            int last = this.lastDay();
            int current = 0;
            for (int day = first; day <= last; day++) {
                if (this.hasLogged(day)) {
                    current++;
                    if (current > longest) {
                        longest = current;
                    }
                } else {
                    current = 0;
                }
            }
        }
        return longest;
    }

    @Override
    public final int firstDay() {
        assert this.size() > 0 : "Violation of: |this| > 0";

        int first = Integer.MAX_VALUE;
        HabitStreakTracker temp = this.newInstance();
        while (this.size() > 0) {
            int day = this.removeAny();
            if (day < first) {
                first = day;
            }
            temp.addLogDay(day);
        }
        this.transferFrom(temp);
        return first;
    }

    @Override
    public final int lastDay() {
        assert this.size() > 0 : "Violation of: |this| > 0";

        int last = Integer.MIN_VALUE;
        HabitStreakTracker temp = this.newInstance();
        while (this.size() > 0) {
            int day = this.removeAny();
            if (day > last) {
                last = day;
            }
            temp.addLogDay(day);
        }
        this.transferFrom(temp);
        return last;
    }
}
