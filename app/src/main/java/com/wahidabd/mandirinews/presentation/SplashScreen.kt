package com.wahidabd.mandirinews.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.mandirinews.R
import com.wahidabd.mandirinews.presentation.destinations.HomeScreenDestination
import kotlinx.coroutines.delay


/**
 * Created by Wahid on 6/28/2023.
 * Github github.com/wahidabd.
 */


@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator
) {
    var animate by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LaunchedEffect(Unit) {
            delay(2000)
            animate = true
            delay(2000)
            navigator.popBackStack()
            navigator.navigate(HomeScreenDestination())
        }

        this.AnimatedVisibility(
            visible = animate.not(),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 2000)
            ) + scaleOut(
                animationSpec = tween(
                    durationMillis = 2000
                )
            )
        ) {
            Image(
                modifier = Modifier
                    .widthIn(max = 200.dp),
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "Logo Mandiri"
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(navigator = EmptyDestinationsNavigator)
}