# CHRONOS Critical Components

## Purpose

This document identifies the components whose compromise, failure, modification, substitution, or disclosure could result in unacceptable mission impact to the CHRONOS system.

The goal is to connect mission capability to technical protection requirements.

## Criticality Scale

- CRITICAL — Loss or compromise could result in mission failure, CPI compromise, unauthorized temporal transition, or loss of trust in the system.
- HIGH — Compromise could significantly degrade mission execution, enable follow-on attacks, or weaken protection of CPI.
- MODERATE — Compromise could affect mission availability or monitoring but would not independently result in catastrophic mission loss.

## Critical Components Matrix

| Component | Type | Criticality | Mission Function | Primary Concern |
|---|---|---:|---|---|
| Temporal Navigation Algorithm | CPI / Software | CRITICAL | Calculates temporal transition solution | Theft or unauthorized modification |
| Flux Capacitor Control System | Mission Hardware / Software | CRITICAL | Executes temporal transition parameters | Manipulation or unsafe activation |
| Mission Compute Module | Hardware | CRITICAL | Executes CPI and mission software | Platform compromise |
| Hardware Root of Trust | Trusted Computing | CRITICAL | Establishes hardware identity and trust | Counterfeit or tampered hardware |
| Boot Firmware | Firmware | CRITICAL | Establishes trusted startup state | Persistent pre-OS compromise |
| Cryptographic Module | Security Component | CRITICAL | Protects keys, signatures, and identities | Key theft or crypto bypass |
| Navigation Sensor Module | Mission Hardware | HIGH | Supplies navigation and environmental data | False or manipulated sensor input |
| Source Code Repository | Software Supply Chain | CRITICAL | Stores CPI source and infrastructure code | Unauthorized modification or disclosure |
| GitHub Actions Pipeline | Software Supply Chain | CRITICAL | Builds, tests, and packages mission software | Build pipeline compromise |
| Dependency Sources | Software Supply Chain | HIGH | Provides external libraries and packages | Malicious or vulnerable dependency |
| Container Image | Mission Software Artifact | CRITICAL | Packages deployable mission workload | Artifact tampering |
| Container Registry | Software Supply Chain | HIGH | Stores approved software artifacts | Image replacement |
| Kubernetes / EKS Platform | Operational Platform | CRITICAL | Executes mission workloads | Unauthorized workload execution |
| IAM / Workload Identity | Security Control | CRITICAL | Controls access to mission resources | Credential theft or privilege escalation |
| Secrets Store | Security Control | CRITICAL | Protects credentials and cryptographic material | Secret disclosure |
| Logging / Monitoring | Mission Assurance | HIGH | Detects suspicious or unauthorized activity | Loss of visibility |
| Evidence Repository | Assurance | MODERATE | Stores compliance and test evidence | Integrity or traceability loss |

## Criticality Principle

A component is considered critical when compromise of that component could:

1. Expose CHRONOS CPI.
2. Modify the Temporal Navigation Algorithm.
3. Cause an unauthorized or unsafe mission event.
4. Break the system's chain of trust.
5. Allow untrusted software or hardware to enter the mission environment.
6. Prevent operators from determining whether the system is trustworthy.
