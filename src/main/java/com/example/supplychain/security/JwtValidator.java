package com.example.supplychain.security;

import com.example.supplychain.exception.SupplyChainException;
import com.example.supplychain.model.UserLoginDetails;
import com.example.supplychain.repository.UserDetailsRepository;
import com.example.supplychain.utils.CommonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JwtValidator {
    private static final Logger LOG = LoggerFactory.getLogger(JwtValidator.class.getName());

    @Value("${jwt-secret}")
    private String SECRET;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserLoginDetails validate(String token) throws SupplyChainException {
       try {
           Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
           LOG.error(body.toString());

           if(body.getExpiration().before(new Date())) {
               throw new SupplyChainException("JWT Expired.");
           }

           String userId = (String) body.getSubject();
           if(CommonUtils.isNull(userId)) {
               throw new SupplyChainException("user details not valid");
           }
           UserLoginDetails userLoginDetails = userDetailsRepository.getById(Long.parseLong(userId));
            if(CommonUtils.isNull(userLoginDetails)) {
               throw new SupplyChainException("user details not valid");
           }

            if(!token.equals(userLoginDetails.getAccessToken())) {
                throw new SupplyChainException("something is not right");
            }

            return userLoginDetails;
       } catch (Exception e) {
            e.printStackTrace();
            throw new SupplyChainException("session logged-out. please try again.");
       }
    }


}
