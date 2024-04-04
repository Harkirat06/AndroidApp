package dadm.hsingh.quotationshake.ui.favourites

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dadm.hsingh.quotationshake.R
import dadm.hsingh.quotationshake.databinding.FragmentFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites), MenuProvider {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouritesViewModel by activityViewModels()

    private lateinit var adapter: QuotationListAdapter

    private val itemTouchHelper by lazy {
        val simpleCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.END
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun isLongPressDragEnabled(): Boolean {
                return false
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteQuotationAtPosition(position)
            }
        }
        ItemTouchHelper(simpleCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)

        setupRecyclerView()
        observeViewModel()
        requireActivity().addMenuProvider(this,
            viewLifecycleOwner, Lifecycle.State.RESUMED)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewFavourites)
    }

    private fun setupRecyclerView() {
        adapter = QuotationListAdapter { author ->
            if (author == "Anonymous") {
                Snackbar.make(
                    requireView(),
                    "Cannot show information for Anonymous authors",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                try {
                    val url = "https://en.wikipedia.org/wiki/Special:Search?search=$author"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Snackbar.make(
                        requireView(),
                        "No application can handle this action",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isDeleteAllMenuVisible.collect {
                    requireActivity().invalidateOptionsMenu()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_favourites, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return if (menuItem.itemId == R.id.deleteAllDialogItem) {
            findNavController().navigate(R.id.action_favouritesFragment_to_deleteAllDialogFragment)
            true
        } else {
            false
        }
    }

    override fun onPrepareMenu(menu: Menu) {
        super.onPrepareMenu(menu)
        val deleteAllItem = menu.findItem(R.id.deleteAllDialogItem)
        deleteAllItem.isVisible = viewModel.isDeleteAllMenuVisible.value
    }

}
