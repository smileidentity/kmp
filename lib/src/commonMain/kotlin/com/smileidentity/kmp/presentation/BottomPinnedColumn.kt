package com.smileidentity.kmp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A column with a pinned footer that is always visible at the bottom of the screen
 */

@Composable
fun BottomPinnedColumn(
    scrollableContent: @Composable ColumnScope.() -> Unit,
    pinnedContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    columnWidth: Dp? = null,
    showDivider: Boolean = false,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,

) {

    Scaffold { contentPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(contentPadding).padding(horizontal = 16.dp)

        ) {

            Column(
                horizontalAlignment = horizontalAlignment,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxHeight().then(
                    if (columnWidth != null) {
                        Modifier.width(columnWidth)
                    } else {
                        Modifier.fillMaxWidth()
                    },
                ).verticalScroll(rememberScrollState()).weight(1f),
            ) {
                scrollableContent()
            }
            if (showDivider) {
                HorizontalDivider(thickness = Dp.Hairline)
            }
            Column(
                horizontalAlignment = horizontalAlignment,
                modifier = Modifier.then(
                    if (columnWidth != null) {
                        Modifier.width(columnWidth)
                    } else {
                        Modifier.fillMaxWidth()
                    },
                ).padding(8.dp),
            ) {
                pinnedContent()
            }
        }
    }
}