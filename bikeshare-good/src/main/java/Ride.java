public record Ride(
    int bikeId,
    String memberId,
    MemberType memberType,
    String returnStation,
    int minutes,
    int fee
) {}
