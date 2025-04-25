package com.fmt.konichi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModelProvider
import com.fmt.konichi.Model.AuthModel
import com.fmt.konichi.ui.theme.KonichiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewmodel = ViewModelProvider(this)[AuthModel::class.java]
            KonichiTheme {
                LoginScreen(viewmodel)
            }
        }
    }
}

@Composable
fun LoginScreen(authModel: AuthModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xF2F2F2))
    ) {
        // Image background
        Image(
            painter = painterResource(id = R.drawable.top_bg),
            contentDescription = "Top Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .align(Alignment.Center)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Hi, \nPlease Login",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(top = 32.dp)
            )

            Spacer(modifier = Modifier.height(80.dp))

            // Email field
            Text(
                text = "Email",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            BasicTextField(
                value = email,
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .background(
                        color = Color(0xD7D6D7),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFF047857), // stroke color
                        shape = RoundedCornerShape(10.dp)
                    )
                    .height(50.dp)
                    .padding(12.dp),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.Gray)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Password field
            Text(
                text = "Password",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            BasicTextField(
                value = password,
                onValueChange = { password = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .background(
                        color = Color(0xD7D6D7),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFF047857), // stroke color
                        shape = RoundedCornerShape(10.dp)
                    )
                    .height(50.dp)
                    .padding(12.dp),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.Gray),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Login button
            Button(
                onClick = {
                    authModel.login(email, password) { sukses, pesan ->
                        if (sukses) {
                            val res = Intent(context, MainActivity2::class.java)
                            // Ubah ini kalau mau ke AnimeList: Screen.AnimeList.route
                            res.putExtra("startDestination", Screen.MangaList.route)
                            context.startActivity(res)
                            Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show()
                        }
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF047857))
            ) {
                Text(
                    text = "Login",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Signup link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    style = TextStyle(fontSize = 16.sp, color = Color.Black)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Signup",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFF388E3C),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .background(
                            color = Color(0xD7D6D7),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFF047857), // stroke color
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 30.dp, vertical = 7.dp)
                        .clickable {
                            val res = Intent(context, Register::class.java)
                            context.startActivity(res)
                        }
                )
            }
        }
    }
}