package com.lance.sharding.based.mapper;

import com.lance.sharding.based.domain.Address;

import java.util.List;

/**
 * user mapper
 *
 * @author lance
 * @date 2022/2/20 20:57
 */
public interface AddressMapper {
  /**
   * save info
   *
   * @param info info
   */
  void save(Address info);

  /**
   * delete by id
   *
   * @param userId userId
   */
  void delete(int userId);

  /**
   * list user
   *
   * @return list
   */
  List<Address> findAll();
}
