package com.felipiberdun.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.felipiberdun.order.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public class Token {

    private static final String BEARER_KEYWORD = "Bearer";
    private static final String PRIVATE_KEY = "S3cr3t-Key";
    private static final String CUSTOMER_ID = "customerId";
    private static final int TOKEN_GROUP = 2;

    private static final Pattern TOKEN_PATTERN = Pattern.compile("(" + BEARER_KEYWORD + " )(.+)");

    @JsonProperty("token_type")
    private String tokenType = BEARER_KEYWORD.toLowerCase();
    @JsonProperty("access_token")
    private String token;

    @JsonIgnore
    private UUID id;
    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonIgnore
    private LocalDateTime expiresAt;
    @JsonIgnore
    private Long customerId;

    private Token(final Long customerId,
                  final long tokenDuration) {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now().withNano(0);
        this.expiresAt = tokenDuration > 0L ? createdAt.plusSeconds(tokenDuration) : null;
        this.customerId = customerId;
        this.token = generateToken(this.id.toString(),
                Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant()),
                Optional.ofNullable(expiresAt).map(localDateTime -> Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())).orElse(null));

    }

    private Token(final UUID id,
                  final LocalDateTime createdAt,
                  final LocalDateTime expiresAt,
                  final Long customerId,
                  final String token) {
        this.id = id;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.customerId = customerId;
        this.token = token;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public Long getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return this.token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public static Token parse(final String authorization) {
        final String extractedToken = extractToken(authorization);

        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(PRIVATE_KEY)
                    .parseClaimsJws(Optional.ofNullable(extractedToken).orElseThrow(InvalidTokenException::new))
                    .getBody();

            final UUID id = UUID.fromString(Optional.ofNullable(claims.getId()).orElseThrow(InvalidTokenException::new));
            final LocalDateTime createdAt = LocalDateTime.ofInstant(Optional.ofNullable(claims.getIssuedAt()).orElseThrow(InvalidTokenException::new).toInstant(), ZoneId.systemDefault());
            final LocalDateTime expiresAt = claims.getExpiration() == null ? null : LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());
            final Long customerId = Long.valueOf(Optional.ofNullable(claims.get(CUSTOMER_ID, String.class)).orElseThrow(InvalidTokenException::new));

            return new Token(id, createdAt, expiresAt, customerId, extractedToken);
        } catch (RuntimeException ex) {
            throw new InvalidTokenException();
        }
    }

    private static String extractToken(final String authorizationHeader) {
        return Optional.ofNullable(authorizationHeader)
                .filter(StringUtils::isNotBlank)
                .map(TOKEN_PATTERN::matcher)
                .filter(Matcher::find)
                .map(matcher -> matcher.group(TOKEN_GROUP))
                .orElseThrow(InvalidTokenException::new);
    }

    private String generateToken(final String tokenId, final Date issuedAt, final Date expiresAt) {
        return Jwts.builder()
                .claim(CUSTOMER_ID, this.customerId)
                .setId(tokenId)
                .setIssuedAt(issuedAt)
                .setNotBefore(issuedAt)
                .setExpiration(expiresAt)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(SignatureAlgorithm.HS256, PRIVATE_KEY)
                .compact();
    }

    public static TokenBuilder of(final Long customerId) {
        return new TokenBuilder(customerId);
    }

    public static class TokenBuilder {

        private static final long AUTHENTICATION_TOKEN_DURATION = 365 * 24 * 60 * 60;
        private Long customerId;

        public TokenBuilder(final Long customerId) {
            this.customerId = customerId;
        }

        public Token buildAuthenticationToken() {
            return new Token(customerId, AUTHENTICATION_TOKEN_DURATION);
        }

    }

}
