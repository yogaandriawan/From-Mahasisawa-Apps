package com.example.profilmahasiswa

import com.example.profilmahasiswa.ui.StudentProfileValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StudentProfileValidatorTest {
    @Test
    fun `form awal kosong tidak valid`() {
        assertFalse(StudentProfileValidator.isFormValid("", "", ""))
    }

    @Test
    fun `nama harus minimal tiga karakter setelah trim`() {
        assertFalse(StudentProfileValidator.isNameValid(" A "))
        assertTrue(StudentProfileValidator.isNameValid(" Budi "))
    }

    @Test
    fun `nim harus berupa enam hingga lima belas digit`() {
        assertFalse(StudentProfileValidator.isNimValid("12345"))
        assertFalse(StudentProfileValidator.isNimValid("20AB1234"))
        assertFalse(StudentProfileValidator.isNimValid("1234567890123456"))
        assertTrue(StudentProfileValidator.isNimValid("202601234"))
    }

    @Test
    fun `semua input benar menghasilkan form valid`() {
        assertTrue(StudentProfileValidator.isFormValid("Budi Santoso", "202601234", "Informatika"))
        assertFalse(StudentProfileValidator.isFormValid("Budi Santoso", "202601234", "  "))
    }
}
