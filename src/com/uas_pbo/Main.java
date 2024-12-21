package com.uas_pbo;

import java.util.ArrayList;
import java.util.Scanner;

// Kelas abstrak untuk Ruang (Abstraksi)
abstract class Room {
    // Properti private untuk enkapsulasi
    private final String namaRuang; 
    private final int kapasitas;

    // Konstruktor untuk inisialisasi properti kelas Room
    // Konstruktor ini memastikan setiap objek Room memiliki nama ruang dan kapasitas
    public Room(String namaRuang, int kapasitas) {
        this.namaRuang = namaRuang;
        this.kapasitas = kapasitas;
    }

    // Getter untuk nama ruang, memberikan akses ke properti private namaRuang
    public String getNamaRuang() {
        return namaRuang;
    }

    // Getter untuk kapasitas ruang, memberikan akses ke properti private kapasitas
    public int getKapasitas() {
        return kapasitas;
    }

    // Method abstrak (Abstraksi) yang wajib diimplementasikan oleh subclass
    public abstract void tampilkanDetail();
}

// Kelas MeetingRoom yang mewarisi Room (Pewarisan)
class MeetingRoom extends Room {
    // Konstruktor untuk MeetingRoom memanggil konstruktor superclass Room
    public MeetingRoom(String namaRuang, int kapasitas) {
        super(namaRuang, kapasitas); // Memanggil konstruktor Room
    }

    // Overriding method tampilkanDetail dari Room (Polimorfisme)
    // Implementasi spesifik untuk ruang meeting
    @Override
    public void tampilkanDetail() {
        System.out.println("Ruang Meeting: " + getNamaRuang() + ", Kapasitas: " + getKapasitas());
    }
}

// Kelas Classroom yang juga mewarisi Room (Pewarisan)
class Classroom extends Room {
    // Konstruktor untuk Classroom memanggil konstruktor superclass Room
    public Classroom(String namaRuang, int kapasitas) {
        super(namaRuang, kapasitas); // Memanggil konstruktor Room
    }

    // Overriding method tampilkanDetail dari Room (Polimorfisme)
    // Implementasi spesifik untuk ruang kelas
    @Override
    public void tampilkanDetail() {
        System.out.println("Ruang Kelas: " + getNamaRuang() + ", Kapasitas: " + getKapasitas());
    }
}

// Kelas Reservasi untuk mengelola data pemesanan
class Reservasi {
    // Properti private untuk enkapsulasi
    private final Room ruang; 
    private final String namaPemesan;
    private final String tanggal;
    private final String waktu;

    // Konstruktor untuk inisialisasi properti reservasi
    // Konstruktor memastikan setiap reservasi memiliki ruang, nama pemesan, tanggal, dan waktu
    public Reservasi(Room ruang, String namaPemesan, String tanggal, String waktu) {
        this.ruang = ruang;
        this.namaPemesan = namaPemesan;
        this.tanggal = tanggal;
        this.waktu = waktu;
    }

    // Getter untuk ruang yang dipesan
    public Room getRuang() {
        return ruang;
    }

    // Getter untuk nama pemesan
    public String getNamaPemesan() {
        return namaPemesan;
    }

    // Getter untuk tanggal reservasi
    public String getTanggal() {
        return tanggal;
    }

    // Getter untuk waktu reservasi
    public String getWaktu() {
        return waktu;
    }   

    // Method untuk menampilkan detail reservasi
    // Memanfaatkan polimorfisme untuk memanggil tampilkanDetail() dari objek Room
    public void tampilkanReservasi() {
        System.out.println("Detail Reservasi:");
        ruang.tampilkanDetail(); // Memanggil implementasi spesifik dari subclass Room
        System.out.println("Pemesan: " + namaPemesan);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Waktu: " + waktu);
    }
}

public class Main {
    // List untuk menyimpan data ruang dan reservasi
    // Menggunakan ArrayList untuk fleksibilitas dalam menambah dan menghapus data
    private static final ArrayList<Room> daftarRuang = new ArrayList<>();
    private static final ArrayList<Reservasi> daftarReservasi = new ArrayList<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            // Menambahkan contoh ruang ke daftar (Polimorfisme)
            daftarRuang.add(new MeetingRoom("Hall Perpus", 10));
            daftarRuang.add(new MeetingRoom("Ruang Rapat", 10));
            daftarRuang.add(new Classroom("Kelas Private", 30));
            daftarRuang.add(new Classroom("Kelas Terbuka", 30));

            boolean berjalan = true;

            // Loop utama untuk menu sistem
            while (berjalan) {
                System.out.println("\nSistem Reservasi Ruang");
                System.out.println("1. Lihat Daftar Ruang");
                System.out.println("2. Buat Reservasi");
                System.out.println("3. Lihat Daftar Reservasi");
                System.out.println("4. Keluar");
                System.out.print("Pilih menu: ");

                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Konsumsi newline

                // Memilih menu berdasarkan input
                switch (pilihan) {
                    case 1 -> lihatRuang(); // Menampilkan daftar ruang yang tersedia
                    case 2 -> buatReservasi(scanner); // Membuat reservasi baru
                    case 3 -> lihatReservasi(); // Menampilkan daftar reservasi
                    case 4 -> {
                        berjalan = false; // Mengakhiri loop utama
                        System.out.println("Keluar dari sistem. Terima kasih!");
                    }
                    default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            }
        }
    }

    // Method untuk menampilkan daftar ruang
    // Menggunakan polimorfisme untuk memanggil method tampilkanDetail dari objek Room
    private static void lihatRuang() {
        System.out.println("\nDaftar Ruang yang Tersedia:");
        for (Room ruang : daftarRuang) {
            ruang.tampilkanDetail(); // Polimorfisme: memanggil implementasi spesifik di subclass
        }
    }

    // Method untuk membuat reservasi baru
    // Menggunakan Scanner untuk menerima input dari pengguna
    private static void buatReservasi(Scanner scanner) {
        System.out.println("\nMasukkan Nama Pemesan: ");
        String namaPemesan = scanner.nextLine();

        System.out.println("Masukkan Tanggal (YYYY-MM-DD): ");
        String tanggal = scanner.nextLine();

        System.out.println("Masukkan Waktu (HH:MM): ");
        String waktu = scanner.nextLine();

        // Menampilkan daftar ruang untuk dipilih
        System.out.println("Pilih Ruang:");
        for (int i = 0; i < daftarRuang.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarRuang.get(i).tampilkanDetail();
        }

        int pilihanRuang = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        // Validasi input pilihan ruang
        if (pilihanRuang > 0 && pilihanRuang <= daftarRuang.size()) {
            Room ruangTerpilih = daftarRuang.get(pilihanRuang - 1); // Mendapatkan ruang berdasarkan index
            daftarReservasi.add(new Reservasi(ruangTerpilih, namaPemesan, tanggal, waktu)); // Menambahkan reservasi baru
            System.out.println("Reservasi berhasil dibuat!");
        } else {
            System.out.println("Pilihan ruang tidak valid. Silakan coba lagi.");
        }
    }

    // Method untuk menampilkan daftar reservasi
    // Menggunakan loop untuk mencetak detail setiap reservasi
    private static void lihatReservasi() {
        if (daftarReservasi.isEmpty()) { // Mengecek apakah ada reservasi
            System.out.println("\nTidak ada reservasi.");
        } else {
            System.out.println("\nDaftar Reservasi:");
            for (Reservasi reservasi : daftarReservasi) {
                reservasi.tampilkanReservasi(); // Memanggil method tampilkanReservasi
            }
        }
    }
}
