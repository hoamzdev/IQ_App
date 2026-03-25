package com.hoamz.iq.presentation.ui.screen.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hoamz.iq.R
import com.hoamz.iq.common.LocalScreenSize
import com.hoamz.iq.common.SetWidth
import com.hoamz.iq.presentation.navigation.MainRouter
import com.hoamz.iq.presentation.common.ConsTextApp
import com.hoamz.iq.presentation.ui.widget.ButtonWidget
import com.hoamz.iq.presentation.ui.widget.IconBtn

/**
 * @author hwa..
 */
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    welcomeViewModel: WelcomeViewModel,
    mainRouter: MainRouter
) {
    val size = LocalScreenSize.current

    LaunchedEffect(Unit) {
        welcomeViewModel.welcomeNavState.collect {
            navState ->
            when(navState){
                is WelcomeNavState.ToPlayScreen -> mainRouter.onNavToPlayScreen()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
            .padding(top = 20.dp, bottom = 10.dp),
        contentAlignment = Alignment.Center
    ){
        //setting
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(alignment = Alignment.TopEnd)
                .padding(top = 20.dp, end = 10.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .padding(6.dp)
                    .size(32.dp)
                    .align(alignment = Alignment.Center)
                ,
                shape = CircleShape,
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .align(alignment = Alignment.TopCenter)
                .padding(top = size.height * 0.2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = ConsTextApp.nameApp,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 30.sp)
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = ConsTextApp.version,//nao cap nhat phien bao -> vao day sua
                style = MaterialTheme.typography.labelSmall
            )
        }

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .align(alignment = Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            ButtonWidget(
                leadingIcon1 = Icons.Outlined.PlayCircle,
                tintIcon = Color.White,
                actionName = "Play"
            ) {
                welcomeViewModel.onNavToPlayScreen()
            }

            ButtonWidget(
                leadingIcon2 = painterResource(R.drawable.ic_daily),
                tintIcon = Color.Green,
                actionName = "Daily Challenges"
            ) {}

            ButtonWidget(
                leadingIcon2 = painterResource(R.drawable.ic_premium),
                tintIcon = Color.Yellow,
                actionName = "Be Premium"
            ) {}
        }

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .align(alignment = Alignment.BottomCenter)
                .padding(top = size.height * 0.2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = ConsTextApp.follow_us,
                style = MaterialTheme.typography.bodyMedium
                    .copy(color = Color.LightGray.copy(0.5f))
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                IconBtn(
                    icon = painterResource(R.drawable.ic_instagram)
                ) { }
                SetWidth(16.dp)
                IconBtn(
                    icon = painterResource(R.drawable.ic_twitter)
                ) { }
                SetWidth(16.dp)
                IconBtn(
                    icon = painterResource(R.drawable.ic_tiktok)
                ) { }
            }
        }
    }
}