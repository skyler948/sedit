package menu;

public enum Item {

    NEW(0),
    OPEN(1),
    SAVE(2),
    SAVE_AS(3),
    QUIT(4),

    UNDO(0),
    REDO(1),
    CUT(2),
    COPY(3),
    PASTE(4),
    FIND(5),

    ZOOM_IN(0),
    ZOOM_OUT(1),

    SHOW_MENU_BAR(0),
    SHOW_STATUS_PANEL(1),
    CONFIGURE_KEYBINDS(2),
    STYLE_KEYBINDS(3),

    REPORT_BUG(0),
    ABOUT_SEDIT(1);

    public final int id;

    Item(int id) {
        this.id = id;
    }

}
