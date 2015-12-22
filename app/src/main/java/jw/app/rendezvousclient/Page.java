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
 */
public class Page {
    private ArrayList<PageObject> objects;

    public Page(PageObject ... objectList) {
        objects = new ArrayList<PageObject>(objectList.length);
        for (PageObject object : objectList) {
            objects.add(object);
        }
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
