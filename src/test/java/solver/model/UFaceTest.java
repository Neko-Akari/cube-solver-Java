package solver.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UFaceTest {
    @Test
    void uAppliedFourTimesReturnsSolved() {
        CubeState s = CubeState.solved();
        s = s.apply(Move.U).apply(Move.U).apply(Move.U).apply(Move.U);
        assertTrue(s.isSolved());
    }

    @Test
    void uThenInverseReturnsSolved() {
        CubeState s = CubeState.solved().apply(Move.U).apply(Move.UP);
        assertTrue(s.isSolved());
    }
}
