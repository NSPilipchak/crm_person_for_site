package NotUses;

import java.io.File;

/**
 * Created by hammer on 05.07.2017.
 */
public class FileLink {
    private int id;
    private String name;
    private File file;
    private String link;

    public FileLink() {
    }

    @Override
    public String toString() {
        return "FileLink{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", file=" + file +
                ", link='" + link + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
