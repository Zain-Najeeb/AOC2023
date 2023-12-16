package Day5;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
   public static HashMap<Long, Long> seed_soil = new HashMap<>();
    public static ArrayList<HashMap<String, Long>> ranges = new ArrayList<>();
    public static HashMap<Long, Long> soil_fert = new HashMap<>();
    public static HashMap<Long, Long> fert_water = new HashMap<>();
    public static HashMap<Long, Long> water_light = new HashMap<>();
    public static HashMap<Long, Long> light_temp = new HashMap<>();
    public static HashMap<Long, Long> temp_humidity = new HashMap<>();
    public static HashMap<Long, Long> humidty_location = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException {
        File obj = new File("SRC/src/Day5/ans.txt");
        Scanner reader = new Scanner(obj);
        ArrayList<Long> seeds = new ArrayList<>();
        ArrayList<Long> minima = new ArrayList<>();

        int count = 0;

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] words = data.split("\\s+");
            if (words.length ==1 ) {
                count++;
                continue;
            }
            if (words.length == 3) {
                if (count==1) {
                    mapping(seed_soil, words, "seed to soil", 0, ranges );
                } else if (count == 2) {
                    mapping(soil_fert, words, "soil to fert",count-1, ranges);
                } else if (count == 3) {
                    mapping( fert_water, words, "fert to water",count-1, ranges);
                } else if (count == 4) {
                    mapping(water_light, words, "water to light",count-1, ranges);
                } else if (count == 5) {
                    mapping(light_temp, words, "light to temp",count-1, ranges);
                }
                else if (count == 6) {
                    mapping(temp_humidity, words, "temp to humidity",count-1, ranges);
                }
                else  {
                    mapping(humidty_location, words, "humidity to location",count-1, ranges);
                }
                continue;


            }
            if (count == 0) {
                for (int i =1 ; i< words.length; i++ ) {
                    seeds.add(Long.parseLong(words[i]));
                }
            }

        }
        for (int i =0; i < seeds.size(); i+= 2) {
            minima.add(naive(seeds.get(i+1), seeds.get(i)));
            System.out.println(i);
        }
        System.out.println(minima);
    }
    public static Long naive (Long limit, Long base) {

        long min = -1 ;
        for (int i =0 ; i < limit; i++) {

            long soil = to_to(seed_soil, ranges.get(0), base + i);
            long fert = to_to(soil_fert, ranges.get(1), soil) ;
            long water = to_to(fert_water, ranges.get(2), fert) ;
            long light = to_to(water_light, ranges.get(3), water) ;
            long temp = to_to(light_temp, ranges.get(4), light) ;
            long humid = to_to(temp_humidity, ranges.get(5), temp) ;
            long loc = to_to(humidty_location, ranges.get(6), humid) ;
            if  (min == -1) {
                min = loc;
            } else {
                if (loc < min) {
                    min = loc;
                }
            }
        }
        return  min;

    }

    public static Long to_to (HashMap<Long, Long> map, HashMap<String, Long> ranges, long seed) {

       for (Long key : map.keySet()) {
           if (key <= seed && seed <= key + ranges.get(Long.toString(key) +","+ Long.toString(map.get(key))) -1) {
               return map.get(key) + (seed - key);
           }

       }
       return seed;
    }

    public static void mapping(HashMap<Long, Long> map, String[] words, String type, int count , ArrayList<HashMap<String, Long>> ranges ) {
        long source = Long.parseLong(words[0]);
        long destination = Long.parseLong(words[1]);
        if (ranges.size() == count) {
            ranges.add(new HashMap<>());
        }
        String key = Long.toString(destination)+","+Long.toString(source);
        ranges.get(count).put(key,Long.parseLong(words[2]));

        map.put(destination, source);


    }
}