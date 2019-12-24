package br.com.igorf91.desafio.adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.igorf91.desafio.util.loadImage
import br.com.igorf91.desafio.vo.PullRequestVO
import kotlinx.android.synthetic.main.pull_request_list_item.view.prDescriptionItem
import kotlinx.android.synthetic.main.pull_request_list_item.view.prImageItem
import kotlinx.android.synthetic.main.pull_request_list_item.view.prCreatedAtItem
import kotlinx.android.synthetic.main.pull_request_list_item.view.prNicknameItem
import kotlinx.android.synthetic.main.pull_request_list_item.view.prTitleItem

class PullRequestItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val prImage = itemView.prImageItem
    private val title = itemView.prTitleItem
    private val description = itemView.prDescriptionItem
    private val nickName = itemView.prNicknameItem
    private val createdAt = itemView.prCreatedAtItem

    fun bindView(item: PullRequestVO){
        prImage.loadImage(item.user.avatarUrl, itemView)

        title.text = item.title
        description.text = item.body
        nickName.text = item.user.login
        createdAt.text = item.createdAt

        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).also { it.data = Uri.parse(item.url) }
            itemView.context.startActivity(intent)
        }
    }
}