package com.safemod.accessibiltiyservice

import android.app.admin.DevicePolicyManager
import android.content.ComponentName

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import android.content.Intent

import android.provider.Settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen {promptForDeviceAdmin()}
                }

        }
    }

    private fun promptForDeviceAdmin() {
        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
        val componentName = ComponentName(this, MyDeviceAdminReceiver::class.java)
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "This app requires device admin permissions.")
        startActivity(intent)
    }
}

@Composable
fun MainScreen(promptForDeviceAdmin: () -> Unit) {


    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Accessibility Service Example")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {

            // Action to direct user to accessibility settings
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }) {
            Text("Enable Accessibility Service")
        }
        Button(onClick = { promptForDeviceAdmin() }) {
            Text(text = "Enable deviceAdmin Service")
        }
    }
}

