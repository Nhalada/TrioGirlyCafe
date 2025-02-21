package com.project.triogirlycafe.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.triogirlycafe.R
import com.project.triogirlycafe.models.drinksList

// Colors
val DarkBrown = Color(0xFF3E2723)
val LightCream = Color(0xFFFFF3E0)
val MediumBrown = Color(0xFF6D4C41)
val DeepCoffee = Color(0xFF4E342E)
val SoftBrown = Color(0xFF8D6E63)
val WarmBeige = Color(0xFFFFE0B2)
val BackgroundLight = Color(0xFFE3C9AD)
val BackgroundDark = Color(0xFF2D221B)

@Composable
fun MainScreen(nc: NavController) {
    DrinksScreen(nc)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinksScreen(nc: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val categories = listOf("All", "Cold Coffee", "Hot Coffee", "Healthy Drinks")
    var selectedCategory by remember { mutableStateOf(categories[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logo), // Ensure logo.png is in res/drawable
                            contentDescription = "App Logo",
                            modifier = Modifier.size(32.dp) // Adjust size as needed
                        )
                        Text(
                            "  TrioGirly Cafe", // Added space for alignment
                            style = TextStyle(color = LightCream, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBrown
                ),
                modifier = Modifier.fillMaxWidth(),
            )
        },
        containerColor = BackgroundLight,
        bottomBar = {
            BottomNavigationBar(nc)
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                placeholder = { Text("Search drinks...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MediumBrown,
                    unfocusedBorderColor = SoftBrown
                )
            )

            // Category Filter
            LazyRow(modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)) {
                items(categories.size) { index ->
                    val category = categories[index]
                    Surface(
                        shape = RoundedCornerShape(50.dp),
                        color = if (selectedCategory == category) MediumBrown else LightCream,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable { selectedCategory = category }
                    ) {
                        Text(
                            category,
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = if (selectedCategory == category) Color.White else DeepCoffee
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            // Drinks List
            DrinksScreenBody(nc, searchQuery, selectedCategory)
        }
    }
}


@Composable
fun BottomNavigationBar(nc: NavController) {
    NavigationBar(containerColor = DarkBrown) {
        listOf("Home", "Menu", "About", "Settings").forEach { item ->
            NavigationBarItem(
                selected = item == "Menu",
                onClick = {
                    when (item) {
                        "Home" -> nc.navigate("login_screen")
                        "Menu" -> nc.navigate("main_screen")
                        "About" -> nc.navigate("about_us_screen")
                        "Settings" -> nc.navigate("settings_screen")
                    }
                },
                label = null,  // Remove text
                icon = {
                    when (item) {
                        "Home" -> Icon(Icons.Filled.Home, contentDescription = "Home", tint = LightCream)
                        "Menu" -> Icon(Icons.Filled.LocalCafe, contentDescription = "Menu", tint = LightCream)
                        "About" -> Icon(Icons.Filled.Info, contentDescription = "About", tint = LightCream)
                        "Settings" -> Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = LightCream)
                    }
                }
            )
        }
    }
}

@Composable
fun DrinksScreenBody(nc: NavController, searchQuery: String, selectedCategory: String) {
    val filteredList = drinksList.filter {
        it.title.contains(searchQuery, ignoreCase = true) &&
                (selectedCategory == "All" || it.category == selectedCategory)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Use 'columns' instead of 'cells'
        modifier = Modifier.padding(10.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(filteredList.size) { index ->
            val drink = filteredList[index]

            Column(
                modifier = Modifier.padding(8.dp),
            ) {

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
                    border = BorderStroke(width = 1.dp, color = SoftBrown)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp) // Reduced padding
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp) // Reduced spacing
                    ) {
                        // Drink Image with Click Event
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp) // Smaller image height
                                .clickable { nc.navigate("drink_detail/${drink.id}") },
                            shape = RoundedCornerShape(12.dp),
                            color = WarmBeige,
                            border = BorderStroke(1.dp, SoftBrown),
                            shadowElevation = 6.dp
                        ) {
                            AsyncImage(
                                model = drink.img,
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp), // Reduced padding
                            verticalArrangement = Arrangement.spacedBy(4.dp) // Reduced spacing
                        ) {
                            // Drink Name
                            Text(
                                text = drink.title,
                                style = TextStyle(
                                    fontSize = 12.sp, // Smaller font size
                                    fontWeight = FontWeight.Bold,
                                    color = DeepCoffee
                                )
                            )

                            // Price
                            Text(
                                text = " $ ${drink.price}",
                                style = TextStyle(
                                    fontSize = 11.sp, // Smaller font size
                                    fontWeight = FontWeight.Medium,
                                    color = MediumBrown
                                )
                            )

                            // Category
                            Text(
                                text = "📌 ${drink.category}",
                                style = TextStyle(
                                    fontSize = 11.sp, // Smaller font size
                                    fontWeight = FontWeight.Normal,
                                    color = MediumBrown
                                )
                            )

                            Spacer(modifier = Modifier.height(8.dp)) // Reduced space

                            // "View Details" Button
                            Surface(
                                color = DeepCoffee,
                                shape = RoundedCornerShape(50.dp),
                                shadowElevation = 10.dp,
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .clickable { nc.navigate("drink_detail/${drink.id}") }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp) // Reduced padding
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Coffee,
                                        contentDescription = "Details",
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp) // Smaller icon
                                    )
                                    Spacer(modifier = Modifier.width(4.dp)) // Reduced space
                                    Text(
                                        text = "Details",
                                        style = TextStyle(
                                            fontSize = 10.sp, // Smaller font size
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




