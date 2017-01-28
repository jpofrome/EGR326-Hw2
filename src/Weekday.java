public enum Weekday {
    MONDAY(), TUESDAY(), WEDNESDAY(), THURSDAY(), FRIDAY();

    public static Weekday fromString(String s) {
        s = s.toLowerCase();

        if (s == "m" || s == "monday") {
            return MONDAY;
        } else if (s == "t" || s == "tuesday") {
            return TUESDAY;
        } else if (s == "w" || s == "wednesday") {
            return WEDNESDAY;
        } else if (s == "r" || s == "thursday") {
            return THURSDAY;
        } else if (s == "f" || s == "friday") {
            return FRIDAY;
        } else {
            throw new IllegalArgumentException();
        }

    }

    public String toShortName() {
        if (this == MONDAY){
            return "M";
        } else if (this == TUESDAY) {
            return "T";
        } else if (this == WEDNESDAY) {
            return "W";
        } else if (this == THURSDAY) {
            return "R";
        } else if (this == FRIDAY) {
            return "F";
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        if (this == MONDAY){
            return "Monday";
        } else if (this == TUESDAY) {
            return "Tuesday";
        } else if (this == WEDNESDAY) {
            return "Wednesday";
        } else if (this == THURSDAY) {
            return "Thursday";
        } else if (this == FRIDAY) {
            return "Friday";
        } else {
            return "";
        }
    }
}