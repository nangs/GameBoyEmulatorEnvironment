package main.kotlin.utils.image

import java.awt.Dimension
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import javax.swing.ImageIcon

import java.lang.*

/**
 * Created by vbolinch on 04/01/2017.
 */
fun ImageIcon.createBufferedImage(width: Int, height: Int, imageType: Int) = BufferedImage(width, height, imageType)


fun ImageIcon.scale(
            bufferedImage: BufferedImage,
            filename: String,
            rendereingHints: Map<*,*> = mapOf(
                Pair(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY),
                Pair(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON),
                Pair(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED)
                )
            ): BufferedImage? {

    try {
        val imageIcon = ImageIcon(filename)
        bufferedImage.createGraphics().apply{
            addRenderingHints(rendereingHints)
            drawImage(imageIcon.image, 0, 0, bufferedImage.width, bufferedImage.height, null)
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }
    finally{
        return bufferedImage
    }
}

fun ImageIcon.getScaledDimension(imgSize: Dimension, boundary: Dimension): Dimension {

    val original_width = imgSize.width
    val original_height = imgSize.height
    val bound_width = boundary.width
    val bound_height = boundary.height
    var new_width = original_width
    var new_height = original_height

    // first check if we need to scale width
    if (original_width > bound_width) {
        //scale width to fit
        new_width = bound_width
        //scale height to maintain aspect ratio
        new_height = new_width * original_height / original_width
    }

    // then check if we need to scale even with the new height
    if (new_height > bound_height) {
        //scale height to fit instead
        new_height = bound_height
        //scale width to maintain aspect ratio
        new_width = new_height * original_width / original_height
    }

    return Dimension(new_width, new_height)
}