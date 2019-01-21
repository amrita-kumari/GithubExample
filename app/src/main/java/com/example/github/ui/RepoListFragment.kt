package com.example.github.ui

import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.github.R
import com.example.github.databinding.FragmentListBinding
import com.example.github.model.Repo
import com.example.github.utils.viewmodelfactory.ViewModelFactory
import com.example.github.utils.replaceFragment
import com.example.github.viewmodel.RepoListViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class RepoListFragment: Fragment(), RepoListAdapter.ItemClickListener {

    private lateinit var listViewModel: RepoListViewModel
    private var errorSnackbar: Snackbar? = null

    private lateinit var binding: FragmentListBinding

    @Inject
    lateinit var viewModeFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater,
                              @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list, container, false);
        binding.recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        binding.recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel = ViewModelProviders.of(this, this.viewModeFactory).get(RepoListViewModel::class.java)
        listViewModel.loadRepo()

        listViewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })

        listViewModel.loading.observe(this, Observer {
                isLoading -> progressBar.visibility = if(isLoading != null && isLoading) View.VISIBLE else View.GONE
        })
        binding.viewModel = listViewModel

    }

    override fun onItemClick(repo: Repo) {
        (activity!! as AppCompatActivity).replaceFragment(RepoDetailFragment.newInstance(repo), R.id.container)
    }


    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(com.example.github.R.string.retry, listViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

}