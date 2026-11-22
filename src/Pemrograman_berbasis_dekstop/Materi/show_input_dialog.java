package Pemrograman_berbasis_dekstop.Materi;

import javax.swing.JOptionPane;

public class show_input_dialog {
    public static void main(String[] args) {
        String input = JOptionPane
                .showInputDialog("pilihan: \n 1. ikan asin" + "\n 2. ikan bandeng" + "\n 3. ikan tongkol ");
        int angka = Integer.parseInt(input);
        if (angka == 1) {
            System.out.println("memilih ikan asin");
        } else if (angka == 2) {
            System.out.println("ikan bandeng");
        } else if (angka == 3) {
            System.out.println("iksn tongkol");
        } else {
            System.err.println("pilihan tidak ada");
        }

    }
}
