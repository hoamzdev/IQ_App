package com.hoamz.iq.presentation.ui.screen.level

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hoamz.iq.common.SetHeight
import com.hoamz.iq.common.SetWidth
import com.hoamz.iq.presentation.navigation.MainRouter
import com.hoamz.iq.presentation.navigation.Page
import com.hoamz.iq.presentation.ui.screen.play.ANumber
import com.hoamz.iq.ui.screen.level.LevelNavState

/**
 * @author hwa..
 */

@Composable
fun LevelScreen(
    modifier: Modifier = Modifier,
    mainRouter: MainRouter,
    levelViewModel: LevelViewModel
) {

    val currentLevel = remember { levelViewModel.getLevel() }

    BackHandler {
        levelViewModel.onNavToWelcomeScreen()
    }

    LaunchedEffect(Unit) {
        levelViewModel.levelsNavState.collect { navState ->
            when(navState){
                is LevelNavState.ToWelComeScreen -> mainRouter.onBackToWelcomeScreen()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 20.dp, bottom = 10.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            //topbar
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(26.dp),
                    onClick = {
                        //back ve man hinh welcome
                        levelViewModel.onNavToWelcomeScreen()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBackIosNew,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
                SetWidth(10.dp)
                Text(
                    text = "Levels",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            //list level
            SetHeight(10.dp)

            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                items(100, key = {it}){index ->
                    ANumber(
                        index = index + 1,
                        h = 70.dp,
                        textColor = if(index < currentLevel) Color.Yellow else Color.White,
                        fontW = if(index < currentLevel) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 13.sp
                    ) {

                    }
                }
            }
        }
    }
}