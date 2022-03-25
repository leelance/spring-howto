package com.lance.oauth2.server.common.helper;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

/**
 * SnowflakeHelper
 *
 * @author lance
 * @date 2022/3/25 18:07
 */
public class SnowflakeHelper {
  /**
   * Sign bit, Unused (always set to 0)
   */
  private static final int UNUSED_BITS = 1;
  private static final int EPOCH_BITS = 41;
  private static final int NODE_ID_BITS = 10;
  private static final int SEQUENCE_BITS = 12;

  private static final long MAX_NODE_ID = (1L << NODE_ID_BITS) - 1;
  private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;

  /**
   * Custom Epoch (January 1, 2015 Midnight UTC = 2015-01-01T00:00:00Z)
   */
  private static final long DEFAULT_CUSTOM_EPOCH = 1420070400000L;

  private final long nodeId;
  private final long customEpoch;

  private volatile long lastTimestamp = -1L;
  private volatile long sequence = 0L;

  /**
   * Create Snowflake with a nodeId and custom epoch
   *
   * @param nodeId      nodeId
   * @param customEpoch customEpoch
   */
  public SnowflakeHelper(long nodeId, long customEpoch) {
    if (nodeId < 0 || nodeId > MAX_NODE_ID) {
      throw new IllegalArgumentException(String.format("NodeId must be between %d and %d", 0, MAX_NODE_ID));
    }
    this.nodeId = nodeId;
    this.customEpoch = customEpoch;
  }

  /**
   * Create Snowflake with a nodeId
   *
   * @param nodeId nodeId
   */
  public SnowflakeHelper(long nodeId) {
    this(nodeId, DEFAULT_CUSTOM_EPOCH);
  }

  /**
   * Let Snowflake generate a nodeId
   */
  public SnowflakeHelper() {
    this.nodeId = createNodeId();
    this.customEpoch = DEFAULT_CUSTOM_EPOCH;
  }

  public synchronized long nextId() {
    long currentTimestamp = timestamp();

    if (currentTimestamp < lastTimestamp) {
      throw new IllegalStateException("Invalid System Clock!");
    }

    if (currentTimestamp == lastTimestamp) {
      sequence = (sequence + 1) & MAX_SEQUENCE;
      if (sequence == 0) {
        // Sequence Exhausted, wait till next millisecond.
        currentTimestamp = waitNextMillis(currentTimestamp);
      }
    } else {
      // reset sequence to start with zero for the next millisecond
      sequence = 0;
    }

    lastTimestamp = currentTimestamp;
    return currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS)
        | (nodeId << SEQUENCE_BITS)
        | sequence;
  }


  /**
   * Get current timestamp in milliseconds, adjust for the custom epoch.
   *
   * @return long
   */
  private long timestamp() {
    return Instant.now().toEpochMilli() - customEpoch;
  }

  /**
   * Block and wait till next millisecond
   *
   * @param currentTimestamp currentTimestamp
   * @return long
   */
  private long waitNextMillis(long currentTimestamp) {
    while (currentTimestamp == lastTimestamp) {
      currentTimestamp = timestamp();
    }
    return currentTimestamp;
  }

  private long createNodeId() {
    long nodeId;
    try {
      StringBuilder sb = new StringBuilder();
      Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
      while (networkInterfaces.hasMoreElements()) {
        NetworkInterface networkInterface = networkInterfaces.nextElement();
        byte[] mac = networkInterface.getHardwareAddress();
        if (mac != null) {
          for (byte macPort : mac) {
            sb.append(String.format("%02X", macPort));
          }
        }
      }
      nodeId = sb.toString().hashCode();
    } catch (Exception ex) {
      nodeId = (new SecureRandom().nextInt());
    }
    nodeId = nodeId & MAX_NODE_ID;
    return nodeId;
  }

  public long[] parse(long id) {
    long maskNodeId = ((1L << NODE_ID_BITS) - 1) << SEQUENCE_BITS;
    long maskSequence = (1L << SEQUENCE_BITS) - 1;

    long timestamp = (id >> (NODE_ID_BITS + SEQUENCE_BITS)) + customEpoch;
    long nodeId = (id & maskNodeId) >> SEQUENCE_BITS;
    long sequence = id & maskSequence;

    return new long[]{timestamp, nodeId, sequence};
  }

  @Override
  public String toString() {
    return "Snowflake Settings [EPOCH_BITS=" + EPOCH_BITS + ", NODE_ID_BITS=" + NODE_ID_BITS
        + ", SEQUENCE_BITS=" + SEQUENCE_BITS + ", CUSTOM_EPOCH=" + customEpoch
        + ", NodeId=" + nodeId + "]";
  }
}
