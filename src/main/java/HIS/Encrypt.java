package HIS;

import java.io.UnsupportedEncodingException;

public class Encrypt {
    private String id;
    private String key;
    public String long_key;
    private String[] all_plaintext;
    private String[] all_ciphertext;
    private String[] all_viewtext;


    public Encrypt(String id, String key) {
        this.id = id;
        this.key = key;
        this.long_key = PseudoRandomGenerator.toLong_key(this.key);
    }


    public void encrypt(String[] plaintext) {
//        分配密文空间
        this.all_plaintext = plaintext;

//        伪随机加密
        PseudoRandomGenerator prg = new PseudoRandomGenerator(this.long_key);
//        this.all_ciphertext = prg.stringENC(this.all_plaintext);

//        伪随机解密
//        this.all_viewtext = prg.stringDEC(all_ciphertext);


////        AES加密
//        HIS.AES aes = new HIS.AES(this.long_key);
//        for (int i = 0; i < this.all_plaintext.length; i ++){
//            for (int j = 0; j < this.all_plaintext[i].length(); j += 2){
//                int[][] data = toHex(this.all_plaintext[i].substring(j, j + 2));
//                data = aes.ENC(data);
//                this.all_ciphertext[i] = this.all_ciphertext[i] + toUTF_8(data);
//            }
//        }
//
//        toDatabase(this.all_ciphertext);
//
//        this.all_viewtext = new String[this.all_ciphertext.length];

////        AES解密
//        for (int i = 0; i < all_plaintext.length; i ++){
//            for (int j = 0; j < all_ciphertext[i].length(); j += 2){
//                int[][] data = toHex(all_ciphertext[i].substring(j, j + 2));
//                data = aes.DEC(data);
//                all_viewtext[i] = all_viewtext[i] + toUTF_8(data);
//            }
//        }


    }

    public static String toHexString(String seed) {
        String hex_string = "";
        try {
            byte[] strbyte=seed.getBytes("utf-8");
            String[] temp = new String[strbyte.length];
            for (int i = 0; i < strbyte.length; i ++){
                temp[i] = Integer.toHexString(strbyte[i] + 128);
                if (temp[i].length() != 2){
                    temp[i] = "0" + temp[i];
                }
            }
            for (int i = 0; i < strbyte.length / 3; i ++){
                hex_string = hex_string + "00" + temp[3 * i] + temp[3 * i + 1] + temp[3 * i + 2];
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return hex_string;
    }

    public static String toUTF_8(String data) {
        String bytedata = "";
        String uft8_string = "";
        for (int i = 0; i < data.length() / 8; i ++){
            bytedata = bytedata + data.substring(8 * i + 2, 8 * i + 8);
        }
        byte[] strbyte = new byte[bytedata.length() / 2];
        for (int i = 0; i < strbyte.length; i ++){
            strbyte[i] = (byte) (Integer.parseInt(bytedata.substring(2 * i, 2 * i + 2),16) - 128);
        }
        try {
            uft8_string=new String(strbyte,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            return uft8_string;
        }
    }

}