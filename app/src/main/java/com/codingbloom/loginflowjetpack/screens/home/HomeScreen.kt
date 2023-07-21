package com.codingbloom.loginflowjetpack.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codingbloom.loginflowjetpack.R
import com.codingbloom.loginflowjetpack.components.AppToolBar
import com.codingbloom.loginflowjetpack.components.ButtonComponent
import com.codingbloom.loginflowjetpack.components.HeadingTextComponent
import com.codingbloom.loginflowjetpack.screens.signup.SignUpViewModel
import com.codingbloom.loginflowjetpack.ui.theme.WhiteColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(signUpViewModel: SignUpViewModel = viewModel()) {



    Scaffold(
      topBar = {
          AppToolBar(
              toolbarTitle = stringResource(id = R.string.home),
              logoutButtonClicked = {signUpViewModel.logOut()}
          )
      },
      content = { paddingValues ->

          Surface(
              modifier = Modifier
                  .fillMaxSize()
                  .background(WhiteColor)
                  .padding(paddingValues),
              content = {

                  Column(
                      modifier = Modifier.fillMaxSize(),
                      content = {
                          HeadingTextComponent(value = stringResource(R.string.home))
                      }
                  )
              }
          )
      }
    )
}

@Preview
@Composable
fun DefaultPreviewOfHomeScreen(){
    HomeScreen()
}
