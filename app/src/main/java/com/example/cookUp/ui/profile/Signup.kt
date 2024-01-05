package com.example.cookUp.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.cookUp.ui.components.CookUpScaffold
import com.example.cookUp.ui.theme.Shadow5
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.cookUp.retrofit.Api
import com.example.cookUp.retrofit.dto.SignUpRequest
import com.example.cookUp.ui.home.CookUpBottomBar
import com.example.cookUp.ui.home.HomeSections
import com.example.cookUp.ui.theme.Ocean0
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.absoluteValue


@Composable
fun Signup(
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    CookUpScaffold(
        bottomBar = {
            CookUpBottomBar(
                tabs = HomeSections.entries.toTypedArray(),
                currentRoute = HomeSections.PROFILE.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(100.dp))

            var emailLength by remember { mutableIntStateOf(0) }
            val email = remember { mutableStateOf(TextFieldValue()) }

            var firstNameLength by remember { mutableIntStateOf(0) }
            var lastNameLength by remember { mutableIntStateOf(0) }
            val firstName = remember { mutableStateOf(TextFieldValue()) }
            val lastName = remember { mutableStateOf(TextFieldValue()) }

            val password = remember { mutableStateOf(TextFieldValue()) }
            var passwordVisible by rememberSaveable { mutableStateOf(false) }
            var passwordFocus = false

            Spacer(modifier = Modifier.height(20.dp))

            //MARK: Email
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 10.dp)
                    .onFocusChanged {
                        if (it.isFocused) {
                            //passwordFocus = false
                            emailLength = 1
                        }
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Shadow5,
                    unfocusedBorderColor = Shadow5,
                    cursorColor = Shadow5,
                    textColor = Shadow5,
                    placeholderColor = Shadow5
                ),
                label = { Text(text = "Емейл", fontSize = 16.sp, color = Shadow5) },
                singleLine = true,
                value = email.value,
                onValueChange = {
                    email.value = it
                    emailLength = it.text.length
                },
            )

            //MARK: First name
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 10.dp)
                    .onFocusChanged {
                        if (it.isFocused) {
                            //passwordFocus = false
                            firstNameLength = 1
                        }
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Shadow5,
                    unfocusedBorderColor = Shadow5,
                    cursorColor = Shadow5,
                    textColor = Shadow5,
                    placeholderColor = Shadow5
                ),
                label = { Text(text = "Імʼя", fontSize = 16.sp, color = Shadow5) },
                singleLine = true,
                value = firstName.value,
                onValueChange = {
                    firstName.value = it
                    firstNameLength = it.text.length
                },
            )

            //MARK: Last name
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 10.dp)
                    .onFocusChanged {
                        if (it.isFocused) {
                            //passwordFocus = false
                            lastNameLength = 1
                        }
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Shadow5,
                    unfocusedBorderColor = Shadow5,
                    cursorColor = Shadow5,
                    textColor = Shadow5,
                    placeholderColor = Shadow5
                ),
                label = { Text(text = "Прізвище", fontSize = 16.sp, color = Shadow5) },
                singleLine = true,
                value = lastName.value,
                onValueChange = {
                    lastName.value = it
                    lastNameLength = it.text.length
                },
            )

            //MARK: Password
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 10.dp)
                    .onFocusChanged {
                        if (it.isFocused) {
                            passwordFocus = true
                            emailLength = 200
                        }
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Shadow5,
                    unfocusedBorderColor = Shadow5,
                    cursorColor = Shadow5,
                    textColor = Shadow5,
                    placeholderColor = Shadow5
                ),
                label = { Text("Пароль", color = Shadow5)},
                placeholder = { Text("Введіть пароль")},
                singleLine = true,
                value = password.value,
                onValueChange = { password.value = it },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image: ImageVector
                    val description: String
                    if (passwordVisible) {
                        image = Icons.Filled.Visibility
                        description = "Сховати пароль"
                        if (!passwordFocus) {
                            passwordFocus = true
                            emailLength = 0
                        }

                    } else {
                        image = Icons.Filled.VisibilityOff
                        description = "Показати пароль"
                        if (passwordFocus) {
                            passwordFocus = false
                            emailLength = 200
                        }
                    }
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description, tint = Shadow5)
                    }
                }
            )

            //MARK: Sign up button
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 20.dp),
                onClick = {
                    if (email.value.text.isNotEmpty() &&
                        firstName.value.text.isNotEmpty() &&
                        lastName.value.text.isNotEmpty() &&
                        password.value.text.isNotEmpty()) {
                        val retrofit = Retrofit.Builder()
                            .baseUrl("https://cook-up-d8bf3908895c.herokuapp.com/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        val api = retrofit.create<Api>(Api::class.java)

                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                val res = api.signUp(
                                    SignUpRequest(
                                        email = email.value.text,
                                        firstName = firstName.value.text,
                                        lastName = lastName.value.text,
                                        password = password.value.text
                                    )
                                )

                                if (res.email.isEmpty()) {
                                    throw Error();
                                }
                            } catch (e: Exception) {
                                print(e.message)
                            }
                        }
                        onNavigateToRoute("home/login")
                    }
                },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(Shadow5),
            ) {
                Text(
                    text = "Зареєструватись",
                    color = Ocean0,
                    fontSize = 16.sp
                )
            }
        }
    }
}