package main.utilities

import main.canny.KernelType
import java.awt.Color
import java.awt.image.BufferedImage

object Image
{

    /**
     * Converts a buffered image into a 2D array of pixels.
     *
     * @param imageToConvert the image to be converted into a 2D array of pixels.
     * @return a 2D array of pixels values of the image.
     */
    fun convertImageToPixels(imageToConvert: BufferedImage): Array<Array<Color>>
    {
        var pixels = arrayOf<Array<Color>>()

        for (i in 0..(imageToConvert.width - 1))
        {
            var innerArray = arrayOf<Color>()
            for (j in 0..(imageToConvert.height - 1))
            {
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
    fun convertPixelsToImage(pixels: Array<Array<Color>>): BufferedImage
    {
        val image = BufferedImage(pixels.size, pixels[0].size, BufferedImage.TYPE_BYTE_GRAY)
        for (j in 0..(pixels.size - 1))
        {
            for (i in 0..(pixels[0].size - 1))
            {
                image.setRGB(j, i, pixels[j][i].rgb)
            }
        }
        return image
    }

    val x_positions = intArrayOf(-1, 0, +1)
    val y_positions = intArrayOf(-1, 0, +1)

    fun applyConvolution(image: BufferedImage, kernel: KernelType = KernelType.IDENTITY): BufferedImage
    {
        val newImage = BufferedImage(image.width, image.height, BufferedImage.TYPE_BYTE_GRAY)
        for (y_axis in 0..(image.height - 1))
        {
            for (x_axis in 0..(image.width -1))
            {

                for (kernel_height in 0..2)
                {
                    for (kernel_width in 0..2)
                    {
                        val index = (kernel_height * 3) + kernel_width
                        val kernelValue = kernel.kernelMatrix[index]

                        if (x_axis + x_positions[kernel_width] < 0 || y_axis + y_positions[kernel_height] < 0) {
                            break
                        }

                        if (x_axis + x_positions[kernel_width] > image.width - 1|| y_axis + y_positions[kernel_height] > image.height - 1){
                            break
                        }

                        //System.err.println("X: ${x_axis + x_positions[kernel_width]} Y: ${y_axis + y_positions[kernel_height]}")
                        val currentPixelRGB = image.getRGB(x_axis + x_positions[kernel_width], y_axis + y_positions[kernel_height])

                        val oldColour = Color(currentPixelRGB)

                        System.err.println("R: ${oldColour.red * kernelValue} G: ${oldColour.green * kernelValue} B: ${oldColour.blue * kernelValue}")

                        if (kernelValue == 0){
                            break;
                        }

                        val newRed = oldColour.red * kernelValue
                        val newGreen = oldColour.green * kernelValue
                        val newBlue = oldColour.blue * kernelValue

                        val newColour = Color(newRed, newGreen, newBlue)
                        newImage.setRGB(x_axis + x_positions[kernel_width], y_axis + y_positions[kernel_height], newColour.rgb)
                    }
                }
            }
        }
        return newImage
    }
}