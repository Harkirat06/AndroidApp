package dadm.hsingh.quotationshake.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FavouritesViewModel : ViewModel() {
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
        return (1..20).map { Quotation(it.toString(), "Cita aleatoria número $it", "Autor aleatorio $it" )}
    }
    public fun deleteAllQuotations(){
        _favoriteQuotations.update{
            emptyList<Quotation>()
        }
    }
}