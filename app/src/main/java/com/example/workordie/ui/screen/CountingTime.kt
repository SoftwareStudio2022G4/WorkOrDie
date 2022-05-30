import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.toSpanned
import com.example.workordie.R
import com.example.workordie.ui.theme.WorkOrDieTheme

@Composable
fun CountingTime(modifier: Modifier = Modifier){
    androidx.compose.material.Surface() {
        Column(Modifier.padding(20.dp)){
            Row(){

                TextField(
                    value = "Countdown",
                    onValueChange = {},
                    modifier = Modifier.width(192.dp)
                )
                TextField(
                    value = "Accumulate",
                    onValueChange = {},
                    modifier = modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
            Text(
                text = "Softwre Studio",
                textAlign = TextAlign.End,
                modifier = modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
            )
            TextField(
                value = "00 : 00 : 00",
                onValueChange = {},

                )
            TextField(
                value = "Hr : min : sec",
                onValueChange = {},

                )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Start",
                    textAlign = TextAlign.Left,


                )
                Text(
                    text = "End",
                    textAlign = TextAlign.Right,
                    modifier = modifier.fillMaxWidth().padding(horizontal = 10.dp)
                )

            }
            Text(
                text = "Subtask1",
                textAlign = TextAlign.Right
            )
            Text(
                text = "Subtask2",
                textAlign = TextAlign.Right
            )
            Text(
                text = "Subtask3",
                textAlign = TextAlign.Right
            )
            Text(
                text = "Subtask4",
                textAlign = TextAlign.Right

            )

        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkOrDieTheme {
        CountingTime()
    }
}


