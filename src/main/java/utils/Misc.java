package utils;

public class Misc {

    public static String getClientOS() {
        return System.getProperty("os.name").split(" ")[0] + "/Java " + System.getProperty("java.version");
    }

    public static String stringMultiply(String value, Integer multiply) {
        String r = "";

        for (int i = 0; i < multiply; i++) {
            r += value;
        }

        return r;
    }

    public static String convertMs2TimeString(long ms) {
        double mps = 1000.0;
        double spm = 60.0;
        double mph = 60.0;

        double millis = (double) ms;
        double seconds = 0.0;
        double minutes = 0.0;
        double hours = 0.0;

        while (millis >= mps) {
            seconds += 1.0;
            millis -= mps;
        }

        while (seconds >= spm) {
            minutes += 1.0;
            seconds -= spm;
        }

        while (minutes >= mph) {
            hours += 1.0;
            minutes -= mph;
        }

        String time = (hours < 10.0 ? "0" + (int) hours : (int) hours) + ":" + (minutes < 10.0 ? "0" + (int) minutes : (int) minutes) + ":" + (seconds < 10.0 ? "0" + (int) seconds : (int) seconds);

        return time;
    }
}
