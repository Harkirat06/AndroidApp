// NewQuotationViewModel.kt
package dadm.hsingh.quotationshake.ui.newquotation

import androidx.lifecycle.ViewModel
import dadm.hsingh.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewQuotationViewModel : ViewModel() {

    private val _userName = MutableStateFlow(getUserName())
    val userNameFlow = _userName.asStateFlow()

    private val _quotation = MutableStateFlow<Quotation?>(null)
    val quotation = _quotation.asStateFlow()

    private val _showIcon = MutableStateFlow<Boolean>(false)
    val showIcon = _showIcon.asStateFlow()

    private val _showFavouriteIcon = MutableStateFlow<Boolean>(false)
    val showFavouriteIcon = _showFavouriteIcon.asStateFlow()

    private fun getUserName(): String {
        return setOf("Alice", "Bob", "Charlie", "David", "Emma", "").random()
    }

    public fun getNewQuotation() {
        _showIcon.update {
            true
        }
        val num = (0..99).random()
        _quotation.update {
            Quotation(
                id = "$num",
                text = "Quotation text #$num",
                author = "Author #$num"
            )
        }
        _showIcon.update {
            false
        }
        _showFavouriteIcon.update {
            true
        }
    }

    public fun addToFavourites(){
        _showFavouriteIcon.update {
            false
        }
    }
}

