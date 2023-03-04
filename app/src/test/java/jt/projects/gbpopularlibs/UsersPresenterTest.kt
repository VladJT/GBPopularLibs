package jt.projects.gbpopularlibs

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.gbpopularlibs.data.users.IUsersRepository
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.presenter.users.UsersPresenter
import jt.projects.gbpopularlibs.ui.users.UsersView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(
    MockitoExtension::class
)
class UsersPresenterTest {

    private lateinit var presenter: UsersPresenter

    @Mock(strictness = Mock.Strictness.LENIENT)
    private lateinit var repository: IUsersRepository

    @Mock
    private lateinit var viewContract: UsersView

    private val RESPONSE_DATA = listOf(UserEntity("TEST_USER"))

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)

        presenter = UsersPresenter(viewContract)
        presenter.usersRepo = repository
        Mockito.`when`(repository.getUsers()).thenReturn(Single.just(RESPONSE_DATA))
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun loadData_Test() {
        presenter.loadData()
        Mockito.verify(repository, times(1)).getUsers()
    }

    @Test
    fun testRepoReturnSuccessData() {
        val result = repository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .blockingGet()

        assertTrue(result.isNotEmpty())
        assertSame(RESPONSE_DATA, result)
        assertArrayEquals(RESPONSE_DATA.toTypedArray(), result.toTypedArray())
    }

    @Test
    fun testOnSuccess() {
        presenter.loadData()

        // проверяем данные
        assertArrayEquals(
            RESPONSE_DATA.toTypedArray(),
            presenter.usersListPresenter.users.toTypedArray()
        )

        // проверяем вызовы методов viewContract
        val inOrder = Mockito.inOrder(viewContract)
        inOrder.verify(viewContract, times(1)).showLoading(true)
        inOrder.verify(viewContract, times(1)).updateList()
        inOrder.verify(viewContract, times(1)).showLoading(false)
        inOrder.verify(viewContract, times(1)).showInfo(anyString())
    }

    @Test
    fun testOnError() {
        val someError = RuntimeException("some Error")
        `when`(repository.getUsers())
            .thenThrow(someError)

        try {
            presenter.loadData()
        } catch (e: Exception) {
            // проверяем данные
            assertSame(someError, e)
            presenter.onError(e)
        }

        // проверяем вызовы методов viewContract
        verify(viewContract, never()).updateList()

        val inOrder = Mockito.inOrder(viewContract)
        inOrder.verify(viewContract, times(1)).showLoading(true)
        inOrder.verify(viewContract, times(1)).showLoading(false)
        inOrder.verify(viewContract, times(1)).showInfo(anyString())
    }

    @Test
    fun example() {
        assertEquals(4, 2 + 2)
    }
}