package part1.Server.provider;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    //集合中存放服务的实例
    private Map<String,Object> interfaceProvider;

    public ServiceProvider(){ this.interfaceProvider = new HashMap<>();}

    public void provideServiceInterface(Object service){
        String serviceName = service.getClass().getName();
        Class<?>[] interfaceName = service.getClass().getInterfaces();

        for (Class<?> clazz:interfaceName){
            interfaceProvider.put(clazz.getName(),service);
        }
    }

    public Object getService(String interfaceName){ return  interfaceProvider.get(interfaceName);}
}
