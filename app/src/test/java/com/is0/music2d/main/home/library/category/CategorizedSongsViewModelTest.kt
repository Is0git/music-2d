import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.is0.music2d.main.home.library.category.CategorizedSongsViewModel
import com.is0.music2d.main.home.library.category.use_case.WatchSongsCategoriesUseCase
import com.is0.music2d.main.home.library.category.utils.data.domain.SongsCategory
import com.is0.music2d.test.MainCoroutineRule
import com.is0.music2d.utils.const.LOADING_DELAY_MILLIS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class CategorizedSongsViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var categorizedSongsViewModel: CategorizedSongsViewModel
    private lateinit var watchSongsCategoriesUseCase: WatchSongsCategoriesUseCase

    @Before
    fun setUp() {
        watchSongsCategoriesUseCase = mock()

        given(watchSongsCategoriesUseCase.watchSongsCategories(CategorizedSongsViewModel.CATEGORY_SONGS_COUNT)).willReturn(
            flowOf(emptyList())
        )

        categorizedSongsViewModel = CategorizedSongsViewModel(watchSongsCategoriesUseCase)
    }

    @Test
    fun watchSongCategories_updatesSongsCategories() {
        runTest {
            val categories = listOf(
                SongsCategory(
                    name = "Category 1",
                    id = "1",
                    songs = listOf(),
                ),
                SongsCategory(
                    name = "Category 2",
                    id = "2",
                    songs = listOf(),
                ),
            )
            given(watchSongsCategoriesUseCase.watchSongsCategories(CategorizedSongsViewModel.CATEGORY_SONGS_COUNT)).willReturn(
                flowOf(categories)
            )

            categorizedSongsViewModel.watchSongCategories()

            assertThat(categorizedSongsViewModel.songsCategories.value, `is`(categories))
        }
    }
}
