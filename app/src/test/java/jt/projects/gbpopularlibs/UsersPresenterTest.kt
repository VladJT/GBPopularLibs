package jt.projects.gbpopularlibs

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.gbpopularlibs.data.users.IUsersRepository
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.presenter.users.UsersPresenter
import jt.projects.gbpopularlibs.ui.users.UsersView
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(
    MockitoExtension::class
)
class PresenterTest {

    private lateinit var presenter: UsersPresenter

    @Mock(strictness = Mock.Strictness.LENIENT)
    private lateinit var repository: IUsersRepository

    @Mock
    private lateinit var viewContract: UsersView

    private val RESPONSE_DATA = listOf(UserEntity("Mark"))

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = UsersPresenter(viewContract, ScheduleProviderStub())
        presenter.usersRepo = repository

        Mockito.`when`(repository.getUsers()).thenReturn(Single.just(RESPONSE_DATA))
    }

    @Test
    fun loadData_Test() {

        presenter.loadData()
        Mockito.verify(repository, times(1)).getUsers()
    }

    @Test
    fun testRepoReturnSuccessData() {
        val result = repository.getUsers()
            .observeOn(Schedulers.io())
            .blockingGet()
        assertTrue(result.isNotEmpty())
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
        inOrder.verify(viewContract, Mockito.times(1)).updateList()
        inOrder.verify(viewContract, Mockito.times(1)).showLoading(false)
        inOrder.verify(viewContract, Mockito.times(1)).showInfo(anyString())
    }

    @Test
    fun testOnError() {
        val someError = RuntimeException("some Error")

        Mockito.`when`(repository.getUsers()).thenReturn(
            Single.error(someError)
        )
        presenter.loadData()

        // проверяем вызовы методов viewContract
        val inOrder = Mockito.inOrder(viewContract)
        inOrder.verify(viewContract, Mockito.times(1)).showLoading(true)
        inOrder.verify(viewContract, Mockito.times(1)).showError(someError.message.toString())
        inOrder.verify(viewContract, Mockito.times(1)).showLoading(false)
    }

    @Test
    fun test_getData() {
        presenter.loadData()
        verify(viewContract, times(1)).showLoading(true)
        verify(repository, times(1)).getUsers()
    }
}