package facebook.bean;

/**
 * Created by salvo on 26/05/17.
 */
public class Paging {

    Cursors cursors;
    String next;

    public Cursors getCursors() {
        return cursors;
    }

    public void setCursors(Cursors cursors) {
        this.cursors = cursors;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}