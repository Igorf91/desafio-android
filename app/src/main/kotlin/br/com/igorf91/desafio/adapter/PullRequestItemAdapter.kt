package br.com.igorf91.desafio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.igorf91.desafio.R
import br.com.igorf91.desafio.vo.PullRequestVO

class PullRequestItemAdapter : RecyclerView.Adapter<PullRequestItemViewHolder>() {

    private var pullRequests: List<PullRequestVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pull_request_list_item, parent, false)
        return PullRequestItemViewHolder(view)
    }

    override fun getItemCount() = pullRequests.size

    override fun onBindViewHolder(holder: PullRequestItemViewHolder, position: Int) {
        val item = pullRequests[position]
        holder.bindView(item)
    }

    fun loadItems(newList: List<PullRequestVO>) {
        pullRequests = ArrayList(newList)
        notifyDataSetChanged()
    }
}