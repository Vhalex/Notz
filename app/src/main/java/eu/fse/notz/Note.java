package eu.fse.notz;

/**
 * Created by Amministratore on 12/04/2018.
 */

public class Note {
    private String title;
    private String description;
    private int id;

    public Note(String title, String description){
        this.title=title;
        this.description=description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isShowOnTop() {
        return isShowOnTop;
    }

    public void setShowOnTop(boolean showOnTop) {
        isShowOnTop = showOnTop;
    }

    private boolean isShowOnTop;

}
