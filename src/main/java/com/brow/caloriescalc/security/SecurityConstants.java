package com.brow.caloriescalc.security;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class SecurityConstants {

    public final static Long JWT_EXPIRATION = 6000000L;

    public final static SecretKey JWT_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS512);
}
