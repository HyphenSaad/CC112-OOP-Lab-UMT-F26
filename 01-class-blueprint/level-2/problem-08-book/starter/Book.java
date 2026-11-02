// Problem 8 — Book
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class Book {

    private String title = "";
    private String author = "";
    private int pages = 1;

    public String getTitle() { return title; }

    public void setTitle(String value) {
        // TODO: use Check.notBlank(value)
    }

    public String getAuthor() { return author; }

    public void setAuthor(String value) {
        // TODO: use Check.isName(value)
    }

    public int getPages() { return pages; }

    public void setPages(int value) {
        // TODO: use Check.inRange(value, 1, Integer.MAX_VALUE)
    }

    public boolean isLong() {
        // TODO: return pages > 300
        return false;
    }

    public int readingMinutes(int secondsPerPage) {
        // TODO: if secondsPerPage <= 0 return 0; else return (pages * secondsPerPage) / 60
        return 0;
    }

    public boolean isValid() {
        // TODO: title not blank AND author is name AND pages >= 1
        return false;
    }
}
