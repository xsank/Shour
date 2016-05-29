package org.nerdboy.chatbot.server;

import org.nerdboy.chatbot.Shour;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xsank.mz on 2016/5/29.
 */
public class QAServlet extends HttpServlet{

    private Shour shour;

    public QAServlet(Shour shour){
        this.shour=shour;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String question=request.getParameter("question");
        String answer=shour.response(question);
        reply(answer,response);
    }

    private void reply(String result,HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer=response.getWriter();
        writer.write(result);
    }
}
