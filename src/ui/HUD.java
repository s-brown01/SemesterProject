package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import states.Playing;
import utils.LoadSave;

/**
 * The HUD or Heads Up Display will always be visible during gameplay to inform the player
 * of their score, their current health, and their lives remaining
 * 
 * @author John Botonakis
 */
public class HUD {
    private BufferedImage portrait, hudbg, hearts, charge;
    /**
     * 
     */
    private final int xPos = 100;
    /**
     * 
     */
    private final int yPos = 15;
    /**
     * 
     */
    private final int width = 80;
    /**
     * 
     */
    private final int height = 80;
    private Playing playing;
    private int playerHealth, playerScore, playerLives;
    private Font hudFont;

    /**
     * Constructor class to instantiate the elements that make up the HUD
     * @param playing - Playing Game state
     */
    public HUD(Playing playing) {
        this.playing = playing;
        this.playerScore = playing.getScore();
        this.playerLives = playing.getPlayer().getLives();
        loadAssets();
    }

    /**
     * Loads in the image assets for the HUD entity
     */
    private void loadAssets() {
        portrait = LoadSave.getSpriteSheet(LoadSave.PLAYER_PORTRAIT);
        hudbg = LoadSave.getSpriteSheet(LoadSave.HUDBG);
//        hearts = LoadSave.getSpriteSheet(LoadSave.HUDBG); //Get assets for hearts
//        charge = LoadSave.getSpriteSheet(LoadSave.HUDBG); //Get assets for Dash charge

        // Font Initialization
        this.hudFont = LoadSave.loadFont(LoadSave.FONT, 25);
    }

    /**
     * Handles any functionality that requires an update, such as lives, score count, and
     * health
     */
    public void updateHUD() {
        // Check to ensure that Playing is active.
        // Letting it run once without it active will simply return nothing,
        // running it while active will have the intended behavior
        if (this.playing == null) {
            return;
        } else {
            updateHealth();
            updateScore();
        }

    }

    /**
     * Draws every HUD element to the screen starting from left most element to right most element
     * @param g
     */
    public void draw(Graphics g) {
        // Score Positioning Vars
        int scoreXPos = xPos - 50;
        int scoreYPos = yPos * 3;
        int playerScoreX = xPos - 30;
        int playerScoreY = yPos + 75;

        // Lives Positioning Vars
        int livesXPos = xPos * 5;
        int livesYPos = yPos * 3;
        int playerLivesX = xPos * 5;
        int playerLivesY = yPos + 75;

        g.drawImage(hudbg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(portrait, Game.GAME_WIDTH - xPos, yPos, width, height, null);

        g.setColor(Color.BLACK);
        g.setFont(hudFont);

        g.drawString("Score ", scoreXPos, scoreYPos);
        g.drawString(String.valueOf(playerScore), playerScoreX, playerScoreY);

        g.drawString("Lives ", livesXPos, livesYPos);
        g.drawString(String.valueOf(playerLives), playerLivesX, playerLivesY);

        g.drawString("Charge", livesXPos + 400, livesYPos);
    }

    /**
     * Function to continuously check that the health is up to date with what the actual value
     * is
     */
    public void updateHealth() {
        this.playerHealth = playing.getPlayer().getHealth();
        if (playerHealth == 2) {
            // Draw two full hearts
        } else if (playerHealth == 1) {
            // Draw one full heart and one dead heart
        } else {
            // Draw two dead hearts
        }

    }

    public void updateScore() {
        this.playerScore = playing.getScore();
    }
    
    /**
     * Function to keep the HUD element tied to lives up to date
     */
    public void updateLives() {
        this.playerLives = playing.getPlayer().getLives();
    }

    /**
     * Function to keep the HUD element tied to the dash up to date 
     */
    public void updateCharge() {

    }

}
