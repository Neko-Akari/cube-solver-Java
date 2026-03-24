package solver.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Boundary / negative tests.
 *
 * <p>These tests intentionally exercise error branches (exceptions) and edge cases to improve confidence
 * and branch coverage (JaCoCo).
 */
class BoundaryTest {

    // ----- Move.from(...) -----
    @Test
    void fromRejectsInvalidTurns() {
        assertThrows(IllegalArgumentException.class, () -> Move.from(Move.Face.U, 0));
        assertThrows(IllegalArgumentException.class, () -> Move.from(Move.Face.U, 4));
        assertThrows(IllegalArgumentException.class, () -> Move.from(Move.Face.U, -1));
    }

    // ----- Move.parse(...) -----
    @Test
    void parseRejectsNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Move.parse(null));
        assertThrows(IllegalArgumentException.class, () -> Move.parse(""));
        assertThrows(IllegalArgumentException.class, () -> Move.parse("  "));
    }

    @Test
    void parseRejectsUnknownFace() {
        assertThrows(IllegalArgumentException.class, () -> Move.parse("X"));
        assertThrows(IllegalArgumentException.class, () -> Move.parse("Q2"));
        assertThrows(IllegalArgumentException.class, () -> Move.parse("NEKO"));
    }

    @Test
    void parseRejectsInvalidSuffixOrLength() {
        assertThrows(IllegalArgumentException.class, () -> Move.parse("U3")); // invalid suffix
        assertThrows(IllegalArgumentException.class, () -> Move.parse("U22")); // invalid length
        assertThrows(IllegalArgumentException.class, () -> Move.parse("U''")); // invalid length
    }

    @Test
    void parseAcceptsPrimeSuffix() {
        assertEquals(Move.UP, Move.parse("U'"));
        assertEquals(Move.UP, Move.parse("U’")); // curly apostrophe
    }

    // ----- CubeState.isSolved() -----
    @Test
    void isSolvedReturnsFalseWhenPermutationIsNotIdentity() {
        // Applying U changes the corner permutation, so the state should no longer be solved.
        CubeState s = CubeState.solved().apply(Move.U);
        assertFalse(s.isSolved());
    }

    @Test
    void isSolvedReturnsFalseWhenAnyCornerOrientationNonZero() throws Exception {
        // Construct a state with identity permutation but a non-zero orientation.
        byte[] cp = new byte[8];
        byte[] co = new byte[8];
        for (byte i = 0; i < 8; i++) {
            cp[i] = i;
            co[i] = 0;
        }
        co[0] = 1; // non-zero orientation => unsolved

        Constructor<CubeState> ctor = CubeState.class.getDeclaredConstructor(byte[].class, byte[].class);
        ctor.setAccessible(true);
        CubeState s = ctor.newInstance(cp, co);

        assertFalse(s.isSolved());
    }
}
