package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme() {
                LemonadeMaker()
            }
        }
    }
}
// 레몬에이드 만들기 앱: 단계별로 중앙의 버튼을 한번씩 클릭해서 레몬에이드를 완성시킨다
// 2단계 레몬 그림을 여러번 클릭해야 다음 단계로 넘어가는 기능은 구현 실패..
@Composable
fun LemonadeMaker() {
    LemonadeWithButtonAndImage()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeWithButtonAndImage(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
) {
    var result by remember { mutableStateOf(1) }
    // 버튼 내부 이미지
    val imageResource = when (result % 4) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    // 버튼 내부 이미지 설명
    val contentDescriptionResource = when (result % 4) {
        1 -> R.string.title_step1
        2 -> R.string.title_step2
        3 -> R.string.title_step3
        else -> R.string.title_step4
    }
    // 버튼 아래 텍스트
    val textResource = when (result % 4) {
        1 -> R.string.step1_description
        2 -> R.string.step2_description
        3 -> R.string.step3_description
        else -> R.string.step4_description
    }

    // UI 구성
    Column(
        modifier = modifier,
    ) {
        // 제목
        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            fontSize = 18.sp ,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(Color(0xFFfede0d))
                .padding(18.dp)
                .fillMaxWidth()
        )
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 중앙 버튼
            Button(
                onClick = { result++ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFc3ecd2)),
                shape = RoundedCornerShape(10),
            ) {
                // 버튼 내부 이미지
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = stringResource(id = contentDescriptionResource),
                )
            }
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            // 각 단계 설명 텍스트
            Text(
                text = stringResource(id = textResource),
                textAlign = TextAlign.Center
            )
        }
    }
}