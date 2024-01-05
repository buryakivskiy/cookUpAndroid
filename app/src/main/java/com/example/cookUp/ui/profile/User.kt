package com.example.cookUp.ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cookUp.R
import com.example.cookUp.retrofit.Api
import com.example.cookUp.retrofit.dto.LoginRequest
import com.example.cookUp.retrofit.dto.SignUpRequest
import com.example.cookUp.retrofit.dto.User
import com.example.cookUp.ui.components.CookUpSurface
import com.example.cookUp.ui.home.CookUpBottomBar
import com.example.cookUp.ui.home.HomeSections
import com.example.cookUp.ui.theme.Ocean0
import com.example.cookUp.ui.theme.Rose4
import com.example.cookUp.ui.utils.AuthTokenManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun User(
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
    user: User,
) {
    val context = LocalContext.current
    val token = AuthTokenManager(context).getAuthToken()
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
            Spacer(modifier = Modifier.height(150.dp))

            CookUpSurface(
                color = Color.LightGray,
                elevation = 16.dp,
                shape = CircleShape,
                modifier = modifier
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://static-00.iconduck.com/assets.00/user-avatar-icon-512x512-vufpcmdn.png")
                        .crossfade(true)
                        .build(),
                    contentDescription = "description",
                    placeholder = painterResource(R.drawable.placeholder),
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Ім'я користувача
            Text(
                text = user.firstName.toString(),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Shadow5
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Емейл користувача
            Text(
                text = user.email,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Shadow5
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Кнопка виходу з облікового запису
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 20.dp),
                onClick = {
                    AuthTokenManager(context).clearAuthToken()
                    onNavigateToRoute("home/login")
                },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(Shadow5),
            ) {
                Text(
                    text = "Вийти",
                    color = Ocean0,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Видалити акаунт",
                modifier = Modifier
                    .clickable {
                        val retrofit = Retrofit.Builder()
                            .baseUrl("https://cook-up-d8bf3908895c.herokuapp.com/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        val api = retrofit.create<Api>(Api::class.java)

                        if (token.isNullOrEmpty()) {
                            onNavigateToRoute("home/login")
                        }

                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                val res = token?.let { it1 -> api.deleteUser(it1) }

                                if (res != null) {
                                    if (res.email.isEmpty()) {
                                        throw Error();
                                    }
                                } else {
                                    AuthTokenManager(context).clearAuthToken()
                                    onNavigateToRoute("home/login")
                                }
                            } catch (e: Exception) {
                                print(e.message)
                            }
                        }
                        AuthTokenManager(context).clearAuthToken()
                        onNavigateToRoute("home/login")
                    }
                    .padding(12.dp)
                    .align(Alignment.CenterHorizontally),
                color = Rose4,
                fontSize = 16.sp
            )
        }
    }
}