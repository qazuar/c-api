package rest.status;

public class Status {

    private final long id;
    private String status = "Server is running";

    public Status(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
