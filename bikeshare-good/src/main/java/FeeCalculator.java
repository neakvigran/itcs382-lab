public class FeeCalculator {
    public int calculate(MemberType type, int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("Minutes must be positive");
        }
        return Math.multiplyExact(type.getRate(), minutes);
    }
}
