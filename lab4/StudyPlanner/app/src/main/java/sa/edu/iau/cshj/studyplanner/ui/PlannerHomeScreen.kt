package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

data class PlannerUiState(
    val greeting: String = "Hello, Noor",
    val totalTasks: Int = 7,
    val completedTasks: Int = 2
)

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onCompleteTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {

        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD2E6FF)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 36.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = uiState.greeting,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF003884)
                )


                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "${uiState.completedTasks} of ${uiState.totalTasks} done",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF003884)
                )


                Spacer(modifier = Modifier.height(32.dp))


                OutlinedButton(
                    onClick = onCompleteTask,
                    enabled = uiState.completedTasks < uiState.totalTasks,
                    shape = RectangleShape,
                    border = BorderStroke(2.dp, Color(0xFF003884)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF003884)
                    ),
                    modifier = Modifier.fillMaxWidth(0.85f)
                ) {
                    Text(
                        text = "Complete a Task",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun PlannerHomeRoute(
    modifier: Modifier = Modifier
) {
    var uiState by remember {
        mutableStateOf(
            PlannerUiState(
                greeting = "Hello, Noor",
                totalTasks = 7,
                completedTasks = 1
            )
        )
    }

    PlannerHomeScreen(
        uiState = uiState,
        onCompleteTask = {
            if (uiState.completedTasks < uiState.totalTasks) {
                uiState = uiState.copy(
                    completedTasks = uiState.completedTasks + 1
                )
            }
        },
        modifier = modifier
    )
}


@Preview(showBackground = true, name = "Nothing Done Yet")
@Composable
private fun PlannerHomeScreenNothingDonePreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                greeting = "Hello, Noor",
                totalTasks = 7,
                completedTasks = 0
            ),
            onCompleteTask = {}
        )
    }
}


@Preview(showBackground = true, name = "Everything Done")
@Composable
private fun PlannerHomeScreenEverythingDonePreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                greeting = "Hello, Noor",
                totalTasks = 7,
                completedTasks = 7
            ),
            onCompleteTask = {}
        )
    }
}