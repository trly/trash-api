package dev.trly.trash

import dev.trly.trash.service.TrashBinService
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
    }
    
    @Test
    fun testTrashBinServiceReturnsRequestedCount() {
        val service = TrashBinService()
        val requestedCount = 10
        val bins = service.generateRandomTrashBins(requestedCount)
        assertEquals(requestedCount, bins.size, "Service should return exactly the requested number of trash bins")
    }
    
    @Test
    fun testTrashBinServiceReturnsRequestedCountForLargerNumbers() {
        val service = TrashBinService()
        val requestedCount = 50
        val bins = service.generateRandomTrashBins(requestedCount)
        assertEquals(requestedCount, bins.size, "Service should return exactly the requested number of trash bins even for larger counts")
    }
}
