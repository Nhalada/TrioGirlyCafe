package com.project.triogirlycafe.screen

import com.project.triogirlycafe.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(nc: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "About",
                        style = TextStyle(color = LightCream, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBrown
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        bottomBar = {
            AboutBottomNavigationBar(nc)
        },
        containerColor = BackgroundLight // Set the background color of the screen
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(BackgroundLight)
        ) {
            // Cafe Image
            Image(
                painter = painterResource(id = R.drawable.cafe_image), // Replace with your actual image resource
                contentDescription = "TrioGirly Cafe",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            )

            Column(modifier = Modifier.padding(16.dp)) {
                // Title
                Text(
                    "Welcome to TrioGirly Cafe ☕",
                    style = TextStyle(fontSize = 26.sp, color = DeepCoffee, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Description with style
                Text(
                    "TrioGirly Cafe is your cozy escape, where every sip of coffee is brewed to perfection. Whether you crave a strong espresso, a creamy latte, or a refreshing iced coffee, we have something special for you. Pair it with our delicious desserts and enjoy a relaxing atmosphere! 💖",
                    style = TextStyle(fontSize = 18.sp, color = SoftBrown),
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Contact & Info
                InfoRow("📍 Location:", "6 Street, Phnom Penh, Cambodia")
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow("🕒 Opening Hours:", "7:00 AM - 9:00 PM")
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow("📞 Contact:", "+855 123 456 789")
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

// Custom Composable for displaying info rows
@Composable
fun InfoRow(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(label, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DeepCoffee))
        Spacer(modifier = Modifier.width(4.dp))
        Text(value, style = TextStyle(fontSize = 18.sp, color = MediumBrown))
    }
}

@Composable
fun AboutBottomNavigationBar(nc: NavController) {
    NavigationBar(containerColor = DarkBrown) {
        listOf("Home" to Icons.Filled.Home, "Menu" to Icons.Filled.LocalCafe, "About" to Icons.Filled.Info, "Settings" to Icons.Filled.Settings).forEach { (item, icon) ->
            NavigationBarItem(
                selected = item == "About",
                onClick = {
                    when (item) {
                        "Home" -> nc.navigate("login_screen")
                        "Menu" -> nc.navigate("main_screen")
                        "About" -> nc.navigate("about_us_screen")
                        "Settings" -> nc.navigate("settings_screen")
                    }
                },
                icon = { Icon(icon, contentDescription = item, tint = LightCream) },
                label = null
            )
        }
    }
}