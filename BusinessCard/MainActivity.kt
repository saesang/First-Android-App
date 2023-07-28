package com.example.businesscard

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCardApp(
                        name = "TakSeha",
                        title = "student",
                        emailAddress = "takseha98@gmail.com",
                        githubAddress = "https://github.com/saesang",
                        university = "Dankook University",
                        major = "Software Science"
                    )
                }
            }
        }
    }
}

// 명함 화면 구현
@Composable
fun BusinessCardApp(
    name: String,
    title: String,
    emailAddress: String,
    githubAddress: String,
    university: String,
    major: String,
    modifier: Modifier = Modifier
) {
    val image = painterResource(id = R.drawable.androidprofile)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF011329))
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 그림
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 70.dp, start = 40.dp, end = 40.dp)
        )
        // TakSeha, student
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 20.dp, bottom = 30.dp)
        ) {
            Text(
                text = name,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3DDC84)
            )
            Text(
                text = title,
                textAlign = TextAlign.End,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3DDC84)
            )
        }
        // 하단 설명
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            // 제목: Univ., Major, E-mail, Github
            Column {
                Text(
                    text = "Univ.",
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(end = 20.dp, bottom = 5.dp)
                )
                Text(
                    text = "Major",
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(end = 20.dp, bottom = 5.dp)
                )
                Text(
                    text = "E-mail",
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(end = 20.dp, bottom = 5.dp),
                )
                Text(
                    text = "Github",
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(end = 20.dp, bottom = 5.dp)
                )
            }
            // Univ., Major, E-mail, Github 주소
            Column {
                Text(
                    text = university,
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                )
                Text(
                    text = major,
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                )
                Text(
                    text = emailAddress,
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                )
                Text(
                    text = githubAddress,
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCardApp(
            name = "TakSeha",
            title = "student",
            emailAddress = "takseha98@gmail.com",
            githubAddress = "https://github.com/saesang",
            university = "Dankook University",
            major = "Software Science"
        )
    }
}