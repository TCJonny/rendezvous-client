package jw.app.rendezvousclient;

import android.app.Application;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This is all the global state we need to maintain
 * for the user. In each session we need information
 * about each of the user's pages. Each page consists
 * of a collection of abstract page objects which can
 * be rendered on the main activity map.
 */
public class User extends Application {
    private static final int MAX_PAGE_COUNT = 20;
    private Page activePage;
    private ArrayList<Page> inactivePages;

    public User () {
        super();
        activePage = null;
        inactivePages = new ArrayList<Page>();
    }

    /**
     * Get the active page
     * @return activePage - the page whose objects are
     * currently rendered in the main activity.
     */
    public Page getActivePage() { return activePage; }

    /**
     * This method can either return the active page or an inactive page
     * @param pagenum
     * @return the requested page
     * @throws NoSuchElementException - Thrown if pagenum is out of bounds or the
     * page list is empty
     */
    public Page getPage(int pagenum) throws NoSuchElementException {
        if (pagenum < 0 || pagenum >= inactivePages.size() || inactivePages.size() == 0) {
            throw new NoSuchElementException("Invalid page index!");
        }
        return inactivePages.get(pagenum);
    }

    /**
     * Removes an inactive page from the list. This method should only
     * be called to remove an inactive page.
     * @param pagenum
     * @return
     * @throws NoSuchElementException - Thrown if the requested page
     * doesn't exist.
     * @throws IllegalStateException - Thrown if this method is used to
     * remove the active page.
     */
    public Page removePage(int pagenum) throws NoSuchElementException, IllegalStateException {
        Page page = getPage(pagenum);
        if (activePage != page) {
            inactivePages.remove(pagenum);
        } else {
            // this method should only be used to remove inactive pages, the
            // assumption is that at least the active page also exists in the list
            throw new IllegalStateException("Trying to remove the active page!");
        }
        return page;
    }

    /**
     * Inserts a new page. The user is limited to 20 total pages.
     * Note that this method does not set the new page as the
     * active page.
     * @param newPage
     * @return the index at which the newly-inserted page resides,
     * this is returned so that the caller may choose to set this
     * new page as the new active page for the user.
     */
    public int insertPage(Page newPage) throws InvalidPageCountException, IllegalStateException {
        if (newPage == null || inactivePages.contains(newPage)) {
            throw new IllegalStateException("Trying to insert a page that already exists!");
        }
        if (inactivePages.size() >= MAX_PAGE_COUNT) {
            throw new InvalidPageCountException("Requested operation exceeds" +
                    " maximum allowed number of pages!");
        }
        inactivePages.add(newPage);
        return inactivePages.size() - 1;
    }

    /**
     * Called when the user switches to a new page from
     * the UI. This ultimately de-renders all the objects
     * currently on the map, resets the activePage variable,
     * and renders the objects contained in the new active
     * page.
     * @param pagenum
     * @return activePage - the new active page
     */
    public Page setActivePage(int pagenum) throws NoSuchElementException {
        Page page = getPage(pagenum);

        if (page == activePage) {
            // don't do work if this is idempotent
            return activePage;
        }
        // if we got to this point the page is valid
        activePage.unrender();
        activePage = page;
        activePage.render();

        return activePage;
    }

    /**
     * Identical to the previous method, but operates on a page.
     * @param page
     * @return The new active page
     * @throws NoSuchElementException - Thrown if the existing page
     * is not in the users active page list.
     */
    public Page setActivePage(Page page) throws NoSuchElementException {
        int newActivePageIndex = inactivePages.indexOf(page);
        if (!inactivePages.contains(page)) {
            throw new NoSuchElementException("Page doesn't exist!");
        }
        if (inactivePages.get(newActivePageIndex) == activePage) {
            // don't work if this is idempotent
            return activePage;
        } else {
            activePage.unrender();
            activePage = inactivePages.get(newActivePageIndex);
            activePage.render();
        }
        return activePage;
    }

    /**
     * Set active page to null. This should be called if
     */
    public void unsetActivePage() {
        activePage = null;
    }

    /**
     * Remove all pages. Should be called on shutdown
     * @return
     */
    public void clearPages() {
        if (activePage != null) {
            activePage.unrender();
        }
        unsetActivePage();
        inactivePages.removeAll(inactivePages);
    }

}
