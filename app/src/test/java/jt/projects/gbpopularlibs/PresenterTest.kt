package jt.projects.gbpopularlibs

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.gbpopularlibs.data.users.IUsersRepository
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.presenter.users.UsersPresenter
import jt.projects.gbpopularlibs.ui.users.UsersView
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
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
        presenter = UsersPresenter(viewContract)
        presenter.usersRepo = repository
        Mockito.`when`(repository.getUsers()).thenReturn(Single.just(RESPONSE_DATA))
    }

    @Test
    fun loadData_Test() {
        presenter.loadData()
        Mockito.verify(repository, times(1)).getUsers()
    }

    @Test
    fun checkData_Test() {
        val result = repository.getUsers()
            .observeOn(Schedulers.io())
            .blockingGet()
        assertTrue(result.isNotEmpty())
    }

    @Test //Проверим вызов метода searchGitHub() у нашего Репозитория
    fun handleSuccess_Test() {
        //Запускаем код, функционал которого хотим протестировать
        presenter.onSuccess(listOf())
        val inOrder = Mockito.inOrder(viewContract)

        //Убеждаемся, что все работает как надо
        inOrder.verify(viewContract, Mockito.times(1)).updateList()
        inOrder.verify(viewContract, Mockito.times(1)).showLoading(false)
        inOrder.verify(viewContract, Mockito.times(1)).showInfo(anyString())
    }

    @Test //Проверяем работу метода handleGitHubError()
    fun handleError_Test() {
        //Вызываем у Презентера метод handleGitHubError()
        presenter.onError(Throwable("some ex"))
        //Проверяем, что у viewContract вызывается метод displayError()
        Mockito.verify(viewContract, Mockito.times(1)).showLoading(false)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}