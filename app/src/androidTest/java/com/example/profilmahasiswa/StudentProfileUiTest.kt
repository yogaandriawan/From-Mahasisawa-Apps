package com.example.profilmahasiswa

import androidx.compose.ui.test.assertDoesNotExist
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StudentProfileUiTest {
    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun formKosong_TombolNonaktif() {
        rule.onNodeWithTag("tombol-simpan").assertIsNotEnabled()
        rule.onNodeWithTag("profil-kosong").assertExists()
    }

    @Test
    fun inputValid_DanKlikSimpan_MengubahKartuProfil() {
        rule.onNodeWithTag("input-nama").performTextInput("Budi Santoso")
        rule.onNodeWithTag("input-nim").performTextInput("202601234")
        rule.onNodeWithTag("input-prodi").performTextInput("Informatika")

        rule.onNodeWithTag("tombol-simpan").assertIsEnabled()
        rule.onNodeWithTag("tombol-simpan").performClick()

        rule.onNodeWithTag("profil-kosong").assertDoesNotExist()
        rule.onNodeWithTag("profil-nama").assertTextEquals("Budi Santoso")
        rule.onNodeWithTag("profil-nim").assertTextEquals("202601234")
        rule.onNodeWithTag("profil-prodi").assertTextEquals("Informatika")
        rule.onNodeWithTag("status-tersimpan").assertTextEquals("Profil berhasil disimpan.")
    }
}
