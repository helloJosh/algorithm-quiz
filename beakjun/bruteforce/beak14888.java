package bruteforce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class beak14888 {
    public static boolean nextPermutation(List<Integer> a){
        int i = a.size()-1;
        while(i>0 && a.get(i) <= a.get(i-1)){
            i-=1;
        }
        if(i<=0){
            return false;
        }
        int j = a.size()-1;
        while(a.get(j) <= a.get(i-1)){
            j-=1;
        }

        int temp = a.get(i-1);
        a.set(i-1, a.get(j));
        a.set(j, temp);

        j = a.size()-1;
        while(i<j){
            temp = a.get(i);
            a.set(i, a.get(j));
            a.set(j, temp);
            j-=1;
            i+=1;
        }        

        return true;
    }
    public static void main(String[] args) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File("test.txt")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int t = Integer.parseInt(st.nextToken());

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();


        st = new StringTokenizer(br.readLine());
        for(int i=0;i<t;i++){
            a.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            b.add(Integer.parseInt(st.nextToken()));
        }
        
        List<Integer> cal = new ArrayList<>();

        int k = 0;//+ - * /
        for(int i : b){
            for(int j=0;j<i;j++){
                cal.add(k);
            }
            k++;
        }
        System.out.println(cal);
        int maxAnswer = Integer.MIN_VALUE;
        int minAnswer = Integer.MAX_VALUE;
        do{
            int opNum = 1;
            int result = a.get(0);
            for(int i=1;i<a.size();i++){
                if(cal.get(opNum-1) == 0){
                    result += a.get(opNum);
                }else if(cal.get(opNum-1) == 1){
                    result -= a.get(opNum);
                }else if(cal.get(opNum-1)== 2){
                    result *= a.get(opNum);
                }else if(cal.get(opNum-1) == 3){
                    result /= a.get(opNum);
                }
                System.out.println(opNum);
                System.out.println(a.get(opNum));
                opNum++;
            }
            if(maxAnswer < result){
                maxAnswer = result;
            }
            if(minAnswer > result){
                minAnswer = result;
            }
        }while(nextPermutation(cal));
        System.out.println(cal);
        
        System.out.println(maxAnswer);
        System.out.println(minAnswer);
    }
}
