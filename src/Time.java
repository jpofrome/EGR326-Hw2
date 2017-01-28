import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

public class Time {
    //variables
    private int hour;
    private int minute;
    //if PM is true it is PM, if it is false it is AM
    private boolean PM;
    private static boolean pm;
    protected static List<Time> time;

    //constructor
    public Time(int hour, int minute, boolean PM) {
        if (hour < 0 || minute < 0) {
            throw new IllegalArgumentException("Can't be below 1");
        }
        this.hour = hour;
        this.minute = minute;
        this.PM = PM;
        time = new ArrayList<Time>();
    }

    //takes String such as "12:09 PM" and converts to a time obj
    public static Time fromString(String str) {
        try {
            if ((str.charAt(6) == 'P' || str.charAt(6) == 'p') && (str.charAt(7) == 'M' || str.charAt(7) == 'm')){
                pm = true;
            } else if ((str.charAt(6) == 'A' || str.charAt(6) == 'a') && (str.charAt(7) == 'M' || str.charAt(7) == 'm')) {
                pm = false;
            }

            Time tObj = new Time(((Character.getNumericValue(str.charAt(0))*10) + (Character.getNumericValue(str.charAt(1)))),
                    ((Character.getNumericValue(str.charAt(3))*10) + (Character.getNumericValue(str.charAt(4)))), pm);
            return tObj;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    //returns copy of obj, following the contract of clone from the java api
    @Override
    public Time clone() {
        Time cloneObj;
        try {
            cloneObj = (Time) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error();
        }
        return cloneObj;
    }

    //compares two time objects
    public int compareTo(Time time){
        int thisMinT = 0;
        int thatMinT = 0;
        if (isPM() == false) {
            thisMinT += hour * 60 + minute;
        } else {
            thatMinT += hour * 60 + 720 + minute;
        }
        if (time.isPM() == false) {
            thatMinT += time.hour * 60 + time.minute;
        } else {
            thatMinT += time.hour * 60 + 720 + time.minute;
        }
        if (thisMinT == thatMinT) {
            return 0;
        } else if (thisMinT > thatMinT) {
            return 1;
        } else {
            return -1;
        }
    }

    //compare objects
    public boolean equals(Time o) {
        if (o != null && o instanceof Time) {
            return (this.getHour() == o.getHour() && this.getMinute() == o.getMinute() && this.isPM() == o.isPM());
        }
        return false;
    }

    //getters
    public int getHour() {return this.hour;}
    public int getMinute() {return this.minute;}
    public boolean isPM() {return PM;}

    //shift time by adding minutes
    public void shift(int minutes) {
        int totalMin = 0;
        if (minutes < 0){
            IllegalArgumentException e;
        } else {
            if (isPM() == false) {
                totalMin += hour * 60 + minute + minutes;
            } else {
                totalMin += hour * 60 + 720 + minute + minutes;
            }
            hour = totalMin/60;
            hour = hour%24;
            if (PM == false && hour == 0) {
                hour = 24;
                PM = false;
            } else if (PM == true && hour == 0) {
                hour = 24;
                PM = true;
            }
            if (hour < 12 && hour == 24) {
                PM = true;
            } else if (hour == 12) {
                PM = false;
            } else if (hour > 12 && hour != 24){
                PM = false;
            }
            if (hour > 12) {
                hour = hour - 12;
            }
            minute = totalMin%60;
        }
    }

    //to string in format "HH:MM AM/PM"
    @Override
    public String toString() {
        if (isPM() == false && hour != 0) {
            return (String.format("%02d", hour) + ":" + String.format("%02d", minute) + " AM");
        } else {
            return (String.format("%02d", hour) + ":" + String.format("%02d", minute) + " PM");
        }
    }
}