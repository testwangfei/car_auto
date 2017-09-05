package com.xinyue.core;

import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM = new Random();

   
    public RandomUtils() {
        super();
    }

    // 创建一个随机字节数组。
    public static byte[] nextBytes(int count) {
        byte[] result = new byte[count];
        RANDOM.nextBytes(result);
        return result;
    }

    // 创建一个随机整数
    public static int nextInt(int startInclusive, int endExclusive) {
        if (startInclusive == endExclusive) {
            return startInclusive;
        }
        
        return startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
    }
    
    public static int nextInt(int n) {
        return nextInt(0, n);
    }
    
   // 创建一个随机的长整型数
    public static long nextLong(long startInclusive, long endExclusive) {
        if (startInclusive == endExclusive) {
            return startInclusive;
        }

        return (long) nextDouble(startInclusive, endExclusive);
    }    
    
    
   //创建一个双精度浮点数
    public static double nextDouble(double startInclusive, double endInclusive) {
        if (startInclusive == endInclusive) {
            return startInclusive;
        }
        
        return startInclusive + ((endInclusive - startInclusive) * RANDOM.nextDouble());
    }
    
   //创建一个单精度浮点数
    public static float nextFloat(float startInclusive, float endInclusive) {
        if (startInclusive == endInclusive) {
            return startInclusive;
        }
        
        return startInclusive + ((endInclusive - startInclusive) * RANDOM.nextFloat());
    }

}
