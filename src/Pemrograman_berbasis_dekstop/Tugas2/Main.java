package Pemrograman_berbasis_dekstop.Tugas2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // BLOK 1: DEKLARASI DATA & INISIALISASI

    private static ArrayList<Menu> daftarMenu = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    private static void menuAwal() {
        daftarMenu.add(new Menu("Nasi Goreng Spesial", 25000, "Makanan"));
        daftarMenu.add(new Menu("Mie Goreng Jawa", 20000, "Makanan"));
        daftarMenu.add(new Menu("Ayam Bakar Madu", 30000, "Makanan"));
        daftarMenu.add(new Menu("Soto Ayam", 22000, "Makanan"));

        daftarMenu.add(new Menu("Es Teh Manis", 5000, "Minuman"));
        daftarMenu.add(new Menu("Es Jeruk", 7000, "Minuman"));
        daftarMenu.add(new Menu("Jus Alpukat", 15000, "Minuman"));
        daftarMenu.add(new Menu("Kopi Hitam", 10000, "Minuman"));
    }

    private static void tampilkanDaftarMenu() {
        System.out.println("\n------------------------------------------");
        System.out.println("            DAFTAR MENU RESTORAN          ");
        System.out.println("------------------------------------------");

        System.out.println("[ MAKANAN ]");
        for (int i = 0; i < daftarMenu.size(); i++) {
            if (daftarMenu.get(i).getKategori().equalsIgnoreCase("Makanan")) {
                System.out.printf("%d. %-22s : Rp %,.0f\n", (i + 1), daftarMenu.get(i).getNama(),
                        daftarMenu.get(i).getHarga());
            }
        }

        System.out.println("\n[ MINUMAN ]");
        for (int i = 0; i < daftarMenu.size(); i++) {
            if (daftarMenu.get(i).getKategori().equalsIgnoreCase("Minuman")) {
                System.out.printf("%d. %-22s : Rp %,.0f\n", (i + 1), daftarMenu.get(i).getNama(),
                        daftarMenu.get(i).getHarga());
            }
        }
        System.out.println("------------------------------------------");
    }

    private static void prosesPemesanan() {
        ArrayList<Menu> pesananMenu = new ArrayList<>();
        ArrayList<Integer> pesananJumlah = new ArrayList<>();

        boolean memesan = true;
        tampilkanDaftarMenu();

        while (memesan) {
            System.out.println("Ketik nomor menu untuk memesan, atau ketik 'selesai' untuk mengakhiri.");
            System.out.print("Masukkan pilihan: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("selesai")) {
                if (pesananMenu.isEmpty()) {
                    System.out.println("Anda belum memesan apapun. Kembali ke menu utama.");
                    return;
                }
                memesan = false;
            } else {
                try {
                    int nomorMenu = Integer.parseInt(input);
                    if (nomorMenu >= 1 && nomorMenu <= daftarMenu.size()) {
                        Menu menuDipilih = daftarMenu.get(nomorMenu - 1);
                        int jumlah = 0;

                        while (jumlah <= 0) {
                            System.out.print("Masukkan jumlah porsi/gelas untuk " + menuDipilih.getNama() + ": ");
                            try {
                                jumlah = Integer.parseInt(scanner.nextLine());
                                if (jumlah <= 0)
                                    System.out.println("Jumlah harus lebih besar dari 0.");
                            } catch (NumberFormatException e) {
                                System.out.println("Input jumlah harus berupa angka!");
                            }
                        }

                        pesananMenu.add(menuDipilih);
                        pesananJumlah.add(jumlah);
                        System.out.println(
                                ">> Berhasil menambahkan " + jumlah + " " + menuDipilih.getNama() + " ke pesanan.");
                    } else {
                        System.out.println(">> Nomor menu tidak tersedia. Silakan masukkan nomor sesuai daftar!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(">> Input tidak valid! Masukkan nomor menu atau 'selesai'.");
                }
            }
        }

        hitungDanCetakStruk(pesananMenu, pesananJumlah);
    }

    private static void hitungDanCetakStruk(ArrayList<Menu> pesananMenu, ArrayList<Integer> pesananJumlah) {
        double subtotal = 0;
        boolean adaMinuman = false;
        int indeksMinumanTermurah = -1;
        double hargaMinumanTermurah = Double.MAX_VALUE;

        for (int i = 0; i < pesananMenu.size(); i++) {
            Menu item = pesananMenu.get(i);
            int qty = pesananJumlah.get(i);
            subtotal += item.getHarga() * qty;

            if (item.getKategori().equalsIgnoreCase("Minuman")) {
                adaMinuman = true;
                if (item.getHarga() < hargaMinumanTermurah) {
                    hargaMinumanTermurah = item.getHarga();
                    indeksMinumanTermurah = i;
                }
            }
        }

        boolean dapatPromoBOGO = (subtotal > 50000 && adaMinuman && indeksMinumanTermurah != -1);
        String namaMinumanBOGO = dapatPromoBOGO ? pesananMenu.get(indeksMinumanTermurah).getNama() : "";

        double diskon10 = (subtotal > 100000) ? subtotal * 0.10 : 0;

        double totalSetelahDiskon = subtotal - diskon10;
        double pajak10 = totalSetelahDiskon * 0.10;
        double biayaPelayanan = 20000;
        double totalAkhir = totalSetelahDiskon + pajak10 + biayaPelayanan;

        System.out.println("\n==========================================");
        System.out.println("              STRUK PEMBAYARAN            ");
        System.out.println("==========================================");

        for (int i = 0; i < pesananMenu.size(); i++) {
            Menu item = pesananMenu.get(i);
            int qty = pesananJumlah.get(i);
            System.out.printf("%-20s %2d x Rp %,7.0f = Rp %,8.0f\n", item.getNama(), qty, item.getHarga(),
                    (item.getHarga() * qty));
        }

        if (dapatPromoBOGO) {
            System.out.printf("%-20s %2d x Rp %,7.0f = Rp %,8.0f\n", "(PROMO) " + namaMinumanBOGO, 1, 0.0, 0.0);
        }

        System.out.println("------------------------------------------");
        System.out.printf("%-32s : Rp %,8.0f\n", "Subtotal Pesanan", subtotal);
        if (diskon10 > 0) {
            System.out.printf("%-32s : -Rp %,8.0f\n", "Diskon 10% (> Rp 100rb)", diskon10);
        }
        System.out.printf("%-32s : Rp %,8.0f\n", "Pajak (10%)", pajak10);
        System.out.printf("%-32s : Rp %,8.0f\n", "Biaya Pelayanan", biayaPelayanan);
        System.out.println("------------------------------------------");
        System.out.printf("%-32s : Rp %,8.0f\n", "TOTAL BAYAR", totalAkhir);
        System.out.println("==========================================");
    }

    // BLOK 3: FITUR ADMIN

    private static void manajemenMenu() {
        boolean diManajemen = true;
        while (diManajemen) {
            System.out.println("\n------------------------------------------");
            System.out.println("          MANAJEMEN MENU RESTORAN         ");
            System.out.println("------------------------------------------");
            System.out.println("1. Tambah Menu Baru");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih opsi (1-4): ");

            try {
                int opsi = Integer.parseInt(scanner.nextLine().trim());
                switch (opsi) {
                    case 1:
                        tambahMenu();
                        break;
                    case 2:
                        ubahHargaMenu();
                        break;
                    case 3:
                        hapusMenu();
                        break;
                    case 4:
                        diManajemen = false;// akan kembali ke meny utama
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan masukkan angka 1 hingga 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid! Harap masukkan instruksi berupa angka (1-4).");
            }
        }
    }

    private static void tambahMenu() {
        boolean tambahLagi = true;
        while (tambahLagi) {
            System.out.println("\n--- Tambah Menu Baru ---");
            System.out.print("Masukkan nama menu: ");
            String nama = scanner.nextLine().trim();

            double harga = 0;
            while (harga <= 0) {
                System.out.print("Masukkan harga menu: ");
                try {
                    harga = Double.parseDouble(scanner.nextLine());
                    if (harga <= 0)
                        System.out.println("Harga harus lebih dari 0.");
                } catch (NumberFormatException e) {
                    System.out.println("Input harga harus berupa angka!");
                }
            }

            String kategori = "";
            while (!kategori.equalsIgnoreCase("Makanan") && !kategori.equalsIgnoreCase("Minuman")) {
                System.out.print("Masukkan kategori (Makanan/Minuman): ");
                kategori = scanner.nextLine().trim();
                if (!kategori.equalsIgnoreCase("Makanan") && !kategori.equalsIgnoreCase("Minuman")) {
                    System.out.println("Kategori hanya boleh 'Makanan' atau 'Minuman'.");
                }
            }

            if (konfirmasi("Apakah Anda yakin ingin menambahkan menu ini? (Ya/Tidak): ")) {
                daftarMenu.add(new Menu(nama, harga, kategori));
                System.out.println(">> Menu berhasil ditambahkan!");
            } else {
                System.out.println(">> Penambahan menu dibatalkan.");
            }

            tambahLagi = konfirmasi("Ingin menambah menu lain sekaligus? (Ya/Tidak): ");
        }
    }

    private static void ubahHargaMenu() {
        tampilkanDaftarMenu();
        int nomor = validasiInputNomorMenu("Masukkan nomor menu yang ingin diubah harganya: ");
        if (nomor == -1)
            return;

        Menu menuDipilih = daftarMenu.get(nomor - 1);
        double hargaBaru = 0;

        while (hargaBaru <= 0) {
            System.out.print("Masukkan harga baru untuk " + menuDipilih.getNama() + ": ");
            try {
                hargaBaru = Double.parseDouble(scanner.nextLine());
                if (hargaBaru <= 0)
                    System.out.println("Harga harus lebih besar dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("Input harga harus berupa angka!");
            }
        }

        if (konfirmasi("Apakah Anda yakin ingin mengubah harga menu ini? (Ya/Tidak): ")) {
            menuDipilih.setHarga(hargaBaru);
            System.out.println(">> Harga menu berhasil diperbarui!");
        } else {
            System.out.println(">> Perubahan harga dibatalkan.");
        }
    }

    private static void hapusMenu() {
        tampilkanDaftarMenu();
        int nomor = validasiInputNomorMenu("Masukkan nomor menu yang ingin dihapus: ");
        if (nomor == -1)
            return;

        Menu menuDipilih = daftarMenu.get(nomor - 1);
        if (konfirmasi("Apakah Anda yakin ingin menghapus menu '" + menuDipilih.getNama() + "'? (Ya/Tidak): ")) {
            daftarMenu.remove(nomor - 1);
            System.out.println(">> Menu berhasil dihapus!");
        } else {
            System.out.println(">> Penghapusan menu dibatalkan.");
        }
    }

    // BLOK 4: FUNGSI UTILITAS

    private static int validasiInputNomorMenu(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                int nomor = Integer.parseInt(scanner.nextLine());
                if (nomor >= 1 && nomor <= daftarMenu.size())
                    return nomor;
                System.out.println("Nomor menu tidak ditemukan. Coba lagi.");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    // UPDATE: Validasi ketat untuk input Ya/Tidak
    private static boolean konfirmasi(String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Ya")) {
                return true;
            } else if (input.equalsIgnoreCase("Tidak")) {
                return false;
            } else {
                System.out.println("Input salah! Harap masukkan 'Ya' atau 'Tidak'.");
            }
        }
    }

    // BLOK 5: METODE UTAMA (MAIN)

    public static void main(String[] args) {
        menuAwal();

        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\n==========================================");
            System.out.println("       APLIKASI RESTORAN SEDERHANA        ");
            System.out.println("==========================================");
            System.out.println("1. Menu Pelanggan (Pemesanan)");
            System.out.println("2. Manajemen Menu Restoran (Pemilik)");
            System.out.println("3. Keluar Aplikasi");
            System.out.print("Pilih menu utama (1-3): ");

            try {
                int pilihan = Integer.parseInt(scanner.nextLine().trim());
                switch (pilihan) {
                    case 1:
                        prosesPemesanan();
                        break;
                    case 2:
                        manajemenMenu();
                        break;
                    case 3:
                        berjalan = false;
                        System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan masukkan angka 1, 2, atau 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid! Harap masukkan instruksi berupa angka (1-3).");
            }
        }
    }
}