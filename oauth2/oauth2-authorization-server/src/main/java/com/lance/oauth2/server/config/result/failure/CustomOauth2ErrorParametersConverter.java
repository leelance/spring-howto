package com.lance.oauth2.server.config.result.failure;

import com.lance.oauth2.server.common.result.R;
import com.lance.oauth2.server.common.result.ResultCode;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * custom oauth2.0 error parameter converter
 *
 * @author lance
 * @date 2022/3/19 15:49
 */
public class CustomOauth2ErrorParametersConverter implements Converter<OAuth2Error, R<Map<String, String>>> {
  @Override
  public R<Map<String, String>> convert(OAuth2Error oauth2Error) {
    R<Map<String, String>> result = R.fail(ResultCode.PARAM_VALID_ERROR);

    Map<String, String> parameters = new HashMap<>(8);
    parameters.put(OAuth2ParameterNames.ERROR, oauth2Error.getErrorCode());
    if (StringUtils.hasText(oauth2Error.getDescription())) {
      parameters.put(OAuth2ParameterNames.ERROR_DESCRIPTION, oauth2Error.getDescription());
    }
    if (StringUtils.hasText(oauth2Error.getUri())) {
      parameters.put(OAuth2ParameterNames.ERROR_URI, oauth2Error.getUri());
    }

    result.setData(parameters);
    return result;
  }
}
