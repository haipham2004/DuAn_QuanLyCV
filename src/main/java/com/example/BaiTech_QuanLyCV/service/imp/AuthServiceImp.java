//package com.example.BaiTech_QuanLyCV.service.imp;
//
//import com.example.BaiTech_QuanLyCV.dto.JwtAuthResponse;
//import com.example.BaiTech_QuanLyCV.dto.LoginDTO;
//import com.example.BaiTech_QuanLyCV.dto.RegisterDto;
//import com.example.BaiTech_QuanLyCV.entity.Account;
//import com.example.BaiTech_QuanLyCV.entity.Roles;
//import com.example.BaiTech_QuanLyCV.filter.JwtTokenProvider;
//import com.example.BaiTech_QuanLyCV.repository.AccountRepository;
//import com.example.BaiTech_QuanLyCV.repository.RoleRepository;
//import com.example.BaiTech_QuanLyCV.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class AuthServiceImp implements AuthService {
//
//    private AccountRepository accountRepository;
//
//    private RoleRepository repository;
//
//    private PasswordEncoder passwordEncoder;
//
//    private AuthenticationManager authenticationManager;
//
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    public AuthServiceImp(AccountRepository accountRepository, RoleRepository repository,
//                          PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
//        this.accountRepository = accountRepository;
//        this.repository = repository;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Override
//    public String register(RegisterDto registerDto) {
//        return null;
//    }
//
//    @Override
//    public JwtAuthResponse login(LoginDTO loginDto) {
//
//        // Xác thực thông tin đăng nhập
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginDto.getUsernameOrEmail(),
//                        loginDto.getPassword()
//                )
//        );
//
//        // Đặt thông tin xác thực vào SecurityContextHolder
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Tạo token JWT
//        String token = jwtTokenProvider.generateToken(authentication);
//
//        // Tìm kiếm tài khoản dựa trên username hoặc email
//        Optional<Account> users = accountRepository.findByUsernameOrEmail(
//                loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail()
//        );
//
//        // Biến để lưu danh sách vai trò
//        String roles = null;
//
//        if (users.isPresent()) {
//            Account userLoggin = users.get();
//
//            // Lấy tất cả các vai trò của người dùng và ghép chúng lại
//            roles = userLoggin.getRoles().stream()
//                    .map(Roles::getAuthority)
//                    .collect(Collectors.joining(","));
//        } else {
//            // Xử lý khi không tìm thấy tài khoản
//            throw new UsernameNotFoundException("User not found with username or email: " + loginDto.getUsernameOrEmail());
//        }
//
//        // Tạo phản hồi cho người dùng với token và danh sách vai trò
//        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
//        jwtAuthResponse.setRoles(roles);
//        jwtAuthResponse.setAccessToken(token);
//        return jwtAuthResponse;
//    }
//
//
//
//}
//
