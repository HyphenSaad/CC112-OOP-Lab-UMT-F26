import chirp.text.Check;

public class Book {

    private String title = "";
    private String author = "";
    private int pages = 1;

    public String getTitle() { return title; }

    public void setTitle(String value) {
        if (Check.notBlank(value)) {
            this.title = value;
        }
    }

    public String getAuthor() { return author; }

    public void setAuthor(String value) {
        if (Check.isName(value)) {
            this.author = value;
        }
    }

    public int getPages() { return pages; }

    public void setPages(int value) {
        if (Check.inRange(value, 1, Integer.MAX_VALUE)) {
            this.pages = value;
        }
    }

    public boolean isLong() {
        return pages > 300;
    }

    public int readingMinutes(int secondsPerPage) {
        if (secondsPerPage <= 0) return 0;
        return (pages * secondsPerPage) / 60;
    }

    public boolean isValid() {
        return Check.notBlank(title)
                && Check.isName(author)
                && Check.inRange(pages, 1, Integer.MAX_VALUE);
    }
}
