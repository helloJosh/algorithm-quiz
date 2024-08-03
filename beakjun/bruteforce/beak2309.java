package bruteforce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class beak2309{
    public static void main(String[] args) throws FileNotFoundException,IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("./test.txt")));
        String line;
        int x = 0;
        int[] a = new int[9];
        int total=0;
        while((line=br.readLine())!=null){
            a[x] = Integer.parseInt(line);
            x++;
        }
        for(int i=0;i<a.length;i++){
            total+=a[i];
        }
        Arrays.sort(a);
        
        for(int i=0; i<a.length;i++){
            for(int j=i+1;j<a.length;j++){
                if(i==j)
                    continue;
                if(total -a[i]-a[j]==100){
                    for(int k=0;k<a.length;k++){
                        if(k==i||k==j)
                            continue;
                        System.out.println(a[k]);
                    }
                    return;
                }
            }
        }
    }
}