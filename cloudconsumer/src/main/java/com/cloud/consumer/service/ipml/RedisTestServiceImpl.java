package com.cloud.consumer.service.ipml;

import com.cloud.consumer.service.RedisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisTestServiceImpl implements RedisTestService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    //Redis新增测试
    @Override
    public int redisAdd(String add) {
        try {
            //设置当前的key以及value值
            redisTemplate.opsForValue().set("333", "333");
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    //Redis获取值测试
    @Override
    public String redisGet(String key) {
        String s = redisTemplate.opsForValue().get(key);
        return s;
    }

    //判断是否有key所对应的值，有则返回true，没有则返回false  --》  redisTemplate.hasKey(key)
    //删除单个key值--》redisTemplate.delete(key)
    //批量删除key--》redisTemplate.delete(keys) --》 其中keys:Collection<K> keys
    //将当前传入的key值序列化为byte[]类型 --》redisTemplate.dump(key)


    //设置过期时间
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    //查找匹配的key值，返回一个Set集合类型
    public Set<String> getPatternKey(String pattern) {
        return redisTemplate.keys(pattern);
    }

    //修改redis中key的名称
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    //返回传入key所存储的值的类型
    public DataType getKeyType(String key) {
        return redisTemplate.type(key);
    }


    //返回剩余过期时间并且指定时间单位
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    //将key持久化保存
    public Boolean persistKey(String key) {
        return redisTemplate.persist(key);
    }

    //在原有的值基础上新增字符串到末尾
    //redisTemplate.opsForValue().append(key, value);


    //redssion分布式锁
    public String miaosh() {
        String lockkey = "商品ID";
        //意思就是每个线程进来的时候都会产生一个唯一的UUID值，作为下面锁对应的值
        String clientId= UUID.randomUUID().toString();
        try {
            /*这两步当中可能存在有一个设置失败的情况发生所以下面还有个优化后成一个原子的方法*/
            //redisTemplate.opsForValue().set(lockkey,"石头");//设置key的值为--》石头
            //redisTemplate.expire(lockkey,10,TimeUnit.SECONDS);//设置key的过期时间为10秒
            /*这两步当中可能存在有一个设置失败的情况发生所以下面还有个优化后成一个原子的方法*/

            /* 根据上面两步的优化成一个原子*/
            //clientId将这个锁lockkey设置一个唯一的值，后面判断是这个对应的锁才能删除
            Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(lockkey, clientId, 10, TimeUnit.SECONDS);
            /*根据上面两步的优化成一个原子*/
            if (!aBoolean) {
                return "当前抢购人数过多，请稍后再试！";
            }

            //获取redis中存储的商品的数量
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                //秒杀一次商品减1；
                int realStore = stock - 1;
                //将减少后的真实的商品数量存回到redis中，这里realStore因为是int类型所以拼接了一个空字符串
                redisTemplate.opsForValue().set("stock", realStore + "");
                System.out.println("扣减成功，剩余库存：" + realStore + "");
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
           //释放锁,意思唯一的clientId对应上了就删除加的这把锁
            if (clientId.equals(redisTemplate.opsForValue().get(lockkey))){
                redisTemplate.delete(lockkey);
            }
        }
        return "抢购成功！";
    }


}
