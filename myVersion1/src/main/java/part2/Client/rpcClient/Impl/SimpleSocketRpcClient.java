package part2.Client.rpcClient.Impl;

import lombok.AllArgsConstructor;
import part2.Client.rpcClient.RpcClient;
import part2.common.Message.RpcRequest;
import part2.common.Message.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@AllArgsConstructor
public class SimpleSocketRpcClient implements RpcClient {
    private String host;
    private int port;
    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        try{
            Socket socket = new Socket(host,port);
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());

            oos.writeObject(request);
            oos.flush();

            RpcResponse response=(RpcResponse) ois.readObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}
