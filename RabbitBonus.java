package com.javarush.games.snake;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

public class RabbitBonus extends GameObject{
    public boolean isAlive = true;

    public RabbitBonus(int x, int y) {
        super(x, y);


    }
    private static final String BONUS_SIGN = "\uD83D\uDC07";
    public void draw(Game game){
        game.setCellValueEx(x, y, Color.NONE, BONUS_SIGN, Color.HOTPINK,75);
    };
}
