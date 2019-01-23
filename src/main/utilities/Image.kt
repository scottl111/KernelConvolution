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

    fun applyConvolution(image: BufferedImage, kernel: KernelType = KernelType.IDENTITY)
    {
        for (y_axis in 0..(image.height - 1))
        {
            for (x_axis in 0..(image.width -1))
            {
                var accumulator = 0
                val centralPixel: Int = image.getRGB(y_axis, x_axis)

                for (kernel_height in 0..2)
                {
                    for (kernel_width in 0..2)
                    {
//                        int index = (i * 3) + j;
                        val kernelValue = kernel.kernelMatrix[kernel_height][kernel_width]
                        System.err.print(kernelValue)
                        image.setRGB(kernel_height, kernel_width, Color.RED.rgb)
                    }
                }

            }
        }
    }
}