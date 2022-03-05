package com.lance.sharding.based.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * 定义历史表
 *
 * @author lance
 * @date 2022/3/5 21:28
 */
@Slf4j
public final class CustomStandardShardingAlgorithm implements StandardShardingAlgorithm<Date> {
  @Override
  public String doSharding(Collection<String> collection, PreciseShardingValue<Date> shardingValue) {
    if (log.isDebugEnabled()) {
      log.debug("===>collection: {}, shardingVal: {}", collection, shardingValue);
    }

    for (String each : collection) {
      Date date = shardingValue.getValue();
      if (Objects.isNull(date)) {
        date = new Date();
      }

      String suffix = DateFormatUtils.format(date, "yyyyMM");
      if (each.endsWith(suffix)) {
        return each;
      }
    }
    return null;
  }

  @Override
  public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
    return collection;
  }

  @Override
  public void init() {

  }

  @Override
  public String getType() {
    return "ORDER_HIS";
  }
}
