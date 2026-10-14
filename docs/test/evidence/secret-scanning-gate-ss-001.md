# CHRONOS Secret Scanning Gate Evidence

## Evidence ID

SS-001

## Title

CHRONOS Secret Scanning CI Security Gate

## Purpose

This evidence record documents the implementation and validation of automated secret scanning within the CHRONOS Continuous Integration pipeline.

The objective is to detect committed credentials, API keys, tokens, passwords, and other sensitive material before changes are accepted into the protected `main` branch.

## System Under Test

**Program:** CHRONOS  
**Repository:** chronos-capstone  
**CI Platform:** GitHub Actions  
**Security Scanner:** Gitleaks  
**Workflow:** CHRONOS Secret Scan  
**Required Status Check:** Secret Scan  
**Protected Branch:** main  

## Security Requirement

CHRONOS source code and repository history must not contain exposed credentials or other sensitive authentication material.

Changes submitted for integration must successfully pass automated secret scanning before they are eligible for merge into the protected `main` branch.

## Implementation

A dedicated GitHub Actions workflow was created at:

`.github/workflows/secret-scan.yml`

The workflow performs the following actions:

1. Executes automatically on pull requests targeting `main`.
2. Executes on pushes to `main`.
3. Checks out the repository with full Git history.
4. Runs Gitleaks against the repository.
5. Uses read-only repository permissions.
6. Reports the result as the `Secret Scan` GitHub status check.

The workflow uses:

`actions/checkout@v7`

and:

`gitleaks/gitleaks-action@v3`

## Least-Privilege Configuration

The workflow explicitly defines:

```yaml
permissions:
  contents: read
