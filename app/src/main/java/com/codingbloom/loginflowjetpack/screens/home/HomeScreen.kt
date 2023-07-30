package com.codingbloom.loginflowjetpack.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codingbloom.loginflowjetpack.R
import com.codingbloom.loginflowjetpack.components.AppDrawer
import com.codingbloom.loginflowjetpack.components.AppToolBar
import com.codingbloom.loginflowjetpack.components.HeadingTextComponent
import com.codingbloom.loginflowjetpack.navigation.AppRoutes
import com.codingbloom.loginflowjetpack.ui.theme.WhiteColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val currentScreenRoute = AppRoutes.currentScreen.value

    homeViewModel.getUserData()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                route = currentScreenRoute.toString(),

                closeDrawer = {

                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
        },
        gesturesEnabled = drawerState.isOpen,
        content = {

            Scaffold(
                topBar = {
                    AppToolBar(
                        toolbarTitle = stringResource(id = R.string.home),
                        logoutButtonClicked = {homeViewModel.logOut()},
                        navigationIconClicked = {
                            coroutineScope.launch { drawerState.open() }
                        }
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
                                },
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            )
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
