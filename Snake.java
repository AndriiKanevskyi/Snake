package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {

     public boolean isAlive = true;
    private Direction direction = Direction.LEFT;
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN ="\u26AB";

    public void setDirection(Direction direction) {
    if (direction.name()==Direction.LEFT.name()&&snakeParts.get(0).y==snakeParts.get(1).y){return;}
    if (direction.name()==Direction.RIGHT.name()&&snakeParts.get(0).y==snakeParts.get(1).y){return;}
    if (direction.name()==Direction.UP.name()&&snakeParts.get(0).x==snakeParts.get(1).x){return;}
    if (direction.name()==Direction.DOWN.name()&&snakeParts.get(0).x==snakeParts.get(1).x){return;}
   if (this.direction.oposite!=direction.name()){
      this.direction=direction;
   }}

    public Direction getDirection() {
        return direction;
    }
public boolean checkCollision(GameObject object){
        boolean result=false;
    for (int i = 0; i < snakeParts.size(); i++) {
        if (snakeParts.get(i).x==object.x&&snakeParts.get(i).y==object.y){
            result= true;
        }
    }
    return result;
}
public int getLength(){
        return snakeParts.size();
}
    public void move(Apple apple, RabbitBonus bonus){
        GameObject newHead;
        newHead= createNewHead();

        //snakeParts.add(0,newHead);
       // removeTail();
    if      (newHead.x<0||
            newHead.x>14||
            newHead.y<0||
            newHead.y>14||checkCollision(newHead))
    {
        isAlive=false;
    }
    else {
        snakeParts.add(0,newHead);
        if (newHead.x==apple.x&&newHead.y==apple.y){
            apple.isAlive=false;

        }
        if (newHead.x==bonus.x&&newHead.y==bonus.y){bonus.isAlive=false;}else { removeTail();}}

    };

    public GameObject createNewHead() {
        if (direction==Direction.LEFT){return new GameObject(snakeParts.get(0).x-1,snakeParts.get(0).y);}
        if (direction==Direction.RIGHT){return new GameObject(snakeParts.get(0).x+1,snakeParts.get(0).y);}
        if (direction==Direction.UP){return new GameObject(snakeParts.get(0).x,snakeParts.get(0).y-1);}
        else {return new GameObject(snakeParts.get(0).x,snakeParts.get(0).y+1);}


    }

    public void removeTail(){
        snakeParts.remove(snakeParts.size()-1);
    }

    public void draw(Game game){
    for (GameObject ob:this.snakeParts){

        if (snakeParts.indexOf(ob)==0){
            if (isAlive==true){game.setCellValueEx(ob.x, ob.y, Color.NONE, HEAD_SIGN, Color.GOLD, 75);}
            else {game.setCellValueEx(ob.x, ob.y, Color.NONE, HEAD_SIGN, Color.RED, 75);}
        }else if (isAlive==true) {game.setCellValueEx(ob.x,ob.y,Color.NONE,BODY_SIGN,Color.GOLD,75);}else {
            game.setCellValueEx(ob.x,ob.y,Color.NONE,BODY_SIGN,Color.RED,75);}
    }

    }
    public Snake(int x, int y) {
        GameObject first = new GameObject(x, y);
        GameObject second = new GameObject(x+1, y);
        GameObject third = new GameObject(x+2, y);
        snakeParts.add(first);
        snakeParts.add(second);
        snakeParts.add(third);
    };
    private List<GameObject> snakeParts = new ArrayList<GameObject>();
}
