package com.rahulraghuwanshi.tablesampleapp.lazyTable

/**
 * The configuration of pinned columns and rows.
 * Columns and rows are pinned from the beginning, then they are always displayed when user scrolls
 * in lazy table.
 *
 * @property columns The count of pinned columns for given row.
 * @property rows The count of pinned rows for given column.
 */
data class LazyTablePinConfiguration internal constructor(
    val columns: (row: Int) -> Int,
    val rows: (column: Int) -> Int,
)

/**
 * Creates configuration of pinned columns and rows.
 *
 * @param columns The count of pinned columns.
 * @param rows The count of pinned rows.
 */
fun lazyTablePinConfiguration(
    columns: Int = 0,
    rows: Int = 0,
): LazyTablePinConfiguration = LazyTablePinConfiguration({ columns }, { rows })

/**
 * Creates configuration of pinned columns and rows.
 *
 * @param columns The count of pinned columns for given row.
 * @param rows The count of pinned rows for given column.
 */
fun lazyTablePinConfiguration(
    columns: (row: Int) -> Int = { 0 },
    rows: (column: Int) -> Int = { 0 },
): LazyTablePinConfiguration = LazyTablePinConfiguration(columns, rows)