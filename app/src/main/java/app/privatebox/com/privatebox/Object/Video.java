package app.privatebox.com.privatebox.Object;

/**
 * Created by Yusri on 4/8/2015.
 */
public class Video {

    private String title;
    private String date;
    private String mimeType;
    private String path;
    private String id;
    //private Bitmap bitmap;

    public Video(String title, String date, String mimeType, String path, String id) {
        this.title = title;
        this.date = date;
        this.mimeType = mimeType;
        this.path = path;
        this.id = id;
        //this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
