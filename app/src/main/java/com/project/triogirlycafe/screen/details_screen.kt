package com.project.triogirlycafe.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.project.triogirlycafe.models.DrinksModel
import com.project.triogirlycafe.models.drinksList



@Preview(showSystemUi = true)
@Composable
fun PreviewDrinkDetailScreen() {
    val nc = rememberNavController()
    DrinkDetailScreen(nc, drinksList[0])
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkDetailScreen(nc: NavController, drink: DrinksModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Drink Detail",color = LightCream, fontWeight = FontWeight.Bold, fontSize = 22.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBrown,
                    navigationIconContentColor = Color.White,
                ),
                navigationIcon = {
                    IconButton(onClick = { nc.popBackStack() }) {
                        Icon(Icons.Default.ArrowBackIosNew, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).background(BackgroundLight)) {
            DrinkDetailScreenBody(nc, drink)
        }
    }
}

@Composable
fun DrinkDetailScreenBody(nc: NavController, drink: DrinksModel) {
    val selectedSize = remember { mutableStateOf("Medium") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(BackgroundLight)
    ) {
        // Image Section
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.Gray,
            border = BorderStroke(1.dp, SoftBrown),
            shadowElevation = 8.dp
        ) {
            AsyncImage(
                model = drink.img,
                contentDescription = drink.description,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Drink Details
        Column(modifier = Modifier.padding(16.dp)) {
            // Title and Price
            Text(
                text = drink.title,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = MediumBrown
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$ ${drink.price}",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = DeepCoffee
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Description
            Text(
                text = drink.description,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal),
                color = DarkBrown,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Size Selection
            SizeSelection(selectedSize = selectedSize.value) { newSize ->
                selectedSize.value = newSize
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Order Button
            Button(
                onClick = {
                    // Handle the cart with the selected size
                    println("Selected size: ${selectedSize.value}")
                    nc.navigate("info_screen")
                },
                colors = ButtonDefaults.buttonColors(containerColor = DarkBrown),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Add to Cart",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun SizeSelection(selectedSize: String, onSizeSelected: (String) -> Unit) {
    Text(
        text = "Select Size",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = MediumBrown,
        modifier = Modifier.padding(bottom = 12.dp)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        listOf("Small", "Medium", "Large").forEach { size ->
            val isSelected = selectedSize == size
            Text(
                text = size,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        onSizeSelected(size)
                    }
                    .background(
                        if (isSelected) DeepCoffee else BackgroundLight,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = if (isSelected) Color.White else DeepCoffee
                )
            )
        }
    }
}

