package main

import main.utilities.Image
import main.utilities.Image.applyConvolution
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

class Main{

    companion object {

        @JvmStatic
        fun main (args: Array<String>)
        {
            val theFile = File("""C:\git\KernelConvolution\resources\old_man_of_storr_black_and_white.jpg""");
            var theImage: BufferedImage = ImageIO.read(theFile)

            showImage(theImage)

            val pixels: Array<Array<Color>> = Image.convertImageToPixels(theImage)
            theImage = Image.convertPixelsToImage(pixels)

            showImage(theImage)
            applyConvolution(theImage)
        }

        fun showImage(img: BufferedImage){
            val frame = JFrame();
            frame.isVisible = true
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            val panel = JPanel()
            frame.add(panel)
            val imageLabel = JLabel()
            imageLabel.icon = ImageIcon(img)
            panel.add(imageLabel)
            frame.pack();
        }
    }
}
