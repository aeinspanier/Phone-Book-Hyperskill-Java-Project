package phonebook;

import java.util.concurrent.TimeUnit;

public class TimeSpan {
    long minutes;
    long seconds;
    long ms;
    public TimeSpan(long timespan) {
        this.minutes = TimeUnit.MILLISECONDS.toMinutes(timespan);
        this.seconds = (TimeUnit.MILLISECONDS.toSeconds(timespan) % 60);
        this.ms = timespan - (minutes * 1000 * 60) - (seconds * 1000);
    }
}
