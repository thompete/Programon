package com.thompete.programon;

import java.io.*;

public class SaveFile implements Serializable {

    private final String name;
    private final String path;
    private boolean isEmpty;

    public SaveFile(String path, boolean isEmpty) {
        this.isEmpty = isEmpty;
        this.path = path;
        String[] splitPath = path.split("/");
        this.name = splitPath[splitPath.length - 1];
    }

    public String getName() {
        return isEmpty ? "Empty file" : name;
    }

    public boolean isEmpty() {
        return path.isEmpty();
    }

    public void save(Game game) {
        isEmpty = false;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Game load() {
        Game game = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            game = (Game) ois.readObject();
        } catch (Exception e) {
            UI.print("File is empty\n");
        }
        return game;
    }

    public void clear() {
        isEmpty = true;
    }
}
