package com.example.laladui.listview_news;
/*
Rubbish作为rubbishAdapter的适配类型
*/
public class Rubbish {
    private String title,content,anthor;
    private int Drawable1,Drawable2,Drawable3;
    public Rubbish(int Da, int Db, int Dc, String z, String x, String y){
        this.Drawable1=Da;
        this.Drawable2=Db;
        this.Drawable3=Dc;
        this.title=z;
        this.content=x;
        this.anthor=y;
    }

    public String getAnthor() {
        return anthor;
    }

    public void setAnthor(String anthor) {
        this.anthor = anthor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDrawable1() {
        return Drawable1;
    }

    public void setDrawable1(int drawable1) {
        Drawable1 = drawable1;
    }

    public int getDrawable2() {
        return Drawable2;
    }

    public void setDrawable2(int drawable2) {
        Drawable2 = drawable2;
    }

    public int getDrawable3() {
        return Drawable3;
    }

    public void setDrawable3(int drawable3) {
        Drawable3 = drawable3;
    }
}
