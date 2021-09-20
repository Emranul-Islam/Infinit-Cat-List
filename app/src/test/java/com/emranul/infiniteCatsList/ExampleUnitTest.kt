package com.emranul.infiniteCatsList

import com.emranul.infiniteCatsList.data.api.Clint
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertTrue(true)
    }

    @Test
    suspend fun catResponseTest() {
        val body = Clint.apiServices.getImages(10).body()
        assertEquals(10, body?.size )

    }
}