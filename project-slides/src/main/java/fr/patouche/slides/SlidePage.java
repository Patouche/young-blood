package fr.patouche.slides;

/**
 * Slide model.
 *
 * @author patouche - 13/12/15.
 */
public class SlidePage {

    /** The position of the page in a section. */
    private int index;

    /** The content of the slide. */
    private String content;

    /**
     * Default constructor.
     */
    public SlidePage() {
        this(0, null);
    }

    /**
     * Slide constructor.
     *
     * @param index   the name of the file
     * @param content the content of the slide
     */
    public SlidePage(final int index, final String content) {
        this.index = index;
        this.content = content;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(final int index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("Page[index=%d]", index);
    }
}
