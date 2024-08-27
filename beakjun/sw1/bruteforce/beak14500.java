package sw1.bruteforce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class beak14500 {
    private final static int BLOCK_COUNT = 19;
    
    private final static int[][][] block = {
        {{0,1}, {0,2}, {0,3}},
        {{1,0}, {2,0}, {3,0}},
        {{1,0}, {1,1}, {1,2}},
        {{0,1}, {1,0}, {2,0}},
        {{0,1}, {0,2}, {1,2}},
        {{1,0}, {2,0}, {2,-1}},
        {{0,1}, {0,2}, {-1,2}},
        {{1,0}, {2,0}, {2,1}},
        {{0,1}, {0,2}, {1,0}},
        {{0,1}, {1,1}, {2,1}},
        {{0,1}, {1,0}, {1,1}},
        {{0,1}, {-1,1}, {-1,2}},
        {{1,0}, {1,1}, {2,1}},
        {{0,1}, {1,1}, {1,2}},
        {{1,0}, {1,-1}, {2,-1}},
        {{0,1}, {0,2}, {-1,1}},
        {{0,1}, {0,2}, {1,1}},
        {{1,0}, {2,0}, {1,1}},
        {{1,0}, {2,0}, {1,-1}},
    };
    public static void main(String[] args) throws FileNotFoundException,IOException  {
        BufferedReader br = new BufferedReader(new FileReader(new File("sw_test_1/test.txt")));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] a = new int[n][m];
        int ans = 0;
        for(int i=0; i<n;i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int j=0;j<m;j++){
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int k=0;k<BLOCK_COUNT;k++){
                    boolean ok = true;
                    int sum = a[i][j];
                    for(int l=0;l<3;l++){
                        int x = i+block[k][l][0];
                        int y = i+block[k][l][1];
                        if (0 <= x && x < n && 0 <= y && y < m) {
                            sum += a[x][y];
                        } else {
                            ok = false;
                            break;
                        }
                    }
                    if(ok && ans < sum){
                        ans = sum;
                    }
                }
            }
        }

    }
}
