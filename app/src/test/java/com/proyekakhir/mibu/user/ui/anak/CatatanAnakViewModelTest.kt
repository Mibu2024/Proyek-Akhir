package com.proyekakhir.mibu.user.ui.anak

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.proyekakhir.mibu.user.api.UserRepository
import com.proyekakhir.mibu.user.api.response.AnakResponse
import com.proyekakhir.mibu.user.api.response.ImunisasiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class CatatanAnakViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var repository: UserRepository

    private lateinit var viewModel: CatatanAnakViewModel

    @Mock
    private lateinit var anakObserver: Observer<AnakResponse>

    @Mock
    private lateinit var imunisasiObserver: Observer<ImunisasiResponse>

    @Mock
    private lateinit var imunisasiResponse: ImunisasiResponse

    @Mock
    private lateinit var isLoadingObserver: Observer<Boolean>

    @Mock
    private lateinit var errorObserver: Observer<String>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CatatanAnakViewModel(repository)
        viewModel.anak.observeForever(anakObserver)
        viewModel.imunisasi.observeForever(imunisasiObserver)
        viewModel.isLoading.observeForever(isLoadingObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @Test
    fun `getCatatanAnak successfully updates anak LiveData`() = testDispatcher.runBlockingTest {
        val mockResponse = Mockito.mock(AnakResponse::class.java)
        Mockito.`when`(repository.getAnak()).thenReturn(mockResponse)

        viewModel.getCatatanAnak()

        val inOrder = Mockito.inOrder(isLoadingObserver, anakObserver)
        inOrder.verify(isLoadingObserver).onChanged(true)
        inOrder.verify(anakObserver).onChanged(mockResponse)
        inOrder.verify(isLoadingObserver).onChanged(false)

        // Use anyOrNull() to avoid NullPointerException
        Mockito.verify(anakObserver, Mockito.never()).onChanged(any())
    }

    @Test
    fun `getCatatanImunisasi successfully updates imunisasi LiveData`() = testDispatcher.runBlockingTest {
        val mockResponse = Mockito.mock(ImunisasiResponse::class.java)
        Mockito.`when`(repository.getImunisasi()).thenReturn(mockResponse)

        viewModel.getCatatanImunisasi()

        val inOrder = Mockito.inOrder(isLoadingObserver, imunisasiObserver)
        inOrder.verify(isLoadingObserver).onChanged(true)
        inOrder.verify(imunisasiObserver).onChanged(mockResponse)
        inOrder.verify(isLoadingObserver).onChanged(Mockito.eq(false))

        Mockito.verify(imunisasiObserver, Mockito.never()).onChanged(imunisasiResponse)
    }
}