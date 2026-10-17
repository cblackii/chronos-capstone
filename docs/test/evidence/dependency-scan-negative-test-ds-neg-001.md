# CHRONOS Dependency Scanning Negative Test Evidence

## Evidence ID

DS-NEG-001

## Title

Dependency Vulnerability Scanning Negative Validation

## Purpose

This evidence record documents a deliberate negative security test performed against the CHRONOS dependency-scanning security gate.

The objective was to verify that a known vulnerable software dependency would be detected by Trivy and prevented from being merged into the protected `main` branch.

## System Under Test

**Program:** CHRONOS  
**Repository:** chronos-capstone  
**CI Platform:** GitHub Actions  
**Security Scanner:** Trivy  
**Workflow:** CHRONOS Dependency Scan  
**Required Status Check:** Dependency Scan  
**Protected Branch:** main  
**Test Branch:** test/negative-dependency-scan

## Security Requirement

CHRONOS source code and repository contents must be evaluated for known software vulnerabilities before changes are accepted into the protected development baseline.

Changes containing qualifying HIGH or CRITICAL vulnerabilities must fail automated security validation and be prevented from merging into `main`.

## Test Fixture

A synthetic dependency test fixture was created under:

`test-data/dependency-negative/`

The fixture contained:

- package: lodash
- version: 4.17.20

The dependency was intentionally selected to trigger vulnerability detection during security-control validation.

## Test Method

1. Created the dedicated branch `test/negative-dependency-scan`.
2. Added a Node.js dependency fixture containing lodash 4.17.20.
3. Generated the corresponding `package-lock.json`.
4. Committed the dependency fixture.
5. Pushed the branch to GitHub.
6. Opened a pull request targeting `main`.
7. Allowed the configured CHRONOS CI security controls to execute.
8. Observed the required status-check results.

## Observed Result

The pull request produced the following results:

- Build and Test TNA: PASS
- Secret Scan: PASS
- Dependency Scan: FAIL

The `Dependency Scan` check was configured as a required status check.

Because the required dependency security gate failed, GitHub disabled merge eligibility for the pull request.

## Security Control Response

The repository behaved as intended.

The control chain was:

```text
Known Vulnerable Dependency
        ↓
Pull Request
        ↓
CHRONOS Dependency Scan
        ↓
Trivy Vulnerability Analysis
        ↓
HIGH Vulnerability Detected
        ↓
Required Status Check FAIL
        ↓
MERGE BLOCKED
        ↓
Vulnerable Dependency Prevented From Entering main
