package com.example.github.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.github.R
import com.example.github.databinding.ItemRepoBinding
import com.example.github.model.Repo

class RepoListAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {
    private lateinit var repoList: List<Repo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListAdapter.ViewHolder {
        val binding: ItemRepoBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_repo, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoListAdapter.ViewHolder, position: Int) {
        holder.bind(repoList[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return if (::repoList.isInitialized) repoList.size else 0
    }

    fun updatePostList(postList: List<Repo>) {
        this.repoList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = RepoViewModel()

        fun bind(
            repo: Repo,
            itemClickListener: ItemClickListener
        ) {
            viewModel.bind(repo)
            binding.viewModel = viewModel
            itemView.setOnClickListener { itemClickListener.onItemClick(repo) }
        }


    }

    interface ItemClickListener{
        fun onItemClick(repo : Repo)
    }
}