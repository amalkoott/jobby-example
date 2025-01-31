package ru.amalkoott.search_impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import ru.amalkoott.common.components.LoadingFiller
import ru.amalkoott.common.components.VacancyCard
import ru.amalkoott.model.Offer
import ru.amalkoott.model.Vacancy
import ru.amalkoott.common.R
import ru.amalkoott.search_impl.presentation.components.ExpandedBar
import ru.amalkoott.search_impl.presentation.components.OffersBar
import ru.amalkoott.search_impl.presentation.components.offer.OfferStrategy
import ru.amalkoott.search_impl.presentation.components.searchbar.SearchBar

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onClick: (Vacancy) -> Unit
){
    val isOfferLoading by viewModel.isOfferLoading.collectAsState()
    val getDisplayStrategy: (Offer) -> OfferStrategy = { offer -> viewModel.getDisplayStrategy(offer)}
    val isVacanciesLoading by viewModel.isVacanciesLoading.collectAsState()
    val vacancies by viewModel.vacancies.collectAsState(initial = emptyList())
    val offers by viewModel.offers.collectAsState(initial = emptyList())
    val expanded by viewModel.isVacancyExpanded
    val onFavoriteClick:(Vacancy) -> Unit = { vacancy -> viewModel.toggleFavorite(vacancy)}
    val onVacancyClick:(Vacancy) -> Unit = {
        vacancy -> viewModel.openVacancy(vacancy);
        onClick(vacancy)
    }
    val onActionClick = { if(expanded) { viewModel.toggleExpand() } else { /* TODO ACTION */ } }
    val onFilterClick = { /* TODO ACTION */ }
    val lazyListState = rememberLazyListState()
    val topBarStrategy = viewModel.getDisplayStrategy(expanded)
    val scope = rememberCoroutineScope()
    if(expanded) {
        Box(modifier = Modifier.fillMaxWidth()
            .height(dimensionResource(R.dimen.search_bar_height))
            .background(MaterialTheme.colorScheme.background)
            .shadow(
                elevation = dimensionResource(R.dimen.search_bar_elevation),
                ambientColor = Color.Black,
                shape = RectangleShape)
            .zIndex(4f)){
            SearchBar(topBarStrategy,onActionClick,onFilterClick)

        }
    }
    LazyColumn(state = lazyListState){
        if(!expanded){
            item{
                SearchBar(topBarStrategy, onActionClick, onFilterClick)
            }
            item {
                OffersBar(
                    isOfferLoading = isOfferLoading,
                    offers = offers,
                    getDisplayStrategy = getDisplayStrategy
                )

            }
            item{
                Text(
                    text = stringResource(R.string.introVacancies),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(
                        bottom = dimensionResource(R.dimen.search_intro_title_bottom_padding),
                        start = dimensionResource(R.dimen.search_intro_title_horizontal_padding),
                        end = dimensionResource(R.dimen.search_intro_title_horizontal_padding))
                )
            }
        }else{
            item{
                ExpandedBar(
                    vacanciesCount = vacancies.size
                )

            }
        }

        if(isVacanciesLoading){
            items(3){
                LoadingFiller(Modifier
                    .padding(
                    start = dimensionResource(R.dimen.vacancy_card_horizontal_padding),
                    end = dimensionResource(R.dimen.vacancy_card_horizontal_padding),
                    bottom = dimensionResource(R.dimen.vacancy_card_bottom_padding)
                )
                    .height(dimensionResource(R.dimen.vacancy_card_height))
                    .fillMaxWidth())
            }
        }else{
            items(items = if (!expanded) vacancies.take(3) else vacancies){ vacancy ->
                val strategy = viewModel.getDisplayStrategy(vacancy)
                VacancyCard(vacancy, strategy,{ onVacancyClick(vacancy) }, onFavoriteClick)
            }
            if(!expanded) {
                item {
                    Button(
                        onClick = {
                            scope.launch { lazyListState.scrollToItem(0) }
                            viewModel.toggleExpand() },
                        colors = ButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSurface,
                            disabledContainerColor = MaterialTheme.colorScheme.secondary,
                            disabledContentColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                        shape = RoundedCornerShape(dimensionResource(R.dimen.show_more_btn_corner)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = dimensionResource(R.dimen.show_more_btn_top_padding),
                                bottom = dimensionResource(R.dimen.show_more_btn_bottom_padding),
                                start = dimensionResource(R.dimen.show_more_btn_horizontal_padding),
                                end = dimensionResource(R.dimen.show_more_btn_horizontal_padding)
                            )
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.show_more_btn_text_horizontal_padding)),
                            text = viewModel.getShowMoreText(),
                            style = MaterialTheme.typography.displayLarge,
                        )
                    }

                }
            }else item{
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.search_layout_bottom_padding)))
            }
        }
    }
}