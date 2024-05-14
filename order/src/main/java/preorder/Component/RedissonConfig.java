package preorder.Component;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RedissonConfig {

	@Bean(destroyMethod = "shutdown")
	public RedissonClient redissonClient() throws IOException {
		// Redisson 설정 파일을 읽어 설정 객체를 만듭니다.
		Config config = Config.fromYAML(getClass().getClassLoader().getResource("secret.yaml"));
		// Redisson 클라이언트를 생성합니다.
		return Redisson.create(config);
	}
}