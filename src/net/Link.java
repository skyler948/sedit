package net;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Link {

    public static void openLink(String url) {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI(url);

            desktop.browse(uri);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
