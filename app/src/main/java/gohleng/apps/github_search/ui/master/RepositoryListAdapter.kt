package gohleng.apps.github_search.ui.master

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gohleng.apps.github_search.data.model.Repository
import gohleng.apps.github_search.util.load
import gohleng.apps.itunes_appetiser_apps.databinding.ViewholderListRowBinding

class RepositoryListAdapter(private val onClickListener: OnRepositoryClickListener) :
    RecyclerView.Adapter<RepositoryViewHolder>() {
    interface OnRepositoryClickListener {
        fun onRepositoryClick(id: Long)
    }

    private val repositoryList = ArrayList<Repository>()

    fun setRepositoryList(repositoryList: List<Repository>) {
        this.repositoryList.clear()
        this.repositoryList.addAll(repositoryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding: ViewholderListRowBinding =
            ViewholderListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding, onClickListener)
    }

    override fun getItemCount(): Int = repositoryList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) =
        holder.bind(repositoryList[position])
}

class RepositoryViewHolder(
    private val binding: ViewholderListRowBinding,
    private val onClickListener: RepositoryListAdapter.OnRepositoryClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var repository: Repository

    fun bind(repository: Repository) {
        this.repository = repository
        binding.txtName.text = repository.name
        binding.txtArtist.text = repository.owner?.login
        binding.txtLabel.text = repository.description
        binding.imgRepository.load(repository.owner?.avatar_url)

        binding.root.setOnClickListener {
            onClickListener.onRepositoryClick(repository.id)
        }
    }
}