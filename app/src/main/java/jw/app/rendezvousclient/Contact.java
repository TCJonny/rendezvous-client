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

    private boolean updateLocation = false;

    public Contact(GoogleMap map, LatLng coord) {
        super(map, coord);
    }

    public boolean getUpdateLocationState() { return updateLocation; }
    public void setUpdateLocationState(boolean update) { updateLocation = update; }

    /**
     * If the user has chosen to share their location with the
     * group, query the groups server to get the location of this
     * contact.
     * @param coords
     * @return
     */
    public boolean updateLocation(LatLng coords) {
        if (getUpdateLocationState()) {
            // query groups server
            return true;
        }
        return false;
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
