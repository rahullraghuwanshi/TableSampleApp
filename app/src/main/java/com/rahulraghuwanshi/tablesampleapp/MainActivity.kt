package com.rahulraghuwanshi.tablesampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rahulraghuwanshi.tablesampleapp.lazyTable.LazyTable
import com.rahulraghuwanshi.tablesampleapp.lazyTable.LazyTableItem
import com.rahulraghuwanshi.tablesampleapp.lazyTable.lazyTableDimensions
import com.rahulraghuwanshi.tablesampleapp.ui.theme.TableSampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TableSampleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    LazyTableSampleScreenFirst()

                    TableScreenSecond()
                }
            }
        }
    }
}

@Composable
fun LazyTableScreen() {
    val columns = 100
    val rows = 1000
    LazyTable(
        dimensions = lazyTableDimensions(48.dp, 32.dp)
    ) {
        items(
            count = columns * rows,
            layoutInfo = {
                LazyTableItem(
                    column = it % columns,
                    row = it / columns,
                )
            }
        ) { index ->
            Text(text = "#$index")
        }
    }
}

/**
 * Basic lazy table usage.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyTableSampleScreenFirst() {
    Column {
        var rowValue by remember { mutableStateOf("30") }
        var columnValue by remember { mutableStateOf("30") }

        val cells = remember { derivedStateOf { createCells(columnValue.toInt(), rowValue.toInt()) } }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Row       : ")
            TextField(
                modifier = Modifier.padding(horizontal = 12.dp),
                value = rowValue, onValueChange = { value ->
                    rowValue = value
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Column : ")
            TextField(
                modifier = Modifier.padding(horizontal = 12.dp),
                value = columnValue, onValueChange = { value ->
                    columnValue = value
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }


        LazyTable(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(
                items = cells.value,
                layoutInfo = { LazyTableItem(it.first, it.second) }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .border(Dp.Hairline, MaterialTheme.colorScheme.onSurface)
                ) {
                    Text(text = "$it")
                }
            }
        }
    }
}

/**
 * Creates data for lazy table.
 */
fun createCells(columns: Int = COLUMNS, rows: Int = ROWS): List<Pair<Int, Int>> =
    buildList {
        repeat(rows) { row ->
            repeat(columns) { column ->
                add(column to row)
            }
        }
    }

private const val COLUMNS = 10
private const val ROWS = 30