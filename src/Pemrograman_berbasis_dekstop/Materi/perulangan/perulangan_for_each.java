package Pemrograman_berbasis_dekstop.Materi.perulangan;

public class perulangan_for_each {
    public static void main(String[] args) {
        String[] nama = { "java", "kotlin", "C++", "PHP" };
        Double[] angka = { 14.5, 56.1, 65.3, 567.8 };

        for (String i : nama) {
            System.out.println(i);
        }
        for (Double i : angka) {
            System.out.print(i);
        }
    }
}
