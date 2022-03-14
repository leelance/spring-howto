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
package org.springframework.security.oauth2.core;

import java.time.Instant;

/**
 * An implementation of an {@link AbstractOAuth2Token}
 * representing an OAuth 2.0 Authorization Code Grant.
 *
 * @author Joe Grandja
 * @see AbstractOAuth2Token
 * @see <a target="_blank" href="https://tools.ietf.org/html/rfc6749#section-4.1">Section 4.1 Authorization Code Grant</a>
 * @since 0.0.3
 */
public class OAuth2AuthorizationCode extends AbstractOAuth2Token {

	/**
	 * Constructs an {@code OAuth2AuthorizationCode} using the provided parameters.
	 *
	 * @param tokenValue the token value
	 * @param issuedAt   the time at which the token was issued
	 * @param expiresAt  the time at which the token expires
	 */
	public OAuth2AuthorizationCode(String tokenValue, Instant issuedAt, Instant expiresAt) {
		super(tokenValue, issuedAt, expiresAt);
	}

}
