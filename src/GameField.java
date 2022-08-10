public class GameField {

    boolean isFillable;
    int value;
    int row;
    int column;

    public boolean getIsFillable() {
        return isFillable;
    }

    public int getValue() {
        return value;
    }

    public void setIsFillable(boolean isFillable) {
        this.isFillable = isFillable;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public GameField(int i, int j) {
        this.row = i;
        this.column = j;
        if (j == 1) {
            this.isFillable = i == 1;
        }
        if (j == 3) {
            this.isFillable = i == 15;
        }
        if (j == 2 || j == 4) this.isFillable = true;
        this.value = 0;
    }

    public void calcFieldValue(int[] dice, int throwCount) {
        if (this.row > 0 && this.row < 7) {
            calcUpperSection(dice);
        } else if (this.row == 8 || this.row == 9) {
            calcMinMax(dice);
        } else if (row == 11) {
            calcStraight(dice, throwCount);
        } else if (row == 12) {
            calcThreeOfAKind(dice);
        } else if (row == 13) {
            calcFullHouse(dice);
        } else if (row == 14) {
            calcFourOfAKind(dice);
        } else if (row == 15) {
            calcYahtzee(dice);
        }
    }

    void calcUpperSection(int[] dice) {
        int count = 0;
        for (int die : dice) {

            if (die == this.row) ++count;
        }
        this.value = row * count;
    }

    void calcMinMax(int[] dice) {
        if (dice.length == 5) {
            int sum = 0;
            for (int die : dice) {
                sum += die;
            }
            this.value = sum;
        }
    }

    void calcStraight(int[] dice, int throwCount) {
        if (dice.length == 5) {
            sort(dice, 5);
            boolean isStraight = true;
            for (int i = 1; i < dice.length; i++) {
                if (dice[i] != dice[i - 1] + 1) {
                    isStraight = false;
                    break;
                }
            }
            if (!isStraight) this.value = 0;
            else {
                if (throwCount == 2) this.value = 66;
                else if (throwCount == 3) this.value = 56;
                else if (throwCount == 1) this.value = 46;
            }
        } else this.value = 0;
    }

    void calcThreeOfAKind(int[] dice) {
        boolean isThreeOfAKind = false;
        int whichDice = 0;
        if (dice.length == 5) {
            sort(dice, 5);
            if (dice[0] == dice[1] && dice[1] == dice[2]) {
                isThreeOfAKind = true;
                whichDice = dice[0];
            } else if (dice[1] == dice[2] && dice[2] == dice[3]) {
                isThreeOfAKind = true;
                whichDice = dice[1];
            } else if (dice[2] == dice[3] && dice[3] == dice[4]) {
                isThreeOfAKind = true;
                whichDice = dice[2];
            }
            if (!isThreeOfAKind) this.value = 0;
            else this.value = 20 + whichDice * 3;
        } else if (dice.length == 4) {
            sort(dice, 4);
            if (dice[0] == dice[1] && dice[1] == dice[2]) {
                isThreeOfAKind = true;
                whichDice = dice[0];
            } else if (dice[1] == dice[2] && dice[2] == dice[3]) {
                isThreeOfAKind = true;
                whichDice = dice[1];
            }
            if (!isThreeOfAKind) this.value = 0;
            else this.value = 20 + whichDice * 3;

        } else if (dice.length == 3) {
            sort(dice, 3);
            if (dice[0] == dice[1] && dice[1] == dice[2]) {
                isThreeOfAKind = true;
                whichDice = dice[0];
            }
            if (!isThreeOfAKind) this.value = 0;
            else this.value = 20 + whichDice * 3;

        } else this.value = 0;
    }

    void calcFullHouse(int[] dice) {
        if (dice.length == 5) {
            sort(dice, 5);
            boolean isFullHouse = false;
            int whichDiceTwo = 0;
            int whichDiceThree = 0;
            if (dice[0] == dice[1] && dice[2] == dice[3] &&
                    dice[3] == dice[4] && dice[1] != dice[2]) {
                isFullHouse = true;
                whichDiceTwo = dice[0];
                whichDiceThree = dice[2];
            } else if (dice[3] == dice[4] && dice[0] == dice[1] &&
                    dice[1] == dice[2] && dice[2] != dice[3]) {
                isFullHouse = true;
                whichDiceTwo = dice[2];
                whichDiceThree = dice[0];
            }
            if (!isFullHouse) this.value = 0;
            else this.value = 30 + 2 * whichDiceTwo + 3 * whichDiceThree;
        } else this.value = 0;
    }

    void calcFourOfAKind(int[] dice) {
        boolean isFourOfAKind = false;
        int whichDice = 0;
        if (dice.length == 5) {
            sort(dice, 5);
            if (dice[1] == dice[2] && dice[2] == dice[3] &&
                    (dice[1] == dice[0] && dice[3] == dice[4])) {
                isFourOfAKind = true;
                whichDice = dice[1];
            }
            if (!isFourOfAKind) this.value = 0;
            else this.value = 40 + 4 * whichDice;
        } else if (dice.length == 4) {
            sort(dice, 4);
            if (dice[0] == dice[1] && dice[1] == dice[2] &&
                    dice[2] == dice[3]) {
                isFourOfAKind = true;
                whichDice = dice[1];
            }
            if (!isFourOfAKind) this.value = 0;
            else this.value = 40 + 4 * whichDice;
        } else if (dice.length < 4) this.value = 0;
    }

    void calcYahtzee(int[] dice) {
        if (dice.length == 5) {
            sort(dice, 5);
            boolean isYahtzee = false;
            int whichDice = 0;
            if (dice[1] == dice[2] && dice[2] == dice[3] &&
                    (dice[1] == dice[0] && dice[3] == dice[4])) {
                isYahtzee = true;
                whichDice = dice[1];
            }
            if (!isYahtzee) this.value = 0;
            else this.value = 50 + 5 * whichDice;
        } else this.value = 0;
    }

    public void sort(int[] array, int n) {
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                if (array[i] > array[j]) {
                    int pom = array[i];
                    array[i] = array[j];
                    array[j] = pom;
                }
    }
}
