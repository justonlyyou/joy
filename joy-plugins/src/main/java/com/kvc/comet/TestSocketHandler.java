package com.kvc.comet;

import com.kvc.joy.plugin.message.comet.core.PushException;
import com.kvc.joy.plugin.message.comet.core.Socket;
import com.kvc.joy.plugin.message.comet.core.SocketHandler;
import com.kvc.joy.plugin.message.comet.listener.SocketEvent;
import com.kvc.joy.plugin.message.comet.listener.SocketListener;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Kevice
 * @time 14-3-6 下午11:33
 * @since 1.0.0
 */
@Service("testComet")
public class TestSocketHandler implements SocketHandler {

    public boolean accept(final Socket socket, HttpServletRequest request) {
        Map<String,String[]> parameterMap = request.getParameterMap();
        String userId = request.getParameter("userId");
        System.out.println("userId: "+userId);

        socket.addListener(new SocketListener() {
            @Override
            public void onReallyClose(SocketEvent event) {
                System.out.println("onReallyClose");
            }

            @Override
            public void onTimeout(SocketEvent event) {
                System.out.println("onTimeout");
            }

            @Override
            public void onError(PushException event) {
                System.out.println("onError");
                event.printStackTrace();
            }
        });

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (socket.isClosed() == false) {
                    socket.send("kkkkkk");
                }
            }
        };
        new Timer().schedule(task, 3000, 10000);

        return true;
    }

    public void quit(Socket socket, HttpServletRequest request) {
        String userId = request.getParameter("userId");
        System.out.println("userId: "+userId);
        System.out.println("################# quit");
        return;
    }


}
