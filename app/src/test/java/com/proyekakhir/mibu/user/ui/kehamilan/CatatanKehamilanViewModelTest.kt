package com.proyekakhir.mibu.user.ui.kehamilan

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.kehamilan.model.KesehatanModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.NifasModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class CatatanKehamilanViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var repository: FirebaseRepository

    private lateinit var viewModel: CatatanKehamilanViewModel

    @Mock
    private lateinit var catatanKesehatanListObserver: Observer<ArrayList<KesehatanModel>>

    @Mock
    private lateinit var catatanNifasListObserver: Observer<ArrayList<NifasModel>>

    @Mock
    private lateinit var isLoadingObserver: Observer<Boolean>

    @Mock
    private lateinit var isEmptyObserver: Observer<String>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel = CatatanKehamilanViewModel(repository)
        viewModel.isLoading.observeForever(isLoadingObserver)
        viewModel.isEmpty.observeForever(isEmptyObserver)
        viewModel.catatanKesehatanList.observeForever(catatanKesehatanListObserver)
        viewModel.catatanNifasList.observeForever(catatanNifasListObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCatatanKesehatanList successfully updates catatanKesehatanList LiveData`() = runTest {
        val uid = "test_uid"
        val mockData = arrayListOf(KesehatanModel(), KesehatanModel())

        doAnswer { invocation ->
            val successCallback = invocation.getArgument<(ArrayList<KesehatanModel>) -> Unit>(1)
            val isLoadingCallback = invocation.getArgument<(Boolean) -> Unit>(3)
            isLoadingCallback(true)
            testDispatcher.scheduler.advanceTimeBy(10000)
            successCallback(mockData)
            isLoadingCallback(false)
            null
        }.`when`(repository).getCatatanKesehatan(eq(uid), any(), any(), any(), any())

        viewModel.getCatatanKesehatanList(uid)

        verify(isLoadingObserver).onChanged(true)
        verify(catatanKesehatanListObserver).onChanged(mockData)
        verify(isLoadingObserver).onChanged(false)
        verify(isEmptyObserver, never()).onChanged(anyString())
    }

    @Test
    fun `getCatatanNifasList successfully updates catatanNifasList LiveData`() = runTest {
        val uid = "test_uid"
        val mockData = arrayListOf(NifasModel(), NifasModel())

        doAnswer { invocation ->
            val successCallback = invocation.getArgument<(ArrayList<NifasModel>) -> Unit>(1)
            val isLoadingCallback = invocation.getArgument<(Boolean) -> Unit>(3)
            isLoadingCallback(true)
            testDispatcher.scheduler.advanceTimeBy(20000)
            successCallback(mockData)
            isLoadingCallback(false)
            null
        }.`when`(repository).getCatatanNifas(eq(uid), any(), any(), any(), any())

        viewModel.getCatatanNifasList(uid)

        verify(isLoadingObserver).onChanged(true)
        verify(catatanNifasListObserver).onChanged(mockData)
        verify(isLoadingObserver).onChanged(false)
        verify(isEmptyObserver, never()).onChanged(anyString())
    }

    @Test
    fun `getCatatanKesehatanList handles empty response`() = runTest {
        val uid = "test_uid"
        val emptyMessage = "No data available"

        doAnswer { invocation ->
            val emptyCallback = invocation.getArgument<(String) -> Unit>(2)
            val isLoadingCallback = invocation.getArgument<(Boolean) -> Unit>(3)
            isLoadingCallback(true)
            testDispatcher.scheduler.advanceTimeBy(1000)
            emptyCallback(emptyMessage)
            isLoadingCallback(false)
            null
        }.`when`(repository).getCatatanKesehatan(eq(uid), any(), any(), any(), any())

        viewModel.getCatatanKesehatanList(uid)

        verify(isLoadingObserver).onChanged(true)
        verify(isEmptyObserver).onChanged(emptyMessage)
        verify(isLoadingObserver).onChanged(false)
        verify(catatanKesehatanListObserver, never()).onChanged(any())
    }

    @Test
    fun `getCatatanNifasList handles empty response`() = runTest {
        val uid = "test_uid"
        val emptyMessage = "No data available"

        doAnswer { invocation ->
            val emptyCallback = invocation.getArgument<(String) -> Unit>(2)
            val isLoadingCallback = invocation.getArgument<(Boolean) -> Unit>(3)
            isLoadingCallback(true)
            testDispatcher.scheduler.advanceTimeBy(1000)
            emptyCallback(emptyMessage)
            isLoadingCallback(false)
            null
        }.`when`(repository).getCatatanNifas(eq(uid), any(), any(), any(), any())

        viewModel.getCatatanNifasList(uid)

        verify(isLoadingObserver).onChanged(true)
        verify(isEmptyObserver).onChanged(emptyMessage)
        verify(isLoadingObserver).onChanged(false)
        verify(catatanNifasListObserver, never()).onChanged(any())
    }
}






