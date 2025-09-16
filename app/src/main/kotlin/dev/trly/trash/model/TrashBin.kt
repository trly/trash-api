package dev.trly.trash.model

import kotlinx.serialization.Serializable

@Serializable
data class TrashBin(
    val shape: Shape,
    val type: Type,
    val volume: Double,
    val color: Color,
)

@Serializable
enum class Shape {
    CYLINDER,
    RECTANGULAR,
    SQUARE,
}

@Serializable
enum class Type {
    HOUSEHOLD,
    COMMERCIAL,
    DUMPSTER,
}

@Serializable
enum class Color {
    GREEN,
    BLUE,
    BROWN,
    BLACK,
}
