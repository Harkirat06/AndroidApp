package dadm.hsingh.quotationshake.ui.favourites

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.appcompat.app.AlertDialog
import dadm.hsingh.quotationshake.R
import dadm.hsingh.quotationshake.ui.favourites.FavouritesViewModel

class DeleteAllDialogFragment : DialogFragment() {

    private val viewModel: FavouritesViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_all_dialog_title))
            .setMessage(getString(R.string.delete_all_dialog_message))
            .setPositiveButton(getString(R.string.delete_all_dialog_positive_button)) { dialog, _ ->
                viewModel.deleteAllQuotations()
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.delete_all_dialog_negative_button)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}
