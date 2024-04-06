package dadm.hsingh.quotationshake.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.quotationshake.data.favourites.FavouritesRepository
import dadm.hsingh.quotationshake.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {
    val favoriteQuotations: StateFlow<List<Quotation>> = favouritesRepository.getAllQuotations().stateIn(
        scope = viewModelScope,
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed()
    )

    val isDeleteAllMenuVisible: StateFlow<Boolean> = favoriteQuotations.map { list ->
        list.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = true
    )


    fun deleteAllQuotations(){
        viewModelScope.launch {
            favouritesRepository.deleteAllQuotations()
        }
    }

    fun deleteQuotationAtPosition(position: Int) {
        viewModelScope.launch {
            val quotationToDelete = favoriteQuotations.value[position]
            favouritesRepository.deleteQuotation(quotationToDelete)
        }
    }

}