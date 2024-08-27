package sw1.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beak6603 {
    public static boolean nextPermutation(int[] a){
        int i = a.length;
        while(i > 0 && a[i-1] >= a[i]){
            i--;
        }
        if(i<=0){
            return false;
        }

        int j = a.length;
        while(a[i-1] <= a[j]){
            j--;
        }
        int temp = a[j];
        a[j] = a[i-1];
        a[i-1]= temp;


        j = a.length -1;
        while(i < j){
            temp = a[j];
            a[j] = a[i];
            a[i] = temp;
            i++;
            j--;
        }
        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line=br.readLine()).equals("0")){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int[] a= new int[t];
            for(int i=0; i< t; i++){
                a[i] = Integer.parseInt(st.nextToken());
            }
            int[] b= new int[t];
            for(int i=0;i<t;i++){
                if(i<6){
                    b[i] =1;
                } else{
                    b[i] = 0;
                }
            }

            do{
                StringBuilder sb = new StringBuilder();
                for(int i=0 ;i<t;i++){
                    int num = a[i]*b[i];
                    if(num != 0){
                        sb.append(num+"");
                    }
                }
                System.out.println(sb.toString());
            }while(nextPermutation(b));

        }
        
    }
}
