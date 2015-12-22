package jw.app.rendezvousclient;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * This abstract class is extended by every
 * object that is renderable on the main
 * activity map. It contains a single render
 * method implemented by extending classes.
 */
public abstract class PageObject {

    protected GoogleMap map;        // main activity map
    protected LatLng coordinates;   // latlng coordinates of the object

    /**
     * Every page object is initialized with some location
     * and is passed a reference to the main activity map.
     * The coord argument is used to render the object on
     * the map.
     * @param map - main activity map
     * @param coord - location to render, if null, the object
     *              is not rendered.
     */
    public PageObject(GoogleMap map, LatLng coord) {
        map = map;
        coordinates = coord;
    }

    /**
     * Update the location of the object on the map. The
     * LatLng object may or may not be ignored depending
     * on whether the location of the object is determined
     * locally, or with a network operation.
     * @param coords
     * @return
     */
    public abstract boolean updateLocation(LatLng coords);

    /**
     * Draw the object
     */
    public abstract void render();

    /**
     * Undraw the object
     */
    public abstract void unrender();
}
