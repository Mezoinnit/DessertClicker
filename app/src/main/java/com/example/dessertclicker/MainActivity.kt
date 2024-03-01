@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.dessertclicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dessertclicker.ui.theme.BottomBar
import com.example.dessertclicker.ui.theme.DessertClickerTheme
import com.example.dessertclicker.ui.theme.ImageDefault
import com.example.dessertclicker.ui.theme.TopBar
import com.example.dessertclicker.ui.theme.shareSoldDessertsInformation
import com.example.dessertclicker.ui.theme.value

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DessertClickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DessertClickerApp()
                }
            }
        }
    }
}


@Composable
fun DessertClickerApp() {
    val intentContext = LocalContext.current
    val scrollBehaviour =
        TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            TopBar(topBarTitle = stringResource(id = R.string.TopBarTitle), scrollBehaviour,
                actionButtonClicked = {
                    shareSoldDessertsInformation(intentContext, value.doubleValue.toString())
                }
            )
        },
        bottomBar = {
            BottomBar()
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ImageDefault(img = painterResource(id = R.drawable.dessert))
            }
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    DessertClickerTheme {
        DessertClickerApp()
    }
}