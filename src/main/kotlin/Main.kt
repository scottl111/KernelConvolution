package main.kotlin

import main.kotlin.utilities.Image
import main.kotlin.utilities.Image.applyEdgeDetectionConvolution
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Main method for start the application.
 *
 * @author Scott Lockett
 */
class Main
{

    /**
     * Static companion method for the main method
     */
    companion object
    {

        /**
         * Starts the application
         *
         * @param args [0] The path to the file to load into the application.
         */
        @JvmStatic
        fun main(args: Array<String>)
        {
            val fileToEdgeDetect: File
            if (args.size == 1)
            {
                fileToEdgeDetect = File(args[0])

                // Lets do some very basic validation on the file
                if (!fileToEdgeDetect.exists() || !fileToEdgeDetect.canRead() || fileToEdgeDetect.isDirectory)
                {
                    throw IllegalArgumentException("The file can not be read")
                }
            }
            else
            {
                throw IllegalArgumentException("Application can not be started as there are the wrong number of " +
                        "arguments")
            }

            val theImage: BufferedImage = ImageIO.read(fileToEdgeDetect)
            Image.showImage(theImage)

            val kernelApplied = applyEdgeDetectionConvolution(theImage, false)
            Image.showImage(kernelApplied)
        }
    }
}
