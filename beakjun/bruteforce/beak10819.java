package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class beak10819 {
    public static boolean nextPermutation(int[] a){
        int i = a.length-1;
        while(i>0 && a[i-1] >= a[i]){
            i--;
        }
        if(i <= 0){
            return false;
        }
        int j = a.length -1;

        while(a[i-1] <= a[j]){
            j--;
        }

        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;

        j = a.length -1;
        while(i<j){
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;

        }

        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0;
        int t = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int[] a = new int[t];
        for(int i=0; i< t;i++){
            a[i] = Integer.parseInt(s[i]);
        }
        
        do{
            int result = 0;
            for(int i =1; i< a.length ;i++){
                result += (a[i-1] - a[i]);
            }
            if(result > ans){
                ans = result;
            }
        }while(nextPermutation(a));

    }
    
}
