package com.lp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @auther lp
 * @date 2020/6/20 0020 14:20
 */
public class RpcNetTransport {
    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public Socket newScoket() throws IOException {
        Socket socket = new Socket(host,port);
        return  socket;
    }
    public Object send(RpcRequest rpcRequest) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            Socket socket = newScoket();
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
         return objectInputStream.readObject();
    }
}
