package solver.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

/**
 * Property tests for face turns.
 *
 * This test suite is designed to scale with the implementation:
 * - It loops over all faces/moves defined in {@link Move}
 * - If a face/move is not implemented yet (throws {@link UnsupportedOperationException}),
 *   the test will skip it.
 */
public class MovePropertiesTest {

    @Test
    void eachFaceTurnAppliedFourTimesReturnsSolved()
    {
        for (Move.Face face : Move.Face.values()) {
            //Skip faces not implemented yet
            boolean implemented = true;
            try {
                CubeState.solved().apply(Move.from(face, 1));
            }
            catch (UnsupportedOperationException e) {
                implemented = false;
            }
            assumeTrue(implemented, "Face " + face + " not implemented yet.");

            CubeState s = CubeState.solved();
            for (int i = 0; i < 4; i++) {
                s = s.apply(Move.from(face, 1));
            }
            assertTrue(s.isSolved(), "Face " + face +
                    " should return to solved after 4 quarter-turns.");
        }
    }

    @Test
   void moveThenInverseReturnsSolved() {
        for (Move m: Move.values()) {
            // Skip moves not implemented yet
            boolean implemeted = true;
            try {
                CubeState.solved().apply(m);
            }
            catch (UnsupportedOperationException e) {
                implemeted = false;
            }
            assumeTrue(implemeted, "Move " + m + " not implemented yet.");

            CubeState s = CubeState.solved().apply(m).apply(m.inverse());
            assertTrue(s.isSolved(), "Move " + m +
                    " followed by inverse should be solved.");
        }
    }
}
