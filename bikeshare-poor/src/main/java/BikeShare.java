import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BikeShare {

    public static String[] stations = {"Library", "Canteen", "Dorm"};
    public static int[] bikeLocations = {0, 0, 1, 1, 2, 2};
    public static Map<String, int[]> rides = new HashMap<>();
    public static final int STUDENT = 1;
    public static final int STAFF = 2;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("\nCommand (rent/return/quit): ");
                String command = scanner.nextLine().trim();

                if (command.equalsIgnoreCase("quit")) {
                    break;
                }

                if (command.equalsIgnoreCase("rent")) {
                    process(scanner, true);
                } else if (command.equalsIgnoreCase("return")) {
                    process(scanner, false);
                } else {
                    System.out.println("Unknown command.");
                }
            }
        }
    }

    public static void process(Scanner scanner, boolean renting) {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();

        if (memberId.isEmpty()) {
            System.out.println("Invalid member ID.");
            return;
        }

        if (renting && rides.containsKey(memberId)) {
            System.out.println("Member already has a bike.");
            return;
        }

        if (!renting && !rides.containsKey(memberId)) {
            System.out.println("No active ride.");
            return;
        }

        if (renting) {
            System.out.print("Member type (1=Student, 2=Staff): ");
            int type = readNumber(scanner);

            if (type != STUDENT && type != STAFF) {
                System.out.println("Invalid member type.");
                return;
            }

            int station = chooseStation(scanner);
            if (station < 0) {
                return;
            }

            for (int i = 0; i < bikeLocations.length; i++) {
                if (bikeLocations[i] == station) {
                    bikeLocations[i] = -1;
                    rides.put(memberId, new int[]{i, type});
                    System.out.println("Rented bike " + (i + 1));
                    return;
                }
            }

            System.out.println("No bikes available.");
        } else {
            int station = chooseStation(scanner);
            if (station < 0) {
                return;
            }

            System.out.print("Ride minutes: ");
            int minutes = readNumber(scanner);

            if (minutes <= 0) {
                System.out.println("Invalid ride time.");
                return;
            }

            int[] ride = rides.get(memberId);
            int bike = ride[0];
            int fee = minutes * (ride[1] == STUDENT ? 2 : 3);

            String record = memberId + "," + (bike + 1)
                    + "," + minutes + "," + fee;

            try (BufferedWriter writer = Files.newBufferedWriter(
                    Path.of("rides.csv"),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND)) {
                writer.write(record);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Could not save ride: " + e.getMessage());
                return;
            }

            bikeLocations[bike] = station;
            rides.remove(memberId);

            System.out.println("\n--- Receipt ---");
            System.out.println("Bike: " + (bike + 1));
            System.out.println("Minutes: " + minutes);
            System.out.println("Fee: " + fee + " baht");
        }
    }

    public static int chooseStation(Scanner scanner) {
        System.out.println("1=Library, 2=Canteen, 3=Dorm");
        System.out.print("Station: ");
        int station = readNumber(scanner) - 1;

        if (station < 0 || station >= stations.length) {
            System.out.println("Invalid station.");
            return -1;
        }

        return station;
    }

    public static int readNumber(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
