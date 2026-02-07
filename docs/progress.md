# Progress Log

## 2026-02-06
- Initialized repo and Maven project (`pom.xml`) with Java 17 and JUnit 5
- Added `solver.cli.Main` and basic JUnit sanity test (mvn test: BUILD SUCCESS)
- Added documentation and updated: Big-O complexity notes for initial move set (U/R/F)
- Added model skeleton:
  - `solver.model.Move` enum (U/R/F variants)
  - `solver.model.CubeState` (immutable) with `isSolved()` implemented
  - `applyQuarter()` currently stubbed (throws UnsupportedOperationException)
- Pushed commits to GitHub (`main` up to date)

### Next
- [ ] Implement quarter-turn logic for U/R/F in `CubeState.applyQuarter()`
- [ ] Add move property tests (4× turn = identity; move + inverse = identity)
- [ ] Add a simple scramble parser/formatter (optional)