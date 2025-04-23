package com.example.shoppinglist.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shoppinglist.data.ShoppingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    navController: NavHostController,
    viewModel: ShoppingViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val itemList by viewModel.shoppingItems.collectAsState(emptyList())

    var showDialog by rememberSaveable { mutableStateOf(false) }
    var itemToEdit: ShoppingItem? by rememberSaveable { mutableStateOf(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping List") },
                actions = {
                    IconButton(onClick = { viewModel.clearAll() }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete all")
                    }
                    IconButton(onClick = {
                        itemToEdit = null
                        showDialog = true
                    }) {
                        Icon(Icons.Filled.AddCircle, contentDescription = "Add item")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            if (showDialog) {
                ShoppingItemDialog(
                    viewModel = viewModel,
                    itemToEdit = itemToEdit,
                    onDismiss = { showDialog = false }
                )
            }

            if (itemList.isEmpty()) {
                Text("No shopping items added yet", modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn {
                    items(itemList) { item ->
                        ShoppingItemCard(
                            item = item,
                            onCheckedChange = { isChecked ->
                                viewModel.updateItem(item.copy(isBought = isChecked))
                            },
                            onEdit = {
                                itemToEdit = it
                                showDialog = true
                            },
                            onDelete = {
                                viewModel.deleteItem(item)
                            },
                            navController = navController

                        )
                    }
                }
            }
        }
    }
}