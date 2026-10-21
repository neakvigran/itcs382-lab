import java.util.ArrayList;
import java.util.List;

public class Station {
    private final String name;
    private final List<Bike> bikes = new ArrayList<>();

    public Station(String name, int firstBikeId) {
        this.name = name;
        bikes.add(new Bike(firstBikeId));
        bikes.add(new Bike(firstBikeId + 1));
    }

    public String getName() {
        return name;
    }

    public Bike takeBike() {
        if (bikes.isEmpty()) {
            throw new IllegalStateException("No bikes available");
        }
        return bikes.remove(0);
    }

    public void addBike(Bike bike) {
        bikes.add(bike);
    }

    public int availableCount() {
        return bikes.size();
    }
}
