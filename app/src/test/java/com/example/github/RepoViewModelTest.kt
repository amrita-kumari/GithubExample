package com.example.github

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.github.model.License
import com.example.github.model.Owner
import com.example.github.model.Repo
import com.example.github.viewmodel.RepoViewModel
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class RepoViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    @Test
    fun bindTest(){
        val viewModel = RepoViewModel()
        viewModel.bind(
            Repo(1, "Test repo", "Test description", "Test language",
                10, Owner("test avatar", "Test owner"), License("Test license")))
        assertEquals(viewModel.repoTitle.value, "Test repo")
        assertEquals(viewModel.repoDescription.value, "Test description")
        assertEquals(viewModel.stars.value, "10")
        assertEquals(viewModel.language.value, "Test language")
        assertEquals(viewModel.avatar.value, "test avatar")
    }

}