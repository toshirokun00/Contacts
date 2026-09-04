package com.comtrade.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.comtrade.presentation.ContactDetailScreenRoute
import com.comtrade.presentation.viewmodel.ContactsViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: ContactsViewModel) {
    val contacts = viewModel.contact.collectAsLazyPagingItems()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Contacts",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(contacts.itemCount, key = contacts.itemKey { it.id }) { index ->
                    contacts[index]?.let { contact ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        ContactDetailScreenRoute(
                                            email = contact.email,
                                            firstName = contact.firstName,
                                            lastName = contact.lastName,
                                            avatarUrl = contact.avatarUrl
                                        )
                                    )
                                }
                                .padding(vertical = 10.dp, horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Avatar(avatarUrl = contact.avatarUrl ?: "")
                            Text(
                                modifier = Modifier.padding(start = 10.dp),
                                text = "${contact.firstName} ${contact.lastName}",
                                style = TextStyle(fontSize = 16.sp),
                                color = Color.DarkGray
                            )
                        }
                    }
                }
                when (contacts.loadState.append) {
                    is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    is LoadState.Error -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                ,
                            ) {
                                Text("Error loading more. ")
                            }
                        }
                    }

                    is LoadState.NotLoading -> {

                    }
                }
            }
        }
    }
}
