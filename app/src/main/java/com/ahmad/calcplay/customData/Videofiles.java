package com.ahmad.calcplay.customData;

public class Videofiles {
    private String id;
    private String path;
    private String title;
    private String file;
    private String size;
    private String dataadded;
    private String duration;

    public Videofiles(String id, String path, String title, String file, String size, String dataadded, String duration) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.file = file;
        this.size = size;
        this.dataadded = dataadded;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDataadded() {
        return dataadded;
    }

    public void setDataadded(String dataadded) {
        this.dataadded = dataadded;
    }

    public String getDuration() {
int realduration=Integer.parseInt(duration);
int hours=realduration/3600000;
int min=(realduration/60000)%60000;
int sc=realduration%60000/1000;
StringBuilder stringBuilder=new StringBuilder();
if (hours>0){
    stringBuilder.append(hours).append(" :");

}else {
    stringBuilder.append("00").append(" :");
}
        if (min>0){
            stringBuilder.append(min).append(" :");

        }else {
            stringBuilder.append("00").append(" :");
        }

        if (sc>0){
            stringBuilder.append(sc).append("");

        }else {
            stringBuilder.append("00").append("");
        }

        return stringBuilder.toString();
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
