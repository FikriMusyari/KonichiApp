package com.fmt.konichi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.fmt.konichi.Model.AuthModel

class Register : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewmodel = ViewModelProvider(this)[AuthModel::class.java]

        setContent {
            SignupScreen(authModel = viewmodel)
        }
    }
}

@Composable
fun SignupScreen(authModel: AuthModel) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        // Background dengan efek blur
        Image(
            painter = painterResource(id = R.drawable.backgroundsignup),
            contentDescription = "Background signup",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 20.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),

            ) {
            Text(
                text = stringResource(id = R.string.create_naccount),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Nama
            Text(
                text = stringResource(id = R.string.name),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(stringResource(id = R.string.enter_your_name)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email
            Text(
                text = "Email",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Enter your Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password
            Text(
                text = stringResource(id = R.string.password),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Enter your Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Password
            Text(
                text = "Confirm Password",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = { Text("Repeat your password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(15.dp))
            
            // Signup Button
            Button(
                onClick = { authModel.signUp(email,name,password,confirmPassword){sukses, pesan ->
                    if (sukses){
                        val res = Intent(context, MainActivity::class.java)
                        context.startActivity(res)
                        Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show()
                    }
                } },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF047857))
            ) {
                Text(
                    text = "Signup",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Already have an account?")
                Text(
                    text = "Login",
                    color = Color(0xFF00C853),
                    modifier = Modifier
                        .padding(start = 16.dp)
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
                            val res = Intent(context, MainActivity::class.java)
                            context.startActivity(res)
                        }
                )
            }
        }
    }
}