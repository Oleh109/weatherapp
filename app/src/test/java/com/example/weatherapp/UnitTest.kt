import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapp.model.WeatherClass
import com.example.weatherapp.repo.WeatherRepo
import com.example.weatherapp.viewmodel.WeatherState
import com.example.weatherapp.viewmodel.WeatherViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var weatherRepo: WeatherRepo

    private lateinit var weatherViewModel: WeatherViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        weatherViewModel = WeatherViewModel(weatherRepo)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getWeather_SuccessfulResponse_WeatherStateResult() = runBlocking {
        val cityName = "London"
        val weatherData = WeatherClass()
        `when`(weatherRepo.getCurrentWeather(any(), any(), any())).thenReturn(Response.success(weatherData))

        weatherViewModel.getWeather(cityName)

        val weatherState = weatherViewModel.weatherState.value
        assertEquals(WeatherState.Result(weatherData), weatherState)
    }

    @Test
    fun getWeather_ErrorResponse_WeatherStateError() = runBlocking {
        val cityName = "London"
        val errorMessage = "Error fetching data"
        `when`(weatherRepo.getCurrentWeather(any(), any(), any())).thenReturn(Response.error(404, null))

        weatherViewModel.getWeather(cityName)

        val weatherState = weatherViewModel.weatherState.value
        assertEquals(WeatherState.Error(errorMessage), weatherState)
    }

    @Test
    fun onSearchTextChange_UpdateSearchText() = runBlocking {
        val searchText = "Paris"

        weatherViewModel.onSearchTextChange(searchText)

        val updatedSearchText = weatherViewModel.searchText.first()
        assertEquals(searchText, updatedSearchText)
    }
}
