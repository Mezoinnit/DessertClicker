@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.dessertclicker.ui.theme

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.dessertclicker.R

@Composable
fun DefaultTitle(title: String) {
    Text(text = title, style = MaterialTheme.typography.titleLarge)
}

@Composable
fun DefaultButton(isClicked: () -> Unit, text: String) {
    Button(
        onClick = isClicked,
        modifier = Modifier.height(55.dp)
    ) {
        Text(text = text)
    }
}

var fieldValue = mutableStateOf("")

@Composable
fun DefaultTextField(labelText: String, placeholderText: String) {
    TextField(
        value = fieldValue.value, onValueChange = { fieldValue.value = it },
        modifier = Modifier
            .padding(start = 16.dp)
            .clip(RoundedCornerShape(100))
            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(100)),
        placeholder = { Text(text = placeholderText, modifier = Modifier.padding(start = 16.dp)) },
        label = { Text(text = labelText, modifier = Modifier.padding(start = 16.dp)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )
    )
}

var value = mutableDoubleStateOf(0.0)
var count = mutableIntStateOf(0)

@Composable
fun ImageDefault(img: Painter) {
    Image(
        painter = img, contentDescription = null,
        modifier = Modifier
            .clickable {
                value.doubleValue += 8.6
                count.intValue++
            }
    )
}

@ExperimentalMaterial3Api
@Composable
fun TopBar(
    topBarTitle: String,
    scrollBehaviour: TopAppBarScrollBehavior,
    actionButtonClicked: () -> Unit
) {
    TopAppBar(
        title = { DefaultTitle(title = topBarTitle) },
        actions = {
            IconButton(onClick = actionButtonClicked) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = stringResource(id = R.string.Share)
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        scrollBehavior = scrollBehaviour
    )
}

@Composable
fun BottomBar() {
    val rnd = String.format("%.1f", value.doubleValue)
    BottomAppBar(
        actions = {
            Text(text = "Desserts Sold!\nTotal Revenue!")
        },
        floatingActionButton = {
            Text(text = "${count.intValue}\n$${rnd}", modifier = Modifier.padding(top = 6.dp), textAlign = TextAlign.Right)
        },

        modifier = Modifier.clip(RoundedCornerShape(20, 20, 0, 0))
    )
}

fun shareSoldDessertsInformation(intentContext: Context, value:String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            intentContext.getString(R.string.share_text, value)
        )
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(
        sendIntent,
        "Sharing your personal score in the main game to your closest friends!"
    )

    try {
        ContextCompat.startActivity(intentContext, shareIntent, null)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            intentContext,
            intentContext.getString(R.string.ShitNotAvailable),
            Toast.LENGTH_LONG
        ).show()
    }
}
