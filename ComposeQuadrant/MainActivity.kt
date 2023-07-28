package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeQuadrantArrange()
                }
            }
        }
    }
}

// compose 카드 사분면 배치
@Composable
fun ComposeQuadrantArrange() {
    Column(
        modifier = Modifier.fillMaxWidth()  // 너비 채우기
    ) {
        Row(
            // 해당 column 내에서 1f 비율의 영역 차지
            modifier = Modifier.weight(1f)
        ) {
            // 제 2사분면
            ComposeCard(
                bgColor = Color.Green,
                title = stringResource(R.string.title_1),
                body = stringResource(R.string.body_1),
                modifier = Modifier.weight(1f)    // modifier: 해당 Row 내에서 1f 비율의 영역 차지
            )
            // 제 1사분면
            ComposeCard(
                bgColor = Color.Yellow,
                title = stringResource(R.string.title_2),
                body = stringResource(R.string.body_2),
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            // 제 3사분면
            ComposeCard(
                bgColor = Color.Cyan,
                title = stringResource(R.string.title_3),
                body = stringResource(R.string.body_3),
                modifier = Modifier.weight(1f)
            )
            // 제 4사분면
            ComposeCard(
                bgColor = Color.LightGray,
                title = stringResource(R.string.title_4),
                body = stringResource(R.string.body_4),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// compose 카드 구성
@Composable
fun ComposeCard(bgColor: Color, title: String, body: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier // 매개변수 modifier의 속성 지정 (!= Modifier)
            .fillMaxSize()
            .background(bgColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = body,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeQuadrantPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrantArrange()
    }
}
