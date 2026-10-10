package sa.edu.iau.cshj.lab04attendance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import sa.edu.iau.cshj.lab04.ui.AttendanceRoute
import sa.edu.iau.cshj.lab04attendance.ui.theme.Lab04AttendanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab04AttendanceTheme {
                AttendanceRoute()
            }
        }
    }
}