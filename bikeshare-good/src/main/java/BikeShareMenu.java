import java.io.IOException;
import java.util.Scanner;

public class BikeShareMenu {
    private final BikeShareService service;

    public BikeShareMenu(BikeShareService service) {
        this.service = service;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nCommand (rent/return/status/quit): ");
            if (!scanner.hasNextLine()) {
                return;
            }

            String command = scanner.nextLine().trim().toLowerCase();
            if (command.equals("quit")) {
                return;
            }

            try {
                switch (command) {
                    case "rent" -> rent(scanner);
                    case "return" -> returnBike(scanner);
                    case "status" -> status(scanner);
                    default -> System.out.println("Unknown command");
                }
            } catch (IllegalArgumentException | IllegalStateException
                     | IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private String ask(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private void rent(Scanner scanner) {
        String memberId = ask(scanner, "Member ID: ");
        MemberType type = MemberType.valueOf(
            ask(scanner, "Type (STUDENT/STAFF): ").toUpperCase()
        );
        String station = ask(scanner, "Station: ");

        int bikeId = service.rent(memberId, type, station);
        System.out.println("Rented bike #" + bikeId);
    }

    private void returnBike(Scanner scanner) throws IOException {
        String memberId = ask(scanner, "Member ID: ");
        String station = ask(scanner, "Return station: ");
        int minutes = Integer.parseInt(ask(scanner, "Minutes: "));

        Ride ride = service.returnBike(memberId, station, minutes);

        System.out.println("--- Receipt ---");
        System.out.println("Bike: " + ride.bikeId());
        System.out.println("Minutes: " + ride.minutes());
        System.out.println("Fee: " + ride.fee() + " baht");
    }

    private void status(Scanner scanner) {
        String station = ask(scanner, "Station: ");
        System.out.println("Available bikes: "
            + service.availableBikes(station));
    }
}
