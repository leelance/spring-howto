package com.lance.oauth2.server.json;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;

/**
 * MapTypeReference
 *
 * @author lance
 * @date 8/25/2021 09:59
 */
class MapTypeReference extends TypeReference<Map<String, Object>> {
	MapTypeReference() {
	}
}
