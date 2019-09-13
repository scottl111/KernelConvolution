package main

import main.utilities.Image.applyEdgeDetectionConvolution
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

class Main {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val theFile = File("""C:\download.jpg""");
            val theImage: BufferedImage = ImageIO.read(theFile)

            showImage(theImage)
            val kernelApplied = applyEdgeDetectionConvolution(theImage)
            showImage(kernelApplied)
        }

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
    }
}
