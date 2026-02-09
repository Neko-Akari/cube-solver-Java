package solver.model;

/**
 * Immutable 2x2 cube state using corner permutation (cp) and corner orientation (co).
 *
 * Corner indices (standard cubie model):
 * 0 URF, 1 UFL, 2 ULB, 3 UBR, 4 DFR, 5 DLF, 6 DBL, 7 DRB
 *
 * Invariants:
 *  - cp is a permutation of 0..7
 *  - co values are in [0,2] and sum(co) % 3 == 0 (valid cube constraint)
 */
public final class CubeState {

    // cp[pos] = which corner cubie is at position pos
    private final byte[] cp; // length 8
    // co[pos] = orientation of corner cubie at position pos (0..2)
    private final byte[] co; // length 8

    private CubeState(byte[] cp, byte[] co) {
        this.cp = cp;
        this.co = co;
    }

    /** Returns the solved 2x2 state. */
    public static CubeState solved() {
        byte[] cp = new byte[8];
        byte[] co = new byte[8];
        for (byte i = 0; i < 8; i++) {
            cp[i] = i;
            co[i] = 0;
        }
        return new CubeState(cp, co);
    }

    /** Applies a move and returns a new CubeState. */
    public CubeState apply(Move move) {
        CubeState s = this;
        for (int i = 0; i < move.turns(); i++) {
            s = s.applyQuarter(move.face());
        }
        return s;
    }

    /** Whether this state is solved. */
    public boolean isSolved() {
        for (byte i = 0; i < 8; i++) {
            if (cp[i] != i) return false;
            if (co[i] != 0) return false;
        }
        return true;
    }

    /** Applies one clockwise quarter-turn of the given face. */
    private CubeState applyQuarter(Move.Face face) {
        return switch (face) {
            case U -> applyUQuarter();
            case R, F -> throw new UnsupportedOperationException("Not supported yet.");
        };
    }

    /**
     * U clockwise: cycles the top-layer corners (0,1,2,3) without changing orientation,
     * Corner positions: 0 URF, 1 UFL, 2 ULB, 3 UBR.
     * After U: new[0] = old[1], ..., new[3] = old[0].
     */
    private CubeState applyUQuarter() {
        final int[] permU = {1, 2, 3, 0, 4, 5, 6, 7};

        byte[] newCp = new byte[8];
        byte[] newCo = new byte[8];

        for (int pos = 0; pos < 8; pos++) {
            int src = permU[pos];
            newCp[pos] = cp[src];
            newCo[pos] = co[src]; // orientation unchanged for U
        }

        return new CubeState(newCp, newCo);
    }
}
