package rest.example;

public class ExampleClass {

    private final long id;
    private final String content;

    public ExampleClass(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}