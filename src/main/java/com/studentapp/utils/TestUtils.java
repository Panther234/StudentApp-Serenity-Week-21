package com.studentapp.utils;
/*
 * Created By: Hiren Patel
 * Project Name: StudentApp-Serenity-Week-21
 */

import java.util.Random;

public class TestUtils {
    public static String getRandomValue(){
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt);
    }

}
