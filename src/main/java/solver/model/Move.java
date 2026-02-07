package solver.model;

/**
 * Initial move set: U / R / F with { , ', 2 } variants.
 *
 * Notation:
 *  - U  :quarter turn clockwise
 *  - U2 : half turn
 *  - U' : quarter turn counter-clockwise
 */
public enum Move {
    U(Face.U, 1), U2(Face.U, 2), UP(Face.U, 3),
    R(Face.R, 1), R2(Face.R, 2), RP(Face.R, 3),
    F(Face.F, 1), F2(Face.F, 2), FP(Face.F, 3);

    public enum Face {U, R, F}

    private final Face face;
    private final int turns; // 1,2,3 quarter-turns

    Move(Face face, int turns) {
        this.face = face;
        this.turns = turns;
    }

    public Face face() {
        return face;
    }

    public int turns() {
        return turns;
    }

    /** Returns the inverse move (e.g., U <-> U', U2 -> U2). */
    public Move inverse() {
        if (turns == 2) return this;
        int invTurns = 4 - turns; // 1->3, 3->1
        return from(face, invTurns);
    }

    public static Move from(Face face, int turns) {
        return switch (face) {
            case U -> (turns == 1) ? U : (turns == 2) ? U2 : UP;
            case R -> (turns == 1) ? R : (turns == 2) ? R2 : RP;
            case F -> (turns == 1) ? F : (turns == 2) ? F2 : FP;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case U -> "U"; case U2 -> "U2"; case UP -> "U'";
            case R -> "R"; case R2 -> "R2"; case RP -> "R'";
            case F -> "F"; case F2 -> "F2"; case FP -> "F'";
        };
    }
}
