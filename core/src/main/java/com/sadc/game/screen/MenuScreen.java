package com.sadc.game.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.sadc.game.GameConstants;

public class MenuScreen  extends GameScreen{
    private final SpriteBatch spriteBatch;
    private final Texture background;
    private final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Minecraftia.ttf"));
    private final BitmapFont startGameFont;
    private final BitmapFont creditsFont;
    private final BitmapFont exitFont;
    private static final int START_SELECTED = 1;
    private static final int CREDITS_SELECTED = 2;
    private static final int EXIT_SELECTED = 3;
    private int selectedMenuItem;
    private final String startGameText = "Start Game";
    private final String creditsText = "Credits";
    private final String exitText = "Exit";
    private boolean oldP1Up = false;
    private boolean oldP1Down = false;
    private boolean oldP2Up = false;
    private boolean oldP2Down = false;
    public MenuScreen(){
        spriteBatch = new SpriteBatch();
        background = new Texture("badlogic.jpg");
        //the number passed to the generator is the size of the text
        startGameFont = generator.generateFont(12);
        creditsFont = generator.generateFont(12);
        exitFont = generator.generateFont(12);
        startGameFont.setColor(Color.BLUE);
        selectedMenuItem = START_SELECTED;
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        spriteBatch.dispose();
        startGameFont.dispose();
        creditsFont.dispose();
        exitFont.dispose();
        background.dispose();
        generator.dispose();
    }

    @Override
    public void update(float delta){
        boolean p1Up = Gdx.input.isKeyPressed(GameConstants.P1_UP);
        boolean p1Down = (Gdx.input.isKeyPressed(GameConstants.P1_DOWN));
        boolean p2Up =  Gdx.input.isKeyPressed(GameConstants.P2_UP);
        boolean p2Down = Gdx.input.isKeyPressed(GameConstants.P2_DOWN);
        boolean p1Enter = Gdx.input.isKeyPressed(GameConstants.P1_B);
        boolean p2Enter = Gdx.input.isKeyPressed(GameConstants.P2_B);




        if (p1Up || p1Down || p2Up || p2Down || p1Enter || p2Enter){
            switch(selectedMenuItem){
                case START_SELECTED:
                    if ((p1Up && !oldP1Up) || (p2Up && !oldP2Up)){
                        exitHighlighted();
                    } else if ((p1Down && !oldP1Down) || (p2Down && !oldP2Down)) {
                        creditsHighlighted();
                    } else {

                    }
                    break;
                case CREDITS_SELECTED:
                    if ((p1Up && !oldP1Up) || (p2Up && !oldP2Up)){
                        startGameHighlighted();
                    } else if ((p1Down && !oldP1Down) || (p2Down && !oldP2Down)) {
                        exitHighlighted();
                    } else {

                    }
                    break;
                case EXIT_SELECTED:
                    if ((p1Up && !oldP1Up) || (p2Up && !oldP2Up)){
                        creditsHighlighted();
                    } else if ((p1Down && !oldP1Down) || (p2Down && !oldP2Down)) {
                        startGameHighlighted();
                    } else {

                    }
                    break;
            }
        }
        oldP1Up = Gdx.input.isKeyPressed(GameConstants.P1_UP);
        oldP1Down = Gdx.input.isKeyPressed(GameConstants.P1_DOWN);
        oldP2Up = Gdx.input.isKeyPressed(GameConstants.P2_UP);
        oldP2Down = Gdx.input.isKeyPressed(GameConstants.P2_DOWN);
    }

    @Override
    public void draw(float delta){
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(background, 200, 200);
        float width = startGameFont.getBounds(creditsText).width;
        //to change a font's color: font.setColor(Color.BLUE);
        //to change spritebatch's color: spriteBatch.setColor(Color.BLUE);
        startGameFont.draw(spriteBatch, startGameText, 300 - width / 2, 130);
        creditsFont.draw(spriteBatch, creditsText, 312 - width / 2, 110);
        exitFont.draw(spriteBatch, exitText, 322 - width / 2, 90);
        spriteBatch.end();
    }

    private void startGameHighlighted(){
        creditsFont.setColor(Color.WHITE);
        exitFont.setColor(Color.WHITE);
        startGameFont.setColor(Color.BLUE);
        selectedMenuItem = START_SELECTED;
    }

    private void creditsHighlighted(){
        startGameFont.setColor(Color.WHITE);
        exitFont.setColor(Color.WHITE);
        creditsFont.setColor(Color.BLUE);
        selectedMenuItem = CREDITS_SELECTED;
    }

    private void exitHighlighted(){
        startGameFont.setColor(Color.WHITE);
        creditsFont.setColor(Color.WHITE);
        exitFont.setColor(Color.BLUE);
        selectedMenuItem = EXIT_SELECTED;
    }
}