package com.blue.mediaeditor.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blue.domain.model.Media
import com.blue.mediaeditor.R
import com.blue.mediaeditor.ui.state.MainUiState

@Composable
fun ProjectComponent(
    uiData: Media
) {
    Surface(
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.image), contentDescription = "")
                    Column {
                        Text(text = uiData.title)
                        Text(text = uiData.date)
                    }
                }
                Text(text = "Click")
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .background(Color.Yellow)
                    .fillMaxWidth()
                    .height(0.5.dp)
            )
        }
    }
}

@Preview
@Composable
fun preview() {
    ProjectComponent(uiData = Media(id = 1, title = "제목이야", date = "날짜날짜", path = ""))
}