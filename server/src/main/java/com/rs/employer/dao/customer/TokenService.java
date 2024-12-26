package com.rs.employer.dao.customer;

import com.rs.employer.service.EmailService;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.customer.Token;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenService {
    public final GenerateTokenService generateTokenService;
    public final TokenRepository  tokenRepository;
    private final EmailService emailService;
    private final CustomerRepo customerRepo;

    public TokenService(GenerateTokenService generateTokenService , TokenRepository tokenRepository, EmailService emailService, CustomerRepo customerRepo) {
        this.generateTokenService = generateTokenService;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.customerRepo = customerRepo;
    }
    public String checkTokenAndRegenerateToken(String email) {
        Token tk = tokenRepository.findTokenByCustomerEmailAndUsed(email,false).orElse(null);
        if (tk == null || tk.isUsed() || tk.getExpireDate().isBefore(LocalDateTime.now())) {
            return generateTokenAndSave(email);
        }
        tk.setUsed(true);
        tokenRepository.save(tk);
        return generateTokenAndSave(email);
    }

    private String generateTokenAndSave(String email) {
        String newToken;
        do {
            newToken = generateTokenService.generateToken();
        } while (tokenRepository.existsByToken(newToken));

        Token newResetToken = new Token();
        newResetToken.setToken(newToken);
        newResetToken.setExpireDate(LocalDateTime.now().plusMinutes(15));
        newResetToken.setUsed(false);
        newResetToken.setCustomer(customerRepo.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
        tokenRepository.save(newResetToken);

        return newToken;
    }
    public Boolean compareToken(String token, String email) {
        Optional<Token> optionalToken = tokenRepository.findTokenByCustomerEmailAndUsed(email,false);
        if (optionalToken.isEmpty()) {
            return false;
        }
        if(optionalToken.get().getToken().equals(token)) {
            optionalToken.get().setUsed(true);
            tokenRepository.save(optionalToken.get());
        }
        return true;
    }

    @Scheduled(fixedRate = 864000000)
    public void deleteExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        tokenRepository.deleteByExpireDateBefore(now);
    }
}
