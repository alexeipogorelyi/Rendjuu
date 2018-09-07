import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestRules {
    private static GameProcessor gameProcessor;
    private static int[][] rocks = new int[15][15];

    static {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                rocks[i][j] = -1;
            }
        }
        gameProcessor = new GameProcessor(rocks);
    }

    private void clear() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                rocks[i][j] = -1;
            }
        }
    }

    @Test
    void firstStep() {
        clear();

        assertFalse(gameProcessor.checkRoles(0, 0, false));
        assertFalse(gameProcessor.checkRoles(1, 4, false));
        assertTrue(gameProcessor.checkRoles(8, 8, false));
    }

    @Test
    void secondStep() {
        clear();

        assertFalse(gameProcessor.checkRoles(0, 0, true));
        assertFalse(gameProcessor.checkRoles(1, 4, true));
        assertTrue(gameProcessor.checkRoles(8, 8, true));
    }

    @Test
    void blackFork() {
        clear();

        for (int i = 0; i < 3; i++) rocks[0][i + 1] = 1;
        for (int i = 0; i < 3; i++) rocks[2][i + 1] = 1;

        assertFalse(gameProcessor.checkRoles(1, 0, true));

        clear();

        for (int i = 0; i < 3; i++) rocks[i][0] = 1;
        for (int i = 0; i < 3; i++) rocks[i][2] = 1;

        assertFalse(gameProcessor.checkRoles(3, 1, true));
    }

    @Test
    void blackSix() {
        clear();

        for (int i = 1; i < 6; i++) rocks[0][i] = 1;
        assertFalse(gameProcessor.checkRoles(0, 0, true));

        clear();

        for (int i = 1; i < 6; i++) rocks[i][0] = 1;
        assertFalse(gameProcessor.checkRoles(0, 0, true));
    }

    @Test
    void emptyLocation() {
        rocks[0][0] = 1;
        assertFalse(gameProcessor.checkRoles(0, 0, false));
    }
}

