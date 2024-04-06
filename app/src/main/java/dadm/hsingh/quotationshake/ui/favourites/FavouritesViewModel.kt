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

    private fun getFavoriteQuotations(): List<Quotation> {
        val quotations = mutableListOf<Quotation>()
        quotations.add(Quotation("19", "La imaginación es más importante que el conocimiento.", "Albert Einstein"))
        quotations.add(Quotation("20", "Cita anónima", "Anonymous"))
        quotations.addAll((1..18).map { Quotation(it.toString(), "Cita aleatoria número $it", "Autor aleatorio $it") })
        return quotations
    }


    fun deleteAllQuotations(){
        /*
        _favoriteQuotations.update{
            emptyList()
        }
         */
    }
    fun deleteQuotationAtPosition(position: Int) {
        /*
        val currentQuotations = _favoriteQuotations.value.toMutableList()
        val newList = currentQuotations.minus(currentQuotations.elementAt(position))
        _favoriteQuotations.update {
            newList
        }
         */
    }

}