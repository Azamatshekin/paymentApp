package kg.azamatshekin.payment.service;

import kg.azamatshekin.payment.utils.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> template;

    public RedisService(
    ) {
    }

    public void checkBrute(String username) throws BusinessException {
        String blocked_key = "blocked:" + username;
        String value  = template.opsForValue().get(blocked_key);
        if (null != value){
            throw new BusinessException("You are blocked for 20 min!");
        }
    }

    private void blockBrute(String username) throws BusinessException {
        String blocked_key = "blocked:" + username;
        template.opsForValue().set(blocked_key, "blocked");
        template.expire(blocked_key, 20, TimeUnit.MINUTES);
        throw new BusinessException("You are blocked for 20 min!");
    }

    public void bruteTrial(String username) throws  BusinessException {
        String value  = template.opsForValue().get(username);
        if (null == value){
            template.opsForValue().set(username, "1");
        }else{
            int trial_count = Integer.parseInt(value) + 1;
            if (trial_count >= 5) {
                blockBrute(username);
            }
            value = Integer.toString( trial_count);
            template.opsForValue().set(username, value);
        }
        template.expire(username, 10, TimeUnit.MINUTES);
    }



}
