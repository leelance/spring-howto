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

import org.springframework.util.Assert;

import java.util.Map;

/**
 * A representation of an OAuth 2.0 Authorization Server Metadata response,
 * which is returned from an OAuth 2.0 Authorization Server's Metadata Endpoint,
 * and contains a set of claims about the Authorization Server's configuration.
 * The claims are defined by the OAuth 2.0 Authorization Server Metadata
 * specification (RFC 8414).
 *
 * @author Daniel Garnier-Moiroux
 * @see AbstractOAuth2AuthorizationServerMetadata
 * @see <a target="_blank" href="https://tools.ietf.org/html/rfc8414#section-3.2">3.2. Authorization Server Metadata Response</a>
 * @since 0.1.1
 */
public final class OAuth2AuthorizationServerMetadata extends AbstractOAuth2AuthorizationServerMetadata {

	private OAuth2AuthorizationServerMetadata(Map<String, Object> claims) {
		super(claims);
	}

	/**
	 * Constructs a new {@link Builder} with empty claims.
	 *
	 * @return the {@link Builder}
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Constructs a new {@link Builder} with the provided claims.
	 *
	 * @param claims the claims to initialize the builder
	 * @return the {@link Builder}
	 */
	public static Builder withClaims(Map<String, Object> claims) {
		Assert.notEmpty(claims, "claims cannot be empty");
		return new Builder()
				.claims(c -> c.putAll(claims));
	}

	/**
	 * Helps configure an {@link OAuth2AuthorizationServerMetadata}.
	 */
	public static class Builder extends AbstractBuilder<OAuth2AuthorizationServerMetadata, Builder> {

		private Builder() {
		}

		/**
		 * Validate the claims and build the {@link OAuth2AuthorizationServerMetadata}.
		 * <p>
		 * The following claims are REQUIRED:
		 * {@code issuer}, {@code authorization_endpoint}, {@code token_endpoint}
		 * and {@code response_types_supported}.
		 *
		 * @return the {@link OAuth2AuthorizationServerMetadata}
		 */
		@Override
		public OAuth2AuthorizationServerMetadata build() {
			validate();
			return new OAuth2AuthorizationServerMetadata(getClaims());
		}

	}

}
