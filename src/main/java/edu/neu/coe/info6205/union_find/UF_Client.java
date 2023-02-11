package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UF_Client {

    public static int count(int n){
        WQUPC wqupc = new WQUPC(n);
        int count = 0;
        Random random = new Random();
        while(!union(wqupc, n)){
            int[] pair = new int[] {random.nextInt(n), random.nextInt(n)};
            if(!wqupc.connected(pair[0], pair[1])){
                wqupc.union(pair[0], pair[1] );
                count++;
            }
        }
        return count;
    }

    public static boolean union(WQUPC wqupc, int n){
        for(int i = 1; i < n; i++){
            if(!wqupc.connected(0, i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.printf("n =%3d, m =%3d\n", 1, count(1));
        System.out.printf("n =%3d, m =%3d\n", 2, count(2));
        System.out.printf("n =%3d, m =%3d\n", 4, count(4));
        System.out.printf("n =%3d, m =%3d\n", 8, count(8));
        System.out.printf("n =%3d, m =%3d\n", 16, count(16));
        System.out.printf("n =%3d, m =%3d\n", 32, count(32));
        System.out.printf("n =%3d, m =%3d\n", 64, count(64));
        System.out.printf("n =%3d, m  =%3d\n", 128, count(128));
    }
}
