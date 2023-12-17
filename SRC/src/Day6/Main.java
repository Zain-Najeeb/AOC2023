package Day6;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File obj = new File("SRC/src/Day6/ans.txt");
        Scanner reader = new Scanner(obj);
        ArrayList<Long> time = new ArrayList<>();
        ArrayList<Long> distance = new ArrayList<>();

        int count = 0;
        while (reader.hasNextLine()) {

            String data = reader.nextLine();

            String[] words = data.split("\\s+");
            if (count == 0) {
                String word = "";
                for (int i = 1; i < words.length; i++) {
                    word += words[i];
                }
                time.add(Long.parseLong(word));
            } else {
                String word = "";
                for (int i = 1; i < words.length; i++) {
                    word += words[i];
                }
                distance.add(Long.parseLong(word));
            }
            count++;
        }
        int sum = 1;
        for (int i = 0 ; i < time.size(); i++) {
            sum*= range(time.get(i), distance.get(i));
        }
        System.out.println(sum);
    }



    public static int range(long time, long distance) {
        double a = -1;
        double b = time;
        double c = (distance+ 1)*-1;

        double sqrt = Math.sqrt(b * b - 4 * a * c);
        double minD = (-1*b - sqrt)/2*a;
        double maxD =  (-1*b + sqrt)/2*a;


        int min = (int) maxD;

        if (min != maxD) {
            min ++;
        }
        int max = (int) minD;
        return Math.abs(max - min) + 1;
    }
}
