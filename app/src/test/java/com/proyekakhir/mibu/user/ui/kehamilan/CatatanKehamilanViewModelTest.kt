package com.proyekakhir.mibu.user.ui.kehamilan

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.proyekakhir.mibu.user.api.UserRepository
import com.proyekakhir.mibu.user.api.response.KbErrorResponse
import com.proyekakhir.mibu.user.api.response.KbResponse
import com.proyekakhir.mibu.user.api.response.KesehatanErrorResponse
import com.proyekakhir.mibu.user.api.response.KesehatanResponse
import com.proyekakhir.mibu.user.api.response.NifasErrorResponse
import com.proyekakhir.mibu.user.api.response.NifasResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class CatatanKehamilanViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var repository: UserRepository

    private lateinit var viewModel: CatatanKehamilanViewModel

    @Mock
    private lateinit var kbObserver: Observer<KbResponse>

    @Mock
    private lateinit var kbErrorObserver: Observer<KbErrorResponse>

    @Mock
    private lateinit var kbResponse: KbResponse

    @Mock
    private lateinit var kbErrorResponse: KbErrorResponse

    @Mock
    private lateinit var kesehatanObserver: Observer<KesehatanResponse>

    @Mock
    private lateinit var kesehatanErrorObserver: Observer<KesehatanErrorResponse>

    @Mock
    private lateinit var kesehatanResponse: KesehatanResponse

    @Mock
    private lateinit var kesehatanErrorResponse: KesehatanErrorResponse

    @Mock
    private lateinit var nifasResponse: NifasResponse

    @Mock
    private lateinit var nifasErrorResponse: NifasErrorResponse

    @Mock
    private lateinit var nifasObserver: Observer<NifasResponse>

    @Mock
    private lateinit var nifasErrorObserver: Observer<NifasErrorResponse>

    @Mock
    private lateinit var isLoadingObserver: Observer<Boolean>

    @Mock
    private lateinit var errorObserver: Observer<String>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CatatanKehamilanViewModel(repository)
        viewModel.kesehatan.observeForever(kesehatanObserver)
        viewModel.nifas.observeForever(nifasObserver)
        viewModel.kb.observeForever(kbObserver)
        viewModel.isLoading.observeForever(isLoadingObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @Test
    fun `getCatatanKehamilan successfully updates kesehatan LiveData`() = testDispatcher.runBlockingTest {
        val mockResponse = mock(KesehatanResponse::class.java)
        `when`(repository.getKesehatan()).thenReturn(mockResponse)

        viewModel.getCatatanKehamilan()

        val inOrder = inOrder(isLoadingObserver, kesehatanObserver)
        inOrder.verify(isLoadingObserver).onChanged(true)
        inOrder.verify(kesehatanObserver).onChanged(mockResponse)
        inOrder.verify(isLoadingObserver).onChanged(eq(false))

        verify(kesehatanObserver, never()).onChanged(kesehatanResponse)
    }

    @Test
    fun `getCatatanNifas successfully updates nifas LiveData`() = testDispatcher.runBlockingTest {
        val mockResponse = mock(NifasResponse::class.java)
        `when`(repository.getNifas()).thenReturn(mockResponse)

        viewModel.getCatatanNifas()

        val inOrder = inOrder(isLoadingObserver, nifasObserver)
        inOrder.verify(isLoadingObserver).onChanged(true)
        inOrder.verify(nifasObserver).onChanged(mockResponse)
        inOrder.verify(isLoadingObserver).onChanged(false)

        verify(nifasObserver, never()).onChanged(nifasResponse)
    }

    @Test
    fun `getCatatanKb successfully updates kb LiveData`() = testDispatcher.runBlockingTest {
        val mockResponse = mock(KbResponse::class.java)
        `when`(repository.getKb()).thenReturn(mockResponse)

        viewModel.getCatatanKb()

        val inOrder = inOrder(isLoadingObserver, kbObserver)
        inOrder.verify(isLoadingObserver).onChanged(true)
        inOrder.verify(kbObserver).onChanged(mockResponse)
        inOrder.verify(isLoadingObserver).onChanged(false)

        verify(kbObserver, never()).onChanged(kbResponse)
    }

    @Test
    fun `getCatatanKehamilan handles error`() = testDispatcher.runBlockingTest {
        val exceptionMessage = "Error"
        `when`(repository.getKesehatan()).thenThrow(RuntimeException(exceptionMessage))

        viewModel.getCatatanKehamilan()

        val inOrder = inOrder(isLoadingObserver, errorObserver)
        inOrder.verify(isLoadingObserver).onChanged(true)
        inOrder.verify(errorObserver).onChanged(exceptionMessage)
        inOrder.verify(isLoadingObserver).onChanged(false)

        verify(kesehatanErrorObserver, never()).onChanged(kesehatanErrorResponse)
    }

    @Test
    fun `getCatatanNifas handles error`() = testDispatcher.runBlockingTest {
        val exceptionMessage = "Error"
        `when`(repository.getNifas()).thenThrow(RuntimeException(exceptionMessage))

        viewModel.getCatatanNifas()

        val inOrder = inOrder(isLoadingObserver, errorObserver)
        inOrder.verify(isLoadingObserver).onChanged(true)
        inOrder.verify(errorObserver).onChanged(exceptionMessage)
        inOrder.verify(isLoadingObserver).onChanged(false)

        verify(nifasErrorObserver, never()).onChanged(nifasErrorResponse)
    }

    @Test
    fun `getCatatanKb handles error`() = testDispatcher.runBlockingTest {
        val exceptionMessage = "Error"
        `when`(repository.getKb()).thenThrow(RuntimeException(exceptionMessage))

        viewModel.getCatatanKb()

        val inOrder = inOrder(isLoadingObserver, errorObserver)
        inOrder.verify(isLoadingObserver).onChanged(true)
        inOrder.verify(errorObserver).onChanged(exceptionMessage)
        inOrder.verify(isLoadingObserver).onChanged(false)

        verify(kbErrorObserver, never()).onChanged(kbErrorResponse)
    }
}
