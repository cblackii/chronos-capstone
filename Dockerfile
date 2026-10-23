# ============================================================
# CHRONOS Temporal Navigation Engine
# Multi-Stage Java 21 Container Build
# ============================================================


# ------------------------------------------------------------
# Stage 1: Build
# ------------------------------------------------------------
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /build

# Copy CHRONOS source code
COPY src/temporal-engine/Main.java .
COPY src/temporal-engine/TemporalNavigationEngine.java .

# Compile Java source
RUN mkdir -p /build/classes && \
    javac \
      -d /build/classes \
      Main.java \
      TemporalNavigationEngine.java


# ------------------------------------------------------------
# Stage 2: Runtime
# ------------------------------------------------------------
FROM eclipse-temurin:21-jre-alpine

# ------------------------------------------------------------
# Apply latest Alpine security updates
#
# apk update:
#   Refreshes the Alpine package repository metadata.
#
# apk upgrade:
#   Upgrades installed operating-system packages to currently
#   available patched versions.
#
# --no-cache:
#   Prevents unnecessary APK cache data from remaining in the
#   final container image.
# ------------------------------------------------------------
RUN apk update && \
    apk upgrade --no-cache && \
    rm -rf /var/cache/apk/*


# ------------------------------------------------------------
# Application Runtime Directory
# ------------------------------------------------------------
WORKDIR /app


# ------------------------------------------------------------
# Copy compiled Java classes from builder stage
# ------------------------------------------------------------
COPY --from=builder /build/classes /app/classes


# ------------------------------------------------------------
# Create dedicated non-root CHRONOS runtime account
#
# Security objective:
#   The application must not execute as root.
#
# UID 10001 provides an explicit non-root identity that can
# later align with Kubernetes securityContext configuration.
# ------------------------------------------------------------
RUN addgroup -S chronos && \
    adduser \
      -S \
      -u 10001 \
      -G chronos \
      chronos


# ------------------------------------------------------------
# Drop root privileges
# ------------------------------------------------------------
USER chronos


# ------------------------------------------------------------
# CHRONOS Runtime
#
# Executes:
#
#   java -cp /app/classes Main
#
# ------------------------------------------------------------
ENTRYPOINT ["java", "-cp", "/app/classes", "Main"]