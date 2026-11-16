package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.StudyTask
import androidx.compose.ui.tooling.preview.Preview


data class PlannerUiState(
    val tasks: List<StudyTask> = emptyList()
)


@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onTaskClick: (Int) -> Unit,
    onDoneChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val totalTasks = uiState.tasks.size
    val doneTasks = uiState.tasks.count { it.isDone }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Study Tasks",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(text = "You have completed $doneTasks of $totalTasks tasks.")

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(uiState.tasks, key = { it.id }) { task ->
                TaskItem(
                    task = task,
                    onTaskClick = onTaskClick,
                    onDoneChange = onDoneChange
                )
            }
        }
    }
}


@Composable
fun PlannerHomeRoute(onTaskClick: (Int) -> Unit) {
    var tasks by remember {
        mutableStateOf(
            listOf(
                StudyTask(1, "Lab 5 report", "CSC 402"),
                StudyTask(2, "CSC 231 Assignment", "CSC 231", true),
                StudyTask(3, "Quiz 2 Preparation", "MATH 101"),
                StudyTask(4, "Write Essay", "ENG 201"),
                StudyTask(5, "Study for Quiz", "PHYS 101")
            )
        )
    }

    PlannerHomeScreen(
        uiState = PlannerUiState(tasks = tasks),
        onTaskClick = onTaskClick,
        onDoneChange = { id, done ->
            tasks = tasks.map { if (it.id == id) it.copy(isDone = done) else it }
        }
    )
}


@Composable
fun TaskItem(
    task: StudyTask,
    onTaskClick: (Int) -> Unit,
    onDoneChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTaskClick(task.id) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isDone,
            onCheckedChange = { isChecked -> onDoneChange(task.id, isChecked) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = task.title, style = MaterialTheme.typography.titleMedium)
            Text(text = task.course, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TaskItemNotDonePreview() {
    TaskItem(
        task = StudyTask(1, "Read Chapter 3", "CSC 402", false),
        onTaskClick = {},
        onDoneChange = { _, _ -> }
    )
}

@Preview(showBackground = true)
@Composable
fun TaskItemDonePreview() {
    TaskItem(
        task = StudyTask(2, "Finish Lab 7", "CSC 402", true),
        onTaskClick = {},
        onDoneChange = { _, _ -> }
    )
}

@Preview(showBackground = true)
@Composable
fun PlannerHomeScreenPreview() {
    PlannerHomeScreen(
        uiState = PlannerUiState(
            tasks = listOf(
                StudyTask(1, "Read Chapter 3", "CSC 402"),
                StudyTask(2, "Finish Lab 7", "CSC 402", true),
                StudyTask(3, "Math Assignment", "MATH 101"),
                StudyTask(4, "Write Essay", "ENG 201"),
                StudyTask(5, "Study for Quiz", "PHYS 101")
            )
        ),
        onTaskClick = {},
        onDoneChange = { _, _ -> }
    )
}