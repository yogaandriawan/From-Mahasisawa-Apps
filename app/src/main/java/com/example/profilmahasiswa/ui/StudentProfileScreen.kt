package com.example.profilmahasiswa.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Parent STATEFUL: mengelola state menggunakan remember + mutableStateOf.
 * Event onValueChange/onClick HANYA memperbarui state, bukan mengubah View manual.
 * Compose otomatis melakukan recomposition atas UI yang membaca state tersebut.
 */
@Composable
fun StudentProfileScreen() {
    var name by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var studyProgram by remember { mutableStateOf("") }
    var savedProfile by remember { mutableStateOf<StudentProfile?>(null) }

    val isFormValid = StudentProfileValidator.isFormValid(name, nim, studyProgram)
    val hasUnsavedChanges = savedProfile != null && savedProfile != StudentProfile(
        name.trim(), nim.trim(), studyProgram.trim()
    )

    StudentProfileContent(
        name = name,
        nim = nim,
        studyProgram = studyProgram,
        savedProfile = savedProfile,
        hasUnsavedChanges = hasUnsavedChanges,
        onNameChange = { name = it },
        onNimChange = { nim = it },
        onStudyProgramChange = { studyProgram = it },
        onSave = {
            if (isFormValid) {
                savedProfile = StudentProfile(
                    name = name.trim(),
                    nim = nim.trim(),
                    studyProgram = studyProgram.trim()
                )
            }
        }
    )
}

/** Child STATELESS: menerima value dan mengirim event ke parent (state hoisting). */
@Composable
private fun StudentProfileContent(
    name: String,
    nim: String,
    studyProgram: String,
    savedProfile: StudentProfile?,
    hasUnsavedChanges: Boolean,
    onNameChange: (String) -> Unit,
    onNimChange: (String) -> Unit,
    onStudyProgramChange: (String) -> Unit,
    onSave: () -> Unit
) {
    val isNameValid = StudentProfileValidator.isNameValid(name)
    val isNimValid = StudentProfileValidator.isNimValid(nim)
    val isStudyProgramValid = StudentProfileValidator.isStudyProgramValid(studyProgram)
    val validFieldCount = listOf(isNameValid, isNimValid, isStudyProgramValid).count { it }
    val formValid = validFieldCount == 3

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 18.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(
                text = "PRAKTIKUM 04 • JETPACK COMPOSE",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Form Profil Mahasiswa",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "State, Events & User Interaction",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(13.dp)
            ) {
                Text(
                    text = "Data Mahasiswa",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Lengkapi tiga kolom berikut untuk menyimpan profil.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = onNameChange,
                    modifier = Modifier.fillMaxWidth().testTag("input-nama"),
                    label = { Text("Nama Lengkap") },
                    placeholder = { Text("Contoh: Budi Santoso") },
                    singleLine = true,
                    isError = name.isNotEmpty() && !isNameValid,
                    supportingText = {
                        if (name.isNotEmpty() && !isNameValid) {
                            Text("Nama minimal 3 karakter.")
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                OutlinedTextField(
                    value = nim,
                    onValueChange = onNimChange,
                    modifier = Modifier.fillMaxWidth().testTag("input-nim"),
                    label = { Text("NIM") },
                    placeholder = { Text("Contoh: 202601234") },
                    singleLine = true,
                    isError = nim.isNotEmpty() && !isNimValid,
                    supportingText = {
                        if (nim.isNotEmpty() && !isNimValid) {
                            Text("NIM harus 6–15 digit angka.")
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value = studyProgram,
                    onValueChange = onStudyProgramChange,
                    modifier = Modifier.fillMaxWidth().testTag("input-prodi"),
                    label = { Text("Program Studi") },
                    placeholder = { Text("Contoh: Informatika") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )

                Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Kelengkapan formulir", style = MaterialTheme.typography.bodyMedium)
                        Text(
                            text = "$validFieldCount/3 valid",
                            modifier = Modifier.testTag("jumlah-input-valid"),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    LinearProgressIndicator(
                        progress = { validFieldCount / 3f },
                        modifier = Modifier.fillMaxWidth().height(7.dp).clip(CircleShape)
                    )
                }
                Button(
                    onClick = onSave,
                    enabled = formValid,
                    modifier = Modifier.fillMaxWidth().height(52.dp).testTag("tombol-simpan"),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Simpan Profil")
                }
                Text(
                    text = if (formValid) "Data valid. Profil siap disimpan."
                    else "Tombol aktif setelah semua input valid.",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (formValid) MaterialTheme.colorScheme.secondary
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth().testTag("status-validasi"),
                    textAlign = TextAlign.Center
                )
            }
        }

        SavedProfileCard(profile = savedProfile, hasUnsavedChanges = hasUnsavedChanges)
        Spacer(Modifier.height(8.dp))
    }
}

/** Kartu profil selalu berasal dari nilai state savedProfile. */
@Composable
private fun SavedProfileCard(profile: StudentProfile?, hasUnsavedChanges: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth().testTag("kartu-profil"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text("Kartu Profil", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            if (profile == null) {
                Text(
                    text = "Belum ada profil yang disimpan. Isi formulir lalu tekan Simpan Profil.",
                    modifier = Modifier.testTag("profil-kosong"),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier.size(58.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = profile.name.trim().firstOrNull()?.uppercaseChar()?.toString() ?: "M",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Column {
                        Text(
                            profile.name,
                            modifier = Modifier.testTag("profil-nama"),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Mahasiswa",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                ProfileDetailRow("NIM", profile.nim, "profil-nim")
                ProfileDetailRow("Program Studi", profile.studyProgram, "profil-prodi")
                Text(
                    text = if (hasUnsavedChanges) "Ada perubahan input yang belum disimpan."
                    else "Profil berhasil disimpan.",
                    modifier = Modifier.testTag("status-tersimpan"),
                    style = MaterialTheme.typography.labelMedium,
                    color = if (hasUnsavedChanges) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
private fun ProfileDetailRow(label: String, value: String, tag: String) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            modifier = Modifier.testTag(tag),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(name = "Sebelum Input", showBackground = true, widthDp = 393, heightDp = 800)
@Composable
private fun BeforeInputPreview() {
    ProfileAppTheme {
        StudentProfileContent(
            name = "", nim = "", studyProgram = "", savedProfile = null,
            hasUnsavedChanges = false,
            onNameChange = {}, onNimChange = {}, onStudyProgramChange = {}, onSave = {}
        )
    }
}

@Preview(name = "Sesudah Disimpan", showBackground = true, widthDp = 393, heightDp = 800)
@Composable
private fun AfterInputPreview() {
    ProfileAppTheme {
        StudentProfileContent(
            name = "Budi Santoso", nim = "202601234", studyProgram = "Informatika",
            savedProfile = StudentProfile("Budi Santoso", "202601234", "Informatika"),
            hasUnsavedChanges = false,
            onNameChange = {}, onNimChange = {}, onStudyProgramChange = {}, onSave = {}
        )
    }
}
