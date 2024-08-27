package sw1.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beak10971 {
    public static boolean nextPermutation(int[] a){
        int i = a.length;
        while(i>0 && a[i-1] >= a[i]){
            i--;
        }
        if(i<=0){
            return false;
        }

        int j = a.length;
        while(a[i-1] <= a[j]){
            j--;
        }
        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;

        while(i < j){
            temp = a[j];
            a[j] = a[i];
            a[i] = temp;

            j--;
            i++;
        }


        return true;
    }   
    public static void main(String[] args) throws IOException ,NumberFormatException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] list = new int[n];
        int[][] a = new int[n][n];

        for(int i=0; i< n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<n;i++){
            list[i] = i;
        }
        int ans = Integer.MAX_VALUE;
        do{
            boolean ok = true;
            int result = 0;
            for(int i=0; i<n-1;i++){
                if(a[list[i]][list[i+1]] ==0){
                    ok = false;
                }else{
                    result += a[list[i-1]][list[i]];
                }
            }
            if (ok && a[list[n-1]][list[0]] != 0) {
                result += a[list[n-1]][list[0]];
                if (ans > result) {
                    ans = result;
                }
            }
        }while(nextPermutation(list));
        
        
    }
}
