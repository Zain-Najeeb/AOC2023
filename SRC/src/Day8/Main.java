package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File obj = new File("SRC/src/Day8/ans.txt");
        Scanner reader = new Scanner(obj);

        ArrayList<String> endingA = new ArrayList<>();
        String instructions = "";
        int count = 0;
        HashMap<String, ArrayList<String>> map= new HashMap<>();
        while (reader.hasNextLine()) {

            String data = reader.nextLine();
            String[] words = data.split("\\s+");
            if (count ==0 ){
                instructions =data;
                }  else {
                if (count >= 2 ){
                    map.put(words[0], new ArrayList<>());
                    map.get(words[0]).add(words[2].replaceAll("[(),]", ""));
                    map.get(words[0]).add(words[3].replaceAll("[(),]", ""));
                    if (words[0].charAt(2) == 'A') {
                        endingA.add(words[0]);
                    }
                }
            }
            count++;
        }

        helper(map, instructions, endingA);
    }


    public static void helper(  HashMap<String, ArrayList<String>> map, String instructions, ArrayList<String > start) {
        int steps = 0;
        int index ;
        ArrayList<String> currs = new ArrayList<>(start);
        ArrayList<Integer> currsSteps = new ArrayList<>();

        int counter ;
        for (int i = 0; i < currs.size(); i++ ) {
            index = 0;
            String curr = currs.get(i);
            while (true) {
                index = index%instructions.length();
                int lr = 1;
                if (instructions.charAt(index)== 'L') {
                    lr = 0 ;
                }
                curr =  map.get(curr).get(lr);
                index++;
                steps++;
                if (curr.charAt(2) == 'Z') {
                    break;
                }
            }
            currsSteps.add(steps);
            steps = 0;

        }

        long sum = 1;
        for (int i = 0 ; i < currs.size(); i++) {
            sum  *= currsSteps.get(i);
        }
        System.out.println(currsSteps);
    }
}
