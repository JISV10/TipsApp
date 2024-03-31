package com.example.tipsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipsapp.model.AgroInfo.agrocards
import com.example.tipsapp.model.Agrocards
import com.example.tipsapp.ui.theme.TipsAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipsAppTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        Text(text = "30 Days of Agro ecology Tips")
                    }
                ) {   // innerPadding is the padding value to be used in the content
                      TipsList(agroList = agrocards)
                }
            }
        }
    }
}




@Composable
fun TipsList(
agroList: List<Agrocards>,
modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(agroList) { agrocards ->
            TipCard(
                tipNumber = agrocards.tipNumber,
                title = agrocards.title,
                description = agrocards.description,
                image = agrocards.image
            )
        }
    }
}



@Composable
fun TipCard(tipNumber: Int, title: Int, description: Int, image: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Tip $tipNumber")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = title))
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id =description)
                )
        }
    }
}

@Preview
@Composable
fun TipsPreview() {
    TipsAppTheme {
        TipsList(agroList = List(1) {
            Agrocards(
                tipNumber = it + 1,
                title = agrocards[1].title,
                description = agrocards[1].description,
                image = agrocards[1].image
            )
        })
    }
}