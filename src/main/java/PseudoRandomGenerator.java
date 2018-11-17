import javax.print.attribute.standard.Sides;

public class PseudoRandomGenerator {

    private int arg;

    public PseudoRandomGenerator(String long_key){
        switch (Integer.valueOf(long_key.substring(0,4))){
            case 0:
                this.arg = 251;
            case 1:
                this.arg = 241;
            case 2:
                this.arg = 239;
            case 3:
                this.arg = 233;
            case 4:
                this.arg = 229;
            case 5:
                this.arg = 227;
            case 6:
                this.arg = 223;
            case 7:
                this.arg = 211;
            case 8:
                this.arg = 199;
            case 9:
                this.arg = 197;
            case 10:
                this.arg = 193;
            case 11:
                this.arg = 191;
            case 12:
                this.arg = 181;
            case 13:
                this.arg = 179;
            case 14:
                this.arg = 173;
            case 15:
                this.arg = 167;
        }
    }

    public  static String toLong_key(String key){
        if (key.length() != 8){
            System.exit(1);
        }

        String bin_long_key = "";
        String long_key = "";

        for (int i = 0; i < key.length(); i ++){
//            key的二进制字符串
            String temp_1 = "";
            String temp_2 = "";
//            伪随机二进制字符串
            String new_temp_1 = "";
            String new_temp_2 = "";

            int value = Integer.valueOf(key.substring(i, i + 1));
            temp_1 = temp_1+ Integer.toBinaryString(value);
//            规格化
            if (temp_1.length() != 4){
                for (int j = 0; j < 4 - bin_long_key.length(); j ++){
                    temp_1 = "0" + temp_1;
                }
            }


//            扩展至8位
            int XOR_value_1 = temp_1.charAt(0) ^ temp_1.charAt(1) ^temp_1.charAt(2) ^temp_1.charAt(3);
            for (int j = 0; j < 4; j ++){
                new_temp_1 = new_temp_1 + Integer.toString(XOR_value_1 ^ temp_1.charAt(j));
            }
//            混合
            for (int j = 0; j < 4; j ++){
                temp_2 = temp_2 + new_temp_1.substring(j, j + 1) + temp_1.substring((j + 2) % 4, (j + 2) % 4 + 1);
            }


//            扩展至16位
            int XOR_value_2 = temp_2.charAt(0) ^ temp_2.charAt(1) ^temp_2.charAt(2) ^temp_2.charAt(3) ^ temp_2.charAt(4) ^ temp_2.charAt(5) ^temp_2.charAt(6) ^temp_2.charAt(7);
            for (int j = 0; j < 8; j ++){
                new_temp_2 = new_temp_2 + Integer.toString(XOR_value_2 ^ temp_2.charAt(j));
            }
//            混合
            for (int j = 0; j < 4; j ++){
                bin_long_key = bin_long_key + new_temp_2.substring(j, j + 1) + temp_2.substring((j + 4) % 8, (j + 4) % 8 + 1);
            }

        }

        for (int i = 0; i < 32; i ++){
            long_key = long_key + Integer.toHexString(Integer.parseInt(bin_long_key.substring(4 * i, 4 * i + 3),2));
        }
        return long_key;
    }

    public String[] ENC(String[] all_plaintext){
        String[] all_ciphertext = new String[all_plaintext.length];
        for (int i = 0; i < all_plaintext.length; i ++){
            String plaintext = all_plaintext[i];
            all_ciphertext[i] = ENC(plaintext);
        }
        return all_ciphertext;
    }

    public String ENC(String plaintext){
        String[] Plaintext = new String[plaintext.length()];
        String[] hexCiphertext = new String[Plaintext.length];
        for (int i = 0; i < Plaintext.length; i ++){
            Plaintext[i] = plaintext.substring(i, i + 1);
        }
        for (int i = 0; i < hexCiphertext.length; i ++){
            String SinglePlaintext = Plaintext[i];
            String hexSinglePlaintext = Encrypt.toHexString(SinglePlaintext);
            String hexSingleCiphertext = hexSinglePlaintext.substring(0,2) + enc(hexSinglePlaintext.substring(2,4))
                    + hexSinglePlaintext.substring(4,6) + enc(hexSinglePlaintext.substring(6,8));
            hexCiphertext[i] = hexSingleCiphertext;
        }
        String ciphertext = "";
        for (int i = 0; i < hexCiphertext.length; i ++){
            ciphertext = ciphertext + hexCiphertext[i];
        }

        return ciphertext;
    }

    public String[] DEC(String[] all_ciphertext){
        String[] all_plaintext = new String[all_ciphertext.length];
        for (int i = 0; i < all_ciphertext.length; i ++){
            String ciphertext = all_ciphertext[i];
            all_plaintext[i] = DEC(ciphertext);
        }
        return all_plaintext;
    }

    public String DEC(String single_ciphertext){
        String[] plaintext = new String[single_ciphertext.length() / 8];
        for (int i = 0; i < plaintext.length; i ++){
            String hexSingleCiphertext = single_ciphertext.substring(8 * i, 8 * i + 8);
            String hex_plaintext = hexSingleCiphertext.substring(0,2) + dec(hexSingleCiphertext.substring(2,4))
                    + hexSingleCiphertext.substring(4,6) + dec(hexSingleCiphertext.substring(6,8));
            plaintext[i] = Encrypt.toUTF_8(hex_plaintext);
        }
        String single_plaintext = "";
        for (int i = 0 ; i < plaintext.length; i ++){
            single_plaintext = single_plaintext + plaintext[i];
        }

        return single_plaintext;

    }


    private String enc(String seed){
        int value = Integer.parseInt(seed,16);
        value = value * this.arg % 256;
        String result = String.format("%02x", value);
        return result;
    }
    private String dec(String seed){
        int value = Integer.parseInt(seed,16);
        while (value % this.arg != 0){
            value = value + 256;
        }
        value = value / this.arg;
        String result = String.format("%02x",value);
        return result;
    }

    public static void main(String[] args){
        PseudoRandomGenerator pseudoRandomGenerator = new PseudoRandomGenerator("0000");
        String plaintext = "薛老狗";
        String ciphertext = pseudoRandomGenerator.ENC(plaintext);
        System.out.println(ciphertext);
        String viewtext = pseudoRandomGenerator.DEC(ciphertext);
        System.out.println(viewtext);
    }

}
