package com.comtrade.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.comtrade.presentation.R

@Composable
fun ContactDetailScreen(
    email: String,
    firstName: String,
    lastName: String,
    avatarUrl: String,
) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Avatar(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(150.dp),
            imageModifier = Modifier.size(150.dp), avatarUrl = avatarUrl
        )
        ContactDetail(title = "First Name", firstName)
        ContactDetail(title = "Last Name", lastName)
        ContactDetail(title = "Email", email)
    }
}

@Composable
fun ContactDetail(title: String, info: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        val information = info.ifEmpty { "NA" }
        Text(
            modifier = Modifier
                .weight(1f),
            text = information,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    avatarUrl: String,
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .background(
                color = Color.LightGray,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = avatarUrl,
            contentDescription = null,
            modifier = imageModifier
                .padding(7.dp)
                .height(58.dp)
                .clip(CircleShape)
                .widthIn(max = 72.dp),
            contentScale = ContentScale.Fit,
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_foreground),
        )
    }
}
