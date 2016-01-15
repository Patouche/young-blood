package fr.patouche.slides;

import java.nio.file.Paths;
import java.util.List;

/**
 * Model of a section in the presentation
 *
 * @author patouche - 13/12/15.
 */
public class SlideSection {

    /** The position of the section in the presentation */
    private int index;

    /** The filename. */
    private String fileName;

    /** The content of the slide. */
    private String content;

    /** The position of the section. */
    private String file;

    /** The page in the presentation. */
    private List<SlidePage> pages;

    /**
     * Default constructor.
     */
    public SlideSection() {
        this(0, null, null, null);
    }

    /**
     * Class constructor.
     *
     * @param file  the name of the file
     * @param pages the number of pages
     */
    public SlideSection(final String file, final String content, final List<SlidePage> pages) {
        this(0, file, content, pages);
    }

    /**
     * Class constructor.
     *
     * @param index   the position of the section
     * @param file    the name of the file
     * @param content the content of the slides
     * @param pages   the pages which is the same as the content split
     */
    public SlideSection(final int index, final String file, final String content, final List<SlidePage> pages) {
        this(index, Paths.get(file).getFileName().toString(), file, content, pages);
    }

    /**
     * Class constructor.
     *
     * @param index    the position of the section
     * @param fileName the filename (as the basename)
     * @param file     the complete path of the file
     * @param content  the content of the slides
     * @param pages    the pages which is the same as the content split
     */
    public SlideSection(final int index, final String fileName, final String file, final String content, final List<SlidePage> pages) {
        this.fileName = fileName;
        this.file = file;
        this.content = content;
        this.index = index;
        this.pages = pages;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<SlidePage> getPages() {
        return pages;
    }

    public void setPages(List<SlidePage> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return String.format("Section[index=%d, filename=%s, path=%s, pages=%s]", index, fileName, file, pages);
    }

}
