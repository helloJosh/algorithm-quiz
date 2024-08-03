package bruteforce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class beak9095recursive{
    public static int go(int sum, int goal){
        if(sum == goal){
            return 1;
        }else if(sum > goal){
            return 0;
        }
        int now = 0;
        for (int i=1; i<=3; i++) {
            now += go(sum+i, goal);
        }
        return now;
    }
    public static void main(String[] args) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File("test.txt")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for(int i=0; i<t ; i++){
            st = new StringTokenizer(br.readLine());
            int goal = Integer.parseInt(st.nextToken());

            System.out.println(go(0, goal));
        }

    }
}