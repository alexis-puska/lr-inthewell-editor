package lr_in_the_well.alexis_puska.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import lr_in_the_well.alexis_puska.domain.sprite.Sprite;
import lr_in_the_well.alexis_puska.domain.sprite.SpriteFile;
import lr_in_the_well.alexis_puska.domain.sprite.SpriteFileContent;

public class SpriteService {

    
    
    private final static Logger LOG = Logger.getLogger(SpriteService.class);

    private final FileService fileService;
    private Map<String, BufferedImage[]> sprites;

    public SpriteService(FileService fileService) {
        this.fileService = fileService;
        sprites = new HashMap<>();
        LOG.info("Load Sprites : START");
        initSprite();
        LOG.info("Load Sprites : DONE");
    }

    /**
     * return a specific sprite 
     * @param animation name of animation
     * @param index index of animation
     * @return Buffered Image
     */
    public BufferedImage getSprite(String animation, int index) {
        BufferedImage[] spritesAnimation = sprites.get(animation);
        return spritesAnimation[index];
    }

    /**
     * parse json file and create buffer sprite in memory
     */
    private void initSprite() {
        InputStream in = this.getClass().getResourceAsStream("/json/json_image_parser.json");

        SpriteFileContent spriteFile = fileService.readJsonSpriteFile(in);
        try {
            BufferedImage temp = null;
            for (SpriteFile file : spriteFile.getSpriteFile()) {
                switch (file.getFile()) {
                case "sprite_rayon_teleporter":
                    temp = ImageIO.read(this.getClass().getResourceAsStream("/sprite/sprite_rayon_teleporter.png"));
                    break;
                case "sprite_light":
                    temp = ImageIO.read(this.getClass().getResourceAsStream("/sprite/sprite_light.png"));
                    break;
                case "sprite_level":
                    temp = ImageIO.read(this.getClass().getResourceAsStream("/sprite/sprite_level.png"));
                    break;
                case "sprite_ennemies":
                    temp = ImageIO.read(this.getClass().getResourceAsStream("/sprite/sprite_ennemies.png"));
                    break;
                }
                for (Sprite area : file.getArea()) {
                    LOG.info("Crop sprite animation : " + area.getAnimation());
                    BufferedImage[] sprite = new BufferedImage[area.getN()];
                    int n = 0;
                    for (int y = 0; y < area.getNy(); y++) {
                        for (int x = 0; x < area.getNx(); x++) {

                            if (n >= area.getN()) {
                                break;
                            }
                            int xCalc = area.getX() + (x * area.getSx());
                            int yCalc = area.getY() + (y * area.getSy());
                            sprite[n] = temp.getSubimage(xCalc, yCalc, area.getSx(), area.getSy());
                            n++;
                        }
                    }
                    if (sprites.containsKey(area.getAnimation())) {
                        LOG.info("need merge");
                        BufferedImage merge[] = mergeBufferedImage(sprites.get(area.getAnimation()), sprite);
                        sprites.put(area.getAnimation(), merge);
                    } else {
                        sprites.put(area.getAnimation(), sprite);
                    }
                }
            }
        } catch (IOException e) {
            LOG.info("IOException : " + e.getMessage());
        }
    }

    /**
     * Merge 2 table of buffered Image
     * @param bufferedImages Table of image
     * @param sprite Table of image
     * @return Table of image merged
     */
    private BufferedImage[] mergeBufferedImage(BufferedImage[] bufferedImages, BufferedImage[] sprite) {
        BufferedImage[] merged = new BufferedImage[bufferedImages.length + sprite.length];
        int index = 0;
        for (int i = 0; i < bufferedImages.length; i++) {
            merged[i] = bufferedImages[index];
            index++;
        }
        index = 0;
        for (int i = bufferedImages.length; i < bufferedImages.length + sprite.length; i++) {
            merged[i] = sprite[index];
            index++;
        }
        return merged;
    }

}
