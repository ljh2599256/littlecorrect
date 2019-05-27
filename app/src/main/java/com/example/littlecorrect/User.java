package com.example.littlecorrect;

public class User {
    private  String id;
    private  String name;
    private  String password;
    private  String school;
    private  String birthday;
    private  String interest;

    public void setname(String name)
    {
        this.name=name;
    }
    public String getname()
    {
        return this.name;
    }

    public void setpassword(String password)
    {
        this.password=password;
    }
    public String getpassword()
    {
        return this.password;
    }

    public void setschool(String school)
    {
        this.school=school;
    }
    public String getschool()
    {
        return this.school;
    }

    public void setbirthday(String birthday)
    {
        this.birthday=birthday;
    }
    public String getbirthday()
    {
        return this.birthday;
    }

    public void setid(String id)
    {
        this.id=id;
    }
    public String getid()
    {
        return this.id;
    }

    public void setinterest(String interest)
    {
        this.interest=interest;
    }
    public String getinterest()
    {
        return this.interest;
    }

}