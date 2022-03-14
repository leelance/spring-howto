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

/**
 * The names of the "claims" an Authorization Server describes about its configuration,
 * used in OAuth 2.0 Authorization Server Metadata and OpenID Connect Discovery 1.0.
 *
 * @author Daniel Garnier-Moiroux
 * @see <a target="_blank" href="https://tools.ietf.org/html/rfc8414#section-2">2. Authorization Server Metadata</a>
 * @see <a target="_blank" href="https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata">3. OpenID Provider Metadata</a>
 * @since 0.1.1
 */
public interface OAuth2AuthorizationServerMetadataClaimNames {

	/**
	 * {@code issuer} - the {@code URL} the Authorization Server asserts as its Issuer Identifier
	 */
	String ISSUER = "issuer";

	/**
	 * {@code authorization_endpoint} - the {@code URL} of the OAuth 2.0 Authorization Endpoint
	 */
	String AUTHORIZATION_ENDPOINT = "authorization_endpoint";

	/**
	 * {@code token_endpoint} - the {@code URL} of the OAuth 2.0 Token Endpoint
	 */
	String TOKEN_ENDPOINT = "token_endpoint";

	/**
	 * {@code token_endpoint_auth_methods_supported} - the client authentication methods supported by the OAuth 2.0 Token Endpoint
	 */
	String TOKEN_ENDPOINT_AUTH_METHODS_SUPPORTED = "token_endpoint_auth_methods_supported";

	/**
	 * {@code jwks_uri} - the {@code URL} of the JSON Web Key Set
	 */
	String JWKS_URI = "jwks_uri";

	/**
	 * {@code scopes_supported} - the OAuth 2.0 {@code scope} values supported
	 */
	String SCOPES_SUPPORTED = "scopes_supported";

	/**
	 * {@code response_types_supported} - the OAuth 2.0 {@code response_type} values supported
	 */
	String RESPONSE_TYPES_SUPPORTED = "response_types_supported";

	/**
	 * {@code grant_types_supported} - the OAuth 2.0 {@code grant_type} values supported
	 */
	String GRANT_TYPES_SUPPORTED = "grant_types_supported";

	/**
	 * {@code revocation_endpoint} - the {@code URL} of the OAuth 2.0 Token Revocation Endpoint
	 */
	String REVOCATION_ENDPOINT = "revocation_endpoint";

	/**
	 * {@code revocation_endpoint_auth_methods_supported} - the client authentication methods supported by the OAuth 2.0 Token Revocation Endpoint
	 */
	String REVOCATION_ENDPOINT_AUTH_METHODS_SUPPORTED = "revocation_endpoint_auth_methods_supported";

	/**
	 * {@code introspection_endpoint} - the {@code URL} of the OAuth 2.0 Token Introspection Endpoint
	 */
	String INTROSPECTION_ENDPOINT = "introspection_endpoint";

	/**
	 * {@code introspection_endpoint_auth_methods_supported} - the client authentication methods supported by the OAuth 2.0 Token Introspection Endpoint
	 */
	String INTROSPECTION_ENDPOINT_AUTH_METHODS_SUPPORTED = "introspection_endpoint_auth_methods_supported";

	/**
	 * {@code code_challenge_methods_supported} - the Proof Key for Code Exchange (PKCE) {@code code_challenge_method} values supported
	 */
	String CODE_CHALLENGE_METHODS_SUPPORTED = "code_challenge_methods_supported";

}
