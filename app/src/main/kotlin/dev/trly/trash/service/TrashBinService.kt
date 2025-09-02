package dev.trly.trash.service

import dev.trly.trash.model.Color
import dev.trly.trash.model.Shape
import dev.trly.trash.model.TrashBin
import dev.trly.trash.model.Type
import kotlin.random.Random

class TrashBinService {
    
    fun generateRandomTrashBin(): TrashBin {
        return try {
            TrashBin(
                shape = Shape.values().random(),
                type = Type.values().random(),
                volume = Random.nextDouble(10.0, 1000.0), // Random volume between 10 and 1000 liters
                color = Color.values().random()
            )
        } catch (e: Exception) {
            // Fallback to ensure we always return a valid TrashBin
            TrashBin(
                shape = Shape.CYLINDER,
                type = Type.HOUSEHOLD,
                volume = 100.0,
                color = Color.GREEN
            )
        }
    }
    
    fun generateRandomTrashBins(count: Int): List<TrashBin> {
        require(count > 0) { "Count must be positive" }
        
        val result = mutableListOf<TrashBin>()
        repeat(count) {
            result.add(generateRandomTrashBin())
        }
        
        // Ensure we always return the exact count requested
        return if (result.size == count) {
            result.toList()
        } else {
            // If something went wrong, pad with default bins to reach the requested count
            val remainingCount = count - result.size
            result.addAll(List(remainingCount) { generateRandomTrashBin() })
            result.toList()
        }
    }
}
