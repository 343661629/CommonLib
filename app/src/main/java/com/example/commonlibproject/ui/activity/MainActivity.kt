package com.example.commonlibproject.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.commonlibproject.ui.theme.CommonLibProjectTheme
import com.example.commonlibproject.viewmodel.NewVideModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CommonLibProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)

                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier,viewModel: NewVideModel = NewVideModel()) {
    Text(
        text = "Hello $name!",
        modifier = modifier.then(Modifier.clickable {
            Log.d("MainActivity", "Hello $name!")
            viewModel.getNewType()
        })
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CommonLibProjectTheme {
        Greeting("Android")
    }
}