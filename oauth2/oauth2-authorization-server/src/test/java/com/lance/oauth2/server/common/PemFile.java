package com.lance.oauth2.server.common;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * perm file
 *
 * @author lance
 * @date 2022/3/17 23:24
 */
public class PemFile {
  public final static String dir = "/Users/lance/home/";
  private final PemObject pemObject;

  public PemFile(Key key, String description) {
    this.pemObject = new PemObject(description, key.getEncoded());
  }

  public void write(String filename) throws Exception {
    try (PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(dir + filename)))) {
      pemWriter.writeObject(this.pemObject);
    }
  }

  /**
   * 读私钥文件
   */
  public static RSAPrivateKey readPrivateKey(String filePath) throws Exception {
    String key = new String(Files.readAllBytes(Paths.get(filePath)), Charset.defaultCharset());

    String privateKeyPEM = key
        .replace("-----BEGIN RSA PRIVATE KEY-----", "")
        .replaceAll(System.lineSeparator(), "")
        .replace("-----END RSA PRIVATE KEY-----", "");

    byte[] encoded = Base64.decodeBase64(privateKeyPEM);

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
    return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
  }
}
