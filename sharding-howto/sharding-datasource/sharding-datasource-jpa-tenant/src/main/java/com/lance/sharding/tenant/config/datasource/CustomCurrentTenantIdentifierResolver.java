package com.lance.sharding.tenant.config.datasource;

import com.lance.sharding.tenant.config.context.DbContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.util.StringUtils;

/**
 * CurrentTenantIdentifierResolver
 *
 * @author lance
 * @date 2022/3/10 18:54
 */
@Slf4j
public class CustomCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {
  private final static String DEFAULT_TENANT_ID = "bbs_1";

  @Override
  public String resolveCurrentTenantIdentifier() {
    String currentTenantId = DbContextHolder.get();
    return StringUtils.hasText(currentTenantId) ? currentTenantId : DEFAULT_TENANT_ID;
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return false;
  }
}
