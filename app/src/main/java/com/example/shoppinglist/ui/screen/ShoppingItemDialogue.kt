package com.example.shoppinglist.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.shoppinglist.data.ItemCategory
import com.example.shoppinglist.data.ShoppingItem
import com.example.shoppinglist.ui.components.SpinnerSample

@Composable
fun ShoppingItemDialog(
    viewModel: ShoppingViewModel,
    itemToEdit: ShoppingItem? = null,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf(itemToEdit?.name ?: "") }
    var description by remember { mutableStateOf(itemToEdit?.description ?: "") }
    var priceText by remember { mutableStateOf(itemToEdit?.estimatedPrice?.toString() ?: "") }
    var selectedCategory by remember { mutableStateOf(itemToEdit?.category?.name ?: "Food") }
    var isBought by remember { mutableStateOf(itemToEdit?.isBought ?: false) }

    var errorMessage by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = if (itemToEdit == null) "New Shopping Item" else "Edit Item",
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Item Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = priceText,
                    onValueChange = { priceText = it },
                    label = { Text("Price") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                // Display price error message if needed
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                SpinnerSample(
                    list = listOf("Food", "Electronic", "Book"),
                    preselected = selectedCategory,
                    onSelectionChanged = { selectedCategory = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isBought,
                        onCheckedChange = { isBought = it }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Bought")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        val price = priceText.toDoubleOrNull()
                        val categoryEnum = ItemCategory.valueOf(selectedCategory.uppercase())

                        // Reset error message before checking fields
                        errorMessage = ""

                        // Validate name, description, and price
                        when {
                            name.isBlank() || description.isBlank() -> {
                                errorMessage = "All fields are required!"
                            }
                            price == null -> {
                                errorMessage = "Invalid price"
                            }
                            else -> {
                                val item = ShoppingItem(
                                    id = itemToEdit?.id ?: 0,
                                    name = name,
                                    description = description,
                                    category = categoryEnum,
                                    estimatedPrice = price,
                                    isBought = isBought
                                )

                                if (itemToEdit == null) {
                                    viewModel.addItem(item)
                                } else {
                                    viewModel.updateItem(item)
                                }

                                onDismiss()
                            }
                        }
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}