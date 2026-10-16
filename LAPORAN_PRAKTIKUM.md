# Laporan Singkat Praktikum 4 — Form Profil Mahasiswa

**Mata kuliah:** Pemrograman Aplikasi Bergerak  
**Topik:** State, Events & User Interaction — Jetpack Compose  
**Nama mahasiswa:** [Isi nama Anda]  
**NIM:** [Isi NIM Anda]  
**Program Studi:** [Isi program studi Anda]

## 1. Tujuan

Mengimplementasikan aplikasi Form Profil Mahasiswa menggunakan Jetpack Compose, dengan penerapan `remember`, `mutableStateOf`, event handling (`onValueChange`, `onClick`), validasi form, dan recomposition.

## 2. Hasil implementasi

Form terdiri dari tiga input: Nama Lengkap, NIM, dan Program Studi. Nilai input disimpan sebagai state di composable `StudentProfileScreen()`. Validitas input menentukan status tombol Simpan Profil: tombol nonaktif ketika ada data yang belum valid, dan aktif saat semua data valid. Setelah tombol ditekan, objek `StudentProfile` disimpan ke state `savedProfile` sehingga Kartu Profil langsung menampilkan hasil input. Saat isi form diubah lagi, aplikasi menampilkan peringatan perubahan yang belum disimpan.

### Alur State dan Event

1. Pengguna mengetik pada `OutlinedTextField` → event `onValueChange` dipanggil.
2. Callback mengubah `name`, `nim`, atau `studyProgram` yang dibuat dengan `remember { mutableStateOf(...) }`.
3. Compose merekomposisi UI yang membaca state, sehingga indikator kelengkapan, validasi, dan keadaan tombol diperbarui otomatis.
4. Tombol Simpan memanggil `onClick` → `savedProfile` diubah → kartu profil menampilkan data terbaru.

## 3. Bagian implementasi UI yang paling sulit

Bagian yang paling sulit adalah **menghubungkan validasi tiga input dengan status tombol Simpan dan kartu profil tanpa mengubah tampilan secara manual**. Tantangannya bukan sekadar menerima teks, tetapi memastikan pengguna mendapat respons yang konsisten ketika nilai input berubah, ketika data valid, setelah menekan Simpan, dan ketika formulir diedit kembali.

Solusinya adalah memisahkan **state** dan **tampilan**. State input disimpan di komponen induk menggunakan `remember` dan `mutableStateOf`, sedangkan validasi dihitung dari nilai state terkini. Tombol menggunakan `enabled = formValid`, dan kartu profil membaca `savedProfile`, bukan langsung mengambil teks dari komponen input. Dengan cara tersebut, perubahan UI terjadi melalui **recomposition** sesuai alur praktikum: **User Event → Update State → Recomposition → UI Refresh**.

Pendekatan ini juga menerapkan *state hoisting*: komponen form menerima nilai dan callback dari parent. Alur data menjadi lebih mudah dipahami, dipelihara, dan diuji.

## 4. Mengapa perubahan data sebaiknya dikendalikan melalui state?

Karena Jetpack Compose bersifat deklaratif: tampilan merupakan representasi dari nilai state. Saat event memperbarui state, Compose otomatis memperbarui bagian UI yang membutuhkan nilai tersebut. Hal ini menghindari perubahan elemen UI manual yang rentan tidak sinkron dengan data.

## 5. Pengujian dan bukti

| Skenario | Hasil yang diharapkan | Bukti |
| --- | --- | --- |
| Form kosong | Indikator `0/3`, tombol nonaktif, kartu belum ada data | `screenshots/sebelum-input.png` |
| Input tidak valid | Pesan kesalahan, tombol tetap nonaktif | Uji langsung di emulator |
| Tiga input valid | Indikator `3/3`, tombol aktif | Uji langsung di emulator |
| Tekan Simpan | Profil tampil, pesan sukses | `screenshots/sesudah-input.png` |
| Edit tanpa Simpan | Muncul pesan perubahan belum disimpan | Uji langsung di emulator |

**Status bukti:** Screenshot nyata harus diambil setelah menjalankan project di emulator/perangkat. File ini tidak mengklaim aplikasi telah dijalankan atau diuji bila screenshot dan hasil uji belum dilampirkan.

## 6. Repository

**Link GitHub:** [Isi setelah push ke GitHub]
