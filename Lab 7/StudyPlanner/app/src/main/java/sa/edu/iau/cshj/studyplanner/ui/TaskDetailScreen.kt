package sa.edu.iau.cshj.studyplanner.ui

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// كلاس الحالة الخاص بشاشة التفاصيل
data class TaskDetailUiState(val title: String)

@Composable
fun TaskDetailScreen(
    uiState: TaskDetailUiState,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = uiState.title,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onShareClick) {
            Text("Share Task")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onBackClick) {
            Text("Back")
        }
    }
}

@Composable
fun TaskDetailRoute(
    id: Int, // 👈 نستقبل الـ id كرقم
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // نحول الـ id إلى نص عشان نقدر نعرضه ونشاركه
    val taskText = "Study Task ID: $id"

    TaskDetailScreen(
        uiState = TaskDetailUiState(title = taskText),
        onBackClick = onBackClick,
        onShareClick = {
            // كود المشاركة (Intent) من Lab 5
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, taskText) // 👈 نمرر النص هنا
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, "Share Task")
            context.startActivity(shareIntent)
        },
        modifier = modifier
    )
}