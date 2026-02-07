# Design Notes

---
## Goal
Build a Rubik's Cube solver in Java, starting with a 2x2 cube, with strong emphasis on correctness, testing, and clean architecture.

---
## Scope (current)
- Target: 2x2 (Pocket Cube)
- Solver: IDDFS (baseline)
- CLI: basic scramble / solve output (later)

---
## Key decisions
### State representation
- Use piece-based representation: corner permutation (cp) + corner orientation (co)
- cp: int[8], a permutation of 0..7
- co: int[8], values in [0,2] with constraint sum(co) % 3 == 0

### Mutability
- CubeState will be:
	- [ ] mutable (apply modifies the same object)[ver.2]
	- [x] immutable (apply returns a new CubeState)[Baseline]

### Move representation
- Defin basic face turns: U, R, F (start)
- Derive inverse a double moves from base turns
- Moves implemented via precomputed tables (preferred for correctness)

---
## Testing strategy
- Move correctness:
	- applying a move 4 times returns to the original state
	- move followed by its inverse returns to original state
- Solver correctness
	- solution sequence applied to scrambled state return solved

---
## Complexity notes (Big-O)
For the initial implementation we restrict moves to **U / R / F** only ,and allow **quarter-turns + 
inverse + double**: {U, U', U2, R, R2, F, F',F2}.
So the branching factor is approximately **b = 9**.

Let:
- **b** = branching factor (here ~9)
- **d** = depth of the optimal solution (shortest solution length)
- **C** = cost to apply one move to a state

### BFS (Breadth-First Search)
- **Time:** O(b^d)
- **Space:** O(b^d)
- Finds shortest solutions but memory grows quickly.

### DFS 
- **Time:** O(b^m) where m is the max depth limit
- **Space:** O(m)
- Low memory but not guaranteed shortest.

### IDDFS
- **Time:** O(b^d) (same exponential order; re-explores shallow levels)
- **Space:** O(d)
- Good trade-off: optimal solution length with low memory.

### A*
- **Time:** worst-case O(b^d)
- **Space:** O(b^d)
- With a strong heuristic, practical runtime can be much faster than BFS/IDDFS.

### State operations (2x2 corner representation)
- Move application via precomputed tables: **C = O(1)**
- Hash/equality: **O(1)** (fixed-size arrays of length 8)
---
## Next steps
- Implement Move enum and CubeState
- Add JUnit tests for move group properties

