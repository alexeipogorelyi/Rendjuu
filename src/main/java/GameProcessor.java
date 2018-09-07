import java.util.Arrays;

class GameProcessor {

    private int[][] rocks;
    private boolean whiteWin;

    GameProcessor(int[][] rocks) {
        this.rocks = rocks;
    }

    boolean checkWin() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 11; j++) {
                if (equalsNums(rocks[i][j], rocks[i][j + 1], rocks[i][j + 2], rocks[i][j + 3], rocks[i][j + 4]) &&
                        rocks[i][j] != -1) {
                    whiteWin = rocks[i][j] != 1;
                    return true;
                }
            }
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 15; j++) {
                if (equalsNums(rocks[i][j], rocks[i + 1][j], rocks[i + 2][j], rocks[i + 3][j], rocks[i + 4][j]) &&
                        rocks[i][j] != -1) {
                    whiteWin = rocks[i][j] != 1;
                    return true;
                }
            }
        }

        for (int dx = -7; dx < 8; dx++) {
            for (int x = 0; x < 11; x++) {
                for (int y = 0; y < 11; y++) {
                    int iX = x + dx;
                    if (x == y && iX > 0 && iX < 11) {
                        if (equalsNums(rocks[iX][y], rocks[iX + 1][y + 1], rocks[iX + 2][y + 2],
                                rocks[iX + 3][y + 3], rocks[iX + 4][y + 4])
                                && rocks[iX][y] != -1) {
                            whiteWin = rocks[iX][y] != 1;
                            return true;
                        }
                    }
                    if (x == (15 - y - 1) && iX > 4 && iX < 11) {
                        if (equalsNums(rocks[iX][15 - y - 1], rocks[iX - 1][15 - y],
                                rocks[iX - 2][15 - y + 1], rocks[iX - 3][15 - y + 2],
                                rocks[iX - 4][15 - y + 3])
                                && rocks[iX][15 - y - 1] != -1) {
                            whiteWin = rocks[iX][15 - y - 1] != 1;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean equalsNums(int... nums) {
        return Arrays.stream(nums).noneMatch(num -> num != nums[0]);
    }

    boolean isWhiteWin() {
        return whiteWin;
    }
}
