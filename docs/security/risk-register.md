# CHRONOS Risk Register

## Purpose

This risk register captures initial mission and cybersecurity risks associated with protecting the CHRONOS Temporal Navigation capability.

Each risk will be traced to security requirements, countermeasures, verification activities, and evidence as the project evolves.

## Risk Rating Method

Likelihood:
- LOW
- MODERATE
- HIGH

Impact:
- LOW
- MODERATE
- HIGH
- CRITICAL

Risk is evaluated primarily from mission impact rather than simple technical severity.

---

## CHR-RISK-001 — Temporal Navigation Algorithm Disclosure

**Asset:** Temporal Navigation Algorithm (TNA)

**Threat:** Unauthorized actor obtains CHRONOS CPI.

**Vulnerability:**
- Excessive repository access
- Weak identity controls
- Unprotected source or artifacts
- Runtime extraction

**Mission Impact:** CRITICAL

Disclosure could allow an adversary to reproduce, analyze, or counter the CHRONOS mission capability.

**Initial Likelihood:** MODERATE  
**Initial Risk:** HIGH

**Planned Countermeasures:**
- Least-privilege repository access
- MFA
- Encryption
- Secrets management
- Workload identity
- Runtime access restrictions
- Audit logging

**Verification:**
- Unauthorized repository access test
- Unauthorized workload access test
- IAM policy validation
- Audit evidence review

---

## CHR-RISK-002 — Unauthorized Algorithm Modification

**Asset:** Temporal Navigation Algorithm

**Threat:** An attacker or malicious insider modifies CPI.

**Vulnerability:**
- Direct commits to protected code
- Compromised developer credentials
- Inadequate peer review
- Weak source integrity controls

**Mission Impact:** CRITICAL

Modified temporal calculations could cause mission failure or unauthorized temporal navigation behavior.

**Initial Likelihood:** MODERATE  
**Initial Risk:** HIGH

**Planned Countermeasures:**
- Protected branches
- Pull request review
- Required CI checks
- Commit traceability
- Automated testing
- Artifact signing

**Verification:**
- Attempt unauthorized direct push
- Submit failing pull request
- Modify artifact after build
- Confirm deployment rejection

---

## CHR-RISK-003 — Malicious Dependency Introduction

**Asset:** Software Supply Chain

**Threat:** A malicious or vulnerable dependency enters CHRONOS.

**Vulnerability:**
- Unrestricted package sources
- Unpinned versions
- Vulnerable third-party libraries
- Dependency confusion

**Mission Impact:** HIGH

A compromised dependency could execute inside a trusted CHRONOS workload.

**Initial Likelihood:** HIGH  
**Initial Risk:** HIGH

**Planned Countermeasures:**
- Version pinning
- Software Composition Analysis
- Dependency review
- Approved dependency policy
- SBOM generation
- Vulnerability gates

**Verification:**
- Introduce known vulnerable test dependency
- Confirm CI failure
- Validate SBOM contents

---

## CHR-RISK-004 — CI/CD Pipeline Compromise

**Asset:** GitHub Actions Pipeline

**Threat:** Attacker modifies or abuses the build pipeline.

**Vulnerability:**
- Excessive workflow permissions
- Long-lived cloud credentials
- Unprotected workflow files
- Untrusted build actions

**Mission Impact:** CRITICAL

A compromised build pipeline could generate malicious artifacts that appear trusted.

**Initial Likelihood:** MODERATE  
**Initial Risk:** HIGH

**Planned Countermeasures:**
- Least-privilege GitHub Actions permissions
- GitHub OIDC
- Short-lived credentials
- Protected workflow changes
- Pinned actions
- Artifact provenance
- Build attestations

**Verification:**
- Review effective workflow permissions
- Attempt unauthorized workflow modification
- Validate OIDC trust policy
- Validate provenance output

---

## CHR-RISK-005 — Mission Artifact Tampering

**Asset:** CHRONOS Container Image

**Threat:** Approved software is altered after build.

**Vulnerability:**
- Unsigned artifacts
- Mutable tags
- Weak registry protections

**Mission Impact:** CRITICAL

Tampered mission software could execute with the trust of an approved workload.

**Initial Likelihood:** MODERATE  
**Initial Risk:** HIGH

**Planned Countermeasures:**
- Cryptographic image signing
- Digest-based deployment
- Registry controls
- Admission verification
- Immutable release practices

**Verification:**
- Deploy signed image
- Attempt deployment of unsigned image
- Attempt deployment of altered image
- Confirm admission denial

---

## CHR-RISK-006 — Unauthorized Mission Workload

**Asset:** Kubernetes / EKS Platform

**Threat:** Unauthorized container or workload executes inside CHRONOS.

**Vulnerability:**
- Excessive Kubernetes privileges
- Missing admission controls
- Weak RBAC
- Compromised deployment identity

**Mission Impact:** CRITICAL

An unauthorized workload could access CPI, secrets, telemetry, or other mission services.

**Initial Likelihood:** MODERATE  
**Initial Risk:** HIGH

**Planned Countermeasures:**
- Kubernetes RBAC
- Workload identity
- Admission policies
- Namespace isolation
- Network policies
- Signed image enforcement

**Verification:**
- Attempt unauthorized deployment
- Attempt privilege escalation
- Attempt unsigned workload execution
- Validate denial evidence

---

## CHR-RISK-007 — Mission Credential Compromise

**Asset:** IAM / Secrets / Cryptographic Material

**Threat:** An adversary obtains privileged credentials.

**Vulnerability:**
- Static credentials
- Embedded secrets
- Excessive IAM permissions
- Improper secret handling

**Mission Impact:** CRITICAL

Credential compromise could enable unauthorized CPI access or mission control.

**Initial Likelihood:** MODERATE  
**Initial Risk:** HIGH

**Planned Countermeasures:**
- No hard-coded secrets
- Short-lived credentials
- IAM least privilege
- Workload identity
- Secrets manager
- Secret scanning

**Verification:**
- Commit test secret and confirm CI detection
- Validate absence of static AWS credentials
- Test unauthorized IAM actions

---

## CHR-RISK-008 — Hardware or Firmware Substitution

**Asset:** Mission Compute / Root of Trust / Firmware

**Threat:** Critical hardware or firmware is replaced, counterfeited, or modified.

**Vulnerability:**
- Unverified suppliers
- Missing provenance
- Weak firmware validation
- Lack of trusted boot chain

**Mission Impact:** CRITICAL

The software stack could operate on an untrusted platform while appearing operationally normal.

**Initial Likelihood:** LOW  
**Initial Risk:** HIGH

**Planned Countermeasures:**
- Supplier risk management
- Hardware Root of Trust
- Secure Boot concepts
- Measured Boot concepts
- Firmware signing
- Component provenance

**Verification:**
- Architecture analysis
- Firmware integrity simulation
- Provenance validation
- Trusted boot evidence model

---

## CHR-RISK-009 — Loss of Mission Visibility

**Asset:** Logging and Monitoring

**Threat:** Adversary activity occurs without detection.

**Vulnerability:**
- Missing audit logs
- Log tampering
- Insufficient telemetry
- Weak alerting

**Mission Impact:** HIGH

The mission team could lose the ability to determine whether CHRONOS remains trustworthy.

**Initial Likelihood:** MODERATE  
**Initial Risk:** MODERATE-HIGH

**Planned Countermeasures:**
- Centralized logging
- Kubernetes audit logging
- CloudTrail
- Security monitoring
- Alerting
- Evidence retention

**Verification:**
- Generate known security events
- Validate log capture
- Validate alert generation
- Verify evidence retention
