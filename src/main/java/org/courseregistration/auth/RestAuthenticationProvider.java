package org.courseregistration.auth;

import org.courseregistration.dao.UserDAO;
import org.courseregistration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDAO userDAO;

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            RestToken restToken = (RestToken) authentication;

            String key = decodeUsingBase64(restToken.getKey());
            String credentials = decodeUsingBase64(restToken.getCredentials());

            User user = userDAO.findByCollegeId(key);

            if(user !=null){
                if(decodeUsingBase64(user.getHashedPassword()).equals(credentials)){
                    getAuthenticatedUser(key, credentials);
                }else{
                    throw new BadCredentialsException("Enter a username and password");
                }
            }else{
               throw new UsernameNotFoundException("User with given Id not found");
            }

            return getAuthenticatedUser(key, credentials);
        }

        private Authentication getAuthenticatedUser(String key, String credentials) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new RestToken(key, credentials, authorities);
        }

        @Override
    /*
        Determines if this class can support the token provided by the filter.
     */
        public boolean supports(Class<?> authentication) {
            return RestToken.class.equals(authentication);
        }

    public String decodeUsingBase64(String toDecode) {
        try {
            byte[] decoded = Base64.decode(toDecode.getBytes("UTF-8"));
            return new String(decoded);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}

