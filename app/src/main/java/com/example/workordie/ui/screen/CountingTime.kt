import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.toSpanned
import com.example.workordie.R
import com.example.workordie.ui.theme.WorkOrDieTheme

@Composable
fun CountingTime(modifier: Modifier = Modifier){
    androidx.compose.material.Surface(color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Column(Modifier.padding(20.dp)){
            Row(){

                TextField(
                    value = "Countdown",
                    onValueChange = {},
                    modifier = Modifier.width(192.dp).padding(vertical = 10.dp)
                )
                TextField(
                    value = "Accumulate",
                    onValueChange = {},
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(vertical = 10.dp)
                )
            }
            Text(
                text = "Softwre Studio",
                textAlign = TextAlign.End,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(vertical = 30.dp),

                )
            TextField(
                value = "00 : 00 : 00",
                onValueChange = {},
                modifier = modifier.padding(horizontal = 30.dp)

                )
            TextField(
                value = "Hr : min : sec",
                onValueChange = {},
                modifier = modifier.padding(horizontal = 30.dp),


                )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Start",
                    textAlign = TextAlign.Left,
                    modifier = modifier.padding(vertical = 30.dp),

                )
                Text(
                    text = "End",
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )

            }
            Text(
                text = "Subtask1",
                textAlign = TextAlign.Right,
                modifier = modifier.padding(vertical = 10.dp),
            )
            Text(
                text = "Subtask2",
                textAlign = TextAlign.Right,
                modifier = modifier.padding(vertical = 10.dp),
            )
            Text(
                text = "Subtask3",
                textAlign = TextAlign.Right,
                modifier = modifier.padding(vertical = 10.dp),
            )
            Text(
                text = "Subtask4",
                textAlign = TextAlign.Right,
                modifier = modifier.padding(vertical = 10.dp),

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


