package jw.app.rendezvousclient;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 *  The Contact class represents a contact whose location
 *  is being broadcasted to the groups server. On the
 *  client-side, each contact object is contained in a
 *  collection stored in the group class. Network operations
 *  are performed at group-granularity. Contact location is
 *  only updated if the global flag here is true.
 */
public class Contact extends PageObject {

    private boolean getLocation = false;

    public Contact(GoogleMap map, LatLng coord) {
        super(map, coord);
    }

    public boolean getLocationState() { return getLocation; }
    public void setUpdateLocationState(boolean update) { getLocation = update; }

    /**
     * Queries the groups server for the most recently broadcasted
     * location of this contact and then uses the updateLocation
     * method to redraw the corresponding marker on the main
     * activity map
     * @return
     */
    public LatLng getLocation() {
        if (getLocationState()) {
            // network operation
        }
        return null;
    }

    /**
     * Update the location of the Contact marker on the map
     * @param coords
     * @return
     */
    public void updateLocation(LatLng coords) {
        if (coords != null) {
            coordinates = coords;
            // draw item on map
        }
    }

    /**
     * Draw the contact on the map
     */
    public void render() {

    }

    /**
     * Remove the contact from the map
     */
    public void unrender() {

    }
}
