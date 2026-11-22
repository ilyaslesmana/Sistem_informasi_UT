package Pemrograman_berbasis_dekstop.Tugas_3;

import java.util.Scanner;

import Pemrograman_berbasis_dekstop.Tugas_3.service.*;
import Pemrograman_berbasis_dekstop.Tugas_3.ui.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        menuService dbmenu = new menuService();
        pesananService keranjang = new pesananService();

        restauranUI.jalankanMenuUtama(dbmenu, keranjang, scan);
    }

}
