package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

// 온라인 Art Space 앱
@Composable
fun ArtSpaceLayout(
    modifier: Modifier = Modifier
) {
    /**
     * previous, next 버튼의 클릭 이벤트 발생에 따라, 클릭 횟수를 저장하는 변수 result를 증가 또는 감소시켜서
     * 작품 사진, 제목 및 작가 정보를 변화시킴
     */

    var result by remember { mutableStateOf(1) }    // 클릭 이벤트 발생 횟수

    var artImage = when (result % 3) {  // 작품 사진
        1 -> R.drawable.max
        2 -> R.drawable.karl
        else -> R.drawable.willem
    }
    var title = when (result % 3) { // 작품의 제목
        1 -> R.string.title_1
        2 -> R.string.title_2
        else -> R.string.title_3
    }
    var artist = when (result % 3) {    // 작품의 작가
        1 -> R.string.artist_1
        2 -> R.string.artist_2
        else -> R.string.artist_3
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(40.dp)
            .horizontalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtWall(artImage)
        ArtDescription(title, artist)
        DisplayController(
            prevClick = {
                if (result == 0) {
                    result = 3
                } else {
                    result--
                }
            },
            nextClick = { result++ }
        )
    }
}

// 작품 사진 UI field
@Composable
fun ArtWall(
    @DrawableRes artImage: Int,
    modifier: Modifier = Modifier
        .padding(20.dp)
        .fillMaxWidth()
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(artImage),
            contentDescription = null,
            modifier = Modifier
                .drawBehind { // 작품 사진 주위 액자 구현
                    drawRect(
                        color = Color.Gray,
                        topLeft = Offset(-15.dp.toPx(), -20.dp.toPx()),
                        style = Stroke(width = 15.dp.toPx()),
                        alpha = 0.6f,
                        size = size / 0.9F
                    )
                }
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

// 작품 설명 field: 작품 제목 및 작가 이름
@Composable
fun ArtDescription(
    @StringRes title: Int,
    @StringRes artist: Int,
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
    Column(
        modifier = modifier
    ) {
        // 작품 제목
        Text(
            text = stringResource(title),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        // 작가 이름
        Text(
            text = stringResource(artist),
        )
    }
}

// 컨트롤러 field: Previous, Next 버튼
@Composable
fun DisplayController(
    prevClick: () -> Unit,
    nextClick: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
    Row(
        modifier = modifier
            .padding(top = 50.dp, start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // previous 버튼
        Button(
            onClick = prevClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            modifier = Modifier
                .height(40.dp)
                .width(110.dp)
        ) {
            Text(text = "Previous")
        }
        // next 버튼
        Button(
            onClick = nextClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            modifier = Modifier
                .height(40.dp)
                .width(110.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceLayoutPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}