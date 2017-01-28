import java.util.Set;
import java.util.TreeSet;

public class Course {
    //variables
    private String name;
    private int credits;
    private int duration;
    Time startTime;
    private Set<Weekday> days;


    //constructor
    public Course(String name, int credits, Set<Weekday> days, Time startTime, int duration) {
        if (days == null || credits < 1 || credits > 5) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.credits = credits;
        this.duration = duration;
        this.startTime = startTime;
        this.days = new TreeSet<Weekday>(days);
    }

    //checks if there is a schedueling conflict
    public boolean conflictsWith(Course course) {
        Time begin = getStartTime();
        Time finish = getEndTime();
        Set<Weekday> days = course.days;

        for (Weekday weekday: days) {
            boolean start = contains(weekday,getStartTime());
            boolean end = contains(weekday, getEndTime());
            if (start || end) {
                return true;
            }
        }
        return false;
    }

    //see if this time is in a class time
    public boolean contains (Weekday day, Time time) {
        if (days.contains(day)) {
            if (startTime.equals(time)) {
                return true;
            } else if (startTime.compareTo(time) < 0 && getEndTime().compareTo(time) > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean equals(Course o) {
        if (o != null && o instanceof Course) {
            return this.days.equals(o.days) && this.startTime.equals(o.startTime) &&
                    this.duration == o.duration && this.credits == o.credits &&
                    this.name.equals(o.getName());
        }
        return false;
    }

    //getters
    public int getCredits() {return credits;}
    public String getName() {return name;}
    public int getDuration() {return duration;}
    public Time getStartTime() {return startTime;}

    public Time getEndTime() {
        Time endTime = startTime;
        endTime.shift(duration);
        return endTime;
    }

    public String toString() {
        String weekDay = "";
        for (Weekday w: this.days) {
            weekDay += w.toShortName();
        }
        return this.getName() + "," + this.getCredits() + "," + weekDay + "," + this.getStartTime()
                + "," + this.getDuration();
    }


}
