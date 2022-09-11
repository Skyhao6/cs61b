package gitlet;


import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

import static gitlet.MyUtils.*;
import static gitlet.Utils.*;


/** Represents a gitlet commit object.
 *
 *  @author Haotian Shen
 */
public class Commit implements Serializable {

    /**
     * Create a date
     */
    private Date date;

    /**
     * The message of commit
     */
    private String message;

    /**
     * The parent commits' id
     */
    private List<String> parent;

    /**
     * The tracked files Map
     * K for path
     * V for id
     */
    private Map<String, String> tracked;

    /**
     * Commit id
     */
    private String id;

    /**
     * Final Commit file with the path generated from id
     */
    private File file;

    public Commit(String message, List<String> parent, Map<String, String> tracked) {
        this.message = message;
        this.parent = parent;
        this.tracked = tracked;
        date = new Date();
        id = generateId();
        file = getObjectFile(id);
    }

    /**
     * Initialize commit
     */
    public Commit() {
        date = new Date(0);
        message = "initial commit";
        parent = new ArrayList<>();
        tracked = new HashMap<>();
        id = generateId();
        file = getObjectFile(id);
    }

    /**
     * Return Commit instance from the file with id
     * @param id
     * @return
     */
    public static Commit fromFile(String id) {
        return readObject(getObjectFile(id), Commit.class);
    }

    public String generateId() {
        return sha1(getTime(), message, parent.toString(), tracked.toString());
    }

    public String getTime() {
        DateFormat dateformat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z", Locale.ENGLISH);
        return dateformat.format(date);
    }

    /**
     * Save this Commit instance to file
     */
    public void save() {
        saveObjectFile(file, this);
    }

    public String getMessage() {
        return message;
    }

    public List<String> getParents() {
        return parent;
    }

    public Map<String, String> getTracked() {
        return tracked;
    }

    /**
     * Restore the tracked file
     * @param filepath
     * @return true if file exists in commit
     */
    public boolean restoreTracked(String filepath) {
        String blobid = tracked.get(filepath);
        if(blobid == null)
            return false;
        Blob.fromFile(blobid).writeContentToSource();
        return true;
    }

    /**
     * Restore all tracked files, overwriting the existing ones
     */
    public void restoreAllTracked() {
        for(String blobid : tracked.values()) {
            Blob.fromFile(blobid).writeContentToSource();
        }
    }

    public String getId() {
        return id;
    }

    /**
     * Get commit log
     * @return
     */
    public String getLog(){
        StringBuilder logbuilder = new StringBuilder();
        logbuilder.append("===\n");
        logbuilder.append("commit " + id + "\n");
        if(parent.size() > 1) {
            logbuilder.append("Merge:");
            for(String p : parent) {
                logbuilder.append(" ").append(p, 0, 7);
            }
            logbuilder.append("\n");
        }
        logbuilder.append("Date: " + getTime() + "\n");
        logbuilder.append(message).append("\n");
        return logbuilder.toString();
    }
}
