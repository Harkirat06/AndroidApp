package dadm.hsingh.quotationshake.ui.favourites

import androidx.lifecycle.ViewModel
import dadm.hsingh.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavouritesViewModel : ViewModel() {
    private val _favoriteQuotations = MutableStateFlow<List<Quotation>>(emptyList())
    val favoriteQuotations: StateFlow<List<Quotation>> = _favoriteQuotations

    init {
        _favoriteQuotations.value = getFavoriteQuotations()
    }

    private fun getFavoriteQuotations(): List<Quotation> {
        return (1..20).map { Quotation(it.toString(), "Cita aleatoria número $it", "Autor aleatorio $it" )}
    }
}