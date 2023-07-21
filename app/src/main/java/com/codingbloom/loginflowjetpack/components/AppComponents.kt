package com.codingbloom.loginflowjetpack.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codingbloom.loginflowjetpack.R
import com.codingbloom.loginflowjetpack.ui.theme.BackgroundColor
import com.codingbloom.loginflowjetpack.ui.theme.GrayColor
import com.codingbloom.loginflowjetpack.ui.theme.Primary
import com.codingbloom.loginflowjetpack.ui.theme.Secondary
import com.codingbloom.loginflowjetpack.ui.theme.TextColor
import com.codingbloom.loginflowjetpack.ui.theme.WhiteColor

@Composable
fun NormalTextComponent(value : String) {

    //Here heightIn is like xml wrap content...
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value : String) {

    //Here heightIn is like xml wrap content...
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {

    val textValue = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = BackgroundColor
        ),
        leadingIcon = {
            Icon(
                painter = painterResource,
                contentDescription = ""
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
        isError = !errorStatus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPasswordTextField(
    labelValue: String,
    painterResource: Painter,
    onPasswordChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {

    val localFocusManager = LocalFocusManager.current
    val passwordValue = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(

            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = BackgroundColor
        ),
        leadingIcon = {
            Icon(
                painter = painterResource,
                contentDescription = ""
            )
        },
        trailingIcon = {
               val iconImage = if(passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

                val description = if (passwordVisible.value)
                                    stringResource(id = R.string.show_password)
                                else
                                    stringResource(id = R.string.hide_password)

            IconButton(onClick = {passwordVisible.value = !passwordVisible.value}) {
                
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },

        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions{localFocusManager.clearFocus()},
        value = passwordValue.value,
        onValueChange = {
            passwordValue.value = it
            onPasswordChanged(it)
        },
        isError = !errorStatus
    )
}

@Composable
fun CheckBoxComponent(onTextSelected: (String) -> Unit, onCheckedChange: (Boolean) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,

        content = {
            val checkedState = remember{ mutableStateOf(false) }

            Checkbox(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = !checkedState.value
                    onCheckedChange.invoke(it)
                }
            )
            
            ClickableTextComponent(onTextSelected = onTextSelected)
        }
    )
}

@Composable
fun ClickableTextComponent(onTextSelected: (String) -> Unit) {

    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy "
    val andText = "and "
    val termsAndConditionsText = "Term of Use"

    val annotatedString = buildAnnotatedString {

        append(initialText)

        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }

        append(andText)

        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }
    
    ClickableText(
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->

                    if ((span.item == termsAndConditionsText) || (span.item == privacyPolicyText)){

                        onTextSelected(span.item)
                    }
                }
        }
    )
}

@Composable
fun ButtonComponent(
    value: String,
    onButtonClicked: () -> Unit,
    isEnabled: Boolean = false
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        onClick = { onButtonClicked.invoke() },
        enabled = isEnabled,
        content = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                        shape = RoundedCornerShape(50.dp)
                    ),
                contentAlignment = Alignment.Center,
                content = {
                    Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            )
        }
    )
}

@Composable
fun DividerTextComponent() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        content = {

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = GrayColor,
                thickness = 1.dp
            )
            
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.or),
                fontSize = 18.sp,
                color = TextColor
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = GrayColor,
                thickness = 1.dp
            )
            
        }
    )
}

@Composable
fun ClickableLoginTextComponent(
    tryingToLogin: Boolean = true,
    onTextSelected: (String) -> Unit
) {

    val initialText = if (tryingToLogin) "Already have an account? " else "Don't have an account yet? "
    val loginText = if (tryingToLogin) "Login " else "Register"

    val annotatedString = buildAnnotatedString {

        append(initialText)

        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->

                    if (span.item == loginText){

                        onTextSelected(span.item)
                    }
                }
        }
    )
}

@Composable
fun UnderlineTextComponent(value : String) {

    //Here heightIn is like xml wrap content...
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textDecoration = TextDecoration.Underline
        ),
        color = colorResource(id = R.color.colorGray),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(toolbarTitle: String, logoutButtonClicked: () -> Unit) {

    TopAppBar(
        title = {
            Text(text = toolbarTitle, color = WhiteColor)
        },
        navigationIcon = {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon", tint = WhiteColor)
        },
        actions = {

            IconButton(
                onClick = logoutButtonClicked,
                content = {
                    Icon(imageVector = Icons.Filled.Logout, contentDescription = "Logout Icon")
                }
            )
        }
    )
}
