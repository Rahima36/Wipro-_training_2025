package com.wipro.user_management.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.wipro.user_management.dto.Token;
import com.wipro.user_management.entity.User;
import com.wipro.user_management.repo.UserRepo;
import com.wipro.user_management.service.UserService;
import com.wipro.user_management.util.AppConstant;
import com.wipro.user_management.util.EncryptUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User findById(int id) {
		Optional<User> userOpt= userRepo.findById(id);
		return userOpt.orElse(null);
	}

	@Override
	public void save(User user) {
		userRepo.save(user);
	}

	@Override
	public void deleteById(int id) {
		userRepo.deleteById(id);
	}

	@Override
	public Token login(User user) {
        User userSalt = userRepo.findByEmail((String) user.getEmail());

        // Check if a user was found before trying to access its properties
        if (userSalt == null) {
            return null; // Return null if user not found
        }
		
        // Now you can safely call methods on userSalt
        String encrypTestPassword = EncryptUtil.getEncryptedPassword(user.getPassWord(), userSalt.getSalt());

        // You also have a potential bug here:
        // You are searching for a user with the password provided by the user,
        // which is not encrypted. This should be fixed.
        // It should be: userRepo.findByEmailAndPassWord(user.getEmail(), encrypTestPassword);
        User userData = userRepo.findByEmailAndPassWord((String)user.getEmail(), encrypTestPassword);
        
        if (userData != null) {
            String userId = String.valueOf(userData.getId());
            String jwtToken = "Bearer " + getJWTToken(userId);
            System.out.println("token=" + jwtToken);
            Token token = new Token();
            token.setToken(jwtToken);
            return token;
        }
        return null;
	}
	
	private String getJWTToken(String username) {
        Key key = Keys.hmacShaKeyFor(AppConstant.SECRET.getBytes(StandardCharsets.UTF_8));
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        return Jwts.builder()
                .setId("jwtExample")
                .setSubject(username)
                .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
