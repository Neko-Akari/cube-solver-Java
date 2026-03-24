package solver.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void inverseOfInverseReturnsOriginal() {
        for (Move m : Move.values()) {
            assertEquals(m, m.inverse().inverse(),
                    "inverse(inverse(m)) should be m for " + m);
        }
    }

    @Test
    void inverseRulesForTurns() {
       // Quarter turn
        assertEquals(Move.UP, Move.U.inverse());
        assertEquals(Move.U, Move.UP.inverse());

        //Half turn is its own inverse
        assertEquals(Move.U2, Move.U2.inverse());
    }

    @Test
    void fromFactoryCreatesExpectedMoves() {
        assertEquals(Move.U,  Move.from(Move.Face.U, 1));
        assertEquals(Move.U2, Move.from(Move.Face.U, 2));
        assertEquals(Move.UP, Move.from(Move.Face.U, 3));

        assertEquals(Move.R,  Move.from(Move.Face.R, 1));
        assertEquals(Move.R2, Move.from(Move.Face.R, 2));
        assertEquals(Move.RP, Move.from(Move.Face.R, 3));

        assertEquals(Move.F,  Move.from(Move.Face.F, 1));
        assertEquals(Move.F2, Move.from(Move.Face.F, 2));
        assertEquals(Move.FP, Move.from(Move.Face.F, 3));
    }

    @Test
    void toStringUsesStandardNotation() {
        assertEquals("U",  Move.U.toString());
        assertEquals("U2", Move.U2.toString());
        assertEquals("U'", Move.UP.toString());

        assertEquals("R",  Move.R.toString());
        assertEquals("R2", Move.R2.toString());
        assertEquals("R'", Move.RP.toString());

        assertEquals("F",  Move.F.toString());
        assertEquals("F2", Move.F2.toString());
        assertEquals("F'", Move.FP.toString());
    }
}
