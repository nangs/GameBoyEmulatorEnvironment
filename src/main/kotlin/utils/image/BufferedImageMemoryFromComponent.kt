package main.kotlin.utils.image

import java.awt.Component
import java.awt.Container
import java.awt.image.BufferedImage
import javax.swing.CellRendererPane

/**
 * Created by vbolinch on 06/04/2017.
 */
object BufferedImageMemoryFromComponent {

    fun invoke(cmp: Component): BufferedImage {
        fun layoutComponent(c: Component) {
            synchronized(c.getTreeLock()) {
                c.doLayout()
                if (c is Container)
                    for (child in c.getComponents())
                        layoutComponent(child)
            }
        }

        cmp.setSize(cmp.getPreferredSize())
        layoutComponent(cmp)
        val img = BufferedImage(cmp.getWidth(), cmp.getHeight(), BufferedImage.TYPE_INT_ARGB)
        val crp = CellRendererPane()
        crp.add(cmp)
        crp.paintComponent(img.createGraphics(), cmp, crp, cmp.getBounds())
        return img
    }

}