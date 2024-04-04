package dadm.hsingh.quotationshake.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class FavouritesViewModel @Inject constructor() : ViewModel() {
    private val _favoriteQuotations = MutableStateFlow<List<Quotation>>(emptyList())
    val favoriteQuotations: StateFlow<List<Quotation>> = _favoriteQuotations

    val isDeleteAllMenuVisible: StateFlow<Boolean> = favoriteQuotations.map { list ->
        list.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = true
    )

    init {
        _favoriteQuotations.value = getFavoriteQuotations()
    }

    private fun getFavoriteQuotations(): List<Quotation> {
        val quotations = mutableListOf<Quotation>()
        quotations.add(Quotation("19", "La imaginación es más importante que el conocimiento.", "Albert Einstein"))
        quotations.add(Quotation("20", "Cita anónima", "Anonymous"))
        quotations.addAll((1..18).map { Quotation(it.toString(), "Cita aleatoria número $it", "Autor aleatorio $it") })
        return quotations
    }
    fun deleteAllQuotations(){
        _favoriteQuotations.update{
            emptyList()
        }
    }
    fun deleteQuotationAtPosition(position: Int) {
        val currentQuotations = _favoriteQuotations.value.toMutableList()
        val newList = currentQuotations.minus(currentQuotations.elementAt(position))
        _favoriteQuotations.update {
            newList
        }
    }
}