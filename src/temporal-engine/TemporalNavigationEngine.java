import java.time.Duration;
import java.time.LocalDateTime;

public class TemporalNavigationEngine {

    private static final double REQUIRED_VELOCITY_MPH = 90.0;
    private static final double BASE_FLUX_ENERGY_GW = 1.21;
    private static final double MIN_STABILITY_SCORE = 75.0;

    public NavigationSolution calculate(
            LocalDateTime origin,
            LocalDateTime destination,
            double velocityMph,
            double availableFluxEnergyGw,
            double stabilityScore) {

        long temporalOffsetSeconds =
                Duration.between(origin, destination).getSeconds();

        String temporalDirection;

        if (temporalOffsetSeconds < 0) {
            temporalDirection = "PAST";
        } else if (temporalOffsetSeconds > 0) {
            temporalDirection = "FUTURE";
        } else {
            temporalDirection = "PRESENT";
        }

        double temporalDistanceYears =
                Math.abs(temporalOffsetSeconds) / 31_557_600.0;

        double fluxCalibration =
                Math.min(100.0, 88.0 + (temporalDistanceYears * 0.10));

        String transitionWindow;

        if (stabilityScore >= 90) {
            transitionWindow = "OPTIMAL";
        } else if (stabilityScore >= MIN_STABILITY_SCORE) {
            transitionWindow = "ACCEPTABLE";
        } else {
            transitionWindow = "UNSTABLE";
        }

        boolean velocityAuthorized =
                velocityMph >= REQUIRED_VELOCITY_MPH;

        boolean energyAuthorized =
                availableFluxEnergyGw >= BASE_FLUX_ENERGY_GW;

        boolean stabilityAuthorized =
                stabilityScore >= MIN_STABILITY_SCORE;

        boolean destinationAuthorized =
                temporalOffsetSeconds != 0;

        boolean missionAuthorized =
                velocityAuthorized &&
                energyAuthorized &&
                stabilityAuthorized &&
                destinationAuthorized;

        String authorizationResult =
                missionAuthorized
                        ? "AUTHORIZED"
                        : "DENIED";

        return new NavigationSolution(
                temporalDirection,
                temporalDistanceYears,
                BASE_FLUX_ENERGY_GW,
                transitionWindow,
                fluxCalibration,
                stabilityScore,
                authorizationResult
        );
    }

    public record NavigationSolution(
            String temporalDirection,
            double temporalDistanceYears,
            double requiredEnergyGw,
            String transitionWindow,
            double fluxCalibration,
            double stabilityScore,
            String authorizationResult
    ) {}
}
