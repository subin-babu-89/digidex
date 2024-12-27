package com.example.digidex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.digidex.network.model.DigimonDetails
import kotlinx.coroutines.launch

@Composable
fun AdditionalPageInfo(
    modifier: Modifier = Modifier,
    releaseData: String,
    descriptions: List<DigimonDetails.Descriptions>,
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { descriptions.size })
    val selectedTabIndex by remember {
        derivedStateOf {
            pagerState.currentPage
        }
    }
    Column(modifier = modifier) {
        AttributeDisplay(
            label = "Release date",
            value = releaseData
        )
        Spacer(modifier = Modifier.height(16.dp))
        TabRow(selectedTabIndex = selectedTabIndex, Modifier.fillMaxWidth()) {
            descriptions.forEachIndexed { index, description ->
                Tab(selected = selectedTabIndex == index, onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }) {
                    Text(text = description.language)
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = descriptions[selectedTabIndex].description, textAlign = TextAlign.Justify)
        }
    }
}

@Preview(showBackground = true, )
@Composable
private fun AdditionalPageInfoPreview() {
    AdditionalPageInfo(
        releaseData = "2000", descriptions = listOf(
            DigimonDetails.Descriptions(
                origin = "reference_book",
                language = "jap",
                description = "“知識のデジメンタル”のパワーによって進化したアーマー体の昆虫型デジモン。“知識のデジメンタル”は“土”の属性を持っており、このデジメンタルを身に付けたものは大地を操る力を持つ。その特異な形状からみてもわかるように、地中での戦いにおいてはどのデジモンにも負けることはない。得意技は回転するドリルで地面に衝撃を与え、地割れを起こす「ビッグクラック」。必殺技は、鼻先と両手のドリルを高速回転させて、ドリルによる一斉攻撃をする「ゴールドラッシュ」。"
            ),
            DigimonDetails.Descriptions(
                origin = "reference_book",
                language = "en_us",
                description = "An Armor-level Insect Digimon which evolved through the power of the \"Digimental of Knowledge\". The \"Digimental of Knowledge\" has the attribute of \"Earth\", and those that don this Digimental wield the power to manipulate the earth. As can be seen from its unique form, it never loses to any Digimon in underground battles. Its Signature Move is impacting the ground with its spinning drills, causing a fissure (Big Crack). Its Special Move is rapidly spinning the drills on its snout and both of its hands, performing a simultaneous attack with its drills (Gold Rush)."
            ),
        )
    )
}