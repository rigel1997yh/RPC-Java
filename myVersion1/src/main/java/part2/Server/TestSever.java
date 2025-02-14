package part2.Server;

import part1.Server.provider.ServiceProvider;
import part1.Server.server.Impl.SimpleRPCRPCServer;
import part1.Server.server.RpcServer;
import part1.common.service.Impl.UserServiceImpl;
import part1.common.service.UserService;

public class TestSever {
    public static void main(String[] args) {
        UserService userService =new UserServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);

        RpcServer rpcServer=new SimpleRPCRPCServer(serviceProvider);
        rpcServer.start(9999);
    }
}
