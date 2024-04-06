package dadm.hsingh.quotationshake.ui.newquotation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.quotationshake.data.favourites.FavouritesRepository
import dadm.hsingh.quotationshake.data.newquotation.NewQuotationRepository
import dadm.hsingh.quotationshake.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import dadm.hsingh.quotationshake.data.settings.SettingsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class NewQuotationViewModel @Inject constructor(
    private val repository: NewQuotationRepository,
    private val settingsRepository: SettingsRepository,
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {

    val userNameFlow: StateFlow<String> = settingsRepository.getUserName().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    private val _quotation = MutableStateFlow<Quotation?>(null)
    val quotation = _quotation.asStateFlow()

    private val _showIcon = MutableStateFlow<Boolean>(false)
    val showIcon = _showIcon.asStateFlow()

    private val _showFavouriteIcon = MutableStateFlow<Boolean>(false)
    val showFavouriteIcon = _showFavouriteIcon.asStateFlow()

    private val _error = MutableStateFlow<Throwable?>(null)
    val error = _error.asStateFlow()

    fun getNewQuotation(): Job {
        return viewModelScope.launch {
            repository.getNewQuotation().fold(
                onSuccess = { quotation ->
                    _quotation.value = quotation
                    _showIcon.update {
                        false
                    }
                    _showFavouriteIcon.update {
                        true
                    }
                },
                onFailure = { throwable ->
                    _error.value = throwable
                }
            )
        }
    }

    fun addToFavourites(){
        viewModelScope.launch {
            _quotation.value?.let { quotation ->
                favouritesRepository.addQuotation(quotation)
            }
            _showFavouriteIcon.update {
                false
            }
        }
    }

    fun resetError() {
        _error.value = null
    }
}
