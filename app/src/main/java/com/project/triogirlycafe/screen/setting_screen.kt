package com.project.triogirlycafe.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(nc: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }
    var isEnglish by remember { mutableStateOf(true) }
    val backgroundColor = if (isDarkMode) BackgroundDark else BackgroundLight
    val textColor = if (isDarkMode) LightCream else DeepCoffee
    val switchColors = SwitchDefaults.colors(
        checkedThumbColor = Color.White,
        checkedTrackColor = DeepCoffee,
        uncheckedThumbColor = Color.Gray,
        uncheckedTrackColor = Color.LightGray
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isEnglish) "Settings" else "ការកំណត់",
                        style = TextStyle(fontSize = 26.sp, color = LightCream, fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBrown)
            )
        },
        bottomBar = {
            AboutBottomNavigationBar(nc, isDarkMode)
        },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            SettingRow(
                icon = Icons.Filled.Language,
                title = if (isEnglish) "Language" else "ភាសា",
                value = if (isEnglish) "English" else "ខ្មែរ",
                checked = isEnglish,
                onCheckedChange = { isEnglish = it },
                switchColors = switchColors,
                textColor = textColor // This will switch dynamically
            )

            SettingRow(
                icon = if (isDarkMode) Icons.Filled.Brightness7 else Icons.Filled.Brightness4,
                title = if (isEnglish) "Mode" else "ម៉ូត",
                value = if (isDarkMode) "\uD83E\uDD0E" else "\uD83E\uDD0D",
                checked = isDarkMode,
                onCheckedChange = { isDarkMode = it },
                switchColors = switchColors,
                textColor = textColor // This will switch dynamically
            )

        }
    }
}

@Composable
fun SettingRow(
    icon: ImageVector,
    title: String,
    value: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    switchColors: SwitchColors,
    textColor: Color // Added parameter for text color
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = title, tint = textColor, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title, fontSize = 18.sp, color = textColor)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = value, fontSize = 18.sp, color = textColor, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = switchColors
            )
        }
    }
}

@Composable
fun AboutBottomNavigationBar(nc: NavController, isDarkMode: Boolean) {
    val navBarColor = if (isDarkMode) DeepCoffee else DarkBrown
    val iconColor = LightCream

    NavigationBar(containerColor = navBarColor) {
        listOf(
            "Home" to Icons.Filled.Home,
            "Menu" to Icons.Filled.LocalCafe,
            "About" to Icons.Filled.Info,
            "Settings" to Icons.Filled.Settings
        ).forEach { (item, icon) ->
            NavigationBarItem(
                selected = item == "Settings",
                onClick = {
                    when (item) {
                        "Home" -> nc.navigate("login_screen")
                        "Menu" -> nc.navigate("main_screen")
                        "About" -> nc.navigate("about_us_screen")
                        "Settings" -> nc.navigate("settings_screen")
                    }
                },
                icon = { Icon(icon, contentDescription = item, tint = iconColor) },
                label = null
            )
        }
    }
}