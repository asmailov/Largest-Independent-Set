package Main;

import java.util.Arrays;

/**
 * @author Aleksandr Šmailov
 */
public class Logs {
    public static void log(String s){
        System.out.println(s);
    }
    public static void log(int s){
        System.out.println(s);
    }
    public static void log(char[] s){
        System.out.println(Arrays.toString(s));
    }
    public static void log(char s){
        System.out.println(s);
    }
    public static void log(short s){
        System.out.println(s);
    }
    public static void log(boolean s){
        System.out.println(s);
    }
}
