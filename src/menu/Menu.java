package menu;

public enum Menu {

    FILE(0),
    EDIT(1),
    VIEW(2),
    SETTINGS(3),
    HELP(4);

    public final int id;

    Menu(int id) {
        this.id = id;
    }

}
