package br.com.igorf91.desafio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.igorf91.desafio.R
import br.com.igorf91.desafio.vo.RepositoryVO

class RepositoryItemAdapter : RecyclerView.Adapter<RepositoryItemViewHolder>() {

    private var repositories : ArrayList<RepositoryVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_list_item, parent, false)
        return RepositoryItemViewHolder(view)
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: RepositoryItemViewHolder, position: Int) {
        val item = repositories[position]
        holder.bindView(item)
    }

    fun loadItems(newList: List<RepositoryVO>) {
        repositories.addAll(newList)
        notifyDataSetChanged()
    }
}