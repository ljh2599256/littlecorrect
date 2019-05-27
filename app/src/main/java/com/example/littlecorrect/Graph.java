package com.example.littlecorrect;
public class Graph {
    private String graphname="";
    private int graphid;
    private String userid="pangji";
    private String teaid="";
    private String comment="";
    private int looked = 0;
    private String classtype="";

    public void setgraphname(String graphname){
        this.graphname=graphname;
    }
    public String getgraphname() {
        return graphname;
    }

    public void setclasstype(String classtype){
        this.classtype=classtype;
    }
    public String getclasstype() {
        return classtype;
    }
    public void setgraphid(int graphid)
    {
        this.graphid=graphid;
    }
    public int getgraphid()
    {
        return this.graphid;
    }

    public void setuserid(String userid)
    {
        this.userid=userid;
    }
    public String getuserid()
    {
        return this.userid;
    }

    public void setcomment(String comment)
    {
        this.comment=comment;
    }
    public String getcomment()
    {
        return this.comment;
    }

    public void setlooked(int looked)
    {
        this.looked=looked;
    }
    public int getlooked()
    {
        return this.looked;
    }

    public void setteaid(String teaid)
    {
        this.teaid=teaid;
    }
    public String getteaid()
    {
        return this.teaid;
    }
}