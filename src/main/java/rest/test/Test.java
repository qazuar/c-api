package rest.test;

public class Test {

    private String input;
    private String output;

    public Test(String input) {
        this.input = input;
        generateOutput();
    }

    private void generateOutput() {
        output = "You gave me '" + input + "' and i hate it";
    }

    public String getOutput() {
        return output;
    }
}
