# Form Profil Mahasiswa — Jetpack Compose

**Praktikum 4: State, Events & User Interaction**  
Mata kuliah: Pemrograman Aplikasi Bergerak — Universitas Mulia.

Aplikasi Android satu layar yang menerima **Nama Lengkap**, **NIM**, dan **Program Studi**, memvalidasi data, dan menampilkan informasi mahasiswa pada **Kartu Profil** setelah tombol **Simpan Profil** ditekan.

## Implementasi sesuai materi

| Ketentuan materi | Implementasi pada project |
| --- | --- |
| Minimal 3 `TextField` | Tiga `OutlinedTextField`: Nama, NIM, Program Studi |
| State dengan `remember` dan `mutableStateOf` | Variabel `name`, `nim`, `studyProgram`, `savedProfile` pada `StudentProfileScreen()` |
| Event `onValueChange` | Ketikan pengguna memodifikasi state di composable induk |
| Event `onClick` | Tombol Simpan mengubah state `savedProfile` |
| Validasi & tombol aktif hanya saat valid | Nama ≥ 3 karakter, NIM 6–15 digit, Program Studi tidak kosong |
| Recomposition | Progress validasi, status tombol, pesan error, dan Kartu Profil otomatis diperbarui |
| State hoisting (materi halaman 9) | `StudentProfileContent` menerima value & callback dari `StudentProfileScreen` |
| Pesan umpan balik | Status input, error validasi, pesan simpan dan perubahan yang belum disimpan |

**Alur utama:** User Event → Update State → Recomposition → UI Refresh.

### Struktur project

```text
FormProfilMahasiswa/
├── app/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   └── java/com/example/profilmahasiswa/
│       │       ├── MainActivity.kt
│       │       └── ui/
│       │           ├── Theme.kt
│       │           ├── StudentProfileScreen.kt
│       │           └── StudentProfileValidator.kt
│       ├── test/.../StudentProfileValidatorTest.kt
│       └── androidTest/.../StudentProfileUiTest.kt
├── gradle/wrapper/gradle-wrapper.properties
├── gradlew / gradlew.bat
├── screenshots/
├── LAPORAN_PRAKTIKUM.md
└── README.md
```

## Persyaratan

- Android Studio versi modern dengan **Android SDK 35** dan JDK 17 atau lebih baru (direkomendasikan JDK 17).
- Emulator Android atau perangkat Android dengan API minimal 24.
- Koneksi internet saat pertama kali Gradle mengunduh dependensi.

**Catatan Gradle Wrapper:** Source ZIP menyediakan launcher `gradlew`, `gradlew.bat`, dan properties versi 8.9, tetapi tidak menyertakan file binary `gradle-wrapper.jar`. Launcher akan mengunduh wrapper resmi dari repository Gradle pada pemanggilan pertama. Jika memakai Android Studio, jalankan perintah bootstrap berikut **sekali** sebelum Sync, atau gunakan terminal bawaan Android Studio.

### Menjalankan di Windows

1. Ekstrak project ZIP. **Sebelum membuka Android Studio untuk Sync**, buka terminal/PowerShell di folder `FormProfilMahasiswa`.
2. Jalankan bootstrap wrapper berikut sekali (butuh koneksi internet):

   ```powershell
   .\gradlew.bat --version
   ```

3. Buka direktori project di Android Studio: **File → Open → FormProfilMahasiswa**.
4. Pastikan Android SDK 35 tersedia (**SDK Manager**) dan **Gradle JDK** terkonfigurasi. Klik **Sync Project with Gradle Files** dan tunggu dependency selesai.
5. Pilih emulator/perangkat lalu tekan **Run 'app'**.

### Menjalankan di Linux / macOS

```bash
chmod +x gradlew
./gradlew --version
./gradlew :app:assembleDebug
```

> Project dibuat berdasarkan materi dan dilakukan pemeriksaan statis terhadap source code, namun **build APK dan eksekusi emulator belum diverifikasi** pada lingkungan pembuatan project karena Android SDK / emulator tidak tersedia.

## Uji manual sebelum mengumpulkan

1. **Sebelum input:** buka aplikasi dengan semua kolom kosong. Indikator `0/3 valid`, tombol nonaktif, dan Kartu Profil masih kosong.
2. **Uji validasi:** isi nama `Bu` dan NIM `123`. Pesan error muncul dan tombol tetap nonaktif.
3. **Setelah input valid:** masukkan Nama `Budi Santoso`, NIM `202601234`, Program Studi `Informatika`. Indikator `3/3 valid` dan tombol aktif.
4. Tekan **Simpan Profil**. Kartu Profil menampilkan informasi yang disimpan beserta pesan sukses.
5. Edit nama menjadi `Budi Pratama` tanpa menyimpan. Kartu Profil tetap berisi data lama dan status menunjukkan ada perubahan yang belum disimpan. Tekan **Simpan Profil** lagi; kartu profil berubah tanpa manipulasi UI manual.

## Bukti screenshot tugas

Simpan dua screenshot **asli dari emulator/perangkat** setelah menjalankan aplikasi:

- `screenshots/sebelum-input.png`: sebelum mengisi kolom; tampil tombol nonaktif dan kartu profil kosong.
- `screenshots/sesudah-input.png`: setelah semua kolom valid **dan tombol Simpan Profil ditekan**; tampil isi kartu profil.

Gunakan **Device Manager / Running Devices → Take Screenshot** di Android Studio, atau terminal ADB:

```bash
adb shell screencap -p /sdcard/sebelum-input.png
adb pull /sdcard/sebelum-input.png screenshots/sebelum-input.png
# Isi input, lalu tekan Simpan Profil
adb shell screencap -p /sdcard/sesudah-input.png
adb pull /sdcard/sesudah-input.png screenshots/sesudah-input.png
```

Jika tinggi layar tidak muat seluruh kartu, gulir hingga terlihat bagian yang membuktikan perubahan state. Contoh isi form tersedia pada bagian uji manual.

## Menjalankan test

```bash
# Unit test aturan validasi
./gradlew :app:testDebugUnitTest

# UI test membutuhkan emulator/perangkat tersambung
./gradlew :app:connectedDebugAndroidTest
```

## Pengumpulan ke GitHub

Project ini memiliki **minimal 3 commit lokal** bermakna (inisialisasi, implementasi, dokumentasi/test). Riwayat git ikut berada dalam ZIP. Setelah ekstrak, buka terminal di folder project dan jalankan:

```bash
# Ganti URL berikut dengan repository kosong yang Anda buat di GitHub.
git remote add origin https://github.com/USERNAME/FormProfilMahasiswa.git
git push -u origin main
```

> Repository GitHub tidak dibuat atau diunggah secara otomatis. Tambahkan screenshot asli sebelum commit/push terakhir jika dosen meminta bukti juga di repository. Pastikan identitas Git saat push sesuai akun Anda (identitas commit di ZIP adalah placeholder project).

**Yang diserahkan ke LMS:** tautan repository GitHub, `screenshots/sebelum-input.png`, `screenshots/sesudah-input.png`, dan refleksi dalam `LAPORAN_PRAKTIKUM.md`.
