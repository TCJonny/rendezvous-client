package jw.app.rendezvousclient;

import com.google.android.gms.maps.GoogleMap;

/**
 * This abstract class is extended by every
 * object that is renderable on the main
 * activity map. It contains a single render
 * method implemented by extending classes.
 */
public abstract class PageObject {

    protected GoogleMap map;

    public PageObject(GoogleMap map) {
        map = map;
    }

    public abstract void render();

    public abstract void unrender();
}
