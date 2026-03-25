package com.hoamz.iq.presentation.ui.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.hoamz.iq.common.SetWidth

/**
 * @author hwa..
 */
@Composable
fun ButtonWidget (
    modifier: Modifier = Modifier,
    leadingIcon1 : ImageVector? = null,
    leadingIcon2 : Painter? = null,
    actionName : String,
    tintIcon : Color,
    onClick :() -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            .border(width = 0.5.dp, color = Color.Black, shape = MaterialTheme.shapes.small),
        onClick = { onClick() },
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp
        ),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp),
            horizontalArrangement = Arrangement.Start
        ){
            leadingIcon1?.let {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = leadingIcon1,
                    tint = tintIcon,
                    contentDescription = null
                )
            }

            leadingIcon2?.let {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = leadingIcon2,
                    tint = tintIcon,
                    contentDescription = null
                )
            }

            SetWidth(10.dp)
            Text(
                text = actionName,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun IconBtn(
    modifier: Modifier = Modifier,
    icon : Painter,
    onClick :() -> Unit
) {
    IconButton(
        modifier = Modifier
            .padding(5.dp)
            .size(26.dp),
        shape = CircleShape,
        onClick = {onClick()}
    ) {
        Icon(
            painter = icon,
            tint = Color.White,
            contentDescription = null
        )
    }
}