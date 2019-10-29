package rest.status;

import utils.Misc;

import java.time.LocalDateTime;

public class Status {

    private final long id;
    private String status = "Server is running";
    private LocalDateTime generated = LocalDateTime.now();
    private String uptime;

    public Status(long id, long startup) {
        this.id = id;
        this.uptime = Misc.convertMs2TimeString(System.currentTimeMillis() - startup);
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getGenerated() {
        return generated;
    }

    public String getUptime() {
        return uptime;
    }
}
