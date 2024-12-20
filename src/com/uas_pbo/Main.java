package com.uas_pbo;

import java.util.ArrayList;
import java.util.Scanner;

// Kelas abstrak untuk Ruang
abstract class Room {
    private final String namaRuang;
    private final int kapasitas;

    public Room(String namaRuang, int kapasitas) {
        this.namaRuang = namaRuang;
        this.kapasitas = kapasitas;
    }

    public String getNamaRuang() {
        return namaRuang;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public abstract void tampilkanDetail();
}

// Kelas MeetingRoom yang mewarisi Room
class MeetingRoom extends Room {
    public MeetingRoom(String namaRuang, int kapasitas) {
        super(namaRuang, kapasitas);
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("Ruang Meeting: " + getNamaRuang() + ", Kapasitas: " + getKapasitas());
    }
}

// Kelas Classroom yang mewarisi Room
class Classroom extends Room {
    public Classroom(String namaRuang, int kapasitas) {
        super(namaRuang, kapasitas);
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("Ruang Kelas: " + getNamaRuang() + ", Kapasitas: " + getKapasitas());
    }
}

// Kelas Reservasi
class Reservasi {
    private final Room ruang;
    private final String namaPemesan;
    private final String tanggal;
    private final String waktu;

    public Reservasi(Room ruang, String namaPemesan, String tanggal, String waktu) {
        this.ruang = ruang;
        this.namaPemesan = namaPemesan;
        this.tanggal = tanggal;
        this.waktu = waktu;
    }

    public Room getRuang() {
        return ruang;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getWaktu() {
        return waktu;
    }   

    public void tampilkanReservasi() {
        System.out.println("Detail Reservasi:");
        ruang.tampilkanDetail();
        System.out.println("Pemesan: " + namaPemesan);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Waktu: " + waktu);
    }
}

public class Main {
    private static final ArrayList<Room> daftarRuang = new ArrayList<>();
    private static final ArrayList<Reservasi> daftarReservasi = new ArrayList<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            // Menambahkan contoh ruang
            daftarRuang.add(new MeetingRoom("Hall Perpus", 10));
            daftarRuang.add(new MeetingRoom("Ruang Rapat", 10));
            daftarRuang.add(new Classroom("Kelas Private", 30));
            daftarRuang.add(new Classroom("Kelas Terbuka", 30));

            boolean berjalan = true;

            while (berjalan) {
                System.out.println("\nSistem Reservasi Ruang");
                System.out.println("1. Lihat Daftar Ruang");
                System.out.println("2. Buat Reservasi");
                System.out.println("3. Lihat Daftar Reservasi");
                System.out.println("4. Keluar");
                System.out.print("Pilih menu: ");

                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Konsumsi newline

                switch (pilihan) {
                    case 1 -> lihatRuang();
                    case 2 -> buatReservasi(scanner);
                    case 3 -> lihatReservasi();
                    case 4 -> {
                        berjalan = false;
                        System.out.println("Keluar dari sistem. Terima kasih!");
                    }
                    default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            }
        }
    }

    // Menampilkan daftar ruang yang tersedia
    private static void lihatRuang() {
        System.out.println("\nDaftar Ruang yang Tersedia:");
        for (Room ruang : daftarRuang) {
            ruang.tampilkanDetail();
        }
    }

    // Membuat reservasi baru
    private static void buatReservasi(Scanner scanner) {
        System.out.println("\nMasukkan Nama Pemesan: ");
        String namaPemesan = scanner.nextLine();

        System.out.println("Masukkan Tanggal (YYYY-MM-DD): ");
        String tanggal = scanner.nextLine();

        System.out.println("Masukkan Waktu (HH:MM): ");
        String waktu = scanner.nextLine();

        System.out.println("Pilih Ruang:");
        for (int i = 0; i < daftarRuang.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarRuang.get(i).tampilkanDetail();
        }

        int pilihanRuang = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        if (pilihanRuang > 0 && pilihanRuang <= daftarRuang.size()) {
            Room ruangTerpilih = daftarRuang.get(pilihanRuang - 1);
            daftarReservasi.add(new Reservasi(ruangTerpilih, namaPemesan, tanggal, waktu));
            System.out.println("Reservasi berhasil dibuat!");
        } else {
            System.out.println("Pilihan ruang tidak valid. Silakan coba lagi.");
        }
    }

    // Menampilkan daftar reservasi
    private static void lihatReservasi() {
        if (daftarReservasi.isEmpty()) {
            System.out.println("\nTidak ada reservasi.");
        } else {
            System.out.println("\nDaftar Reservasi:");
            for (Reservasi reservasi : daftarReservasi) {
                reservasi.tampilkanReservasi();
            }
        }
    }
}
