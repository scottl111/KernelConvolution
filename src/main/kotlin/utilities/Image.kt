package main.kotlin.utilities

import main.kotlin.canny.KernelType
import java.awt.Color
import java.awt.Point
import java.awt.image.BufferedImage
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

object Image {

    /**
     * Displays an image within a JFrame
     *
     * @param img The image to display
     */
    fun showImage(img: BufferedImage) {
        val frame = JFrame();
        frame.isVisible = true
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val panel = JPanel()
        frame.add(panel)
        val imageLabel = JLabel()
        imageLabel.icon = ImageIcon(img)
        panel.add(imageLabel)
        frame.pack()
    }

    /**
     * Converts a buffered image into a 2D array of pixels.
     *
     * @param imageToConvert the image to be converted into a 2D array of pixels.
     * @return a 2D array of pixels values of the image.
     */
    fun convertImageToPixels(imageToConvert: BufferedImage): Array<Array<Color>> {
        var pixels = arrayOf<Array<Color>>()

        for (i in 0..(imageToConvert.width - 1)) {
            var innerArray = arrayOf<Color>()
            for (j in 0..(imageToConvert.height - 1)) {
                innerArray += Color(imageToConvert.getRGB(i, j))
            }
            pixels += innerArray
        }

        return pixels
    }

    /**
     * Converts a 2D array of pixels into an buffered image and returns it
     *
     * @param
     * @return A buffered image of the
     */
    fun convertPixelsToImage(pixels: Array<Array<Color>>): BufferedImage {
        val image = BufferedImage(pixels.size, pixels[0].size, BufferedImage.TYPE_BYTE_GRAY)
        for (j in 0..(pixels.size - 1)) {
            for (i in 0..(pixels[0].size - 1)) {
                image.setRGB(j, i, pixels[j][i].rgb)
            }
        }
        return image
    }

    /**
     * Applied an edge detection to the given image.
     *
     * @param image the image to apply edge detection to
     * @param asColor True if the image should be applied as colour or not
     * @param kernelMatrix The kernel to apply to the image.
     *
     * @return The edge detected buffered image.
     */
    fun applyEdgeDetectionConvolution(image: BufferedImage,
                                      asColor: Boolean = false,
                                      kernelToApply: KernelType = KernelType.EDGE_DETECTION_1): BufferedImage {

        //Create a new image same width and height as the original
        val newImage: BufferedImage = when (asColor) {
            true -> BufferedImage(image.width, image.height, BufferedImage.TYPE_3BYTE_BGR)
            false -> BufferedImage(image.width, image.height, BufferedImage.TYPE_BYTE_GRAY)
        }

        // To avoid any out of bounds exceptions start from pixel 1 in the image's top left and bottom left corners.
        // Minus 1 from the width and height as we start from 0 in the loop and minus another one to avoid out of bounds
        // exceptions in the images top right and bottom right corners.
        for (yAxis in 1..(image.height - 2)) {
            for (xAxis in 1..(image.width - 2)) {

                // Now we'll apply the value of each pixel's colour to the value within the kernel.
                // the 'new...' colours below are the cumulative values of each colour channel that will become the
                // rgb for the current pixel.
                var newRed = 0;
                var newGreen = 0
                var newBlue = 0
                for (kernel_height in 0..2) {
                    for (kernel_width in 0..2) {

                        val listOfSurroundingPoints = getSurroundingPixelPositions(Point(xAxis, yAxis))

                        // Determine the point within the matrix that we want to extract the weight from.
                        val index = (kernel_height * 3) + kernel_width

                        // Get the point from the list of points that will have the kernel value applied to it.
                        val currentPixelPoint: Point = listOfSurroundingPoints[index]
                        val currentPixelColor = Color(image.getRGB(currentPixelPoint.x, currentPixelPoint.y))

                        // Get the value from the matrix that we want to apply to the colour
                        val kernelValue: Int = kernelToApply.matrix[index]

                        // Apply the kernel to each channel and add it to  the new value
                        newRed += (currentPixelColor.red * kernelValue)
                        newGreen += (currentPixelColor.green * kernelValue)
                        newBlue += (currentPixelColor.blue * kernelValue)
                    }
                }

                // As we are summing the results of the 3 x 3 matrix, a channel could be either greater than 255 or less
                // than 0. As a temporary catch, lets just set the channel to 0 if they go out of a Color's bounds.
                if (newRed < 0 || newRed > 255) {
                    newRed = 0
                }

                if (newGreen < 0 || newGreen > 255) {
                    newGreen = 0
                }

                if (newBlue < 0 || newBlue > 255) {
                    newBlue = 0
                }

                // Create the new pixel colour with the new RGB values for the channels and set it within the new image.
                val newPixelColour = Color(newRed, newGreen, newBlue)
                newImage.setRGB(xAxis, yAxis, newPixelColour.rgb)
            }
        }
        return newImage
    }

    internal fun convertRGBtoHSV(hsvColour: Color) {
        //todo
    }

    internal fun convertHSVtoRGB(rgbColour: Color): FloatArray {
        val hsv = FloatArray(3)
        Color.RGBtoHSB(rgbColour.red, rgbColour.green, rgbColour.blue, hsv)
        return hsv
    }

    /**
     * Generates an array of all of the points surrounding a central pixel.
     *
     * @param centralPoint The central point from which to generate the surrounding points from
     * @return an array of all of the surrounding points from top left -> bottom right
     */
    internal fun getSurroundingPixelPositions(centralPoint: Point): Array<Point> {

        // Generate a series of points surrounding the current pixel
        val topLeft = Point(centralPoint.x - 1, centralPoint.y - 1)
        val topCentral = Point(centralPoint.x - 1, centralPoint.y)
        val topRight = Point(centralPoint.x + 1, centralPoint.y - 1)

        val centralLeft = Point(centralPoint.x - 1, centralPoint.y)
        val central = Point(centralPoint.x, centralPoint.y)
        val centralRight = Point(centralPoint.x + 1, centralPoint.y)

        val bottomLeft = Point(centralPoint.x - 1, centralPoint.y + 1)
        val bottomCentral = Point(centralPoint.x, centralPoint.y + 1)
        val bottomRight = Point(centralPoint.x + 1, centralPoint.y + 1)

        return arrayOf(
            topLeft,
            topCentral,
            topRight,
            centralLeft,
            central,
            centralRight,
            bottomLeft,
            bottomCentral,
            bottomRight
        )
    }
}