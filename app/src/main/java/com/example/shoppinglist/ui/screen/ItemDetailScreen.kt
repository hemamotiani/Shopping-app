package com.example.shoppinglist.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ItemDetailScreen(
    itemId: Int,
    navController: NavController,
    viewModel: ShoppingViewModel = hiltViewModel()
) {
    val itemFlow = remember(itemId) { viewModel.getItemById(itemId) }
    val item by itemFlow.collectAsState(initial = null)

    if (item == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        Text("Item Details", style = MaterialTheme.typography.headlineSmall)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(item!!.category.getIcon()),
                contentDescription = item!!.category.name,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(item!!.name, style = MaterialTheme.typography.titleLarge)
        }

        Text("Description: ${item!!.description}", style = MaterialTheme.typography.bodyLarge)
        Text("Category: ${item!!.category}", style = MaterialTheme.typography.bodyLarge)
        Text("Estimated Price: $${item!!.estimatedPrice}", style = MaterialTheme.typography.bodyLarge)
        Text(
            text = if (item!!.isBought) "Status: Bought" else "Status: Not bought",
            style = MaterialTheme.typography.bodyLarge,
            color = if (item!!.isBought) Color.Green else Color.Red
        )
    }
}