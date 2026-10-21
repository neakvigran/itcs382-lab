public class Bike {
    private final int id;
    private String memberId;
    private MemberType memberType;

    public Bike(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return memberId == null;
    }

    public void rent(String memberId, MemberType type) {
        if (!isAvailable()) {
            throw new IllegalStateException("Bike already rented");
        }
        this.memberId = memberId;
        this.memberType = type;
    }

    public String getMemberId() {
        return memberId;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void release() {
        memberId = null;
        memberType = null;
    }
}
