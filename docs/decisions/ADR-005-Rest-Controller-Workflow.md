# ADR-0005: Cycle 1 In-Memory Persistence via ConcurrentHashMap

* **Status**: Accepted
* **Date**: 2026-10-29
* **Authors**: Shu Perez (Development Lead), Architecture & Development Team
* **Deciders**: Team 02 (COMP 330 Fall 2026)
* **Related Issues / PRs**: Issue #66, PR #75 (`a4-rest-controller`)
* **Related Requirements / ADRs**: REQ-003, API-001, API-006, ADR-0002

---

## Context and Problem Statement

During Assignment 3 (Architecture & Design), the initial design specified a file-backed H2 relational database for storing student support requests. 

During Assignment 4 (Cycle 1 Construction & Integration), the primary goal was to deliver a working, controlled vertical slice of the CampusConnect REST API (`POST /api/requests`, `GET /api/requests`, `PATCH /api/requests/{id}/status`) backed by automated unit tests and continuous integration (`mvn clean test`). 

Integrating full file-backed H2 schema migrations and JPA/JDBC configurations posed a risk of setup overhead and environmental fragility in GitHub Actions CI during the tight Cycle 1 phase gate. The team needed to decide whether to implement H2 database persistence immediately or use a lightweight in-memory store for Cycle 1.

---

## Decision Drivers

1. **Cycle 1 Velocity & Scope**: Focus effort on core REST API controllers, validation (`RequestValidator`), student isolation, and status workflow state machines.
2. **CI Reliability**: Ensure `mvn clean test` runs deterministically, fast (< 2 seconds), and without local file path or database locking issues across operating systems.
3. **Thread Safety**: Support concurrent REST requests safely in the Spring Boot application environment.
4. **Architectural Grounding**: Maintain clear traceability to `REQ-003` (Unique ID generation and ticket persistence) while explicitly recording implementation trade-offs.

---

## Considered Options

1. **Option 1**: In-Memory Thread-Safe Data Structure (`ConcurrentHashMap` + `AtomicLong`).
2. **Option 2**: File-backed H2 Relational Database (`Spring Data JPA` / `H2`).

---

## Decision Outcome

**Chosen Option**: **Option 1 — In-Memory `ConcurrentHashMap` Storage for Cycle 1**.

The team implemented `WorkflowService` using `ConcurrentHashMap<String, SupportRequest>` and an `AtomicLong` sequence starting at `1000` (`REQ-1001`). This provides thread-safe, fast, and isolated ticket persistence for the duration of the application runtime.

File-backed H2 database integration is formally deferred to **Cycle 2 (Maturity Phase)** as planned technical debt.

---

## Consequences & Trade-offs

### Positive
* **Zero Configuration Overhead**: No external database drivers, schema SQL files, or JPA entity mappings required for Cycle 1.
* **Fast & Reliable CI**: Automated GitHub Actions workflow (`mvn clean test`) executes completely isolated, clean builds in under 2 seconds.
* **Thread Safety**: `ConcurrentHashMap` natively handles concurrent thread access across `POST`, `GET`, and `PATCH` endpoints without data corruption.

### Negative / Trade-offs
* **Volatile Storage**: All ticket data resets when the Spring Boot process stops or restarts (`mvn spring-boot:run`).
* **Cycle 2 Debt**: Relational database schemas, H2 file storage, and DAO/Repository layers must be implemented in Cycle 2 to support permanent data retention.

---

## Verification Evidence

* **Source Location**: `src/main/java/edu/loyola/campusconnect/service/WorkflowService.java`
* **Test Verification**: `CampusConnectApplicationTests.java`, `RequestControllerTest.java`
