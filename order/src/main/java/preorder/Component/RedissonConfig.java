package preorder.Component;

import org.redisson.api.RedissonClient;
import org.redisson.api.RLock;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.redisson.spring.starter.RedissonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

	@Bean(destroyMethod = "shutdown")
	public RedissonClient redisson(RedissonProperties redissonProperties) throws IOException {
		return RedissonAutoConfiguration.redisson(redissonProperties);
	}
}