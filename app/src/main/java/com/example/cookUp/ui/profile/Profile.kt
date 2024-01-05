package com.example.cookUp.ui.profile

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookUp.R
import com.example.cookUp.retrofit.Api
import com.example.cookUp.retrofit.dto.LoginRequest
import com.example.cookUp.retrofit.dto.User
import com.example.cookUp.ui.components.CookUpScaffold
import com.example.cookUp.ui.home.CookUpBottomBar
import com.example.cookUp.ui.home.HomeSections
import com.example.cookUp.ui.theme.CookUpTheme
import com.example.cookUp.ui.theme.Ocean0
import com.example.cookUp.ui.theme.Shadow5
import com.example.cookUp.ui.utils.AuthTokenManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Profile(
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val token = AuthTokenManager(context).getAuthToken()
    if (token.isNullOrEmpty()) {
        Login(onNavigateToRoute, modifier)
    } else {
        var user by remember { mutableStateOf<User?>(null) }

        LaunchedEffect(token) {
            if (!token.isNullOrEmpty()) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://cook-up-d8bf3908895c.herokuapp.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val api = retrofit.create<Api>(Api::class.java)

                try {
                    val res = api.getUser(token)
                    user = res
                } catch (e: Exception) {
                    print(e.message)
                }
            }
        }

        if (user != null) {
            User(onNavigateToRoute, modifier, user!!)
        } else {
            Login(onNavigateToRoute, modifier)
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun ProfilePreview() {
    CookUpTheme {
        Profile(onNavigateToRoute = { })
    }
}
