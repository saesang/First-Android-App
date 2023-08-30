package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipTimeLayout()
                }
            }
        }
    }
}

// 팁 계산기 어플
@Composable
fun TipTimeLayout() {
    var amountInput by remember { mutableStateOf("") }  // Bill Amount 입력값
    var tipPercentInput by remember { mutableStateOf("") }  // Tip Percentage 입력값
    var roundUp by remember { mutableStateOf(false) }   // Round up 유무
    val amount = amountInput.toDoubleOrNull() ?: 0.0    // amountInput Double형으로 변환(null일 경우 0.0으로 저장)
    val tipPercent = tipPercentInput.toDoubleOrNull() ?: 0.0    // tipPercentInput Double형으로 변환(null일 경우 0.0으로 저장)

    val tip = calculateTip(amount, tipPercent, roundUp) // 최종 출력값 tip

    Column(
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()), // 세로 스크롤바 생성
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 제목: Calculation Tip
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start)
        )
        // Bill Amount field
        EditNumberField(
            label = R.string.bill_amount,
            leadingIcon = R.drawable.money,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = amountInput,
            onValueChange = { amountInput = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        // Tip Percentage field
        EditNumberField(
            label = R.string.how_was_the_service,
            leadingIcon = R.drawable.percent,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            value = tipPercentInput,
            onValueChange = { tipPercentInput = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        // Switch field: Round up tip?
        RoundTheTipRow(
            roundUp = roundUp,
            onRoundUpChanged = { roundUp = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
        )
        // tip amount 출력 field: Tip Amount: $0.0
        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

// 숫자 입력 칸 UI 구현 함수
@Composable
fun EditNumberField(
    @StringRes label: Int,  // 입력칸 내부 설명 리소스
    @DrawableRes leadingIcon: Int,  // 입력칸 내부 아이콘 리소스
    keyboardOptions: KeyboardOptions,   // 키보드 타입
    value: String,  // 키보드 입력값
    onValueChange: (String) -> Unit,    // 입력값 상태 변화 저장
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        leadingIcon = { Icon(painter = painterResource(leadingIcon), null) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

// Round Up 유무(Switch) 구현 함수
@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(stringResource(R.string.round_up_tip))
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

// tip 계산 함수
private fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean
): String {
    var tip = tipPercent / 100 * amount
    if (roundUp) {
        tip = kotlin.math.ceil(tip) // 숫자 올림
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout()
    }
}
