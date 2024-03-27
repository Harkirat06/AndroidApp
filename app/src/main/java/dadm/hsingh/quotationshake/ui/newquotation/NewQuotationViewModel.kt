// NewQuotationViewModel.kt
package dadm.hsingh.quotationshake.ui.newquotation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewQuotationViewModel : ViewModel() {

    private val _userNameFlow = MutableStateFlow(getUserName())
    val userNameFlow = _userNameFlow.asStateFlow()

    private fun getUserName(): String {
        return setOf("Alice", "Bob", "Charlie", "David", "Emma", "").random()
    }
}
