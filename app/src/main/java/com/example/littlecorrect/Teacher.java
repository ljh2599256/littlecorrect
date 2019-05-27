package com.example.littlecorrect;
public class Teacher {
    private  String teaid;
    private  String teaname;
    private  String password;
    private  String teaschool;
    private  String birthday;
    private  String score;
    private  String classtype;
    public void setclasstype(String classtype) {
        this.classtype = classtype;
    }
    public String getclasstype() {
        return classtype;
    }
    public void setteaname(String teaname)
    {
        this.teaname=teaname;
    }
    public String getteaname()
    {
        return this.teaname;
    }

    public void setpassword(String password)
    {
        this.password=password;
    }
    public String getpassword()
    {
        return this.password;
    }

    public void setteaschool(String teaschool)
    {
        this.teaschool=teaschool;
    }
    public String getteaschool()
    {
        return this.teaschool;
    }

    public void setbirthday(String birthday)
    {
        this.birthday=birthday;
    }
    public String getbirthday()
    {
        return this.birthday;
    }

    public void setteaid(String teaid)
    {
        this.teaid=teaid;
    }
    public String getteaid()
    {
        return this.teaid;
    }

    public void setscore(String score)
    {
        this.score=score;
    }
    public String getscore()
    {
        return this.score;
    }

}