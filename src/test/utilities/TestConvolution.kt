package test.utilities

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestConvolution {

    @Test
    fun testConvolutionMatrix()
    {
        var counter = 0
        for (kernel_height in 0..2) {
            for (kernel_width in 0..2) {
                val index = (kernel_height * 3) + kernel_width
                assertEquals(counter++, index);
            }
        }
    }
}
