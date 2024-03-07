package br.com.fiap.fiapmobile

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.fiapmobile.ui.theme.FiapmobileTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiapmobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun optIN() {
    val fineLocationPermissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    val coarseLocationPermissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_COARSE_LOCATION)

    LaunchedEffect(key1 = Unit) {
        fineLocationPermissionState.launchPermissionRequest()
        coarseLocationPermissionState.launchPermissionRequest()
    }

    when {
        fineLocationPermissionState.status.isGranted && coarseLocationPermissionState.status.isGranted -> {
            // Ambas as permissões foram concedidas
            Text("Permissão para localização precisa e aproximada concedida.")
        }
        else -> {
            // Uma ou ambas as permissões não foram concedidas
            Text("Permissão de localização não concedida.")
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier.background(Color.Magenta)) {
    Column (modifier = Modifier.fillMaxSize()) {
        optIN() // Chamada para solicitar as permissões
        Text(text = "Olá mundo")
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreen2 () {
    MainScreen()
}