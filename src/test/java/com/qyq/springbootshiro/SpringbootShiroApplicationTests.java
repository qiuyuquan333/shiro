package com.qyq.springbootshiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootShiroApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void RedisTest() {
//        redisTemplate.opsForValue().set("Test:test","helo,Redis11111111111111111111");
//        redisTemplate.expire("Test:test",1, TimeUnit.SECONDS);
//        System.out.println( redisTemplate.opsForValue().get("Test:test"));

        redisTemplate.opsForValue().set("中文", "还是中文11");
        System.out.println(redisTemplate.opsForValue().get("中文"));
    }

    @Test
    public void Test1() {
        int hashIterations = 10000;//加密的次数
        Object salt = "zhao";//盐值
        Object credentials = "123456";//密码
        String hashAlgorithmName = "MD5";//加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
                salt, hashIterations);
        System.out.println("加密后的值----->" + simpleHash);

    }


    /**
     * 二进制转换成16进制，加密后的字节数组不能直接转换为字符串
     */
    static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 16进制转换成二进制
     */
    static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
