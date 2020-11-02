package co.edu.eam.sistemasdistribuidos.catalog.config;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

    //establece la conexion con redis
    @Bean
    JedisConnectionFactory jedisConnectionFactory(){return jedisConnectionFactory();}

    @Bean
    public RedisTemplate<String, String> redisTemplate(){
        //crear template de redis y manda o guarda datos en este
        RedisTemplate<String, String> Template = new RedisTemplate<>();
        Template.setKeySerializer(new StringRedisSerializer());
        Template.setValueSerializer(new StringRedisSerializer());

        //se conecta y manda datos a redis
        Template.setConnectionFactory(jedisConnectionFactory());

        return Template;
    }
}
