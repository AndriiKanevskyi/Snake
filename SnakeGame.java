package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH =15;
    public static final int HEIGHT =15;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private int score;

    private static final int GOAL =28;

private void win(){
    stopTurnTimer();
    isGameStopped=true;
    showMessageDialog(Color.GREEN,"YOU WIN",Color.GOLD,75);
}
private void createNewApple(){

    apple = new Apple(getRandomNumber(WIDTH),getRandomNumber(HEIGHT));
    while (snake.checkCollision(apple)){apple = new Apple(getRandomNumber(WIDTH),getRandomNumber(HEIGHT));}
}

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if (!apple.isAlive){
            score=score+5;
            setScore(score);
            turnDelay=turnDelay-10;
            setTurnTimer(turnDelay);
            createNewApple();}
        if (!snake.isAlive){gameOver();}
        if (snake.getLength()>GOAL){
            win();}

        drawScene();


    }

    @Override
    public void initialize() {
    setScreenSize(WIDTH,HEIGHT);
    createGame();
    }

    private void createGame(){

        score=0;
        setScore(score);
        isGameStopped=false;
        snake = new Snake(WIDTH/2,HEIGHT/2);
        turnDelay = 300;
        setTurnTimer(turnDelay);
        createNewApple();
        drawScene();

    }
    private void gameOver(){
    stopTurnTimer();
    isGameStopped=true;
    showMessageDialog(Color.RED,"GAME OVER",Color.YELLOW,75);
    }
    private void drawScene(){
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.DARKGREEN, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }
    @Override
    public void onKeyPress(Key key){
    if (key==Key.LEFT){snake.setDirection(Direction.LEFT);}
    if (key==Key.RIGHT){snake.setDirection(Direction.RIGHT);}
    if (key==Key.UP){snake.setDirection(Direction.UP);}
    if (key==Key.DOWN){snake.setDirection(Direction.DOWN);}
    if (key==Key.SPACE&& isGameStopped){createGame();}
    }


}
