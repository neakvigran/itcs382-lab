import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CsvRideRepository implements RideRepository {
    private final Path path;

    public CsvRideRepository(String filename) {
        path = Path.of(filename);
    }

    @Override
    public void save(Ride ride) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(
                path,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            writer.write("%d,%s,%s,%s,%d,%d%n".formatted(
                ride.bikeId(),
                ride.memberId(),
                ride.memberType(),
                ride.returnStation(),
                ride.minutes(),
                ride.fee()
            ));
        }
    }
}
