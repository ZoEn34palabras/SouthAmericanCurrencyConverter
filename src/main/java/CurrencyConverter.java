import model.ConversionOption;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CurrencyConverter {

    private static final Map<Integer, ConversionOption> OPTIONS = Map.ofEntries(
            Map.entry(1, new ConversionOption("USD", "ARS", false)),
            Map.entry(2, new ConversionOption("ARS", "USD", true)),
            Map.entry(3, new ConversionOption("USD", "BOB", false)),
            Map.entry(4, new ConversionOption("BOB", "USD", true)),
            Map.entry(5, new ConversionOption("USD", "BRL", false)),
            Map.entry(6, new ConversionOption("BRL", "USD", true)),
            Map.entry(7, new ConversionOption("USD", "CLP", false)),
            Map.entry(8, new ConversionOption("CLP", "USD", true)),
            Map.entry(9, new ConversionOption("USD", "COP", false)),
            Map.entry(10, new ConversionOption("COP", "USD", true)),
            Map.entry(11, new ConversionOption("USD", "PEN", false)),
            Map.entry(12, new ConversionOption("PEN", "USD", true)),
            Map.entry(13, new ConversionOption("USD", "UYU", false)),
            Map.entry(14, new ConversionOption("UYU", "USD", true)),
            Map.entry(15, new ConversionOption("USD", "VES", false)),
            Map.entry(16, new ConversionOption("VES", "USD", true)),
            Map.entry(17, new ConversionOption("USD", "PYG", false)),
            Map.entry(18, new ConversionOption("PYG", "USD", true)),
            Map.entry(19, new ConversionOption("USD", "GYD", false)),
            Map.entry(20, new ConversionOption("GYD", "USD", true)),
            Map.entry(21, new ConversionOption("USD", "SRD", false)),
            Map.entry(22, new ConversionOption("SRD", "USD", true)),
            Map.entry(23, new ConversionOption("USD", "EUR", false)),
            Map.entry(24, new ConversionOption("EUR", "USD", true))
    );

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExchangeRateService rateService = new ExchangeRateService();
        boolean keepGoing = true;

        while (keepGoing) {
            showMenu();

            int option = getUserOption(sc);
            if (option == -1) continue;

            if (option == 25) {
                System.out.println("Thank you for using the converter. See you soon!");
                keepGoing = false;
                continue;
            }

            ConversionOption conversion = OPTIONS.get(option);
            double amount = getAmountToConvert(sc);
            double rate = rateService.getRateFor(conversion);

            if (rate == -1) {
                System.out.println("Invalid option or failed to retrieve rate.");
            } else {
                double result = amount * rate;
                System.out.printf("%.2f %s equals: %.2f %s%n",
                        amount, conversion.source(), result, conversion.target());
            }

            pauseForUser(sc);
        }

        sc.close();
    }

    private static int getUserOption(Scanner sc) {
        System.out.print("Enter a menu option (1-25): ");

        try {
            if (!sc.hasNextLine()) {
                System.out.println(" No input detected (EOF). Exiting program.");
                System.exit(0);
            }

            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(" No input entered. Please type a number.");
                pauseForUser(sc);
                return -1;
            }

            int option;
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a valid number.");
                pauseForUser(sc);
                return -1;
            }

            if (option < 1 || option > 25) {
                System.out.println(" Option out of range. Please choose between 1 and 25.");
                pauseForUser(sc);
                return -1;
            }

            return option;

        } catch (NoSuchElementException e) {
            System.out.println(" Input stream closed unexpectedly (EOF). Exiting.");
            System.exit(0);
            return -1;
        }
    }

    private static double getAmountToConvert(Scanner sc) {
        System.out.println("Enter the amount to convert: ");

        try {
            if (!sc.hasNextLine()) {
                System.out.println(" No input detected (EOF). Exiting program.");
                System.exit(0);
            }

            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(" No input entered. Please type a number.");
                pauseForUser(sc);
                return getAmountToConvert(sc);
            }

            double amount;
            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a valid numeric amount.");
                pauseForUser(sc);
                return getAmountToConvert(sc);
            }

            if (amount < 0) {
                System.out.println(" Negative amounts are not allowed.");
                pauseForUser(sc);
                return getAmountToConvert(sc);
            }

            return amount;

        } catch (NoSuchElementException e) {
            System.out.println(" Input stream closed unexpectedly (EOF). Exiting.");
            System.exit(0);
            return -1;
        }
    }

    private static void pauseForUser(Scanner sc) {
        System.out.println("Press Enter to continue...");
        sc.nextLine(); // clean buffer
        sc.nextLine(); // wait for Enter
    }

    private static void showMenu() {
        System.out.println("""
                =================================================
                       SouthAmerican Currency Converter
                =================================================
                 1)  USD     -->     ARS (Argentine Peso)
                 2)  ARS     -->     USD
                 3)  USD     -->     BOB (Bolivian Boliviano)
                 4)  BOB     -->     USD
                 5)  USD     -->     BRL (Brazilian Real)
                 6)  BRL     -->     USD
                 7)  USD     -->     CLP (Chilean Peso)
                 8)  CLP     -->     USD
                 9)  USD     -->     COP (Colombian Peso)
                10)  COP     -->     USD
                11)  USD     -->     PEN (Peruvian Sol)
                12)  PEN     -->     USD
                13)  USD     -->     UYU (Uruguayan Peso)
                14)  UYU     -->     USD
                15)  USD     -->     VES (Venezuelan Bolívar)
                16)  VES     -->     USD
                17)  USD     -->     PYG (Paraguayan Guaraní)
                18)  PYG     -->     USD
                19)  USD     -->     GYD (Guyanese Dollar)
                20)  GYD     -->     USD
                21)  USD     -->     SRD (Surinamese Dollar)
                22)  SRD     -->     USD
                23)  USD     -->     EUR (Euro)
                24)  EUR     -->     USD
                25)  Exit
                ----------------------------------------------
                Choose an option (1-25):                
                """);
    }
}
