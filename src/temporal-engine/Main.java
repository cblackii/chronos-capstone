import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TemporalNavigationEngine engine =
                new TemporalNavigationEngine();

        System.out.println();
        System.out.println("======================================");
        System.out.println("      CHRONOS MISSION SYSTEM");
        System.out.println("   Temporal Navigation Engine v0.1");
        System.out.println("======================================");
        System.out.println();

        try {
            System.out.print(
                    "Origin [yyyy-MM-ddTHH:mm:ss]: ");
            LocalDateTime origin =
                    LocalDateTime.parse(
                            scanner.nextLine(),
                            FORMATTER
                    );

            System.out.print(
                    "Destination [yyyy-MM-ddTHH:mm:ss]: ");
            LocalDateTime destination =
                    LocalDateTime.parse(
                            scanner.nextLine(),
                            FORMATTER
                    );

            System.out.print(
                    "Vehicle velocity (MPH): ");
            double velocity =
                    Double.parseDouble(scanner.nextLine());

            System.out.print(
                    "Available flux energy (GW): ");
            double fluxEnergy =
                    Double.parseDouble(scanner.nextLine());

            System.out.print(
                    "Temporal stability score (0-100): ");
            double stability =
                    Double.parseDouble(scanner.nextLine());

            TemporalNavigationEngine.NavigationSolution solution =
                    engine.calculate(
                            origin,
                            destination,
                            velocity,
                            fluxEnergy,
                            stability
                    );

            System.out.println();
            System.out.println("========== MISSION SOLUTION ==========");

            System.out.println(
                    "Temporal Vector:       "
                            + solution.temporalDirection()
            );

            System.out.printf(
                    "Temporal Distance:     %.2f years%n",
                    solution.temporalDistanceYears()
            );

            System.out.printf(
                    "Required Energy:       %.2f GW%n",
                    solution.requiredEnergyGw()
            );

            System.out.println(
                    "Transition Window:     "
                            + solution.transitionWindow()
            );

            System.out.printf(
                    "Flux Calibration:      %.2f%%%n",
                    solution.fluxCalibration()
            );

            System.out.printf(
                    "Stability Score:       %.2f%%%n",
                    solution.stabilityScore()
            );

            System.out.println(
                    "Mission Authorization: "
                            + solution.authorizationResult()
            );

            System.out.println("======================================");

        } catch (Exception exception) {

            System.err.println();
            System.err.println(
                    "CHRONOS ERROR: Invalid mission parameters."
            );

            System.err.println(
                    "Reason: " + exception.getMessage()
            );
        }
    }
}
