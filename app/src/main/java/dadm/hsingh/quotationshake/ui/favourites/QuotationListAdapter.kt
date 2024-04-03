package dadm.hsingh.quotationshake.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.hsingh.quotationshake.databinding.QuotationItemBinding
import dadm.hsingh.quotationshake.domain.model.Quotation

object QuotationDiff : DiffUtil.ItemCallback<Quotation>() {
    override fun areItemsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
        return oldItem == newItem
    }
}

class QuotationListAdapter(private val onItemClick: (String) -> Unit) : ListAdapter<Quotation, QuotationListAdapter.ViewHolder>(QuotationDiff) {

    inner class ViewHolder(private val binding: QuotationItemBinding, onItemClick: (String) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick(binding.authorTextView.text.toString())
            }
        }

        fun bind(quotation: Quotation) {
            binding.quoteTextView.text = quotation.text
            binding.authorTextView.text = quotation.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = QuotationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
