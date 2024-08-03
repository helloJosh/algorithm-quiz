package classthree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Beak5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String line1 = br.readLine();

            List<String> ops = new ArrayList<>();
            for (int j = 0; j < line1.length(); j++) {
                ops.add(String.valueOf(line1.charAt(j)));
            }

            int length = Integer.parseInt(br.readLine());

            String line2 = br.readLine();

            line2 = line2.replace("[","");
            line2 = line2.replace("]","");

            List<Integer> numbers = new ArrayList<>();

            if(!line2.isEmpty()) {
                String[] numbersInString = line2.split(",");

                for (String line : numbersInString) {
                    numbers.add(Integer.parseInt(line));
                }
            }
            boolean flag = true;

            for (int j = 0; j < ops.size() ; j++) {
                if (ops.get(j).equals("R")) {
                    flag = !flag;
                } else {
                    if (numbers.isEmpty()){
                        System.out.println("error");
                        break;
                    }
                    if(flag) {
                        numbers.remove(0);
                    } else {
                        numbers.remove(numbers.size()-1);
                    }
                }
            }
            if (!numbers.isEmpty()) {
                if (!flag) {
                    Collections.reverse(numbers);
                }
                System.out.println(numbers);
            }
        }
    }
}
