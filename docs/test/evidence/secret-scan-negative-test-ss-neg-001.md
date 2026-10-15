# CHRONOS Secret Scanning Negative Test Evidence

## Evidence ID

SS-NEG-001

## Title

Credential Push Protection and Secret Scanning Negative Validation

## Purpose

This evidence record documents a deliberate negative security test performed against the CHRONOS repository.

The objective was to verify that synthetic credential material resembling AWS credentials would be detected and blocked before it could be accepted into the remote repository.

This test validates the repository's credential-protection controls and demonstrates that secret exposure is prevented before integration into the protected development baseline.

## System Under Test

**Program:** CHRONOS  
**Repository:** chronos-capstone  
**CI Platform:** GitHub Actions  
**Security Scanner:** Gitleaks  
**Repository Security Control:** GitHub Push Protection  
**Protected Branch:** main  
**Test Branch:** test/negative-secret-scan

## Security Requirement

CHRONOS source code and repository history must not contain exposed credentials, API keys, tokens, passwords, or other sensitive authentication material.

Credential material introduced during development must be detected before it becomes part of the accepted remote repository baseline.

## Test Objective

Verify that recognizable synthetic AWS credentials are detected during repository push activity and rejected before being uploaded to GitHub.

The expected control behavior was:

1. Create synthetic AWS credential material.
2. Commit the material locally.
3. Attempt to push the commit to the remote test branch.
4. Confirm automated credential detection.
5. Confirm GitHub rejects the push.
6. Confirm the sensitive commit never becomes part of the remote repository.

## Synthetic Test Credential

The test used intentionally fabricated AWS-style credentials created solely for security-control validation.

The credentials were not associated with any real AWS account, user, role, workload, or environment.

The test file was:

`test-data/fake-secret.txt`

Credential types represented:

- Amazon AWS Access Key ID
- Amazon AWS Secret Access Key

## Test Method

A synthetic credential file was created on the dedicated test branch:

`test/negative-secret-scan`

The credential material was staged and committed locally.

A push was then attempted using the configured Git remote.

The push triggered GitHub repository credential protection controls.

## Observed Result

The push was rejected by GitHub.

The remote server returned:

`GH013: Repository rule violations found`

GitHub Push Protection identified both credential patterns:

- Amazon AWS Access Key ID
- Amazon AWS Secret Access Key

The remote additionally reported:

`Push cannot contain secrets`

The detected locations referenced the synthetic credential file within the attempted commit.

## Security Control Response

The repository behaved as intended.

The control chain was:

```text
Synthetic AWS Credential
        ↓
Local Commit
        ↓
git push
        ↓
GitHub Push Protection
        ↓
AWS Credential Pattern Detected
        ↓
Repository Rule Violation
        ↓
PUSH REJECTED
        ↓
Credential Prevented From Reaching Remote Repository
