# CHRONOS Dependency Scanning Gate Evidence

## Evidence ID

DS-001

## Title

CHRONOS Dependency Vulnerability Scanning CI Security Gate

## Purpose

This evidence record documents the implementation and baseline validation of automated dependency and filesystem vulnerability scanning within the CHRONOS Continuous Integration pipeline.

The objective is to identify HIGH and CRITICAL vulnerabilities before changes are accepted into the protected `main` branch.

## System Under Test

**Program:** CHRONOS  
**Repository:** chronos-capstone  
**CI Platform:** GitHub Actions  
**Security Scanner:** Trivy  
**Workflow:** CHRONOS Dependency Scan  
**Required Status Check:** Dependency Scan  
**Protected Branch:** main

## Security Requirement

CHRONOS source code and repository contents must be evaluated for known software vulnerabilities before changes are accepted into the protected development baseline.

Changes submitted for integration must successfully pass automated vulnerability scanning before they are eligible for merge into `main`.

## Implementation

A dedicated GitHub Actions workflow was created at:

`.github/workflows/dependency-scan.yml`

The workflow performs the following actions:

1. Executes automatically on pull requests targeting `main`.
2. Executes on pushes to `main`.
3. Checks out the repository.
4. Runs Trivy against the repository filesystem.
5. Evaluates HIGH and CRITICAL vulnerabilities.
6. Ignores vulnerabilities that do not currently have a fix available.
7. Returns exit code `1` when qualifying vulnerabilities are identified.
8. Reports the result as the `Dependency Scan` GitHub status check.

## Scanner Configuration

The dependency scanning workflow uses:

`aquasecurity/trivy-action`

Configured with:

```yaml
scan-type: fs
scan-ref: .
format: table
severity: HIGH,CRITICAL
ignore-unfixed: true
exit-code: '1'
