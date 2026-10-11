# CHRONOS CI Negative Test Evidence

## Evidence ID

CI-NEG-001

## Title

Automated Mission Requirement Enforcement — Negative CI Validation

## Purpose

This evidence record documents a deliberate negative test performed against the CHRONOS Continuous Integration pipeline.

The objective was to verify that an unauthorized or incorrect modification to a defined Temporal Navigation Engine mission requirement would be detected automatically before the change could be accepted into the protected `main` branch.

## System Under Test

**Program:** CHRONOS  
**Component:** Temporal Navigation Engine (TNA)  
**Repository:** chronos-capstone  
**CI Platform:** GitHub Actions  
**Workflow:** CHRONOS TNA CI  
**Target Branch:** main  

## Requirement Under Test

The CHRONOS Temporal Navigation Engine requires a minimum mission velocity of:

**88 MPH**

This requirement is enforced in:

`src/temporal-engine/TemporalNavigationEngine.java`

The associated positive functional test is:

**TNA-TC-001 — Valid mission parameters**

The authorized mission test uses:

- Vehicle velocity: 88 MPH
- Flux energy: 1.21 GW
- Stability score: 95
- Expected authorization result: AUTHORIZED

## Test Objective

Verify that the CHRONOS CI pipeline detects a software modification that violates the established 88 MPH mission requirement.

The pipeline should fail automatically and provide objective evidence that the modified implementation does not satisfy the established requirement.

## Test Method

A dedicated Git branch was created for the negative test:

`test/negative-ci`

The Temporal Navigation Engine was intentionally modified from:

`REQUIRED_VELOCITY_MPH = 88.0`

to:

`REQUIRED_VELOCITY_MPH = 90.0`

The deliberate violation was committed using:

`test: intentionally violate TNA velocity requirement`

The branch was pushed to GitHub and submitted through a pull request targeting:

`main`

The pull request automatically triggered the CHRONOS GitHub Actions CI workflow.

## Expected Result

The authorized mission test, TNA-TC-001, continued to provide 88 MPH as the valid mission velocity.

Because the modified implementation incorrectly required 90 MPH, the Temporal Navigation Engine was expected to return:

`DENIED`

instead of:

`AUTHORIZED`

Therefore, the automated test suite and GitHub Actions workflow were expected to fail.

## Observed Result

The CHRONOS GitHub Actions workflow failed as expected.

The pull request displayed:

**All checks have failed**

The failing workflow was:

**CHRONOS TNA CI / Build and Test TNA**

The deliberate requirement violation was therefore detected automatically before the change reached the `main` branch.

## Negative Test Result

**PASS**

The expected CI failure occurred.

This confirms that the automated verification pipeline can detect a software implementation that violates a defined CHRONOS mission requirement.

## Corrective Action

After the negative test result was captured, the required velocity was restored from:

`90.0 MPH`

to:

`88.0 MPH`

The corrective change was committed using:

`test: restore TNA velocity requirement after negative CI validation`

The corrected branch was pushed to GitHub.

GitHub Actions automatically reran the CHRONOS CI workflow.

## Corrective Verification

After restoration of the correct 88 MPH requirement:

**All checks passed**

This demonstrated that:

1. The invalid implementation caused CI failure.
2. The corrected implementation satisfied the established requirement.
3. The CI pipeline returned to a successful state without manual bypass of the automated tests.

## Final Assessment

**PASS**

The CHRONOS CI pipeline successfully detected a deliberate mission requirement violation and verified the corrected implementation.

The negative test demonstrated the following control behavior:

```text
Defined Mission Requirement
        ↓
Unauthorized / Incorrect Code Change
        ↓
Pull Request
        ↓
Automated CI Execution
        ↓
Requirement Verification
        ↓
Test Failure
        ↓
Change Identified Before Acceptance
