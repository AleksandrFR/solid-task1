import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrackService {
    List<Integer> tracks = new ArrayList<>();
    final String[] status = {"Формируется", "В пути", "Создан", "В городе получателя", "Утерян", "Отменён", "Возвращается",
            "Доставлен", "Утрачен службой доставки"};
    Logger logger = new ConsoleLogger();
    public void addTrack(int i) {
        tracks.add(i);
    }

    public void showStatus() {
        PrintStream ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        Random random = new Random();
        int rnd = random.nextInt(status.length);
        logger.log("Логистический статус: " + status[rnd]);
    }
}
