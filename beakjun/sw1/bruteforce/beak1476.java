package sw1.bruteforce;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class beak1476 {
    public static void main(String[] args) throws FileNotFoundException,IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] a = line.split(" ");
        int e = Integer.parseInt(a[0]);
        int s = Integer.parseInt(a[1]);
        int m = Integer.parseInt(a[2]);
        int count = 1;

        int E = 1;
        int S = 1;
        int M = 1;

        while(true){
            if(e == E && s == S && m == M){
                System.out.println(count);
                break;
            }
            E++;
            S++;
            M++;
            if(E%16==0){
                E=1;
            }
            if(S%29==0){
                S=1;
            }
            if(M%20==0){
                M=1;
            }
            count++;
        }
    }    
}
