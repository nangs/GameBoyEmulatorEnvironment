package assets.panel.multipleImages

import assets.panel.multipleImages.base.PanelMultipleImages
import src.configuration.Display
import java.awt.Graphics
import java.awt.Graphics2D

/**
 * Created by vicboma on 08/01/17.
 */
class PanelCartridge internal constructor(private val classLoader:ClassLoader,_back: String, val _front: String) : PanelMultipleImages(classLoader,_back,_front) {

    companion object {
        fun create(classLoader:ClassLoader,_back: String,_front: String) = PanelCartridge(classLoader,_back,_front)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponents(g)
        var g2 = g as Graphics2D

        if(list[0] != null)
            g2.drawImage(list[0], (this.width * 0.07).toInt() , (this.height * 0.07).toInt(), null)
        
        if(list[1] != null)
            g2.drawImage(list[1],(this.width * 0.337).toInt() , (this.height * 0.39).toInt(),   ((Display.WIDHT / 3) * 0.495).toInt(), ((Display.HEIGTH / 2) * 0.495).toInt(), null)

    }
}