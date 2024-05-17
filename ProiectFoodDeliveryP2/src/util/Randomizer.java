package util;

import java.util.Random;

public class Randomizer {

    public Randomizer() {}

    public int GenerateNumber(int right)
    {
        Random random = new Random();
        int randomNumber = random.nextInt(right) ;
        return randomNumber;
    }
}
