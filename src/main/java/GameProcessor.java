import java.util.Arrays;

class GameProcessor {

    private int[][] rocks;
    private boolean whiteWin;

    GameProcessor(int[][] rocks) {
        this.rocks = rocks;
    }

    boolean checkRoles(int rockX, int rockY, boolean isBlack) {
        if (rocks[rockX][rockY] != -1) return false;

        if (isBlack) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if ((rocks[i][j + 1] == 1 && rocks[i][j + 2] == 1 && rocks[i][j + 3] == 1 &&
                            rocks[i][j + 4] == 1 && rocks[i][j + 5] == 1) ||
                            (rocks[i + 1][j] == 1 && rocks[i + 2][j] == 1 && rocks[i + 3][j] == 1 &&
                                    rocks[i + 4][j] == 1 && rocks[i + 5][j] == 1)) return false;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                sum += rocks[i][j];
            }
        }

        if (sum == (15 * 15 * -1) && (rockX > 9 || rockX < 7) && (rockY > 9 || rockY < 7)) return false;

        if (sum == (15 * 15 * -1 + 2) && (rockX > 9 || rockX < 7) && (rockY > 9 || rockY < 7)) return false;

        if (isBlack) {

            if ((rockY + 3 < 15 && rockX + 1 < 15 &&
                    rocks[rockX + 1][rockY + 1] == 1 &&
                    rocks[rockX + 1][rockY + 2] == 1 &&
                    rocks[rockX + 1][rockY + 3] == 1) &&
                    (rockX - 1 >= 0 &&
                            rocks[rockX - 1][rockY + 1] == 1 &&
                            rocks[rockX - 1][rockY + 2] == 1 &&
                            rocks[rockX - 1][rockY + 3] == 1)) {
                return false;
            }
            if ((rockY - 3 >= 0 && rockX + 1 < 15 &&
                    rocks[rockX + 1][rockY - 1] == 1 &&
                    rocks[rockX + 1][rockY - 2] == 1 &&
                    rocks[rockX + 1][rockY - 3] == 1) &&
                    (rockX - 1 >= 0 &&
                            rocks[rockX - 1][rockY - 1] == 1 &&
                            rocks[rockX - 1][rockY - 2] == 1 &&
                            rocks[rockX - 1][rockY - 3] == 1)) return false;
            if ((rockX + 3 < 15 && rockY + 1 < 15 &&
                    rocks[rockX + 1][rockY + 1] == 1 &&
                    rocks[rockX + 2][rockY + 1] == 1 &&
                    rocks[rockX + 3][rockY + 1] == 1) &&
                    (rockY - 1 <= 0 &&
                            rocks[rockX + 1][rockY - 1] == 1 &&
                            rocks[rockX + 2][rockY - 1] == 1 &&
                            rocks[rockX + 3][rockY - 1] == 1)) return false;
            if ((rockX - 3 >= 0 && rockY + 1 < 15 &&
                    rocks[rockX - 1][rockY + 1] == 1 &&
                    rocks[rockX - 2][rockY + 1] == 1 &&
                    rocks[rockX - 3][rockY + 1] == 1) &&
                    (rockY - 1 <= 0 &&
                            rocks[rockX - 1][rockY - 1] == 1 &&
                            rocks[rockX - 2][rockY - 1] == 1 &&
                            rocks[rockX - 3][rockY - 1] == 1)) return false;
        }

        return true;
    }

    boolean checkWin(int rockX, int rockY) {
        for (int dCord = 0; dCord < 11; dCord++) {
            if (equalsNums(rocks[rockX][dCord], rocks[rockX][dCord + 1], rocks[rockX][dCord + 2],
                    rocks[rockX][dCord + 3], rocks[rockX][dCord + 4]) && rocks[rockX][dCord] != -1) {
                whiteWin = rocks[rockX][dCord] != 1;
                return true;
            }
            if (equalsNums(rocks[dCord][rockY], rocks[dCord + 1][rockY], rocks[dCord + 1][rockY],
                    rocks[dCord + 3][rockY], rocks[dCord + 4][rockY]) && rocks[dCord][rockY] != -1) {
                whiteWin = rocks[dCord][rockY] != 1;
                return true;
            }
        }


        int startXH = rockX;
        int startYH = rockY;
        int startXP = rockX;

        while ((startXH > 0 || startXP < 15) && startYH > 0) {
            startYH--;
            if (startXH > 0) startXH--;
            if (startXP < 15) startXP++;
        }


        while ((startXH < 11 || startXP > 0) && startYH < 11) {
            if (startXH < 11 &&
                    equalsNums(rocks[startXH][startYH], rocks[startXH + 1][startYH + 1],
                            rocks[startXH + 2][startYH + 2], rocks[startXH + 3][startYH + 3], rocks[startXH + 4][startYH + 4])) {
                whiteWin = rocks[startXH][rockY] != 1;
                return true;
            }
            if (startXP >= 4 &&
                    equalsNums(rocks[startXH][startYH], rocks[startXH - 1][startYH + 1],
                            rocks[startXH - 2][startYH + 2], rocks[startXH - 3][startYH + 3], rocks[startXH - 4][startYH + 4])) {
                whiteWin = rocks[startXP][rockY] != 1;
                return true;
            }

            startXH++;
            startXP--;
            startYH++;
        }

        return false;
    }

    private boolean equalsNums(int... nums) {
        return Arrays.stream(nums).noneMatch(num -> num == -1) && Arrays.stream(nums).noneMatch(num -> num != nums[0]);
    }

    boolean isWhiteWin() {
        return whiteWin;
    }
}
