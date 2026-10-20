package sa.edu.iau.cshj.lab05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import sa.edu.iau.cshj.lab05.navigation.CourseApp
import sa.edu.iau.cshj.lab05.ui.theme.Lab05CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab05CoursesTheme {
                CourseApp()
            }
        }
    }
}