package dadm.hsingh.quotationshake.ui.newquotation

import android.net.http.NetworkException
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresExtension
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import dadm.hsingh.quotationshake.R
import dadm.hsingh.quotationshake.databinding.FragmentNewQuotationBinding
import dadm.hsingh.quotationshake.domain.model.Quotation
import dadm.hsingh.quotationshake.utils.NoInternetException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeoutException

@AndroidEntryPoint
class NewQuotationFragment : Fragment(R.layout.fragment_new_quotation), MenuProvider {
    private var _binding: FragmentNewQuotationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewQuotationViewModel by viewModels()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showFavouriteIcon.collect { favouriteIcon ->
                    binding.floatingActionButton.isVisible = favouriteIcon
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect { error ->
                    if(error != null) {
                        val message = when (error) {
                            is NoInternetException -> getString(R.string.internet_connection_is_not_available)
                            is NetworkException -> getString(R.string.error_network)
                            is TimeoutException -> getString(R.string.error_timeout)
                            else -> getString(R.string.error_unknown)
                        }
                        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
                        viewModel.resetError()
                    }
                }
            }
        }
        requireActivity().addMenuProvider(this,
            viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getNewQuotation()
            updateQuotation(viewModel.quotation.value)
        }

        binding.floatingActionButton.setOnClickListener{
            viewModel.addToFavourites()
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

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_new_quotation, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return if (menuItem.itemId == R.id.refreshItem) {
            viewModel.getNewQuotation()
            updateQuotation(viewModel.quotation.value)
            true
        } else {
            false
        }
    }
}


