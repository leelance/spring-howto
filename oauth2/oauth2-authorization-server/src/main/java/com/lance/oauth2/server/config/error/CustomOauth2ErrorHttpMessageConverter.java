package com.lance.oauth2.server.config.error;

import com.lance.oauth2.server.common.result.R;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * custom oauth2.0 error http message converter
 *
 * @author lance
 * @date 2022/3/19 16:05
 */
public class CustomOauth2ErrorHttpMessageConverter extends AbstractHttpMessageConverter<OAuth2Error> {
  private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

  private static final ParameterizedTypeReference<Map<String, Object>> STRING_OBJECT_MAP = new ParameterizedTypeReference<Map<String, Object>>() {
  };

  private final GenericHttpMessageConverter<Object> jsonMessageConverter = new MappingJackson2HttpMessageConverter();

  protected Converter<Map<String, String>, OAuth2Error> errorConverter = new CustomOauth2ErrorHttpMessageConverter.OAuth2ErrorConverter();

  protected Converter<OAuth2Error, R<Map<String, String>>> errorParametersConverter = new CustomOauth2ErrorParametersConverter();


  public CustomOauth2ErrorHttpMessageConverter() {
    super(DEFAULT_CHARSET, MediaType.APPLICATION_JSON, new MediaType("application", "*+json"));
  }

  @Override
  protected boolean supports(Class<?> clazz) {
    return OAuth2Error.class.isAssignableFrom(clazz);
  }

  @Override
  @SuppressWarnings("unchecked")
  protected OAuth2Error readInternal(Class<? extends OAuth2Error> clazz, HttpInputMessage inputMessage)
      throws HttpMessageNotReadableException {
    try {
      // gh-8157: Parse parameter values as Object in order to handle potential JSON
      // Object and then convert values to String
      Map<String, Object> errorParameters = (Map<String, Object>) this.jsonMessageConverter
          .read(STRING_OBJECT_MAP.getType(), null, inputMessage);
      return this.errorConverter.convert(errorParameters.entrySet().stream()
          .collect(Collectors.toMap(Map.Entry::getKey, (entry) -> String.valueOf(entry.getValue()))));
    } catch (Exception ex) {
      throw new HttpMessageNotReadableException(
          "An error occurred reading the OAuth 2.0 Error: " + ex.getMessage(), ex, inputMessage);
    }
  }

  @Override
  protected void writeInternal(OAuth2Error oauth2Error, HttpOutputMessage outputMessage)
      throws HttpMessageNotWritableException {
    try {
      R<Map<String, String>> errorParameters = this.errorParametersConverter.convert(oauth2Error);
      this.jsonMessageConverter.write(errorParameters, STRING_OBJECT_MAP.getType(), MediaType.APPLICATION_JSON,
          outputMessage);
    } catch (Exception ex) {
      throw new HttpMessageNotWritableException(
          "An error occurred writing the OAuth 2.0 Error: " + ex.getMessage(), ex);
    }
  }

  /**
   * Sets the {@link Converter} used for converting the OAuth 2.0 Error parameters to an
   * {@link OAuth2Error}.
   *
   * @param errorConverter the {@link Converter} used for converting to an
   *                       {@link OAuth2Error}
   */
  public final void setErrorConverter(Converter<Map<String, String>, OAuth2Error> errorConverter) {
    Assert.notNull(errorConverter, "errorConverter cannot be null");
    this.errorConverter = errorConverter;
  }

  /**
   * A {@link Converter} that converts the provided OAuth 2.0 Error parameters to an
   * {@link OAuth2Error}.
   */
  private static class OAuth2ErrorConverter implements Converter<Map<String, String>, OAuth2Error> {
    @Override
    public OAuth2Error convert(Map<String, String> parameters) {
      String errorCode = parameters.get(OAuth2ParameterNames.ERROR);
      String errorDescription = parameters.get(OAuth2ParameterNames.ERROR_DESCRIPTION);
      String errorUri = parameters.get(OAuth2ParameterNames.ERROR_URI);
      return new OAuth2Error(errorCode, errorDescription, errorUri);
    }

  }
}
