
package main;

import irrgarten.Game;
import UI.GraphicalUI;
import controller.Controller;


public class MainGUI{
    
public static void main(String[] args) throws InterruptedException {

    GraphicalUI vista = new GraphicalUI();
    Game game = new Game(1);
    Controller controller = new Controller (game, vista);
    controller.play();
    
    }
}