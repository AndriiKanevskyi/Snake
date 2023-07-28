package com.javarush.games.snake;

public enum Direction {
    UP("DOWN"),
    DOWN("UP"),
    RIGHT("LEFT"),

    LEFT("RIGHT");

    String oposite;
    Direction(String oposite) {
        this.oposite=oposite;
    }
}
