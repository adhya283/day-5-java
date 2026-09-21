import java.util.Arrays;

public final class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(
            int index, String newId) {

        String[] newBookIds = getBookIds();
        newBookIds[index] = newId;

        return new LoanReceipt(memberId, newBookIds);
    }

    public static void main(String[] args) {

        LoanReceipt r = new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"}
        );

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println("Original Book ID: " +
                r.getBookIds()[0]);

        LoanReceipt corrected =
                r.withCorrectedBookId(1, "BK-102");

        System.out.println("Original Receipt: " +
                Arrays.toString(r.getBookIds()));

        System.out.println("Corrected Receipt: " +
                Arrays.toString(corrected.getBookIds()));
    }
}