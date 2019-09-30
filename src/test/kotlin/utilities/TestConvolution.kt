package test.kotlin.utilities

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestConvolution {

    @Test
    fun testConvolutionMatrix()
    {
        val expectedPositions = IntRange(0, 8).toList()

        for (kernel_height in 0..2)
        {
            for (kernel_width in 0..2)
            {
                val index = (kernel_height * 3) + kernel_width
                assertEquals(expectedPositions[index], index)
            }
        }
    }
}
