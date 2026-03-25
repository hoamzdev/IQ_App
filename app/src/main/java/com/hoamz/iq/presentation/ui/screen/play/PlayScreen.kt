package com.hoamz.iq.presentation.ui.screen.play

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hoamz.iq.R
import com.hoamz.iq.common.LocalScreenSize
import com.hoamz.iq.common.SetHeight
import com.hoamz.iq.common.SetWidth
import com.hoamz.iq.common.noRippleClick
import com.hoamz.iq.presentation.common.playAudioCorrectAnswer
import com.hoamz.iq.presentation.navigation.MainRouter
import com.hoamz.iq.presentation.ui.widget.toast
import kotlinx.coroutines.delay

/**
 * @author hwa..
 */

@Composable
fun PlayScreen(
    modifier: Modifier = Modifier,
    mainRouter: MainRouter,
    playViewModel: PlayViewModel
) {
    val size = LocalScreenSize.current
    val context = LocalContext.current
    val questionsData by playViewModel.questions.collectAsState()

    var indexQuestion by remember { mutableIntStateOf(playViewModel.getLevel()) }
    var isWrong by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    BackHandler {
        mainRouter.onBackPress()
    }

    LaunchedEffect(Unit) {
        playViewModel.playNavState.collect { navState ->
            when (navState) {
                is PlayNavState.ToLevelScreen -> mainRouter.onNavToLevelScreen()
            }
        }
    }

    LaunchedEffect(Unit) {
        playViewModel.errorState.collect { error ->
            toast(context = context, message = error)
        }
    }

    LaunchedEffect(isWrong) {
        if (isWrong) {
            delay(1000)
            isWrong = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 20.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TopAppBar(
                modifier = Modifier.padding(top = 16.dp), level = indexQuestion + 1, onBackPress = {
                    mainRouter.onBackPress()
                }) {
                //onNavToLevels
                playViewModel.onNavToLevelsScreen()
            }
            SetHeight(10.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 2.dp, vertical = 2.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .shadow(elevation = 0.5f.dp)
            ) {
                if (questionsData.questions.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(alignment = Alignment.Center),
                        text = questionsData.questions[indexQuestion].question,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
                    )
                }

                if (isWrong) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(alignment = Alignment.BottomCenter)
                            .padding(bottom = 50.dp),
                        text = "Wrong. Try again!",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp)
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .shadow(elevation = 0.5f.dp),
            ) {
                FormAnswer(
                    modifier = Modifier.padding(5.dp)
                ) { answer ->
                    if (answer == questionsData.questions[indexQuestion].correct_answer_value) {
                        playAudioCorrectAnswer(context)
                        isCorrect = true
                    } else {
                        isWrong = true
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = isCorrect, enter = scaleIn(
                initialScale = 0.3f, animationSpec = tween(300)
            ) + fadeIn(), exit = fadeOut(animationSpec = tween(300))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(100.dp),
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "Correct!", style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Referring to the next level!",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Button(
                    modifier = Modifier
                        .width(size.width * 0.5f)
                        .align(alignment = Alignment.BottomCenter)
                        .padding(bottom = 50.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 10.dp),
                    onClick = {
                        isCorrect = false
                        if (indexQuestion < 99) {
                            indexQuestion++
                            playViewModel.saveLevel(indexQuestion)
                        }
                    },
                    elevation = ButtonDefaults.buttonElevation(2.dp),
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Outlined.PlayCircle,
                            tint = Color.Black,
                            contentDescription = null
                        )
                        SetWidth(10.dp)
                        Text(
                            text = "Next Level",
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

//code bang nhap dap an
@Composable
fun FormAnswer(
    modifier: Modifier = Modifier, onEnter: (Int) -> Unit
) {
    var answer by remember { mutableStateOf("Answer") }
    var number by remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .wrapContentHeight(), verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .background(
                        color = Color.Black.copy(0.15f), shape = RoundedCornerShape(5.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    text = answer,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                            color = if (answer == "Answer") Color.White.copy(0.5f) else Color.White
                        )
                )

                IconButton(
                    modifier = Modifier
                        .padding(start = 6.dp, end = 8.dp)
                        .size(22.dp), onClick = {
                        answer = "Answer"
                        number = 0
                    }) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Outlined.Clear,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(
                        color = Color.Black.copy(0.3f), shape = RoundedCornerShape(5.dp)
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Lightbulb,
                    tint = Color.White,
                    contentDescription = null
                )
            }

            Button(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(), onClick = {
                    if (number != 0) {
                        onEnter(number)
                        answer = "Answer"
                        number = 0
                    }
                }, shape = RoundedCornerShape(5.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black.copy(0.3f)
                )
            ) {
                Text(
                    text = "Enter", style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            repeat(5) { index ->
                ANumber(
                    modifier = Modifier.weight(1f), index = index + 1, h = 50.dp
                ) {
                    number = number * 10 + (index + 1)
                    answer = "$number"
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            repeat(5) { index ->
                ANumber(
                    modifier = Modifier.weight(1f), index = (index + 6) % 10, h = 50.dp
                ) {
                    number = (number * 10 + (index + 6) % 10)
                    answer = "$number"
                }
            }
        }
    }
}


@Composable
fun ANumber(
    modifier: Modifier = Modifier,
    index: Int,
    h: Dp,
    fontSize: TextUnit = 18.sp,
    fontW: FontWeight = FontWeight.Normal,
    textColor: Color = Color.White,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .height(h)
            .clip(RoundedCornerShape(3.dp))
            .background(Color.Black.copy(alpha = 0.15f))
            .noRippleClick { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$index",
            fontSize = fontSize,
            color = textColor,
            fontWeight = fontW,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1
        )
    }
}


@Composable
fun TopAppBar(
    modifier: Modifier = Modifier, level: Int, onBackPress: () -> Unit, onClickLevels: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .padding(5.dp)
                    .size(26.dp), onClick = { onBackPress() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    tint = Color.White,
                    contentDescription = null
                )
            }

            SetWidth(20.dp)

            Text(
                modifier = Modifier.wrapContentWidth(),
                text = "Level $level",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        IconButton(
            modifier = Modifier
                .padding(6.dp)
                .size(36.dp), onClick = { onClickLevels() }) {
            Icon(
                painter = painterResource(R.drawable.ic_window),
                tint = Color.White,
                contentDescription = null
            )
        }
    }
}

