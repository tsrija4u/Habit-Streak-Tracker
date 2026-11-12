import components.set.Set;
import components.set.Set1L;

/**
 * HabitStreakTracker1L.
 *
 * @convention <pre>
 * this.loggedDays is a set of integers representing the days that have been logged
 * </pre>
 * @correspondence <pre>
 * this = this.loggedDays
 * </pre>
 * @author Srijani Tirunagiri
 */
public class HabitStreakTracker1L extends HabitStreakTrackerSecondary {

    /**
     * Representation of this HabitStreakTracker1L as a set of logged days.
     */
    private Set<Integer> loggedDays;

    /**
     * Creates a new representation of this HabitStreakTracker1L.
     */
    private void createNewRep() {
        this.loggedDays = new Set1L<>();
    }

    /* Constructors */

    /**
     * No-argument constructor.
     */
    public HabitStreakTracker1L() {
        this.createNewRep();
    }

    /* Standard methods */

    @Override
    public final HabitStreakTracker newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(HabitStreakTracker source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";

        HabitStreakTracker1L localSource = (HabitStreakTracker1L) source;
        this.loggedDays = localSource.loggedDays;
        localSource.createNewRep();
    }

    /* Kernel methods */

    @Override
    public final void addLogDay(int day) {
        assert !this.loggedDays
                .contains(day) : "Violation of: day is not in this";
        this.loggedDays.add(day);
    }

    @Override
    public final void removeLogDay(int day) {
        assert this.loggedDays.contains(day) : "Violation of: day is in this";
        this.loggedDays.remove(day);
    }

    @Override
    public final boolean hasLogged(int day) {
        return this.loggedDays.contains(day);
    }

    @Override
    public final int removeAny() {
        assert this.loggedDays.size() > 0 : "Violation of: this is not empty";
        return this.loggedDays.removeAny();
    }

    @Override
    public final int size() {
        return this.loggedDays.size();
    }
}
