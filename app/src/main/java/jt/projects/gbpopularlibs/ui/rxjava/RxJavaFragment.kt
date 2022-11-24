package jt.projects.gbpopularlibs.ui.rxjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import jt.projects.gbpopularlibs.databinding.FragmentRxjavaBinding

// observable
class Creation {
    public fun exec() {
        Consumer(Producer()).exec()
    }

    // в Producer мы станем создавать Observable разными способами
    class Producer {
        fun just(): Observable<String> {
            return Observable.just("1", "2", "3")
        }
    }

    // в Consumer — на них подписываться
    class Consumer(val producer: Producer) {
        val stringObserver = object : Observer<String> {
            //Disposable — интерфейс, реализация которого напрямую связана с потоком данных (источником).
            //Этот интерфейс позволяет отписаться от источника посредством вызова у него метода dispose().
            //Например, пользователь закрыл экран, на котором отображались данные из потока. Данные больше
            //не требуются, надо отписаться от источника и прекратить обработку данных из потока. Чтобы
            //добиться этого, предварительно сохраняем Disposable, полученный в onSubscribe, а затем просто
            //вызываем у него dispose(), например, где-нибудь на этапе onPause.
            var disposable: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                disposable = d
                println("onSubscribe")
            }

            override fun onNext(t: String) {
                println("onNext: $t")
            }

            override fun onError(e: Throwable) {
                println("onError: ${e.message}")
            }

            override fun onComplete() {
                println("on complete")
            }

        }

        fun execJust() {
            producer.just().subscribe(stringObserver)
        }

        fun exec() = execJust()
    }
}


class RxJavaFragment : Fragment() {
    private var _binding: FragmentRxjavaBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = RxJavaFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRxjavaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Creation().exec()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}