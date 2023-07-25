package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greetingcard.ui.theme.GreetingCardTheme

// 메인 클래스 : ComponentActivity() 클래스 상속
class MainActivity : ComponentActivity() {
    // onCreate() 메서드 재정의, onCreate() == main()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // 상위 클래스 메서드 실행
        // UI 레이아웃 정의
        setContent {
            // greetingCard 테마
            GreetingCardTheme {
                // UI 섹션 컨테이너
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

// 컴포저블 함수: UI 구성 함수
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color.Yellow) {
        Text(
            text = "Hi, my name is $name!",
            modifier = Modifier.padding(24.dp)
        )
    }
}

// 미리보기
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GreetingCardTheme {
        Greeting("Seha")
    }
}