package com.sbvadmin.config;
/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2024/4/15 10:52
 */
import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.RandomUtil;
import com.jayway.jsonpath.Criteria;
import com.sbvadmin.model.User;
import com.sbvadmin.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SmsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    RedisTemplate redisTemplate;

    private UserDetailsService userDetailsService;
    protected UserDetailsService getUserDetailsService() {
        return this.userDetailsService;
    }
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        try {
            UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username);
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
            return loadedUser;
        }
        catch (UsernameNotFoundException ex) {
            if(registerPhoneUser(username,authentication)){ // 判断是否要直接注册一个新用户
                UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username);
                return loadedUser;
            }else{
                throw ex;
            }
        }
        catch (InternalAuthenticationServiceException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Notes:  判断是否要直接注册一个新用户
     * @param: [username, authentication]
     * @return: void
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2024/4/16 15:45
     **/
    private boolean registerPhoneUser(String username, UsernamePasswordAuthenticationToken authentication){
        boolean isValid = PhoneUtil.isMobile(username);
        if (isValid){
            Object codeInCache = redisTemplate.opsForValue().get(username); // 短信生成的验证码
            if (codeInCache == null){
                return false;
            }
            String presentedCode = authentication.getCredentials().toString();
            if (!codeInCache.toString().equals(presentedCode)){
                return false;
            }
            // 直接注册一个新用户
            User user = new User();
            user.setUsername(username);
            user.setNickname(username);
            user.setAvatar("avatar.png");
            user.setRoleIds(Arrays.asList(3L)); // 普通用户
            user.setDeptIds(Arrays.asList(1L)); // 默认机构
            String passwordRaw = RandomUtil.randomString(8);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 密码加密
            user.setPassword(passwordEncoder.encode(passwordRaw));
            this.userService.save(user);
            return true;
        }
        return false;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            this.logger.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        String presentedCode = authentication.getCredentials().toString(); // 待匹配的验证码
        Object codeInCache = redisTemplate.opsForValue().get(userDetails.getUsername()); // 短信生成的验证码
        if (codeInCache == null){
            this.logger.info("验证码已经失效");
            throw new BadCredentialsException("验证码已经失效");
        }
        if (!codeInCache.toString().equals(presentedCode)){
            this.logger.info("验证码错误");
            throw new BadCredentialsException("验证码错误");
        }
    }
}