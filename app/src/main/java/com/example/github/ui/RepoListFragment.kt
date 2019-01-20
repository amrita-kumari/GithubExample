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
import com.example.github.utils.replaceFragment
import kotlinx.android.synthetic.main.fragment_list.*


class RepoListFragment: Fragment() {

    private lateinit var listViewModel: RepoListViewModel
    private var errorSnackbar: Snackbar? = null

    private lateinit var binding: FragmentListBinding


    override fun onCreateView(inflater: LayoutInflater,
                              @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list, container, false);
        binding.recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        binding.recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


        listViewModel = ViewModelProviders.of(this).get(RepoListViewModel::class.java)

        listViewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })



        listViewModel.loading.observe(this, Observer {
                isLoading -> progressBar.visibility = if(isLoading != null && isLoading) View.VISIBLE else View.GONE
        })

        listViewModel.onItemClick.observe(this, Observer {

            if(it != null){
                (activity!! as AppCompatActivity).replaceFragment(RepoDetailFragment.newInstance(it), R.id.container)
            }

        })

        binding.viewModel = listViewModel

        return binding.root
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