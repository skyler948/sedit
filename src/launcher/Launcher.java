package launcher;

import display.EditorDisplay;

public class Launcher {

    public static final String VERSION_STRING = "0.3.0";

    public static void main(String[] args) {
        EditorDisplay editorDisplay = new EditorDisplay(1280, 720);
        editorDisplay.createDisplay();
    }

}
