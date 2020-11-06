package com.TETOSOFT.tilegame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import com.TETOSOFT.graphics.*;
import com.TETOSOFT.tilegame.sprites.*;


/**
    The ResourceManager class loads and manages tile Images and
    "host" Sprites used in the game. Game Sprites are cloned from
    "host" Sprites.
*/
public class MapLoader 
{
    private ArrayList tiles;
    public int currentMap;
    private GraphicsConfiguration gc;

    // host sprites used for cloning
    private Sprite playerSprite;
    private Sprite musicSprite;
    private Sprite coinSprite;
    private Sprite goalSprite;
    private Sprite grubSprite;
    private Sprite flySprite;
    private Sprite cockSprite;
    private Sprite goalSprite2;
    private Sprite phoSprite;
    private Sprite birdSprite;
    private Sprite snakeSprite;
    private Sprite drinkSprite;
    private Sprite dogSprite;
    /**
        Creates a new ResourceManager with the specified
        GraphicsConfiguration.
    */
    public MapLoader(GraphicsConfiguration gc) 
    {
        this.gc = gc;
        loadTileImages();
        loadCreatureSprites();
        loadPowerUpSprites();
    }


    /**
        Gets an image from the images/ directory.
    */
    public Image loadImage(String name) 
    {
        String filename = "images/" + name;
        return new ImageIcon(filename).getImage();
    }


    public Image getMirrorImage(Image image) 
    {
        return getScaledImage(image, -1, 1);
    }


    public Image getFlippedImage(Image image) 
    {
        return getScaledImage(image, 1, -1);
    }


    private Image getScaledImage(Image image, float x, float y) 
    {

        // set up the transform
        AffineTransform transform = new AffineTransform();
        transform.scale(x, y);
        transform.translate(
            (x-1) * image.getWidth(null) / 2,
            (y-1) * image.getHeight(null) / 2);

        // create a transparent (not translucent) image
        Image newImage = gc.createCompatibleImage(
            image.getWidth(null),
            image.getHeight(null),
            Transparency.BITMASK);

        // draw the transformed image
        Graphics2D g = (Graphics2D)newImage.getGraphics();
        g.drawImage(image, transform, null);
        g.dispose();

        return newImage;
    }


    public TileMap loadNextMap() 
    {
        TileMap map = null;
        while (map == null) 
        {
            currentMap++;
            try {
                map = loadMap(
                    "maps/map" + currentMap + ".txt");
            }
            catch (IOException ex) 
            {
                if (currentMap == 2) 
                {
                    // no maps to load!
                    return null;
                }
                  currentMap = 0;
                map = null;
            }
        }

        return map;
    }


    public TileMap reloadMap() 
    {
        try {
            return loadMap(
                "maps/map" + currentMap + ".txt");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private TileMap loadMap(String filename)
        throws IOException
    {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

        // read every line in the text file into the list
        BufferedReader reader = new BufferedReader(
            new FileReader(filename));
        while (true) {
            String line = reader.readLine();
            // no more lines to read
            if (line == null) {
                reader.close();
                break;
            }

            // add every line except for comments
            if (!line.startsWith("#")) {
                lines.add(line);
                width = Math.max(width, line.length());
            }
        }

        // parse the lines to create a TileEngine
        height = lines.size();
        TileMap newMap = new TileMap(width, height);
        for (int y=0; y<height; y++) {
            String line = (String)lines.get(y);
            for (int x=0; x<line.length(); x++) {
                char ch = line.charAt(x);

                // check if the char represents tile A, B, C etc.
                int tile = ch - 'A';
                if (tile >= 0 && tile < tiles.size()) {
                    newMap.setTile(x, y, (Image)tiles.get(tile));
                }

                // check if the char represents a sprite
                else if (ch == 'o') {
                    addSprite(newMap, coinSprite, x, y);
                }
                else if (ch == '0') {
                    addSprite(newMap, phoSprite, x, y);
                }
                else if (ch == '!') {
                    addSprite(newMap, musicSprite, x, y);
                }
                else if (ch == '*') {
                    addSprite(newMap, goalSprite, x, y);
                }
                else if (ch == '1') {
                    addSprite(newMap, grubSprite, x, y);
                }
                else if (ch == '2') {
                    addSprite(newMap, flySprite, x, y);
                }
                else if (ch == '3') {
                    addSprite(newMap, cockSprite, x, y);
                }
                else if (ch == '4'){
                    addSprite(newMap, birdSprite, x, y);
                }
                else if (ch == '5'){
                    addSprite(newMap, snakeSprite, x, y);
                }
                else if (ch == '6'){
                    addSprite(newMap, dogSprite, x, y);
                }
                else if (ch == '+'){
                    addSprite(newMap, goalSprite2, x, y);
                }
                else if (ch == '?'){
                    addSprite(newMap, drinkSprite, x, y);
                }
            }
        }

        // add the player to the map
        Sprite player = (Sprite)playerSprite.clone();
        player.setX(TileMapDrawer.tilesToPixels(3));
        player.setY(lines.size());
        newMap.setPlayer(player);

        return newMap;
    }


    private void addSprite(TileMap map,
        Sprite hostSprite, int tileX, int tileY)
    {
        if (hostSprite != null) {
            // clone the sprite from the "host"
            Sprite sprite = (Sprite)hostSprite.clone();

            // center the sprite
            sprite.setX(
                TileMapDrawer.tilesToPixels(tileX) +
                (TileMapDrawer.tilesToPixels(1) -
                sprite.getWidth()) / 2);

            // bottom-justify the sprite
            sprite.setY(
                TileMapDrawer.tilesToPixels(tileY + 1) -
                sprite.getHeight());

            // add it to the map
            map.addSprite(sprite);
        }
    }


    // -----------------------------------------------------------
    // code for loading sprites and images
    // -----------------------------------------------------------


    public void loadTileImages()
    {
        // keep looking for tile A,B,C, etc. this makes it
        // easy to drop new tiles in the images/ directory
        tiles = new ArrayList();
        char ch = 'A';
        
        while (true) 
        {
            String name = ch + ".png";
            File file = new File("images/" + name);
            if (!file.exists()) 
                break;
            
            tiles.add(loadImage(name));
            ch++;
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void loadCreatureSprites() 
    {

        Image[][] images = new Image[4][];

        // load left-facing images
        images[0] = new Image[] {
            loadImage("player.png"),         
            loadImage("fly1.png"),
            loadImage("fly2.png"),
            loadImage("fly3.png"),
            loadImage("police1.png"),
            loadImage("police2.png"),
            loadImage("police3.png"),
            loadImage("police4.png"),
            loadImage("police5.png"),
            loadImage("police6.png"),
            loadImage("police7.png"),
            loadImage("police8.png"),
            loadImage("police9.png"),
            loadImage("police10.png"),
            loadImage("police11.png"),
            loadImage("police12.png"),
            loadImage("police13.png"),
            loadImage("police14.png"),
            loadImage("co1.png"),
            loadImage("co2.png"),
            loadImage("co3.png"),
            loadImage("bird1.png"),
            loadImage("bird2.png"),
            loadImage("snake1.png"),
            loadImage("snake2.png"),
            loadImage("snake3.png"),
            loadImage("snake4.png"),
            loadImage("snake5.png"),
            loadImage("dog1.png"),
            loadImage("dog2.png"),
            loadImage("dog3.png")
        };

        images[1] = new Image[images[0].length];
        images[2] = new Image[images[0].length];
        images[3] = new Image[images[0].length];
        
        for (int i=0; i<images[0].length; i++) 
        {
            // right-facing images
            images[1][i] = getMirrorImage(images[0][i]);
            // left-facing "dead" images
            images[2][i] = getFlippedImage(images[0][i]);
            // right-facing "dead" images
            images[3][i] = getFlippedImage(images[1][i]);
        }

        // create creature animations
        Animation[] playerAnim = new Animation[4];
        Animation[] flyAnim = new Animation[4];
        Animation[] grubAnim = new Animation[4];
        Animation[] cockAnim = new Animation[4];
        Animation[] birdAnim = new Animation[4];
        Animation[] snakeAnim = new Animation[4];
        Animation[] dogAnim = new Animation[4];
        
        for (int i=0; i<4; i++) 
        {
            playerAnim[i] = createPlayerAnim (images[i][0]);
            flyAnim[i] = createFlyAnim (images[i][1], images[i][1], images[i][3]);
            grubAnim[i] = createGrubAnim (images[i][4], images[i][5], images[i][6], images[i][7], images[i][8], images[i][9], images[i][10], images[i][11], images[i][12], images[i][13], images[i][14], images[i][15], images[i][16], images[i][17]);
            cockAnim[i] = createCockAnim (images[i][18], images[i][19], images[i][20]);
            birdAnim[i] = createBirdAnim (images[i][21], images[i][22]);
            snakeAnim[i] = createSnakeAnim (images[i][23], images[i][24], images[i][25], images[i][26], images[i][27]);
            dogAnim[i] = createDogAnim (images[i][28], images[i][29], images[i][30]);
        }

        // create creature sprites
        playerSprite = new Player (playerAnim[0], playerAnim[1],playerAnim[2], playerAnim[3]);
        flySprite = new Fly (flyAnim[0], flyAnim[1],flyAnim[2], flyAnim[3]);
        grubSprite = new Grub (grubAnim[0], grubAnim[1],grubAnim[2], grubAnim[3]);
        cockSprite = new Cock (cockAnim[0], cockAnim[1], cockAnim[2], cockAnim[3]);
        birdSprite = new Bird (birdAnim[0], birdAnim[1], birdAnim[2], birdAnim[3]);
        snakeSprite = new Snake (snakeAnim[0], snakeAnim[1], snakeAnim[2], snakeAnim[3]);
        dogSprite = new Dog (dogAnim[0], dogAnim[1], dogAnim[2], dogAnim[3]);
    }


    private Animation createPlayerAnim(Image player)
    {
        Animation anim = new Animation();
        anim.addFrame(player, 250);
     
        return anim;
    }


    private Animation createFlyAnim(Image img1, Image img2, Image img3)
    {
        Animation anim = new Animation();
        anim.addFrame(img1, 50);
        anim.addFrame(img2, 50);
        anim.addFrame(img3, 50);
        anim.addFrame(img2, 50);
        return anim;
    }


    private Animation createGrubAnim(Image img1, Image img2, Image img3, Image img4, Image img5, Image img6, Image img7, Image img8, Image img9, Image img10, Image img11, Image img12, Image img13, Image img14)
    {
        Animation anim = new Animation();
        anim.addFrame(img1, 100);
        anim.addFrame(img2, 100);
        anim.addFrame(img3, 100);
        anim.addFrame(img4, 100);
        anim.addFrame(img5, 100);
        anim.addFrame(img6, 100);
        anim.addFrame(img7, 100);
        anim.addFrame(img8, 100);
        anim.addFrame(img9, 100);
        anim.addFrame(img10, 100);
        anim.addFrame(img11, 100);
        anim.addFrame(img12, 100);
        anim.addFrame(img13, 100);
        anim.addFrame(img14, 100);
        return anim;
    }
    private Animation createSnakeAnim(Image img1, Image img2, Image img3, Image img4, Image img5){
        Animation anim = new Animation();
        anim.addFrame(img1, 130);
        anim.addFrame(img2, 130);
        anim.addFrame(img3, 130);
        anim.addFrame(img4, 130);
        anim.addFrame(img5, 130);
        return anim;
    }
    private Animation createBirdAnim(Image img1, Image img2)
    {
        Animation anim = new Animation();
        anim.addFrame(img1, 50);
        anim.addFrame(img2, 50);
        return anim;
    }
    
    private Animation createCockAnim(Image img1, Image img2, Image img3)
    {
        Animation anim = new Animation();
        anim.addFrame(img1, 100);
        anim.addFrame(img2, 100);
        anim.addFrame(img3, 100);
        return anim;
    }
    private Animation createDogAnim(Image img1, Image img2, Image img3){
        Animation anim = new Animation();
        anim.addFrame(img1, 100);
        anim.addFrame(img2, 100);
        anim.addFrame(img3, 100);
        return anim;
    }
    private void loadPowerUpSprites() 
    {
        // create "goal" sprite
        Animation anim = new Animation();
        anim.addFrame(loadImage("heart.png"), 150);
        goalSprite = new PowerUp.Goal(anim);

        // create "star" sprite
        anim = new Animation();
        anim.addFrame(loadImage("coin1.png"),100) ;  
        anim.addFrame(loadImage("coin2.png"),100);
        anim.addFrame(loadImage("coin3.png"),100);
        anim.addFrame(loadImage("coin4.png"),100);
        anim.addFrame(loadImage("coin5.png"),100);
        anim.addFrame(loadImage("coin6.png"),100);
        anim.addFrame(loadImage("coin7.png"),100);
        anim.addFrame(loadImage("coin8.png"),100);
        anim.addFrame(loadImage("coin9.png"),100);
        anim.addFrame(loadImage("coin10.png"),100);
        anim.addFrame(loadImage("coin11.png"),100);
        anim.addFrame(loadImage("coin12.png"),100);
        coinSprite = new PowerUp.Star(anim);
        
        anim = new Animation();
        anim.addFrame(loadImage("pho1.png"), 50);
        anim.addFrame(loadImage("pho2.png"), 50);
        phoSprite = new PowerUp.Music(anim);
        
        anim = new Animation();
        anim.addFrame(loadImage("drink1.png"), 80);
        anim.addFrame(loadImage("drink2.png"), 80);
        drinkSprite = new PowerUp.Drink(anim);
        // create "music" sprite
        anim = new Animation();
        anim.addFrame(loadImage("music1.png"), 150);
        anim.addFrame(loadImage("music2.png"), 150);
        anim.addFrame(loadImage("music3.png"), 150);
        anim.addFrame(loadImage("music2.png"), 150);
        musicSprite = new PowerUp.Music(anim);
        musicSprite=new PowerUp.Music(anim);
        
        anim.addFrame(loadImage("goal2.png"), 150);
        goalSprite2 = new PowerUp.Goal2(anim);
    }

}
