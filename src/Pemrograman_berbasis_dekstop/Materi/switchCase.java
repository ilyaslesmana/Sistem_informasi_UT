package Pemrograman_berbasis_dekstop.Materi;

import java.util.Scanner;

public class switchCase {
    public static void main(String[] hady) {
        Scanner input = new Scanner(System.in);
        int panjang, lebar;
        double luas, keliling;
        int pilih;
        System.out.println("==menghitung luas dan keliling persegi panjang==");
        System.out.print("masukan panjang : ");
        panjang = input.nextInt();
        System.out.print("masukan lebar : ");
        lebar = input.nextInt();
        System.out.println("1. luas \n2. keliling");
        System.out.print("masukan pilihan : ");
        pilih = input.nextInt();
        switch (pilih) {
            case 1:
                luas = panjang * lebar;
                System.out.println("luas persegi panjang : " + luas);
                break;
            case 2:
                keliling = (2 * panjang) + (2 * lebar);
                System.out.println("luas persegi panjang : " + keliling);
                break;
        }
        input.close();
    }

}
