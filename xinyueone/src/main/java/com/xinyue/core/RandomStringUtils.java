package com.xinyue.core;

import java.util.Random;

public class RandomStringUtils {

  
    private static final Random RANDOM = new Random();

    
    public RandomStringUtils() {
      super();
    }

    // Random
    //-----------------------------------------------------------------------
    
    
    //在Asicii码范围内, 创建一个指定长度的随机的字符串
    public static String randomAscii(int count) {
        return random(count, 32, 127, false, false);
    }
    
   //在字母码范围内,创建一个指定长度的随机的字符串
    public static String randomAlphabetic(int count) {
        return random(count, true, false);
    }
    
    //在字母+数字 码范围内,创建一个指定长度的随机的字符串
    public static String randomAlphanumeric(int count) {
        return random(count, true, true);
    }
    
  // 在数字范围内, 创建一个指定长度的随机的字符串
    public static String randomNumeric(int count) {
        return random(count, false, true);
    }

    // 创建一个随机的字符串, 如果letter值为true则包含字母, 如果numbers值为true,则包含数字
    public static String random(int count, boolean letters, boolean numbers) {
        return random(count, 0, 0, letters, numbers);
    }
    
    /**
     * 创建一个指定长度的随机的字符串,
     * @param count	 指定创建的字符串的长度
     * @param start 开始位置
     * @param end	  结束位置
     * @param letters	如果为true,则包含字母
     * @param numbers	如果为true,则包含数字
     * @return
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers) {
        return random(count, start, end, letters, numbers, null, RANDOM);
    }


    /**
     * 创建一个指定长度的随机的字符串,
    * @param count	 			指定创建的字符串的长度
     * @param start 				开始位置, 当我们给定了随机范围才有效,此处无意义
     * @param end	  				结束位置,当我们给定了随机范围才有效, 此处无意义
     * @param letters			如果为true,则包含字母
     * @param numbers		如果为true,则包含数字
     * @param chars		这个是给定的随机范围的char类型的字符
     * @return
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers, char... chars) {
        return random(count, start, end, letters, numbers, chars, RANDOM);
       
    }

    /**
     * 创建一个指定长度的随机的字符串,
    * @param count	 			指定创建的字符串的长度
     * @param start 				开始位置, 当我们给定了随机范围才有效,此处无意义
     * @param end	  				结束位置,当我们给定了随机范围才有效, 此处无意义
     * @param letters			如果为true,则包含字母
     * @param numbers		如果为true,则包含数字
     * @param chars		这个是给定的随机范围的char类型的数组
     * @return
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers,
                                char[] chars, Random random) {
        if (count == 0) {
            return "";
        } else if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        if (start == 0 && end == 0) {
            end = 'z' + 1;
            start = ' ';
            if (!letters && !numbers) {
                start = 0;
                end = Integer.MAX_VALUE;
            }
        }

        char[] buffer = new char[count];
        int gap = end - start;

        while (count-- != 0) {
            char ch;
            if (chars == null) {
                ch = (char) (random.nextInt(gap) + start);
            } else {
                ch = chars[random.nextInt(gap) + start];
            }
            if (letters && Character.isLetter(ch)
                    || numbers && Character.isDigit(ch)
                    || !letters && !numbers) {
                if(ch >= 56320 && ch <= 57343) {
                    if(count == 0) {
                        count++;
                    } else {
                        // low surrogate, insert high surrogate after putting it in
                        buffer[count] = ch;
                        count--;
                        buffer[count] = (char) (55296 + random.nextInt(128));
                    }
                } else if(ch >= 55296 && ch <= 56191) {
                    if(count == 0) {
                        count++;
                    } else {
                        // high surrogate, insert low surrogate before putting it in
                        buffer[count] = (char) (56320 + random.nextInt(128));
                        count--;
                        buffer[count] = ch;
                    }
                } else if(ch >= 56192 && ch <= 56319) {
                    // private high surrogate, no effing clue, so skip it
                    count++;
                } else {
                    buffer[count] = ch;
                }
            } else {
                count++;
            }
        }
        return new String(buffer);
    }

    // 给定一个随机的字符串, 创建一个随机的字符串
    public static String random(int count, String chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, RANDOM);
        }
        return random(count, chars.toCharArray());
    }

  
    public static String random(int count, char... chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, RANDOM);
        }
        return random(count, 0, chars.length, false, false, chars, RANDOM);
    }
    
    public static void main(String[] args) {
    	System.out.println(RandomStringUtils.random(2, "straing"));
	}
    
    
    
    
}