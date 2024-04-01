package com.example.tipsapp

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioHighBouncy
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipsapp.model.Agrocards
import com.example.tipsapp.ui.theme.TipsAppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TipsList(
    agrocards: List<Agrocards>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    // Fade in entry animation for the entire list
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ){
        LazyColumn(modifier = modifier,
                   contentPadding = contentPadding) {
            itemsIndexed(agrocards){ index, agrocard ->
                TipCard(
                    agrocard = agrocard,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        // Animate each list item to slide in vertically
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) }
                            )
                        )
                    )
                }
            }
        }
    }

@Composable
fun TipCard(
    agrocard: Agrocards,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }


    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),

    ) {
        Column(
            modifier = Modifier

                .clickable(onClick = { expanded = !expanded })
                .padding(4.dp)
        ) {
            Text(
                text = "Tip " + stringResource(agrocard.tipNumber),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(id = agrocard.title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Image(
                painter = painterResource(id = agrocard.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.large),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = stringResource(id = agrocard.description),
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TipsPreviewLight() {
val agrocard = Agrocards(
    R.string.tip_1_number,
    R.string.tip_1_title,
    R.string.tip_1,
    R.drawable.tip_1

    )
    TipsAppTheme {
        TipCard(agrocard = agrocard)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TipsPreviewDark( ) {
    val agrocard = Agrocards(
        R.string.tip_1_number,
        R.string.tip_1_title,
        R.string.tip_1,
        R.drawable.tip_1
    )
    TipsAppTheme {
        TipCard(agrocard = agrocard)
    }
}
