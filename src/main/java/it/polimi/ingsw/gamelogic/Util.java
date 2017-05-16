package it.polimi.ingsw.gamelogic;

public class Util {
    public static int subtractOrSetToZero(int firstValue, int secondValue) {
        int result = firstValue - secondValue;
        if (result > 0)
            return result;
        else return 0;
    }
}
