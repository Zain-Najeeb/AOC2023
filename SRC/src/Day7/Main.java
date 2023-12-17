package Day7;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File obj = new File("SRC/src/Day7/ans.txt");
        Scanner reader = new Scanner(obj);
        HashMap <String,Integer> hand_to_bid = new HashMap<>();

        HashMap<String, ArrayList<String >>  kinds = new HashMap<>();
        HashMap<Integer, String> rank = new HashMap<>();
        String high = "high";
        String oneP = "oneP";
        String twoP= "twoP";
        String three = "three";
        String full = "full";
        String four = "four";
        String five = "five";

        HashMap<Character, Integer> strenght = new HashMap<>();
        strenght.put('A', 13);
        strenght.put('K', 12);
        strenght.put('Q', 11);
        strenght.put('J', 1);
        strenght.put('T', 10);
        strenght.put('9', 9);
        strenght.put('8', 8);
        strenght.put('7', 7);
        strenght.put('6', 6);
        strenght.put('5', 5);
        strenght.put('4', 4);
        strenght.put('3', 3);
        strenght.put('2', 2);
        kinds.put(high, new ArrayList<>());

        kinds.put(oneP, new ArrayList<>());
        kinds.put(twoP, new ArrayList<>());
        kinds.put(three, new ArrayList<>());

        kinds.put(full, new ArrayList<>());
        kinds.put(four, new ArrayList<>());
        kinds.put(five, new ArrayList<>());
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] words = data.split("\\s+");
            hand_to_bid.put(words[0], Integer.parseInt(words[1]));
            determineKind(kinds, words[0]);
        }
        int cur = 0 ;
        cur = determineRank(kinds.get("high"), rank, cur, strenght);

        cur = determineRank(kinds.get("oneP"), rank, cur, strenght);
        int sum = 0;
        cur = determineRank(kinds.get("twoP"), rank, cur, strenght);
        cur = determineRank(kinds.get("three"), rank, cur, strenght);
        cur = determineRank(kinds.get("full"), rank, cur, strenght);
        cur = determineRank(kinds.get("four"), rank, cur, strenght);
        cur = determineRank(kinds.get("five"), rank, cur, strenght);
        for (int i = 1; i <= cur; i++) {
            sum +=hand_to_bid.get( rank.get(i))*i;
        }
        System.out.println(sum);


    }
    public static int determineRank( ArrayList<String> cards ,HashMap<Integer, String> rank, int base, HashMap<Character, Integer> strenghts) {
        int curr = base;
        ArrayList<String> sorted = new ArrayList<>();

        int stop = cards.size();
        for (int i = 0; i < stop; i++) {
            String currString = cards.get(0);
            for (int j = 1 ; j < cards.size(); j++) {

                    if (strenghts.get(currString.charAt(0)) > strenghts.get(cards.get(j).charAt(0))) {
                        currString= cards.get(j);

                    } else if(strenghts.get(currString.charAt(0)) == strenghts.get(cards.get(j).charAt(0))) {
                        for (int ii = 1 ; ii < currString.length(); ii++) {

                            if (currString.charAt(ii) != cards.get(j).charAt(ii)) {
                                if (strenghts.get(currString.charAt(ii)) > strenghts.get(cards.get(j).charAt(ii))) {
                                    currString = cards.get(j);
                                } else {
                                    break;
                                }
                            }

                        }
                    }

            }
            sorted.add(currString);
            cards.remove(currString);
        }
        for (int i= 0 ; i < sorted.size(); i++ ) {
            curr++;
            rank.put(curr, sorted.get(i));
        }
        return curr;

    }




    public static void determineKind(     HashMap<String, ArrayList<String >>  kinds , String card) {
        String high = "high";
        String oneP = "oneP";
        String twoP= "twoP";
        String three = "three";
        String full = "full";
        String four = "four";
        String five = "five";
        Set<Character> set = new HashSet<>();
        HashMap<Character, Integer> amt = new HashMap<>();
        for (int i =0; i < card.length(); i ++) {
            set.add(card.charAt(i));

            if (amt.containsKey(card.charAt(i))) {
                amt.put(card.charAt(i), amt.get(card.charAt(i)) + 1);
            } else {
                amt.put(card.charAt(i), 1) ;
            }
        }
        if (set.size() ==1 ) {
            kinds.get(five).add(card);
            return;
        }
        if (set.size() == 5) {
            if (set.contains('J')) {
                kinds.get(oneP).add(card);
            }else {
                kinds.get(high).add(card);
            }
            return;
        }
        if (set.size() == 4) {
            if (set.contains('J')) {
                    kinds.get(three).add(card);
            }else {
                kinds.get(oneP).add(card);
            }
            return;
        }

        if (set.size() ==2 ) {
            int check = 0;
            int p1 = 0;
            int p2 = card.length() -1 ;
            while (p1 < p2 ) {
                if (card.charAt(p1) == card.charAt(p2)) {
                    check ++;
                }
                p2--;
            }
            if (check == 0 || check == 3) {
                if (set.contains('J')){
                    kinds.get(five).add(card);
                } else {
                    kinds.get(four).add(card);
                }
            } else {
                if (set.contains('J')) {
                    kinds.get(five).add(card);
                } else {
                    kinds.get(full).add(card);
                }
            }
            return;
        }
        if (set.size() ==3 ) {
            for (Character key : amt.keySet()) {
                if (amt.get(key) == 3) {
                    if (set.contains('J')) {
                        kinds.get(four).add(card);
                    } else {
                        kinds.get(three).add(card);
                    }
                    return;
                }
            }
        }
        if (set.contains('J')) {
            if (amt.get('J') == 2)  {
                kinds.get(four).add(card);
            }  else {
                kinds.get(full).add(card);
            }
        }else {
            kinds.get(twoP).add(card);
        }

    }
}
