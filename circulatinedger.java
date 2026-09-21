import java.util.Arrays;

final class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
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
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}

public class CirculationLedger {

    static String branchCode;

    static {
        branchCode = "SRM-LIB";
        System.out.println("Branch code loaded");
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {
                nullSkipped++;
            } else {
                processed++;

                if (receipts[i] instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                referenceOnly + " reference-only | " +
                regular + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt(
                    "LIB-001",
                    new String[]{"BK-200"},
                    "Reading Room 3"
            ),
            null,
            new LoanReceipt(
                    "LIB-002",
                    new String[]{"BK-201"}
            )
        };

        System.out.println(
                processNightlyCirculation(receipts)
        );
    }
}