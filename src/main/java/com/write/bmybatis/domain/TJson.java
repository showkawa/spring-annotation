package com.write.bmybatis.domain;

public class TJson {

    private int id;
    private  Object info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "TJson{" +
                "id=" + id +
                ", info=" + info +
                '}';
    }
}
