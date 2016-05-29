package org.nerdboy.chatbot.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.nerdboy.chatbot.Shour;

/**
 * Created by xsank.mz on 2016/5/19.
 */
public class QAServer {

    private static int DEFAUTL_PORT = 8888;

    private Server server;
    private WebAppContext context;
    private boolean isInit = false;

    public QAServer(String port) {
        this(Integer.parseInt(port));
    }

    public QAServer(int port) {
        server = new Server(port);
        init();
    }

    private void init() {
        context = buildContext();
        server.setHandler(context);
        isInit = true;
    }

    public void start(Shour shour) {
        if (isInit) {
            try {
                context.addServlet(new ServletHolder(new QAServlet(shour)),"/shour");
                server.start();
                server.join();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                isInit = false;
            }
        }
    }

    private WebAppContext buildContext() {
        WebAppContext context = new WebAppContext();
        context.setDefaultsDescriptor("webapp/WEB-INF/webdefault.xml");
        context.setDescriptor("webapp/WEB-INF/web.xml");
        context.setResourceBase("webapp/");
        context.setContextPath("/");
        return context;
    }

    public QAServer() {
        this(DEFAUTL_PORT);
    }

    public void destroy() {
        this.server.destroy();
        this.isInit = false;
    }
}
