/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.oauth2.server.authorization.authentication;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.Version;
import org.springframework.util.Assert;

import java.util.Collections;

/**
 * An {@link Authentication} implementation used for OAuth 2.0 Token Revocation.
 *
 * @author Vivek Babu
 * @author Joe Grandja
 * @see AbstractAuthenticationToken
 * @see OAuth2TokenRevocationAuthenticationProvider
 * @since 0.0.3
 */
public class OAuth2TokenRevocationAuthenticationToken extends AbstractAuthenticationToken {
	private static final long serialVersionUID = Version.SERIAL_VERSION_UID;
	private final String token;
	private final Authentication clientPrincipal;
	private final String tokenTypeHint;

	/**
	 * Constructs an {@code OAuth2TokenRevocationAuthenticationToken} using the provided parameters.
	 *
	 * @param token           the token
	 * @param clientPrincipal the authenticated client principal
	 * @param tokenTypeHint   the token type hint
	 */
	public OAuth2TokenRevocationAuthenticationToken(String token,
	                                                Authentication clientPrincipal, @Nullable String tokenTypeHint) {
		super(Collections.emptyList());
		Assert.hasText(token, "token cannot be empty");
		Assert.notNull(clientPrincipal, "clientPrincipal cannot be null");
		this.token = token;
		this.clientPrincipal = clientPrincipal;
		this.tokenTypeHint = tokenTypeHint;
	}

	/**
	 * Constructs an {@code OAuth2TokenRevocationAuthenticationToken} using the provided parameters.
	 *
	 * @param revokedToken    the revoked token
	 * @param clientPrincipal the authenticated client principal
	 */
	public OAuth2TokenRevocationAuthenticationToken(OAuth2Token revokedToken,
	                                                Authentication clientPrincipal) {
		super(Collections.emptyList());
		Assert.notNull(revokedToken, "revokedToken cannot be null");
		Assert.notNull(clientPrincipal, "clientPrincipal cannot be null");
		this.token = revokedToken.getTokenValue();
		this.clientPrincipal = clientPrincipal;
		this.tokenTypeHint = null;
		setAuthenticated(true);    // Indicates that the token was authenticated and revoked
	}

	@Override
	public Object getPrincipal() {
		return this.clientPrincipal;
	}

	@Override
	public Object getCredentials() {
		return "";
	}

	/**
	 * Returns the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * Returns the token type hint.
	 *
	 * @return the token type hint
	 */
	@Nullable
	public String getTokenTypeHint() {
		return this.tokenTypeHint;
	}
}
