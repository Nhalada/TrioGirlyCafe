package com.project.triogirlycafe.screen

import com.project.triogirlycafe.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage


@Composable
fun LoginScreen(nc: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var hide by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3C9AD)), // Light tan background
    ) {
        AsyncImage(
            model = ("https://i.pinimg.com/1200x/e6/66/27/e666274ae48540fa878bd49965fd36ba.jpg"),
            contentDescription = "Coffee Cup",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(300.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 350.dp, start = 24.dp, end = 24.dp),  // Adjusted top padding
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Sign In",
                style = TextStyle(fontSize = 28.sp, color = Color(0xFF220C02), fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                shape = RoundedCornerShape(25.dp),
                placeholder = { Text("Enter Email") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                textStyle = TextStyle(color = Color(0xFF392A25)),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF3D4BA),
                    focusedContainerColor = Color(0xFFEFB789),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                shape = RoundedCornerShape(25.dp),
                placeholder = { Text("Enter Password") },
                leadingIcon = { Icon(Icons.Default.Key, contentDescription = null) },
                textStyle = TextStyle(color = Color(0xFF392A25)),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF3D4BA),
                    focusedContainerColor = Color(0xFFEFB789),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                ),
                trailingIcon = {
                    IconButton(onClick = { hide = !hide }) {
                        Icon(
                            if (hide) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (hide) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (email == "admin@gmail.com" && password == "abcdef") {
                        nc.navigate("main_screen") {
                            popUpTo("login_screen") { inclusive = true }
                        }
                    } else {
                        result = "Login Failed"
                    }
                },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5742)),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
            ) {
                Text("Sign In", color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Don't have an account? Sign Up",
                color = Color(0xFF220C02),
                modifier = Modifier.clickable {
                    nc.navigate("signup_screen")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = result, color = Color.Red)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Or", color = Color(0xFF220C02))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Facebook Icon (from built-in Material Icons)
                Icon(
                    imageVector = Icons.Filled.Facebook,
                    contentDescription = "Facebook",
                    modifier = Modifier
                        .size(38.dp)
                        .clickable { /* Handle Facebook click */ }
                )

                // Google Icon (from drawable folder)
                Image(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = "Google",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { /* Handle Google click */ }
                )
            }
        }

        }
    }




