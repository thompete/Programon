package com.thompete.programon;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class App implements Serializable {

    private Game game;
    private SaveFile[] saveSlots = new SaveFile[3];
    private SaveFile saveFile;

    public App() {
        for (int i = 0; i < saveSlots.length; i++) {
            String path = "./saves/save" + (i + 1) + ".dat";
            File f = new File(path);
            if (!f.isDirectory()) {
                if (f.exists()) saveSlots[i] = new SaveFile(path, false);
                else saveSlots[i] = new SaveFile(path, true);
            }
        }
    }

    public void start() {
        loop();
    }

    public SaveFile getSaveSlot(int saveSlot) {
        return saveSlots[saveSlot];
    }

    public SaveFile getSaveFile() {
        return saveFile;
    }

    public void setSaveFile(SaveFile saveFile) {
        this.saveFile = saveFile;
    }

    private void loop() {
        loop: while (true) {
            switch (displayMainMenu()) {
                case 0 -> newGame();
                case 1 -> loadGame();
                case 2 -> displayHelp();
                case 3 -> { break loop; }
            }
        }
    }

    private int displayMainMenu() {
        return UI.getChoice(
                "Welcome to the Programon!",
                List.of("New Game", "Load Game", "Help", "Exit")
        );
    }

    private void displayHelp() {
        UI.print("This page is going to be filled with helpful information :)\n");
        UI.print("Lorem ipsum dolor sit amet\n");
        UI.getChoice(List.of("Back"));
    }

    private void newGame() {
        game = new Game(this);
        game.start();
    }

    private void loadGame() {
        int choice = UI.getChoice(
                "Choose save file:",
                List.of(
                        saveSlots[0].getName(),
                        saveSlots[1].getName(),
                        saveSlots[2].getName()
                )
        );
        SaveFile saveFile = saveSlots[choice];

        if (saveFile.isEmpty()) {
            UI.print("File doesn't contain any saved game\n");
            return;
        }

        game = saveFile.load();
        if (game != null) game.continueGame();
    }
}
