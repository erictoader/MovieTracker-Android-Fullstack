package com.erictoader.ui.feature.auth.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erictoader.ui.R
import com.erictoader.ui.feature.auth.viewmodel.AuthEvent
import com.erictoader.ui.feature.auth.viewmodel.AuthState
import com.erictoader.ui.feature.common.navigator.ScreenDestination
import com.erictoader.ui.feature.common.navigator.navigate
import com.erictoader.ui.feature.common.theme.fontSize
import com.erictoader.ui.feature.common.theme.spacing

@Composable
fun AuthScreen(
    navController: NavController = rememberNavController(),
    state: AuthState,
    onEvent: (AuthEvent) -> Unit
) {
    var errorText by remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val scrollState = rememberScrollState()
        var showDialog by remember { mutableStateOf(false) }
        val painter = painterResource(id = R.drawable.backdrop)

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Surface(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.large,
                    vertical = MaterialTheme.spacing.XXL
                )
                .align(Alignment.Center),
            color = MaterialTheme.colors.surface,
            border = BorderStroke(MaterialTheme.spacing.minuscule, MaterialTheme.colors.background),
            shape = RoundedCornerShape(5)
        ) {
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Log in or Register",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = MaterialTheme.fontSize.large,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(Modifier.height(MaterialTheme.spacing.medium))

                if (errorText.isNotEmpty()) {
                    Text(
                        textAlign = TextAlign.Start,
                        text = errorText,
                        color = Color.Red,
                        fontSize = MaterialTheme.fontSize.small
                    )
                }

                SimpleTextField(fieldState = username, label = "Username")

                PasswordTextField(fieldState = password)

                Spacer(Modifier.height(MaterialTheme.spacing.medium))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    StyledButton(
                        modifier = Modifier.weight(1f),
                        text = "Register",
                        onClick = { showDialog = true }
                    )

                    StyledButton(
                        modifier = Modifier.weight(1f),
                        text = "Login",
                        onClick = {
                            onEvent(
                                AuthEvent.Login(
                                    username = username.value,
                                    password = password.value
                                )
                            )
                        }
                    )
                }
            }
        }

        RegisterDialog(
            modifier = Modifier.align(Alignment.Center),
            showDialog = showDialog,
            onClose = { showDialog = false },
            state = state,
            onRegister = { username, email, password ->
                onEvent(
                    AuthEvent.Register(
                        username = username,
                        email = email,
                        password = password
                    )
                )
            }
        )
    }

    LaunchedEffect(state) {
        when (state) {
            is AuthState.Init -> Unit

            is AuthState.RegisterSuccessful -> {
                errorText = ""
                username.value = state.usernameAutofill
                password.value = ""
            }

            is AuthState.LoginSuccessful -> {
                navController.navigate(ScreenDestination.Main)
            }

            is AuthState.ErrorLogin -> {
                errorText = state.message
            }

            else -> Unit
        }
    }
}

@Composable
fun RegisterDialog(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    onClose: () -> Unit,
    state: AuthState,
    onRegister: (username: String, email: String, password: String) -> Unit
) {
    var errorText by remember { mutableStateOf("") }

    if (showDialog) {
        val username = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        AlertDialog(
            modifier = modifier,
            onDismissRequest = onClose,
            title = { Text(text = "Register new account") },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                ) {
                    if (errorText.isNotEmpty()) {
                        Text(
                            textAlign = TextAlign.Start,
                            text = errorText,
                            color = Color.Red,
                            fontSize = MaterialTheme.fontSize.small
                        )
                    }

                    SimpleTextField(fieldState = username, label = "Username")

                    SimpleTextField(fieldState = email, label = "E-mail address")

                    PasswordTextField(fieldState = password)
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.large),
                    horizontalArrangement = Arrangement.Center
                ) {
                    StyledButton(
                        modifier = Modifier.weight(1f),
                        text = "Cancel",
                        onClick = onClose
                    )

                    StyledButton(
                        modifier = Modifier.weight(1f),
                        text = "Register",
                        onClick = {
                            onRegister(username.value, email.value, password.value)
                        }
                    )
                }
            },
        )
    }

    LaunchedEffect(state) {
        when (state) {
            is AuthState.ErrorRegister -> {
                errorText = state.message
            }

            is AuthState.RegisterSuccessful -> {
                errorText = ""
                onClose()
            }

            else -> Unit
        }
    }
}

@Composable
fun SimpleTextField(
    fieldState: MutableState<String>,
    label: String
) {
    TextField(
        value = fieldState.value,
        onValueChange = { newText ->
            fieldState.value = newText
        },
        label = { Text(label) },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Red.copy(0.6f),
            focusedLabelColor = MaterialTheme.colors.onSurface,
            unfocusedLabelColor = MaterialTheme.colors.onSurface
        )
    )
}

@Composable
private fun PasswordTextField(
    fieldState: MutableState<String>
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    TextField(
        value = fieldState.value,
        onValueChange = { newPassword ->
            fieldState.value = newPassword
        },
        label = { Text("Password") },
        visualTransformation = if (passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = if (passwordVisibility) Icons.Default.Visibility
                    else Icons.Default.VisibilityOff,
                    contentDescription = null
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Red.copy(0.6f),
            focusedLabelColor = MaterialTheme.colors.onSurface,
            unfocusedLabelColor = MaterialTheme.colors.onSurface
        )
    )
}

@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(horizontal = MaterialTheme.spacing.medium),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onBackground,
            contentColor = MaterialTheme.colors.surface,
        )
    ) {
        Text(text)
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_AuthScreen() {
    AuthScreen(
        state = AuthState.Init,
        onEvent = {}
    )
}
