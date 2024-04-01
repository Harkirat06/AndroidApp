package dadm.hsingh.quotationshake.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dadm.hsingh.quotationshake.R
import dadm.hsingh.quotationshake.databinding.FragmentFavouritesBinding
import kotlinx.coroutines.launch

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouritesViewModel by viewModels()

    private lateinit var adapter: QuotationListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = QuotationListAdapter()
        binding.recyclerViewFavourites.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFavourites.adapter = adapter
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoriteQuotations.collect { quotations ->
                    adapter.submitList(quotations)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
