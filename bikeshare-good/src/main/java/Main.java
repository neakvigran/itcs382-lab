import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Station> stations = List.of(
            new Station("Library", 1),
            new Station("Canteen", 3),
            new Station("Dorm", 5)
        );

        RideRepository repository = new CsvRideRepository("rides.csv");
        BikeShareService service = new BikeShareService(
            stations, new FeeCalculator(), repository
        );

        new BikeShareMenu(service).run();
    }
}
