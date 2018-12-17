package me.test.springboottryit.redis;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Slf4j
public class RedisUtil {

    @Setter
    private RedisTemplate<String, Object> redisTemplate;

    private static final String EXCEPTION__DEFAULT_MESSAGE = "RedisUtil execute failed";

    /**
     * 指定缓存失效时间
     *
     * @param key
     * @param expire
     * @param unit
     * @return
     */
    public boolean expire(String key, long expire, TimeUnit unit) {
        try {
            return redisTemplate.expire(key, expire, unit);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false 不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key    键
     * @param value  值
     * @param expire 时间 time要大于0 如果time小于等于0 将设置无限期
     * @param unit   时间单位
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long expire, TimeUnit unit) {
        try {
            if (expire > 0) {
                redisTemplate.opsForValue().set(key, value, expire, unit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key       键
     * @param increment 要增加几(大于0)
     * @return
     */
    public long incrBy(String key, long increment) {
        if (increment < 0) {
            throw new IllegalArgumentException("increment [" + increment + "] must > 0");
        }
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 递减
     *
     * @param key       键
     * @param increment 要减少几(小于0)
     * @return
     */
    public long decrBy(String key, long increment) {
        if (increment < 0) {
            throw new IllegalArgumentException("increment [" + increment + "] must > 0");
        }
        return redisTemplate.opsForValue().increment(key, -increment);
    }

    //================================Map=================================

    /**
     * HashGet
     *
     * @param key   键 不能为null
     * @param field 项 不能为null
     * @return 值
     */
    public Object hget(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key    键
     * @param field  项
     * @param value  值
     * @param expire 时间 注意:如果已存在的hash表有时间,这里将会替换原有的时间，且redis只支持在key上设置超时，不能再field上设置超时
     * @return true 成功 false失败
     */
    public boolean hset(String key, String field, Object value, long expire, TimeUnit unit) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
            if (expire > 0) {
                expire(key, expire, unit);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key    键
     * @param map    对应多个键值
     * @param expire 时间
     * @param unit   时间单位
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long expire, TimeUnit unit) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (expire > 0) {
                expire(key, expire, unit);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sMembers(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sExists(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sAdd(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param expire 时间
     * @param unit   时间单位
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sAdd(String key, long expire, TimeUnit unit, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (expire > 0) expire(key, expire, unit);
            return count;
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long sSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long sRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return 0;
        }
    }

    /**
     * 随机从set中选取一个元素
     *
     * @param key
     * @return
     */
    public Object sRandomMember(String key) {
        try {
            return redisTemplate.opsForSet().randomMember(key);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return null;
        }
    }

    /**
     * 随机选取set元素
     *
     * @param key
     * @param count
     * @return
     */
    public List<Object> sRandomMembers(String key, long count) {
        try {
            return redisTemplate.opsForSet().randomMembers(key, count);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * 随机从set中选取不同元素
     *
     * @param key
     * @param count
     * @return
     */
    public Set<Object> sDistinctRandomMembers(String key, long count) {
        try {
            return redisTemplate.opsForSet().distinctRandomMembers(key, count);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return Collections.EMPTY_SET;
        }
    }

    /**
     * 随机从set中弹出一个元素
     *
     * @param key
     * @return
     */
    public Object sRandomPop(String key) {
        try {
            return redisTemplate.opsForSet().pop(key);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return null;
        }
    }

    /**
     * 随机从set中弹出若干元素
     *
     * @param key
     * @param count
     * @return
     */
    public List<Object> sRandomPop(String key, long count) {
        try {
            return redisTemplate.opsForSet().pop(key, count);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return Collections.EMPTY_LIST;
        }
    }


    //===============================list=================================

    /**
     * 弹出缓存list最左元素
     *
     * @param key
     * @return
     */
    public Object lPop(String key) {
        try {
            return redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return null;
        }
    }

    /**
     * 弹出缓存list最左元素
     * 若没有元素可弹出，则等待一定超时时间（阻塞操作）
     *
     * @param key
     * @param waitTimeout
     * @param unit
     * @return
     */
    public Object lPop(String key, long waitTimeout, TimeUnit unit) {
        try {
            return redisTemplate.opsForList().leftPop(key, waitTimeout, unit);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return null;
        }
    }

    /**
     * 获取list缓存最左元素（不弹出）
     *
     * @param key
     * @return
     */
    public Object lGet(String key) {
        return lGetIndex(key, 0);
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long listSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lPush(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key    键
     * @param value  值
     * @param expire 时间
     * @param unit   时间单位
     * @return
     */
    public boolean lPush(String key, Object value, long expire, TimeUnit unit) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (expire > 0) expire(key, expire, unit);
            return true;
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lMultiPush(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            log.error(EXCEPTION__DEFAULT_MESSAGE, e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key    键
     * @param value  值
     * @param expire 时间
     * @param unit   时间单位
     * @return
     */
    public boolean lMultiPush(String key, List<Object> value, long expire, TimeUnit unit) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (expire > 0) expire(key, expire, unit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}
