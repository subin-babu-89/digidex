package com.example.digidex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class AdditionalDetailsPage(val title: String, val content: @Composable () -> Unit)

@Composable
fun AdditionalDetails(
    modifier: Modifier = Modifier,
    subPages: List<AdditionalDetailsPage>,
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { subPages.size })
    val selectedTabIndex by remember { derivedStateOf { pagerState.currentPage } }
    Column(modifier = modifier) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth()
        ) {
            subPages.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex == index,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary,
                    onClick = {scope.launch {
                        pagerState.animateScrollToPage(subPages.indexOf(currentTab))
                    }}
                ) {
                    Text(text = currentTab.title)
                }
            }
        }
        HorizontalPager(state = pagerState) { page ->
            subPages[page].content()
        }
    }
}

@Preview
@Composable
private fun AdditionalDetailsPreview() {
    AdditionalDetails(
        subPages = listOf(
            AdditionalDetailsPage(title = "page1") {
                Text(text = "page1 content")
            },
            AdditionalDetailsPage(title = "page2") {
                Text(text = "page2 content")
            }
        )
    )
}