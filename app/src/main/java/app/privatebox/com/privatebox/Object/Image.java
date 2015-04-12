package app.privatebox.com.privatebox.Object;

/**
 * Created by Yusri on 4/8/2015.
 */
public class Image {

    private String title;
    private String date;
    private String mimeType;
    private String path;

    public Image(String title, String date, String mimeType, String path) {
        this.title = title;
        this.date = date;
        this.mimeType = mimeType;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
