package com.example.github

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.github.model.License
import com.example.github.model.Owner
import com.example.github.model.Repo
import com.example.github.model.RepoResponse
import com.example.github.network.RepoApi
import com.example.github.viewmodel.RepoListViewModel
import io.reactivex.Observable
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.`is`
import org.junit.*
import org.junit.Assert.*
import java.lang.Exception


class RepoListViewModelTest {

    @Mock
    lateinit var api : RepoApi
    private lateinit var viewModel: RepoListViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    @Before
    fun setUp(){
        api = mock(RepoApi::class.java)
        viewModel =
                RepoListViewModel(api, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun loadRepoTest(){
        val repoResponse = RepoResponse(listOf(Repo(1, "Test repo", "Test description", "Test language",
            10, Owner("test avatar", "Test owner"), License("Test license"))))

        Mockito.`when`(api.getRepo())
            .thenReturn(Observable.just(repoResponse))

        val testObserver = TestObserver<RepoResponse>()
        api.getRepo().subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        val listResult = testObserver.values()[0]
        assertThat(listResult.repoList.size, `is`(1))
        assertThat(listResult.repoList[0].id, `is`(1))
        assertThat(listResult.repoList[0].fullName, `is`("Test repo"))

        Mockito.`when`(api.getRepo())
            .thenReturn(Observable.just(repoResponse))

        viewModel.loadRepo()

        assertEquals(viewModel.loading.value, false)
    }

    @Test
    fun loadRepoErrorTest(){
        Mockito.`when`(api.getRepo())
            .thenReturn(Observable.error(Exception()))

        viewModel.loadRepo()

        assertEquals(viewModel.loading.value, false);
        assertNotNull(viewModel.errorMessage.value)
    }
}
