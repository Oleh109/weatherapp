package com.example.weatherapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R


@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchSubmit: () -> Unit
) {

    var isDropdownExpanded by remember { mutableStateOf(false) }
    var isCityNameValid by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = 18.dp,
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(size = 16.dp),
    ) {
        Column {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        isDropdownExpanded = focusState.isCaptured
                    },
                value = searchText,
                onValueChange = { text ->
                    onSearchTextChange(text)
                    isCityNameValid = text.isNotEmpty()
                },
                placeholder = {
                    Text(
                        text = "Search...",
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.subtitle2
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.primary,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                if (isCityNameValid) {
                                    onSearchSubmit()
                                }
                            },
                        painter = painterResource(R.drawable.ic_search),
                        tint = MaterialTheme.colors.primary,
                        contentDescription = "Search Button",
                    )
                }
            )
            //ToDO
//            if (isDropdownExpanded && searchText.isNotEmpty()) {
//                DropdownMenu(
//                    expanded = true,
//                    onDismissRequest = { isDropdownExpanded = false },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                ) {
//                    suggestions.filter { it.contains(searchText, ignoreCase = true) }
//                        .forEach { suggestion ->
//                            DropdownMenuItem(onClick = {
//                                onSearchTextChange(suggestion)
//                                isCityNameValid =
//                                    true // Consider the suggestion as a valid city name
//                                isDropdownExpanded = false
//                            }) {
//                                Text(text = suggestion)
//                            }
//                        }
//                }
//            }
        }
    }
}

