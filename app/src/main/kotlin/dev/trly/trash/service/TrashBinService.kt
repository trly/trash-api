package dev.trly.trash.service

import dev.trly.trash.model.Color
import dev.trly.trash.model.Shape
import dev.trly.trash.model.TrashBin
import dev.trly.trash.model.Type
import kotlin.random.Random

class TrashBinService {
    
    fun generateRandomTrashBin(): TrashBin {
        return TrashBin(
            shape = Shape.values().random(),
            type = Type.values().random(),
            volume = Random.nextDouble(10.0, 1000.0), // Random volume between 10 and 1000 liters
            color = Color.values().random()
        )
    }
    
    fun generateRandomTrashBins(count: Int): List<TrashBin> {
        return (1..count).map { 
            generateRandomTrashBin()
        }
    }
}
