package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // R.string.happy_birthday_text: "Happy B-day, Sam!"의 리소스 파일 저장 경로
                    // R.string.signature_text: "- from Emma"의 리소스 파일 저장 경로
                    BirthdayGreetingWithImage(getString(R.string.happy_birthday_text), getString(R.string.signature_text))
                }
            }
        }
    }
}

// 축하 텍스트 출력 함수
@Composable
fun BirthdayGreetingWithText(message: String, from: String, modifier: Modifier = Modifier) {
    Column( // 세로 정렬
        modifier = modifier.fillMaxSize(),
        // column 내부 요소 정렬
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // "Happy B-day, Sam!"
        Text(
            text = message,
            fontSize = 36.sp,
            // 패딩 추가
            modifier = Modifier
                .padding(top = 16.dp)
        )
        // "- from Emma"
        Text(
            text = from,
            fontSize = 24.sp,
            // 패딩 추가 및 정렬
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp)
        )
    }
}

// 축하 텍스트 + 배경 이미지 출력 함수
@Composable
fun BirthdayGreetingWithImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.androidparty)   // 리소스 파일에서 이미지 불러오기
    Box {   // 내부 요소 겹치기
        // 배경 이미지
        Image(
            painter = image,
            contentDescription = null,
            // 배경 크기에 이미지 맞추기
            contentScale = ContentScale.Crop
        )
        BirthdayGreetingWithText(message, from)
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        BirthdayGreetingWithImage( stringResource(R.string.happy_birthday_text), stringResource(R.string.signature_text))
    }
}