package dev.trly.trash

import dev.trly.trash.service.TrashBinService
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
    }
    
    @Test
    fun testGenerateRandomTrashBinsReturnsCorrectCount() {
        val service = TrashBinService()
        
        // Test various counts
        val testCounts = listOf(1, 5, 10, 50, 100)
        
        testCounts.forEach { count ->
            val bins = service.generateRandomTrashBins(count)
            assertEquals(count, bins.size, "Expected $count bins but got ${bins.size}")
            assertTrue(bins.isNotEmpty(), "Bins list should not be empty")
        }
    }
    
    @Test
    fun testGenerateRandomTrashBinReturnsValidBin() {
        val service = TrashBinService()
        val bin = service.generateRandomTrashBin()
        
        assertTrue(bin.volume >= 10.0 && bin.volume <= 1000.0, "Volume should be between 10 and 1000")
    }
}
