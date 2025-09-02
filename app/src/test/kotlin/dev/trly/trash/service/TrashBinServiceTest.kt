package dev.trly.trash.service

import dev.trly.trash.model.Color
import dev.trly.trash.model.Shape
import dev.trly.trash.model.TrashBin
import dev.trly.trash.model.Type
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class TrashBinServiceTest {
    
    private val trashBinService = TrashBinService()
    
    @Test
    fun `generateRandomTrashBin should return valid TrashBin`() {
        val trashBin = trashBinService.generateRandomTrashBin()
        
        assertNotNull(trashBin)
        assertTrue(trashBin.shape in Shape.values())
        assertTrue(trashBin.type in Type.values())
        assertTrue(trashBin.volume >= 10.0)
        assertTrue(trashBin.volume <= 1000.0)
        assertTrue(trashBin.color in Color.values())
    }
    
    @Test
    fun `generateRandomTrashBins should return exact count requested`() {
        val testCounts = listOf(1, 5, 10, 25, 50, 100)
        
        testCounts.forEach { count ->
            val trashBins = trashBinService.generateRandomTrashBins(count)
            assertEquals(count, trashBins.size, "Expected $count bins but got ${trashBins.size}")
        }
    }
    
    @Test
    fun `generateRandomTrashBins with large count should return correct number`() {
        val count = 100
        val trashBins = trashBinService.generateRandomTrashBins(count)
        
        assertEquals(count, trashBins.size)
        trashBins.forEach { trashBin ->
            assertNotNull(trashBin)
            assertTrue(trashBin.shape in Shape.values())
            assertTrue(trashBin.type in Type.values())
            assertTrue(trashBin.volume >= 10.0)
            assertTrue(trashBin.volume <= 1000.0)
            assertTrue(trashBin.color in Color.values())
        }
    }
    
    @Test
    fun `generateRandomTrashBins should handle edge cases`() {
        // Test minimum valid count
        val singleBin = trashBinService.generateRandomTrashBins(1)
        assertEquals(1, singleBin.size)
        
        // Test multiple calls return consistent counts
        repeat(10) {
            val bins = trashBinService.generateRandomTrashBins(5)
            assertEquals(5, bins.size, "Failed on iteration $it")
        }
    }
    
    @Test
    fun `multiple calls should generate different TrashBins`() {
        val bins1 = trashBinService.generateRandomTrashBins(10)
        val bins2 = trashBinService.generateRandomTrashBins(10)
        
        assertEquals(10, bins1.size)
        assertEquals(10, bins2.size)
        
        // While it's theoretically possible for all bins to be identical,
        // it's extremely unlikely with proper randomization
        val allIdentical = bins1.zip(bins2).all { (bin1, bin2) ->
            bin1.shape == bin2.shape && 
            bin1.type == bin2.type && 
            bin1.color == bin2.color &&
            bin1.volume == bin2.volume
        }
        assertTrue(!allIdentical, "All bins should not be identical across different calls")
    }
}
