//Liav Burger 208277871

import animation.AnimationRunner;
import arkanoid.levels.GameFlow;
import arkanoid.levels.LevelInformation;
import arkanoid.levels.Level1;
import arkanoid.levels.Level2;
import arkanoid.levels.Level3;
import arkanoid.levels.Level4;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will be used to run the "Assignment 6 Game".
 */
public class ArkanoidGame {

    /**
     * Main function to run the game.
     *
     * @param args empty
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui);
        List<LevelInformation> levels = new ArrayList<>();
        argsToLevels(args, levels);
        GameFlow game = new GameFlow(runner, gui.getKeyboardSensor());
        game.runLevels(levels);
        gui.close();
    }

    /**
     * This function takes the arguments and converts them to the corresponding level.
     * @param levels list of level information
     * @param args   arguments in string array
     */
    public static void argsToLevels(String[] args, List<LevelInformation> levels) {
        for (String arg : args) {
            try {
                int temp = Integer.parseInt(arg);
                switch (temp) {
                    case 1 -> levels.add(new Level1());
                    case 2 -> levels.add(new Level2());
                    case 3 -> levels.add(new Level3());
                    case 4 -> levels.add(new Level4());
                    default -> {
                    }
                }
            } catch (Exception ignored) {
            }
        }
        if (levels.isEmpty()) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }
    }
}
