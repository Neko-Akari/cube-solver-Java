# Design Notes

## Goal
Build a Rubic's Cube solver in Java, starting with a 2x2 cube, with strong emphasis on correctness, testing, and clean architecture.

## Scope (current)
- Target: 2x2 (Pocket Cube)
- Solver: IDDFS (baseline)
- CLI: basic scramble / solve output (later)

## Key decisions
### State representation
- Use piece-based representation: corner permutation (cp) + corner orientation (co)
- cp: int[8], a permutation of 0..7
- co: int[8], values in [0,2] with constraint sum(co) % 3 == 0

### Mutability
- CubeState will be:
	- [ ] mutable (apply modifies the same object)[ver.2]
	- [x] immutable (apply returns a new CubeState)[Baselline]

### Move representation
- Defin basic face turns: U, R, F (start)
- Derive inverse a double moves from base turns
- Moves implemented via precomputed tables (preferred for correctness)

## Testing strategy
- Move correctness:
	- applying a move 4 times returns to the original state
	- move followed by its inverse returns to original state
- Solver correctness
	- solution sequence applied to scrambled state return sloved

## Complexity notes (rough)
- IDDFS: time O(b^d), memory O(d)
- Branching factor b ~ number of moves used

## Next steps
- Implement Move enum and CubeState
- Add JUnit tests for move group properties

