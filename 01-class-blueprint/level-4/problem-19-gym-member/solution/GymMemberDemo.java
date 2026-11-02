import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class GymMemberDemo {

    public static void main(String[] args) {
        start();

        Session.me()
                .name("John Baba")
                .rollNumber("2024-CS-001")
                .degree("BSCS")
                .batch("2024")
                .section("A");

        if (!Session.me().isComplete()) {
            Console.warn("Session incomplete. Missing: " + Session.me().missingFields());
        }

        String name = readNonEmpty("Member name");
        double fee = readDouble("Monthly fee", 0.01, Double.MAX_VALUE);

        GymMember member = new GymMember();
        member.setName(name);
        member.setMonthlyFee(fee);

        Console.println();
        Console.println("Member: " + member.getName());
        Console.println("Months active: " + member.getMonthsActive()
                + ", total paid: " + rupees(member.getTotalPaid()));

        int months1 = readInt("Renew months", 1, 120);
        member.renew(months1);
        Console.println("After renew: " + member.getMonthsActive()
                + " months, total paid: " + rupees(member.getTotalPaid()));
        Console.println("isActive? " + member.isActive());
        Console.println("monthlyCost: " + rupees(member.monthlyCost()));

        int months2 = readInt("Renew again months", 1, 120);
        member.renew(months2);
        Console.println("After renew: " + member.getMonthsActive()
                + " months, total paid: " + rupees(member.getTotalPaid()));

        member.cancel();
        Console.println();
        Console.println("After cancel: " + member.getMonthsActive()
                + " months, total paid: " + rupees(member.getTotalPaid())
                + " (unchanged)");
        Console.println("isActive? " + member.isActive());
        Console.println("monthlyCost: " + rupees(member.monthlyCost()));

        Console.println();
        Console.watermark();
    }
}
