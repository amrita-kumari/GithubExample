package com.example.github.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.github.R
import com.example.github.databinding.FragmentDetailBinding
import com.example.github.model.Repo

class RepoDetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater,
                              @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false)

        binding.repo = arguments?.getSerializable(REPO) as Repo

        return binding.root
    }

    companion object {

        val REPO = "repo"

        fun newInstance(repo: Repo): RepoDetailFragment {
            val fragment = RepoDetailFragment()
            val args = Bundle()
            args.putSerializable(REPO, repo)
            fragment.arguments = args
            return fragment
        }
    }

}
