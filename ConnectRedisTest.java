package connect_redis;


import java.util.HashMap;
import java.util.Map;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.cluster.api.async.RedisClusterAsyncCommands;

public class ConnectRedisTest {

	public static void main(String[] args) {
		RedisClient client = RedisClient.create("redis://192.168.10.204");

        // connection, 线程安全的长连接，连接丢失时会自动重连，直到调用 close 关闭连接。
        StatefulRedisConnection<String, String> connection = client.connect();

        // sync, 默认超时时间为 60s. 
        RedisClusterAsyncCommands<String, String> sync = connection.async();
        
        Map<String, String> map = new HashMap<String, String>();
        
        map.put("id", "100");
        map.put("name", "yqc");
        
        
        
        sync.hmset("user", map);
        
        
        connection.close();
        
        client.shutdown();
	}
}
