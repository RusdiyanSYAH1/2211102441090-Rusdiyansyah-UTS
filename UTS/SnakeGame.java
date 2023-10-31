import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeGame extends World
{

    private Snake snake;
    private Food food;
    private int score;
    public SnakeGame()
    {    
        super(600, 600, 1);
        snake = new Snake();
        food = new Food();
        addObject(snake,getWidth() / 2, getHeight() / 2);
        addObject(food,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        score = 0;
    }
    
    public void act(){
        snake.moveBody();
        checkCollision();
    }
    
    public void checkCollision(){
        if (snake.intersects(food)) {
            snake.extend();
            removeObject(food);
            addObject(food, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
            score++;
        }
        
        if (snake.hitWall() || snake.hitItself()){
            Greenfoot.stop();
            showText("Game Over! Score: " + score,getWidth() / 2,getHeight() / 2);
        }
    }
}
