package test.kotlin.utilities

import main.kotlin.utilities.Image
import org.junit.jupiter.api.Test
import java.awt.Point
import kotlin.test.assertEquals

/**
 * Class that tests the methods within the Utility Image class.
 *
 * @author Scott Lockett
 */
class TestImage {

    /**
     * Tests that getting all the surrounding point positions is correct.
     */
    @Test
    fun testGettingSurroundingPixelPositions() {
        val listOfPoints = Image.getSurroundingPixelPositions(Point(10, 10))

        // Make sure that we have 9 points in the returned list
        assertEquals(9, listOfPoints.size)

        // strictly speaking we don't care about the order of the points just that they are all these so just check that
        // the list contains all the points we expect.
        listOfPoints.contains(Point(9, 9))
        listOfPoints.contains(Point(9, 10))
        listOfPoints.contains(Point(10, 9))
        listOfPoints.contains(Point(11, 9))
        listOfPoints.contains(Point(9, 11))
        listOfPoints.contains(Point(10, 11))
        listOfPoints.contains(Point(11, 10))
        listOfPoints.contains(Point(11, 11))
    }

    @Test
    fun testApplyEdgeDetectionConvolution() {
        // todo Hmmmm. how to test this....
    }
}