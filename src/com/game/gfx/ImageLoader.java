package com.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Jason Anthony Galicia
 */
public class ImageLoader {
    /**
     * Loads the image from the String path given.
     * @param path The path of the file.
     * @return A buffered image.
     */
    public static BufferedImage loadImage (String path) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, 
                    null, ex);
            System.exit(0);
        }
        return null;
    }
}
