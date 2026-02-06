# Cube Solver (Java)
A Rubik's Cube solver written in Java. This project starts with a **2x2 (Pocket Cube)** solver and is designed to be extended.

## Why this project
I’m rebuilding my earlier cube-solver attempt with a stronger focus on:
- Correctness (move validation + tests)
- Clean architecture (model / search / CLI)
- Reproducibility (seedable scrambles)
- Engineering quality (CI, documentation, benchmarks)

## Planned features
- Piece-based cube state representation
- Move set: U, D, L, R, F, B (+ inverse and double moves)
- Scramble generator (seedable)
- Solver:
  - Baseline: IDDFS
  - Stretch: A* with heuristics / pattern database
- CLI: scramble / solve / verify
- JUnit tests for move correctness and solver validity
- GitHub Actions CI (build + test)

## Project structure (planned)
```text
src/main/java/…
solver/model/	// CubeState, Move, applyMove
solver/search/	// IDDFS, (A* later)
solver/cli/	// Main entry point
src/test/java/…
docs/
```

## Getting started
### Requirements
- Java 17+
- Maven 3+

### Build & run tests
```bash
mvn test
```

## Usage (coming soon)
```bash
# Example (placeholder)
# mvn -q exec:java -Dexec.mainClass="solver.cli.Main" -- "R U R' U'"
```

## Roadmap
- Repository initialized + GitHub sync
- Maven project skeleton
- Cube model + move tables
...TBD

## License
TBD
