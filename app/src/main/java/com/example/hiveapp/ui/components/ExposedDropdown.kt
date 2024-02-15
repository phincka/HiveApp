package com.example.hiveapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdown(
    expanded: Boolean,
    setExpanded: (Boolean) -> Unit,
    options: List<String>,
    selected: Int,
    setSelected: (Int) -> Unit,
    label: String,
) {
    ExposedDropdownMenuBox(
        expanded,
        onExpandedChange = { setExpanded(it) },
        modifier = Modifier.fillMaxWidth(),
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = options[selected],
            onValueChange = {},
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded,
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = { setExpanded(false) },
        ) {
            options.forEachIndexed { index, selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        setSelected(index)
                        setExpanded(false)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}