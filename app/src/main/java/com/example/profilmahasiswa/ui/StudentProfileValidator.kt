package com.example.profilmahasiswa.ui

/** Aturan validasi sederhana yang mudah diuji tanpa menjalankan UI Compose. */
object StudentProfileValidator {
    fun isNameValid(name: String): Boolean = name.trim().length >= 3

    // Contoh aturan praktikum: NIM berisi 6 sampai 15 digit numerik.
    fun isNimValid(nim: String): Boolean = nim.trim().matches(Regex("[0-9]{6,15}"))

    fun isStudyProgramValid(studyProgram: String): Boolean = studyProgram.trim().isNotEmpty()

    fun isFormValid(name: String, nim: String, studyProgram: String): Boolean =
        isNameValid(name) && isNimValid(nim) && isStudyProgramValid(studyProgram)
}

data class StudentProfile(
    val name: String,
    val nim: String,
    val studyProgram: String
)
