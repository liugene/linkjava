package app.index.controller;

import frame.linkjava.boot.handler.Do;
import frame.util.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index implements Do
{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "success";
    }

//    public void index()
//    {
//        Function.echo("linkjava");
//    }
}
