package com.example.profilmahasiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.profilmahasiswa.ui.ProfileAppTheme
import com.example.profilmahasiswa.ui.StudentProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileAppTheme {
                StudentProfileScreen()
            }
        }
    }
}
