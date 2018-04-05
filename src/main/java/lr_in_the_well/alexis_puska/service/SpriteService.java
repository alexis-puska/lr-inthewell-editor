package lr_in_the_well.alexis_puska.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import lr_in_the_well.alexis_puska.domain.sprite.Sprite;
import lr_in_the_well.alexis_puska.domain.sprite.SpriteFile;
import lr_in_the_well.alexis_puska.domain.sprite.SpriteFileContent;

public class SpriteService {

    private final static Logger LOG = Logger.getLogger(SpriteService.class);

    private final ClassLoader classLoader;
    private final FileService fileService;
    private Map<String, BufferedImage[]> sprites;

    public SpriteService(FileService fileService) {
        this.fileService = fileService;
        this.classLoader = getClass().getClassLoader();
        sprites = new HashMap<>();
        LOG.info("Load Sprites : START");
        initSprite();
        LOG.info("Load Sprites : DONE");
    }

    public BufferedImage getSprite(String animation, int index) {
        return sprites.get(animation)[index];
    }

    private void initSprite() {
        File spriteDesciptor = new File(classLoader.getResource("json/json_image_parser.json").getFile());
        SpriteFileContent spriteFile = fileService.readJsonSpriteFile(spriteDesciptor);
        try {
            BufferedImage temp = null;
            for (SpriteFile file : spriteFile.getSpriteFile()) {
                switch (file.getFile()) {
                case "sprite_rayon_teleporter":
                    temp = ImageIO
                            .read(new File(classLoader.getResource("sprite/sprite_rayon_teleporter.png").getFile()));
                    break;
                case "sprite_light":
                    temp = ImageIO.read(new File(classLoader.getResource("sprite/sprite_light.png").getFile()));
                    break;
                case "sprite_level":
                    temp = ImageIO.read(new File(classLoader.getResource("sprite/sprite_level.png").getFile()));
                    break;
                case "sprite_ennemies":
                    temp = ImageIO.read(new File(classLoader.getResource("sprite/sprite_ennemies.png").getFile()));
                    break;
                }
                for (Sprite area : file.getArea()) {
                    LOG.info("Crop sprite animation : " + area.getAnimation());
                    BufferedImage[] sprite = new BufferedImage[area.getN()];
                    int n = 0;
                    for (int x = 0; x < area.getNx(); x++) {
                        for (int y = 0; y < area.getNy(); y++) {
                            if (n >= area.getN()) {
                                break;
                            }
                            sprite[n] = temp.getSubimage(area.getX(), area.getY(), area.getSx(), area.getSy());
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
