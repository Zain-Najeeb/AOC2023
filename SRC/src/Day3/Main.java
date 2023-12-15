package Day3;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File obj = new File("SRC/src/Day3/ans.txt");
        Scanner reader = new Scanner(obj);

        ArrayList<ArrayList<Character>> twoD = new ArrayList<>();
        while (reader.hasNextLine()) {
            ArrayList<Character> line = new ArrayList<>();
            String data = reader.nextLine();

            for (int i=0;i < data.length(); i++) {
                line.add(data.charAt(i));

            }
            twoD.add(line);
        }
        helperP2(twoD);
    }

    public static void helperP1(   ArrayList<ArrayList<Character>> twoD ) {
        int sum = 0;
        int num = 0;
        boolean start = false;
        boolean valid = false;
        ArrayList<Character> symbols = new ArrayList<>();
        symbols.add('+');
        symbols.add('$');
        symbols.add('=');
        symbols.add('-');
        symbols.add('*');
        symbols.add('@');
        symbols.add('#');
        symbols.add('/');
        symbols.add('%');
        symbols.add('&');
        for (int i =0 ; i<twoD.size(); i++) {

            num = 0;
            for (int j = 0; j < twoD.get(i).size(); j++) {
                if (twoD.get(i).get(j) - '0' <= 9 && twoD.get(i).get(j) - '0' >= 0) {

                    if (!start) {
                        start= true;
                        num = twoD.get(i).get(j) - '0';
                    } else {
                        num = num*10 +  twoD.get(i).get(j) - '0';
                    }
                    if (!valid) {
                        if (i != 0) {
                            if (symbols.contains(twoD.get(i-1).get(j))) {
                                valid = true;
                                continue;
                            }
                        }

                        if (i+1 != twoD.size()) {
                            if (symbols.contains(twoD.get(i+1).get(j))) {
                                valid = true;
                                continue;
                            }
                        }
                        if (j!= 0) {
                            if (symbols.contains(twoD.get(i).get(j-1))) {
                                valid = true;
                                continue;
                            }
                            if (i != 0) {
                                if (symbols.contains(twoD.get(i-1).get(j-1))) {
                                    valid = true;
                                    continue;
                                }
                            }
                            if (i+1 != twoD.size()) {
                                if (symbols.contains(twoD.get(i+1).get(j-1)) ) {
                                    valid = true;
                                    continue;
                                }
                            }
                        }
                        if (j+1 != twoD.get(i).size()) {
                            if (symbols.contains(twoD.get(i).get(j+1))) {
                                valid = true;
                                continue;
                            }
                            if (i != 0) {
                                if (symbols.contains(twoD.get(i-1).get(j+1))) {
                                    valid = true;
                                    continue;
                                }
                            }
                            if (i+1 != twoD.size()) {
                                if (symbols.contains(twoD.get(i+1).get(j+1)) ) {
                                    valid = true;
                                }
                            }
                        }

                    } else {
                        if (j + 1 ==  twoD.get(i).size()) {
                            start = false;
                            System.out.println(num);
                            sum += num;
                            valid = false;

                        }
                    }
                } else  {
                    start = false;
                    if (valid) {
                        System.out.println(num);
                        sum += num;
                        valid = false;
                    }
                }


            }
        }
        System.out.println(sum);
    }
    public static void helperP2(   ArrayList<ArrayList<Character>> twoD ) {
        int sum = 0;
        int num = 0;
        HashMap<String, point> keys = new HashMap<>();
        boolean start = false;
        boolean valid = false;
        ArrayList<Character> symbols = new ArrayList<>();
        symbols.add('*');

        for (int i =0 ; i<twoD.size(); i++) {

            num = 0;
            for (int j = 0; j < twoD.get(i).size(); j++) {
                if (twoD.get(i).get(j) - '0' <= 9 && twoD.get(i).get(j) - '0' >= 0) {
                    if (!start) {
                        start= true;
                        num = twoD.get(i).get(j) - '0';
                        keys.put(String.valueOf(i) + ','+ String.valueOf(j), new point(i ,j ));
                    } else {
                        num = num*10 +  twoD.get(i).get(j) - '0';
                        keys.put(String.valueOf(i) + ','+ String.valueOf(j), keys.get(String.valueOf(i) + ','+ String.valueOf(j-1)));
                    }
                    if (j + 1 == twoD.get(i).size()) {
                        keys.get( String.valueOf(i) + ','+ String.valueOf(j)).setValue(num);
                        start = false;
                    }
                } else  {
                    if (start) {
                        keys.get(String.valueOf(i) + ','+ String.valueOf(j-1)).setValue(num);

                        start = false;
                    }
                }
            }
        }



        for (int i =0 ; i<twoD.size(); i++) {


            for (int j = 0; j < twoD.get(i).size(); j++) {
                String Origi = "";
                num = 0;
                if (twoD.get(i).get(j) == '*') {
                    if (i != 0) {
                        String key = String.valueOf(i - 1) + ','+ String.valueOf(j);
                        if (keys.containsKey(key)) {
                            if (!(keys.get(key).isChecked())) {
                                keys.get(key).setChecked(true);
                                num = keys.get(key).getValue();
                                Origi = String.valueOf(keys.get(key).getX()) + " "  + String.valueOf(keys.get(key).getY());
                                 System.out.println(num + " " + keys.get(key).getValue() + " " + key);
                            }
                        }
                    }


                    if (i + 1 != twoD.size()) {
                        String key = String.valueOf(i + 1) + ','+ String.valueOf(j);
                        if (keys.containsKey(key)) {
                            if (!(keys.get(key).isChecked())) {
                                keys.get(key).setChecked(true);
                                if (num == 0) {
                                    num = keys.get(key).getValue();
                                    Origi = String.valueOf(keys.get(key).getX()) + " "  + String.valueOf(keys.get(key).getY());
                                } else {
                                    System.out.println(num + " " + keys.get(key).getValue() + " " + key + " "+Origi);
                                    num = num * keys.get(key).getValue();
                                    sum += num;
                                    continue;
                                }
                            }
                        }
                    }
                    if (j != 0) {
                        String key = String.valueOf(i) + ','+ String.valueOf(j - 1);
                        if (keys.containsKey(key)) {
                            if (!(keys.get(key).isChecked())) {

                                keys.get(key).setChecked(true);
                                if (num == 0) {
                                    num = keys.get(key).getValue();
                                    Origi = String.valueOf(keys.get(key).getX()) + " "  + String.valueOf(keys.get(key).getY());
                                } else {
                                    System.out.println(num + " " + keys.get(key).getValue() + " " + key + " "+Origi);
                                    num = num * keys.get(key).getValue();
                                    sum += num;
                                    continue;
                                }
                            }
                        }
                        key = String.valueOf(i - 1) + ','+ String.valueOf(j - 1);
                        if (keys.containsKey(key)) {
                            if (!(keys.get(key).isChecked())) {
                                keys.get(key).setChecked(true);
                                if (num == 0) {
                                    num = keys.get(key).getValue();
                                    Origi = String.valueOf(keys.get(key).getX()) + ","  + String.valueOf(keys.get(key).getY());
                                } else {
                                    System.out.println(num + " " + keys.get(key).getValue() + " " + key + " "+Origi);
                                    num = num * keys.get(key).getValue();
                                    sum += num;
                                    continue;
                                }
                            }
                        }

                        if (i + 1 != twoD.size()) {
                            key = String.valueOf(i + 1) + ','+ String.valueOf(j - 1);
                            if (keys.containsKey(key)) {
                                if (!(keys.get(key).isChecked())) {
                                    keys.get(key).setChecked(true);
                                    if (num == 0) {
                                        num = keys.get(key).getValue();
                                        Origi = String.valueOf(keys.get(key).getX()) + " "  + String.valueOf(keys.get(key).getY());
                                    } else {
                                        System.out.println(num + " " + keys.get(key).getValue() + " " + key + " "+Origi);
                                        num = num * keys.get(key).getValue();
                                        sum += num;
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    if (j + 1 != twoD.get(i).size()) {

                        String key = String.valueOf(i) + ','+ String.valueOf(j + 1);
                        if (keys.containsKey(key)) {
                            if (!(keys.get(key).isChecked())) {
                                keys.get(key).setChecked(true);
                                if (num == 0) {
                                    num = keys.get(key).getValue();
                                    Origi = String.valueOf(keys.get(key).getX()) + " "  + String.valueOf(keys.get(key).getY());
                                } else {
                                    System.out.println(num + " " + keys.get(key).getValue() + " " + key + " "+Origi);
                                    num = num * keys.get(key).getValue();
                                    sum += num;
                                    continue;
                                }
                            }
                        }


                        if (i != 0) {
                            key = String.valueOf(i - 1) + ','+ String.valueOf(j + 1);
                            if (keys.containsKey(key)) {
                                if (!(keys.get(key).isChecked())) {
                                    keys.get(key).setChecked(true);
                                    if (num == 0) {
                                        num = keys.get(key).getValue();
                                        Origi = String.valueOf(keys.get(key).getX()) + " "  + String.valueOf(keys.get(key).getY());
                                    } else {
                                        System.out.println(num + " " + keys.get(key).getValue() + " " + key + " "+Origi);
                                        num = num * keys.get(key).getValue();
                                        sum += num;
                                        continue;
                                    }
                                }
                            }
                        }
                        if (i + 1 != twoD.size()) {

                            key = String.valueOf(i + 1) + ','+ String.valueOf(j + 1);
                            if (keys.containsKey(key)) {
                                if (!(keys.get(key).isChecked())) {
                                    keys.get(key).setChecked(true);
                                    if (num == 0) {
                                        num = keys.get(key).getValue();
                                        Origi = String.valueOf(keys.get(key).getX()) + " "  + String.valueOf(keys.get(key).getY());
                                    } else {
                                        System.out.println(num + " " + keys.get(key).getValue() + " " + key + " "+Origi);
                                        num = num * keys.get(key).getValue();
                                        sum += num;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }



        System.out.println(sum);
    }
}