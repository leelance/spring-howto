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

import java.net.URL;
import java.util.List;

/**
 * A {@link ClaimAccessor} for the "claims" an Authorization Server describes about its configuration,
 * used in OAuth 2.0 Authorization Server Metadata and OpenID Connect Discovery 1.0.
 *
 * @author Daniel Garnier-Moiroux
 * @see ClaimAccessor
 * @see OAuth2AuthorizationServerMetadataClaimNames
 * @see <a target="_blank" href="https://tools.ietf.org/html/rfc8414#section-2">2. Authorization Server Metadata</a>
 * @see <a target="_blank" href="https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata">3. OpenID Provider Metadata</a>
 * @since 0.1.1
 */
public interface OAuth2AuthorizationServerMetadataClaimAccessor extends ClaimAccessor {

	/**
	 * Returns the {@code URL} the Authorization Server asserts as its Issuer Identifier {@code (issuer)}.
	 *
	 * @return the {@code URL} the Authorization Server asserts as its Issuer Identifier
	 */
	default URL getIssuer() {
		return getClaimAsURL(OAuth2AuthorizationServerMetadataClaimNames.ISSUER);
	}

	/**
	 * Returns the {@code URL} of the OAuth 2.0 Authorization Endpoint {@code (authorization_endpoint)}.
	 *
	 * @return the {@code URL} of the OAuth 2.0 Authorization Endpoint
	 */
	default URL getAuthorizationEndpoint() {
		return getClaimAsURL(OAuth2AuthorizationServerMetadataClaimNames.AUTHORIZATION_ENDPOINT);
	}

	/**
	 * Returns the {@code URL} of the OAuth 2.0 Token Endpoint {@code (token_endpoint)}.
	 *
	 * @return the {@code URL} of the OAuth 2.0 Token Endpoint
	 */
	default URL getTokenEndpoint() {
		return getClaimAsURL(OAuth2AuthorizationServerMetadataClaimNames.TOKEN_ENDPOINT);
	}

	/**
	 * Returns the client authentication methods supported by the OAuth 2.0 Token Endpoint {@code (token_endpoint_auth_methods_supported)}.
	 *
	 * @return the client authentication methods supported by the OAuth 2.0 Token Endpoint
	 */
	default List<String> getTokenEndpointAuthenticationMethods() {
		return getClaimAsStringList(OAuth2AuthorizationServerMetadataClaimNames.TOKEN_ENDPOINT_AUTH_METHODS_SUPPORTED);
	}

	/**
	 * Returns the {@code URL} of the JSON Web Key Set {@code (jwks_uri)}.
	 *
	 * @return the {@code URL} of the JSON Web Key Set
	 */
	default URL getJwkSetUrl() {
		return getClaimAsURL(OAuth2AuthorizationServerMetadataClaimNames.JWKS_URI);
	}

	/**
	 * Returns the OAuth 2.0 {@code scope} values supported {@code (scopes_supported)}.
	 *
	 * @return the OAuth 2.0 {@code scope} values supported
	 */
	default List<String> getScopes() {
		return getClaimAsStringList(OAuth2AuthorizationServerMetadataClaimNames.SCOPES_SUPPORTED);
	}

	/**
	 * Returns the OAuth 2.0 {@code response_type} values supported {@code (response_types_supported)}.
	 *
	 * @return the OAuth 2.0 {@code response_type} values supported
	 */
	default List<String> getResponseTypes() {
		return getClaimAsStringList(OAuth2AuthorizationServerMetadataClaimNames.RESPONSE_TYPES_SUPPORTED);
	}

	/**
	 * Returns the OAuth 2.0 {@code grant_type} values supported {@code (grant_types_supported)}.
	 *
	 * @return the OAuth 2.0 {@code grant_type} values supported
	 */
	default List<String> getGrantTypes() {
		return getClaimAsStringList(OAuth2AuthorizationServerMetadataClaimNames.GRANT_TYPES_SUPPORTED);
	}

	/**
	 * Returns the {@code URL} of the OAuth 2.0 Token Revocation Endpoint {@code (revocation_endpoint)}.
	 *
	 * @return the {@code URL} of the OAuth 2.0 Token Revocation Endpoint
	 */
	default URL getTokenRevocationEndpoint() {
		return getClaimAsURL(OAuth2AuthorizationServerMetadataClaimNames.REVOCATION_ENDPOINT);
	}

	/**
	 * Returns the client authentication methods supported by the OAuth 2.0 Token Revocation Endpoint {@code (revocation_endpoint_auth_methods_supported)}.
	 *
	 * @return the client authentication methods supported by the OAuth 2.0 Token Revocation Endpoint
	 */
	default List<String> getTokenRevocationEndpointAuthenticationMethods() {
		return getClaimAsStringList(OAuth2AuthorizationServerMetadataClaimNames.REVOCATION_ENDPOINT_AUTH_METHODS_SUPPORTED);
	}

	/**
	 * Returns the {@code URL} of the OAuth 2.0 Token Introspection Endpoint {@code (introspection_endpoint)}.
	 *
	 * @return the {@code URL} of the OAuth 2.0 Token Introspection Endpoint
	 */
	default URL getTokenIntrospectionEndpoint() {
		return getClaimAsURL(OAuth2AuthorizationServerMetadataClaimNames.INTROSPECTION_ENDPOINT);
	}

	/**
	 * Returns the client authentication methods supported by the OAuth 2.0 Token Introspection Endpoint {@code (introspection_endpoint_auth_methods_supported)}.
	 *
	 * @return the client authentication methods supported by the OAuth 2.0 Token Introspection Endpoint
	 */
	default List<String> getTokenIntrospectionEndpointAuthenticationMethods() {
		return getClaimAsStringList(OAuth2AuthorizationServerMetadataClaimNames.INTROSPECTION_ENDPOINT_AUTH_METHODS_SUPPORTED);
	}

	/**
	 * Returns the Proof Key for Code Exchange (PKCE) {@code code_challenge_method} values supported {@code (code_challenge_methods_supported)}.
	 *
	 * @return the {@code code_challenge_method} values supported
	 */
	default List<String> getCodeChallengeMethods() {
		return getClaimAsStringList(OAuth2AuthorizationServerMetadataClaimNames.CODE_CHALLENGE_METHODS_SUPPORTED);
	}

}
