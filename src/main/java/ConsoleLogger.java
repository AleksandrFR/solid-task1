import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ConsoleLogger implements Logger {
    PrintStream ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    void printInConsole(String msg) {
        ps.println(msg);
    }

    @Override
    public void log(String msg) {
        printInConsole(msg);
    }

}
