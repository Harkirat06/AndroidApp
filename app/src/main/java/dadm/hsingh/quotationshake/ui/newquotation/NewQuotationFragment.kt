package dadm.hsingh.quotationshake.ui.newquotation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dadm.hsingh.quotationshake.R
import dadm.hsingh.quotationshake.databinding.FragmentNewQuotationBinding
import dadm.hsingh.quotationshake.domain.model.Quotation
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
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getNewQuotation()
            updateQuotation(viewModel.quotation.value)
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

    private fun updateQuotation(quotation: Quotation?) {
        if (quotation != null) {
            binding.welcomeTextView.isVisible = false
            binding.quoteTextView.text = quotation.text
            binding.authorTextView.text = quotation.author.ifEmpty { getString(R.string.anonymous) }
        }

        binding.swipeRefreshLayout.isRefreshing = viewModel.showIcon.value
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


