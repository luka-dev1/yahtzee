import java.util.Random;


public class Engine {

    GameField[][] fields = new GameField[17][6];
    public int indexUp = 1;
    public int indexDown = 15;
    public int sum = 0;
    public boolean announcementInProgress = false;
    public int announcedRow = 0;

    public Engine(){
        for (int i = 0; i < 17; i++)
            for (int j = 0; j < 6; j++)
            {
                fields[i][j] = new GameField(i,j);
            }
    }

    public void updateSums(){
        for (int i = 1; i < 5; i++){
            int sum = 0;
            for (int j = 1; j < 7; j++){
                sum += fields[j][i].value;
            }
            if (sum > 60) {
                fields[7][i].value = sum + 30;
            }
            else{
                fields[7][i].value = sum;
            }
        }
        for (int i = 1; i < 5; i++){
            if (fields[9][i].value != 0){
                if (fields[9][i].value > fields[8][i].value) {
                    fields[10][i].value = fields[1][i].value *
                            (fields[9][i].value - fields[8][i].value);
                }
                else{
                    fields[10][i].value = fields[1][i].value *
                            (fields[8][i].value - fields[9][i].value);
                }
            }
            if (fields[10][i].value >= 60) fields[10][i].value +=30;
        }
        for (int i = 1; i < 5; i++){
            int sum = 0;
            for (int j = 11; j < 16; j++){
                sum += fields[j][i].value;
            }
            fields[16][i].value = sum;
        }
        int bigSum1 = 0;
        int bigSum2 = 0;
        int bigSum3 = 0;
        for (int i = 1; i < 5; i++) {
            bigSum1 += fields[7][i].value;
            bigSum2 += fields[10][i].value;
            bigSum3 += fields[16][i].value;
        }
        fields[7][5].value = bigSum1;
        fields[10][5].value = bigSum2;
        fields[16][5].value = bigSum3;
        sum = bigSum1 + bigSum2 + bigSum3;
    }

    public int[] randomize(int n)
    {
        int[] array = new int[n];
        for (int i = 0; i < n; i++){
            array[i] = randInt(1,n);
        }
        return array;
    }

    public static int randInt(int min, int max) {
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return rand.nextInt((max - min) + 1) + min;
    }

    public void newGame(){
        for (int i = 1; i < 17; i++)
            for (int j = 1; j < 6; j++) {
                fields[i][j].value = 0;
                if (j == 1){
                    fields[i][j].isFillable = i == 1;
                }
                if (j == 3){
                    fields[i][j].isFillable = i == 15;
                }
                if (j == 2 || j == 4) fields[i][j].isFillable = true;
                //fields[i][j].value = 0;
            }
        sum = 0;
        announcementInProgress = false;
        announcedRow = 0;
    }

    public boolean gameOver(){
        boolean exists = false;
        for (int i = 1; i < 17; i++)
            for (int j = 1; j < 6; j++) {
                if (fields[i][j].getIsFillable() && i != 7
                        && i != 10 && i != 16) {
                    exists = true;
                    break;
                }
            }
        return !exists;
    }


}