package Day4;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File obj = new File("SRC/src/Day4/ans.txt");
        Scanner reader = new Scanner(obj);
        int sum = 0;
        HashMap<Integer, Integer> matches = new HashMap<>();
        HashMap<Integer, Integer> amount = new HashMap<>();

        int count = 1;
        int max ;
        ArrayList<Integer> queue = new ArrayList<>();
        while (reader.hasNextLine()) {
            String data = reader.nextLine();

            String[] words = data.split("\\s+");
            matches.put(count, helperP2(words));
            amount.put(count,1);

            count ++;
        }
        max = count;



        for (int i = 1; i < max; i++) {
            int amt = matches.get(i);
            for (int j = 1; j <= amt; j++) {
                if (i + j < max) {
                    amount.put(i + j, amount.get(i + j) + amount.get(i));
                }
            }
        }

        for (int i =1 ; i < max; i ++) {
            sum += amount.get(i);
        }
        System.out.println(sum);
    }

    public static int helperP2(String[] words) {

        int index = 0 ;
        int count = 0;
        ArrayList<String> nums = new ArrayList<>();
        for (int i = 2; i < words.length; i++) {
            if (!words[i].equals("|")) {
                nums.add(words[i]);
            } else {
                index = i;
                break;
            }
        }
        for (int i = index + 1; i < words.length; i++) {
            if (nums.contains(words[i])) {
                count ++;
            }
        }
        return  count;
    }

    public static int helperP1(String [] words) {
        int index = 0;
        int sum = 0;
        ArrayList<String> nums = new ArrayList<>();
        for (int i = 2; i < words.length; i++) {
            if (!words[i].equals("|")) {
                nums.add(words[i]);
            } else {
                index = i;
                break;
            }
        }
        for (int i = index + 1; i < words.length; i++) {
            if (nums.contains(words[i])) {
                if (sum == 0) {
                    sum = 1;
                } else {
                    sum = sum*2;
                }
            }
        }
        return  sum;
    }


}

// 2, 2, 3,3,44,5
//