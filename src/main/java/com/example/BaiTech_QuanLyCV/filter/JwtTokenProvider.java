//package com.example.BaiTech_QuanLyCV.filter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//public class JwtTokenProvider {
//
//    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(JwtTokenProvider.class);
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expirationMs}")
//    private int expirationMs;
//
//    @Value("${jwt.tokenPrefix}")
//    private String tokenPrefix;
//
//    @Value("${jwt.headerString}")
//    private String headerString;
//
////    Tạo token:
//    public String generateToken(Authentication authentication) {
//        String username = authentication.getName();
//
//        Date currentDate = new Date();
//        Date expireDate = new Date(currentDate.getTime() + expirationMs);
//
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(expireDate)
//                .signWith(key())
//                .compact();
//
//        return token;
//    }
//
//    private Key key() {
//        return Keys.hmacShaKeyFor(
//                Decoders.BASE64.decode(secret)
//        );
//    }
//
//
////    Trích xuất thông tin:
//    public String getUsername(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(key())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        String username = claims.getSubject();
//
//        return username;
//    }
//
////    Xác thực token:
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(key())
//                    .build()
//                    .parse(token);
//            return true;
//        } catch (Exception e) {
//            logger.error("Invalid JWT token: {}", e.getMessage());
//            return false;
//        }
//    }
//
//}
