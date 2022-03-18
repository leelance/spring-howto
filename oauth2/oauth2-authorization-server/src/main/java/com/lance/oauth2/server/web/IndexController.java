package com.lance.oauth2.server.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * index controller
 *
 * @author lance
 * @date 2022/3/17 20:07
 */
@Slf4j
@RestController
public class IndexController {

  @GetMapping("/resources")
  public String getResources() {
    String keys = "{\"keys\": [{\"p\":\"9Bc5RkbhgFF5gPTcCYxGeqlIP7tyigrh_XKP9qEqXfa20LAb-nAOjPlnPJGWWYdyXXcX2ub2EnRRy36Ve-LARmJ0eXnZfxGZqVLgQyD5FX62uTbKcCMa7OeNBadLb0-SokTbthLxnVfvNnSpE3cMjReYXX8LAJRY3Mu83-eiug0\",\"kty\":\"RSA\",\"q\":\"z-iZHgeep940PrO6HAT_t3cQihFkBCNULXKYYnG2hXrVDlRnQ_kV55w3lG1yKtPO7tss3uN691Se-ubK18V-byhEgCev-VVc4GbNIhHfxZxYb8IbborqoH0fWFfv3V00M5PsaABsDrcF2SPZm1IXQHR3cS2VDhjRKMGkcgPMMXM\",\"d\":\"hVSPbjTw-WxAL1Z7cQnXEHn1_h5TPKMyJwmCu9m2hHzAbC9fkJqFQBXZMqZ3APxdPw-I_elEsssQcEK1ftaGpJHZpNaFfW-i4hqTsPxCqTHs3d8PNNbhcPGkgKkiHoU54wLMG8t4hJ8WhVjHnWnmCUypnu5VnWfHyrJdYJcmfmAvB2agdFiqz2UpM-Qe5qMMxAdiYZ4JG9sR_a5WpKB17cKrgoqsYOD1deYSDUKs72zMxWHJLXCApP8gKmeIFGBZ8i6aY7wIn_5G80FaAND6E_sCsRY10-GVpYOsgeDROmm3KLtnfrBQBUDukzC1iV05ozJ0ixyos192nuHLPuH7KQ\",\"e\":\"AQAB\",\"use\":\"sig\",\"kid\":\"8000000014\",\"qi\":\"CLITBh67ZXEBDXtakxUackPH9FVLDAQXlWEQEYgtTc6Rn5N0b1ne3z9Li5vqgja65lz-lCwJnnUSi_0YDWYokeOdNyzxR1413rWBOPNtRNMU87tvwLFtW9J3RnpJIBOEB4vsENHIhfOLKA20Fq5Eyehee9em3cstWQ-m9IxVGig\",\"dp\":\"wJHx5bfWAF5G9a44SnAYynzzOxSvcKjVUhcn7POJcUhLQqkt6Y6ubBn3kJlrfIaXe5WRhGniiPyr61eap3plJyJVseCn7s_74tI3i75fxM2LauQHIiEQBQdJAsTRhqfIj6yzihm39OKDHvs6yNiLVmTfGCQ6keJ3mPpDsIIWp6E\",\"alg\":\"RS256\",\"dq\":\"WaX_GB7K_7Flr7_JuTlClnaFsDMtgEdHjc1C_ffwbv_3DJXCbWHjWxpWMO88clEggogJghZuuGVJpACOaJnlGcMwPl5el9GCGdTaER3J8qimzZHSSEkoQImTUYGhkT8WOkNHhdTgwzuSDv5RX5L3KbjOpAO2qPx0e_F7mhofGxc\",\"n\":\"xjyOWj44MqyI1AuD94FAjhY8HFf71etmD57zewc7mDMXHU-AOrGTuUeQlkGaNcmp-9uY6L3QBEXrwBAzxIdaPc8Zlk-7EsV6JDDXjt1azRNAyMkGNTkHKPH1nhRDEOEcZT6nnX9_27X7IrhywC_4BvBCsaK-BD9teR8iw9Q0R1d3mgh8AgfWsTP8nHkxonQIbtVs0WlGbNH6IzocJPbkNiF2iwzkldu3GwuFFEVI4T0JUAMxvVlZUDsXignzbBRBn0kCwEqgb2mqLp-_o1BrZK9ELSTXw2WIycy-2yrYxVlW2erg0mG_WWmTOTCAXtzErl5ifaLHszYCRF-PnRIQ1w\"}]}";
    log.info("===>{}", keys);
    return keys;
  }

  @GetMapping("/index")
  public String index() {
    return "index";
  }
}
