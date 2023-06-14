package uz.lazydevv.duolingobutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uz.lazydevv.duolingobutton.ui.components.DuolingoButton
import uz.lazydevv.duolingobutton.ui.theme.DuolingoButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DuolingoButtonTheme(dynamicColor = false) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    DuolingoButton(
                        modifier = Modifier.fillMaxWidth(0.6f),
                        text = stringResource(id = R.string.app_name),
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_3a")
@Composable
fun GreetingPreview() {
    DuolingoButtonTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DuolingoButton(
                modifier = Modifier.fillMaxWidth(0.6f),
                text = stringResource(id = R.string.app_name),
                onClick = {}
            )
        }
    }
}