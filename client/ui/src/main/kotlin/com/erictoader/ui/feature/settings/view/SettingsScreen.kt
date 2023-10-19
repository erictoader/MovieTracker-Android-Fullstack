package com.erictoader.ui.feature.settings.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.erictoader.ui.feature.auth.view.SimpleTextField
import com.erictoader.ui.feature.auth.view.StyledButton
import com.erictoader.ui.feature.common.theme.spacing

@Composable
fun SettingsScreen(

) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(
                horizontal = MaterialTheme.spacing.large,
                vertical = MaterialTheme.spacing.XXL
            )
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InitialsCircle(
                firstName = "John",
                lastName = "Addams"
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                Text(
                    text = "John Addams",
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "@johnaddams",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        val email = remember { mutableStateOf("") }

        SimpleTextField(
            fieldState = email,
            label = "E-mail Adress"
        )

        val firstName = remember { mutableStateOf("") }

        SimpleTextField(
            fieldState = firstName,
            label = "First name"
        )

        val lastName = remember { mutableStateOf("") }

        SimpleTextField(
            fieldState = lastName,
            label = "Last name"
        )

        val isUnsavedChanges = email.value == ""

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        if (isUnsavedChanges) {
            StyledButton(text = "Submit changes") {

            }
        }
    }
}

@Composable
fun InitialsCircle(firstName: String, lastName: String) {
    val initials = "${firstName.firstOrNull() ?: ""}${lastName.firstOrNull() ?: ""}"

    Box(
        modifier = Modifier
            .size(MaterialTheme.spacing.huge)
            .clip(CircleShape)
            .background(MaterialTheme.colors.secondary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onSecondary,
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_SettingsScreen() {
    SettingsScreen()
}
