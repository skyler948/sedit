package menu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public enum Item {

    NEW(0, KeyStroke.getKeyStroke("control N"), Menu.FILE),
    OPEN(1, KeyStroke.getKeyStroke("control O"), Menu.FILE),
    SAVE(2, KeyStroke.getKeyStroke("control S"), Menu.FILE),
    SAVE_AS(3, KeyStroke.getKeyStroke("control shift S"), Menu.FILE),
    QUIT(4, KeyStroke.getKeyStroke("control Q"), Menu.FILE),

    UNDO(0, KeyStroke.getKeyStroke("control Z"), Menu.EDIT),
    REDO(1, KeyStroke.getKeyStroke("control shift Z"), Menu.EDIT),
    CUT(2, KeyStroke.getKeyStroke("control X"), Menu.EDIT),
    COPY(3, KeyStroke.getKeyStroke("control C"), Menu.EDIT),
    PASTE(4, KeyStroke.getKeyStroke("control V"), Menu.EDIT),
    FIND(5, KeyStroke.getKeyStroke("control F"), Menu.EDIT),

    ZOOM_IN(0, KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.CTRL_DOWN_MASK), Menu.VIEW),
    ZOOM_OUT(1, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK), Menu.VIEW),
    RESET_ZOOM(2, KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK), Menu.VIEW),

    SHOW_STATUS_PANEL(0, null, Menu.SETTINGS),
    CONFIGURE_STYLE(1, null, Menu.SETTINGS),

    REPORT_BUG(0, null, Menu.HELP),
    ABOUT_SEDIT(1, null, Menu.HELP);

    public final int id;
    public final KeyStroke key;
    public final Menu menu;

    Item(int id, KeyStroke key, Menu menu) {
        this.id = id;
        this.key = key;
        this.menu = menu;
    }

}
