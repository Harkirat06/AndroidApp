package dadm.hsingh.quotationshake.ui.newquotation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dadm.hsingh.quotationshake.R
import dadm.hsingh.quotationshake.databinding.FragmentNewQuotationBinding
import kotlinx.coroutines.launch

class NewQuotationFragment : Fragment(R.layout.fragment_new_quotation)  {
    private var _binding: FragmentNewQuotationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewQuotationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewQuotationBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userNameFlow.collect { userName ->
                    updateWelcomeMessage(userName)
                }
            }
        }
    }

    private fun updateWelcomeMessage(userName: String) {
        val greetingMessage = if (userName.isNotEmpty()) {
            getString(R.string.greetings, userName)
        } else {
            getString(R.string.greetings, getString(R.string.anonymous))
        }
        binding.welcomeTextView.text = greetingMessage
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


