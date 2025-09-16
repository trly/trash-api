package dev.trly.trash

import io.ktor.server.testing.testApplication
import kotlin.test.Test

class ApplicationTest {
    @Test
    fun testRoot() =
        testApplication {
            application {
                module()
            }
        }
}
