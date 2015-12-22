package jw.app.rendezvousclient;

import java.util.ArrayList;

/**
 * Each 'page' represents a collection of objects
 * that can be drawn on the main activity map. These
 * objects range from things like images, travel paths,
 * restaurant reviews, friends, etc. The user is given
 * the ability to modify the page contents of the
 * current active page, which is represented by a variable
 * in the user class.
 *
 * The page object also contains a Group, which represents
 * a collection of Contact objects. network operations are
 * dispatched to the group object, which concurrently
 * retrieves the locations of all contacts in the group
 * that are broadcasting location.
 */
public class Page {
    private ArrayList<PageObject> objects;
    private Group group;

    public Page(PageObject ... objectList) {
        objects = new ArrayList<PageObject>(objectList.length);
        for (PageObject object : objectList) {
            objects.add(object);
        }

        group = new Group(); // network operation should not be done on the main thread
    }

    public void render() {
        for (PageObject object : objects) {
            object.render();
        }
    }

    public void unrender() {
        for (PageObject object: objects) {
            object.unrender();
        }
    }
}
