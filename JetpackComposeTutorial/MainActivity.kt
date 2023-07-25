package com.example.jetpackcomposetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PrintJetpackComposeTutorial(getString(R.string.jetpack_compose_tutorial_title), getString(R.string.jetpack_compose_body_1), getString(R.string.jetpack_compose_body_2))
                }
            }
        }
    }
}

// Jetpack Compose tutorial 화면 구현
@Composable
fun PrintJetpackComposeTutorial(title: String, body_1: String, body_2: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.bg_compose_background)
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth   // 이미지 화면 너비에 맞추기
        )
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = body_1,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify   // 화면에 맞춰서 텍스트 정렬
        )
        Text(
            text = body_2,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun JetpackComposeTutorialPreview() {
    JetpackComposeTutorialTheme {
        PrintJetpackComposeTutorial(stringResource(R.string.jetpack_compose_tutorial_title), stringResource(
                    R.string.jetpack_compose_body_1), stringResource(R.string.jetpack_compose_body_2)
                            )
    }
}