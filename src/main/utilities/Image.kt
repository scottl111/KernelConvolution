package main.utilities

import main.canny.KernelType
import java.awt.Color
import java.awt.Point
import java.awt.image.BufferedImage

object Image {

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

    fun applyConvolution(image: BufferedImage, kernel: KernelType = KernelType.IDENTITY): BufferedImage {

        //Create a new image same width and height as the original
        val newImage = BufferedImage(image.width, image.height, BufferedImage.TYPE_BYTE_GRAY)

        // Start from 5 pixels in and minus 5 from the width and height as I can't be dealing with out of bounds exception.
        for (y_axis in 5..(image.height - 5)) {
            for (x_axis in 5..(image.width - 5)) {

                // Generate a series of points surrounding the current pixel
                val topLeft = Point(x_axis - 1, y_axis - 1)
                val topCentral = Point(x_axis - 1, y_axis)
                val topRight = Point(x_axis + 1, y_axis - 1)

                val centralLeft = Point(x_axis - 1, y_axis)
                val central = Point(x_axis, y_axis)
                val centralRight = Point(x_axis + 1, y_axis)

                val bottomLeft = Point(x_axis - 1, y_axis + 1)
                val bottomCentral = Point(x_axis, y_axis + 1)
                val bottomRight = Point(x_axis + 1, y_axis + 1)

                val listOfSurroundingPoints = arrayListOf(
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

                // Now we'll apply the value of each pixel's colour to the value within the kernel.
                // the 'new...' colours below are the cumulative values of each colour channel that will become the
                // rgb at the central position of the new image.
                var newRed = 0;
                var newGreen = 0
                var newBlue = 0
                for (kernel_height in 0..2) {
                    for (kernel_width in 0..2) {
                        val index = (kernel_height * 3) + kernel_width

                        val currentPixelPoint: Point = listOfSurroundingPoints[index]

                        val currentPixelColor = Color(image.getRGB(currentPixelPoint.x, currentPixelPoint.y))
                        val kernelValue: Int = KernelType.EDGE_DETECTION_X.kernelMatrix[index]

                        newRed += (currentPixelColor.red * kernelValue)
                        newGreen += (currentPixelColor.green * kernelValue)
                        newBlue += (currentPixelColor.blue * kernelValue)
                    }
                }

                if (newRed < 0) {
                    newRed = 0
                }

                if (newGreen < 0) {
                    newGreen = 0
                }

                if (newBlue < 0) {
                    newBlue = 0
                }

                //System.err.println("X: $x_axis  Y:$y_axis -> R: $newRed G: $newGreen B: $newBlue")
                val newPixelColour = Color(newRed, newGreen, newBlue)

                newImage.setRGB(x_axis, y_axis, newPixelColour.rgb)
            }
        }
        return newImage
    }
}