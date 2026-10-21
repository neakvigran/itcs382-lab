import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BikeShareService {
    private final Map<String, Station> stations = new HashMap<>();
    private final Map<String, Bike> rentals = new HashMap<>();
    private final FeeCalculator calculator;
    private final RideRepository repository;

    public BikeShareService(
            List<Station> stations,
            FeeCalculator calculator,
            RideRepository repository) {

        for (Station station : stations) {
            this.stations.put(station.getName().toLowerCase(), station);
        }
        this.calculator = calculator;
        this.repository = repository;
    }

    private Station findStation(String name) {
        Station station = stations.get(name.toLowerCase());
        if (station == null) {
            throw new IllegalArgumentException("Unknown station");
        }
        return station;
    }

    public int rent(String memberId, MemberType type, String stationName) {
        if (memberId.isBlank() || memberId.contains(",")) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        if (rentals.containsKey(memberId)) {
            throw new IllegalStateException("Member already has a bike");
        }

        Bike bike = findStation(stationName).takeBike();
        bike.rent(memberId, type);
        rentals.put(memberId, bike);
        return bike.getId();
    }

    public Ride returnBike(
            String memberId, String stationName, int minutes)
            throws IOException {

        Station station = findStation(stationName);
        Bike bike = rentals.get(memberId);

        if (bike == null) {
            throw new IllegalArgumentException("No active rental");
        }

        int fee = calculator.calculate(bike.getMemberType(), minutes);
        Ride ride = new Ride(
            bike.getId(), memberId, bike.getMemberType(),
            station.getName(), minutes, fee
        );

        repository.save(ride);

        rentals.remove(memberId);
        bike.release();
        station.addBike(bike);
        return ride;
    }

    public int availableBikes(String stationName) {
        return findStation(stationName).availableCount();
    }
}
