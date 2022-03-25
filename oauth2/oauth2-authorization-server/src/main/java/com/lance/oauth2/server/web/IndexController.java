package com.lance.oauth2.server.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * index controller
 *
 * @author lance
 * @date 2022/3/17 20:07
 */
@Slf4j
@RestController
public class IndexController {
  /**
   * jwk获取公私钥
   */
  @GetMapping("/resources")
  public String getResources() {
    String keys = "{\"keys\": [{\"p\":\"4k33oQYFzEyCdpb9Q4gu9aN37PoeP516fXBasp4JI5ODnkw8Q3ZbCHVfR80GBIQ8eJmfd7sOK4bHYvLbdCWKTuDDSGczqt3p1MSc9dcBDHQaC6KxqMdFtb-79cNH8zgVFLSN0vxJs6ViA9lHQkLuJamvCNAD5iVJJbp6o88RuO8\",\"kty\":\"RSA\",\"q\":\"mvcl6tYXXPumxmTzXKkWZrvhEwFEyC-SvVut9CMa3Jl9qUjFAvv6t2Y3m8EjeqrJJa2MYchrOCfdL0TO2dQiaOOT_q2_JMjezI1Y8MkMeU2iQreVEvVExzjfnmUH83C9PkLZcWedA9Cy3GiQkrZ5_HcuEayMp8mrSWm2P_tDKh8\",\"d\":\"eH_p4xA2wrWpkj2uk8tVvwPB3dbbyUXbm2QQZPHY7AHabcBfatw49zRweyeVNAvkugIlhZJ2WD59rAS0LR-Lb8-fSX2QubRB_q-mwqDKkYRNAWiR9OYzsROIyBcY_P_4duLXdGHs6Ds_etB69-vRV1ad6kMjBqoRUbOcgjEwVCoaG7qN34hIaNnOgvcG-nrCR-vW8LnWNSCeA9BUIxLs1nOSVsT_3LFRt0G6qpapPgpJJ4DpBeaT1LtS1UjeeDnfxvOWouPRy31WW4AHEnxMG2EEEgHdu-17Nhf1XpX1JYEW5U7Or3SfJUF-e-WnqnIDLLey-ZK0AIDvFnmmWMDewQ\",\"e\":\"AQAB\",\"use\":\"sig\",\"kid\":\"8000000015\",\"qi\":\"D6WY18x_p_64322Fq1MMzLuPUYaR8RkXZmk5tsjDcUJdutGebwSk9MGBgDrYZ410O4rGx31DTk0WLoKirU1KRbv7DmtCrg_v7WrhwVw8Rzoux5CZH2jjJ-7n7cf3WaG8Yyte7LXeSQ-5R_0V7VHx9cpD5MEYW1PNxivu4zA1gPQ\",\"dp\":\"p2-6XLghCBQ_ntB-GD4lSHcnHv277aNtYmwDnw5QgrL8-d075Uondm2pDm3CGwtf1ncLRsHubQPud-ZpuligKVYhBmvdDXndLSA_ZaErVHR-ZMd-HPs7N4rh3acbj_eZmVUxbb5eJX2itGhJUdxFaTZPm_yUNe62XSTI4nQtj58\",\"alg\":\"RS256\",\"dq\":\"cMvKvredA-Jsz2X1uhGdq6O_vYyj2y69mbVc1iKjT8UtsYW1WWm-YbY2bdnUkFo11YYQc7BlUte5aMml4upFLaHRPISNaCIKAkw0W_eLa9Q1cmicStNaf8SHCI0zj2AVb3dLPP-VS4yiVEqMSS4ndrvtPZIiQ5NIaURYExQ6HDM\",\"n\":\"iP1htadMv8DRfwCAQVc5GcNTWdktZxqGJsr6ACbtTcdURAttQbQiMPF37w3tgdES2t7REagFBWviEBF6ySoZyfqGZkz-nHp5cNZ1dXt5yw-yJwhByGcQO251W7o7WLZqHGZg4DXc8gn7RDWrfEzG9Zc4xj1ORYwB-H2Kb1P-mk5J7OPAsjh6_yErxnZju-aiXteJzrLaQIqURWIR6nQBNlCKoUIsze0VOqwSJiXj4pX05fIoaVNHGTeKQygVR95rLTp9ra95iJUyYFdlQDgBWaG67vF4TGZavW-BpFkVUgYa3SO26w5_3xlebwzsa_xEFeYgLSdjCEVudriVtwma8Q\"}]}";
    log.info("===>{}", keys);
    return keys;
  }

  @GetMapping("/index")
  public String index() {
    return "index";
  }

  @GetMapping("/principal")
  public Principal principal() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    log.info("===>{}", authentication);
    return authentication;
  }
}
