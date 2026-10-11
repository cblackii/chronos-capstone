import java.time.LocalDateTime;

public class TemporalNavigationEngineTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        TemporalNavigationEngine engine =
                new TemporalNavigationEngine();

        testAuthorizedMission(engine);
        testLowVelocityDenied(engine);
        testLowEnergyDenied(engine);
        testLowStabilityDenied(engine);
        testSameOriginDestinationDenied(engine);

        System.out.println();
        System.out.println("======================================");
        System.out.println("        CHRONOS TEST SUMMARY");
        System.out.println("======================================");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("======================================");

        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void testAuthorizedMission(
            TemporalNavigationEngine engine) {

        var solution = engine.calculate(
                LocalDateTime.of(1985, 10, 26, 1, 20),
                LocalDateTime.of(1955, 11, 5, 6, 0),
                88,
                1.21,
                95
        );

        assertEquals(
                "AUTHORIZED",
                solution.authorizationResult(),
                "Authorized mission"
        );
    }

    private static void testLowVelocityDenied(
            TemporalNavigationEngine engine) {

        var solution = engine.calculate(
                LocalDateTime.of(1985, 10, 26, 1, 20),
                LocalDateTime.of(1955, 11, 5, 6, 0),
                75,
                1.21,
                95
        );

        assertEquals(
                "DENIED",
                solution.authorizationResult(),
                "Low velocity denied"
        );
    }

    private static void testLowEnergyDenied(
            TemporalNavigationEngine engine) {

        var solution = engine.calculate(
                LocalDateTime.of(1985, 10, 26, 1, 20),
                LocalDateTime.of(1955, 11, 5, 6, 0),
                88,
                1.00,
                95
        );

        assertEquals(
                "DENIED",
                solution.authorizationResult(),
                "Low energy denied"
        );
    }

    private static void testLowStabilityDenied(
            TemporalNavigationEngine engine) {

        var solution = engine.calculate(
                LocalDateTime.of(1985, 10, 26, 1, 20),
                LocalDateTime.of(1955, 11, 5, 6, 0),
                88,
                1.21,
                60
        );

        assertEquals(
                "DENIED",
                solution.authorizationResult(),
                "Low stability denied"
        );
    }

    private static void testSameOriginDestinationDenied(
            TemporalNavigationEngine engine) {

        LocalDateTime timestamp =
                LocalDateTime.of(1985, 10, 26, 1, 20);

        var solution = engine.calculate(
                timestamp,
                timestamp,
                88,
                1.21,
                95
        );

        assertEquals(
                "DENIED",
                solution.authorizationResult(),
                "Same origin and destination denied"
        );
    }

    private static void assertEquals(
            String expected,
            String actual,
            String testName) {

        if (expected.equals(actual)) {
            System.out.println("[PASS] " + testName);
            passed++;
        } else {
            System.out.println(
                    "[FAIL] "
                            + testName
                            + " | expected="
                            + expected
                            + " actual="
                            + actual
            );
            failed++;
        }
    }
}
