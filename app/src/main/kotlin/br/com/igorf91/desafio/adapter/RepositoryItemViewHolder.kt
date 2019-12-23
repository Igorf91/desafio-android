package br.com.igorf91.desafio.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.igorf91.desafio.util.loadImage
import br.com.igorf91.desafio.vo.RepositoryVO
import kotlinx.android.synthetic.main.repository_list_item.view.repoDescriptionItemTextView
import kotlinx.android.synthetic.main.repository_list_item.view.repoForkItemTextView
import kotlinx.android.synthetic.main.repository_list_item.view.repoImageItem
import kotlinx.android.synthetic.main.repository_list_item.view.repoNameItemTextView
import kotlinx.android.synthetic.main.repository_list_item.view.repoNicknameItemTextView
import kotlinx.android.synthetic.main.repository_list_item.view.repoStarsItemTextView

class RepositoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val repoImage = itemView.repoImageItem
    private val name = itemView.repoNameItemTextView
    private val description = itemView.repoDescriptionItemTextView
    private val forks = itemView.repoForkItemTextView
    private val stars = itemView.repoStarsItemTextView
    private val nickName = itemView.repoNicknameItemTextView

    fun bindView(item: RepositoryVO){
        repoImage.loadImage(item.owner.avatarUrl, itemView)

        name.text = item.fullName
        description.text = item.description
        forks.text = item.forksCount.toString()
        stars.text = item.stargazersCount.toString()
        nickName.text = item.owner.login
    }
}