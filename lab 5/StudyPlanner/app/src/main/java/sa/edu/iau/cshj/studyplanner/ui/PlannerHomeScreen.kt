package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class PlannerUiState(
    val tasks: List<String> = emptyList()
)


@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onTaskClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Study Tasks",
            style = MaterialTheme.typography.headlineMedium
        )
        uiState.tasks.forEach { taskTitle ->
            Button(
                onClick = { onTaskClick(taskTitle) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = taskTitle)
            }
        }
    }
}


@Composable
fun PlannerHomeRoute(onTaskClick: (String) -> Unit) {
    PlannerHomeScreen(
        uiState = PlannerUiState(
            tasks = listOf("Lab 5 report", "CSC 231 Assignment", "Quiz 2 Preparation")
        ),
        onTaskClick = onTaskClick
    )
}