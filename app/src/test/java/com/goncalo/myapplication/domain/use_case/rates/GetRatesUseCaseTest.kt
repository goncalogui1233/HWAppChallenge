package com.goncalo.myapplication.domain.use_case.rates

import com.goncalo.myapplication.common.Result
import com.goncalo.myapplication.data.repository.FakeRateRepository
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetRatesUseCaseTest {

    private lateinit var useCase: GetRatesUseCase
    private lateinit var repository: FakeRateRepository
    private val rates = arrayOf("EUR", "USD", "GBP", "AUD", "XAU")
    private val r =
        listOf(Pair("EUR", "0.00"), Pair("USD", "1.00"), Pair("GBP", "2.00")).sortedBy { it.first }

    @Before
    fun setUp() {
        repository = FakeRateRepository()
        useCase = GetRatesUseCase(repository)

        rates.forEachIndexed { index, s ->
            repository.addNewRate(s, index.toDouble())
        }
    }

    @Test
    fun checkIfResponseIsNotErrorAndContainsItems() {
        val case = useCase(1.0)
        val testObserver = TestObserver<Result<List<Pair<String, String>>>>()

        case.subscribe(testObserver)
        testObserver.assertValue(Result(isSuccess = true, content = r))
    }

    @Test
    fun checkIfHasASpecificItemPassed() {
        val case = useCase(1.0)
        val testObserver = TestObserver<Result<List<Pair<String, String>>>>()

        case.subscribe(testObserver)

        val list = testObserver.values().first().content
        assertTrue(list?.contains(r.random()) == true)
    }

    @Test
    fun checkIfItemHasBeenRemoved() {
        val case = useCase(1.0)
        val testObserver = TestObserver<Result<List<Pair<String, String>>>>()

        case.subscribe(testObserver)

        val list = testObserver.values().first().content

        assertTrue(list?.any { it.first == "XAU" } == false)
    }


    @Test
    fun checkIfReturnsErrorWhenEmptyList() {
        repository.removeRates()

        val case = useCase(1.0)
        val testObserver = TestObserver<Result<List<Pair<String, String>>>>()

        case.subscribe(testObserver)

        val result = testObserver.values().first()
        assertTrue(result.isSuccess.not())
    }
}