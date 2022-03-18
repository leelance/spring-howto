package com.lance.oauth2.server.common;

import com.nimbusds.jose.jwk.JWK;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.security.interfaces.RSAPrivateKey;

/**
 * valid pubKey priKey
 *
 * @author lance
 * @date 2022/3/18 00:05
 */
@Slf4j
class ValidPubPriKeyTests {

  @Test
  @Disabled
  void check() throws Exception {
    RSAPrivateKey privateKey = PemFile.readPrivateKey(PemFile.dir + "id_rsa");

    String json = "{\"p\":\"-1ndrbl0wNMXiUCV5FgG6UmwjOqBTVTi7clhWqpE8uT_SetPHGuEhpKuq9k_m-8YooDMcMnITB0DZK8sMK3mJC09-wYGknq-DHfkjEMNnGmoHXWI5R3rfFGvM6qTd8pXHpK-td-gh5TTh-ivt19EXiC23Bib9Fqc7dSAIecHEHk\",\"kty\":\"RSA\",\"q\":\"1AFLRTUHP0jcVETDSNJ2TZgnda34uFRcxgEI8QmmsiN7ESInIdV3ePinGs4h-338U35d3sdYzdCB3MoIXFy03u2k4fzNTk3fOXqMwBP-rppa2ERnC0UuXJ4OUYx8S4zZxMsXsecdC4wJ8nBu5QS261aD9y56w6Zf7RvkGXlIPQk\",\"d\":\"jbNXV8qYYOg1vM-Uwu54lv4VkQQGhfOo8MXfgH2uux-mYL6ZI7VvKyVaMNlLHQjcAKq6sqAF_V34uQOA1tUd5pO3EbrLmJLC1z6Q_XXcVZ5Bi3nV3IYrfmmTyTvzvgCp2DFLakXfj7TMRGcSRrT1C50Apj3RZO-U5nFX6L7NDZK3WWB_vFRoEKiuvUNXsqLnrTRNV1Y_SsAk6_Nai5jcQpZNIMkm8HVtBtlTcK058hECQc-VQnce7NriN40RfkX-61-QCdDl5Ma3eIm7KFm5S92lqScrQoKBZNriQrA1X53UWsJaXjGI53Lo8Hdspbt1fUQYLj0nl3EGjXkCYQEWgQ\",\"e\":\"AQAB\",\"use\":\"sig\",\"kid\":\"8000000014\",\"qi\":\"LlHjFTCrRHYFA7Z0rdF2vcUr8mSiZ73YB9SN-qYzemoooUGndwZWUF7UcCJgQaBwiSMYRleGMfD8WIWt12DAp7024lf18Yyvc4K9cxej7UNllWG9kpN7ivlMrJ7zKObr7nZcRCEtuennXd7FDFfQ6s5G-TfnqqzH_oti0Q0Dy_0\",\"dp\":\"lFUQcbmEoVbyCNnIm50nVv1Z549tB0oVMecqQOtDDWT_E4x-f3MkHpbp5hirZUbQ7vW7sMQkJGbq3bxTA9dcffUFIuMHdeJhzdHjWrMnkSr5P4lhZHUnx5IdJwx_Qq5yg2Ruj76NafX2b7puGHVsT3BW-cS8JJRlECXcCCXjEWE\",\"dq\":\"PMluMpbE0DFs80CQGcY2o4L39Xb_dKLDwwYy8SHKSROBSuVXAQ165xWsnDP0bG6wNaRq9-CCpuidA5huE0WFmtHaz-0zVLthQNLRnpVdFeUuHQpn3URlyaqMAnfcf7EQE6wDzD-vBUuO4pO4SDQ02n-A8RPDoWuH3616lo8-1jk\",\"n\":\"0Cew1QJ9xshvk-Ducb90ewR3qzkLrsLKfScfvsyGUoE1N60-jBIOn8B_jy-RltTvilaqLVi_xozBnvVZZtOgK_xzqmXqms4EiHu3CMuwskNz0QNHRPX1Wnkh84kLCjRmR-2RIyLXQcuTlF9KerjnRaNRz0Nf8gzuySsDz4RdlEo670hasVzkApJNcrkQ1MD11et9CIcwXq9CwB9krjahI7bYXh5MoZ3Ap_tSdQcDZtsTpP7nBNK_6IEbhitXbqb6KVN28CB1CwTth6y7VqbCymUXHX-LFa5ZX-kUOrDVBkGQHCbWUYKpgEzhOpVcmdQgKr_4HkaHGHuq20XUoTRpQQ\"}";
    JWK jwk = JWK.parse(json);

    log.info("===>Is privateKey: {}, keyType: {}", jwk.isPrivate(), jwk.getKeyType());
  }
}
