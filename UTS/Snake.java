import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends Actor
{
    private int speed = 1;
    private int length = 1;
    private int delay = 10;
    private int counter = 0;
    private int[] xCoords = new int[100];
    private int[] yCoords = new int[100];
    private int direction = 0;
    
    public Snake(){
        setImage("snakebody.png");
    }
    
    public void act()
    {
        if (counter == delay){
            move(speed);
            counter = 0;
            
            xCoords[0] = getX();
            yCoords[0] = getY();
            
            moveBody(); 
            
            checkKeyPress();
            
            checkEdgeCollision();
        } else{
            counter++;
        }
    }
    
    public void moveBody(){
        for (int i = length - 1; i > 0; i--){
            xCoords[i] = xCoords[i-1];
            yCoords[i] = yCoords[i-1];
        }
    }
    
    public void checkKeyPress(){
        if (Greenfoot.isKeyDown("right") && direction != 2){
            direction = 0;
        }
        if (Greenfoot.isKeyDown("up") && direction != 3) {
            direction = 1;
        }
        if (Greenfoot.isKeyDown("left") && direction != 0) {
            direction = 2;
        }
        if (Greenfoot.isKeyDown("down") && direction != 1) {
            direction = 3;
        }
    }
    
    public void checkEdgeCollision() {
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1 || getY() <= 0 || getY()>= getWorld().getHeight() - 1){
            Greenfoot.stop();
        }
    }
    
    public boolean hitWall() {
        if (getX() <= 0 || getX() >= getWorld().getWidth() -1 || getY() >= getWorld().getHeight() -1){
            return true;
        }
        return false;
        
    }
    
    public boolean hitItself() {
        for (int i = 1; i <length; i++){
            if (getX() == xCoords[i] && getY() == yCoords[i]){
                return true;
            }
        } return false;
    }
    
    public void extend() {
        length++;
        xCoords[length - 1] = xCoords[length - 2];
        yCoords[length - 1] = yCoords[length - 2];
        getWorld().addObject(new Tail(), xCoords[length - 1], yCoords[length - 1]);
    }
    
    public boolean intersects(Food food) {
    if (food != null) {
        // Get the coordinates of the snake's head and the food
        int snakeX = getX();
        int snakeY = getY();
        int foodX = food.getX();
        int foodY = food.getY();

        // Check if the snake's head is at the same position as the food
        if (snakeX == foodX && snakeY == foodY) {
            return true;
        }
    }
    return false;
    }
}