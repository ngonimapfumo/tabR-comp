package com.ngoni.tabrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ngoni.tabrow.model.TabItem
import com.ngoni.tabrow.ui.theme.TabRowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val tabItems = listOf(
            TabItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unSelectedIcon = Icons.Outlined.Home
            ),
            TabItem(
                title = "People",
                selectedIcon = Icons.Filled.People,
                unSelectedIcon = Icons.Outlined.PeopleOutline
            )
        )

        setContent {
            TabRowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        var selectedTabIndex by remember {
                            mutableIntStateOf(0)
                        }
                        val pagerState = rememberPagerState {
                            tabItems.size
                        }
                        Column(modifier = Modifier.fillMaxSize()) {
                            TabRow(selectedTabIndex = selectedTabIndex) {
                                tabItems.forEachIndexed { index, tabItem ->
                                    Tab(
                                        index == selectedTabIndex,
                                        onClick = {
                                            selectedTabIndex = index
                                        },
                                        text = {
                                            Text(tabItem.title)
                                        }, icon = {
                                            Icon(
                                                imageVector = if (index == selectedTabIndex) {
                                                    tabItem.selectedIcon
                                                } else {
                                                    tabItem.unSelectedIcon
                                                },
                                                contentDescription = null
                                            )
                                        }
                                    )
                                }
                            }
                            HorizontalPager(pagerState,
                                modifier = Modifier.fillMaxWidth()
                                    .weight(1f)) {
                                    if (it == 0) {
                                        Text(text= "Home")
                                    } else {
                                        Text(text= "People")
                                    }

                            }
                        }

                    }
                }
            }
        }
    }
}
