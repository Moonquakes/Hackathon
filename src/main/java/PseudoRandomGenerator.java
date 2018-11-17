
public class PseudoRandomGenerator {

    private int strArg;
    private String intArg;

    public PseudoRandomGenerator(String long_key){
        switch (Integer.parseInt(long_key.substring(0,1),16)){
            case 0:
                this.strArg = 251;
                this.intArg = "079";
            case 1:
                this.strArg = 241;
                this.intArg = "687";
            case 2:
                this.strArg = 239;
                this.intArg = "888";
            case 3:
                this.strArg = 233;
                this.intArg = "677";
            case 4:
                this.strArg = 229;
                this.intArg = "833";
            case 5:
                this.strArg = 227;
                this.intArg = "288";
            case 6:
                this.strArg = 223;
                this.intArg = "535";
            case 7:
                this.strArg = 211;
                this.intArg = "791";
            case 8:
                this.strArg = 199;
                this.intArg = "415";
            case 9:
                this.strArg = 197;
                this.intArg = "756";
            case 10:
                this.strArg = 193;
                this.intArg = "713";
            case 11:
                this.strArg = 191;
                this.intArg = "429";
            case 12:
                this.strArg = 181;
                this.intArg = "136";
            case 13:
                this.strArg = 179;
                this.intArg = "131";
            case 14:
                this.strArg = 173;
                this.intArg = "168";
            case 15:
                this.strArg = 167;
                this.intArg = "725";
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
            int length = temp_1.length();
//            规格化
            if (temp_1.length() != 4){
                for (int j = 0; j < 4 - length; j ++){
                    temp_1 = "0" + temp_1;
                }
            }


//            扩展至8位
            int XOR_value_1 = temp_1.charAt(0) ^ temp_1.charAt(1) ^temp_1.charAt(2) ^temp_1.charAt(3);
            for (int j = 0; j < 4; j ++){
                new_temp_1 = new_temp_1 + Integer.toString(XOR_value_1 ^ (temp_1.charAt(j) - '0'));
            }
//            混合
            for (int j = 0; j < 4; j ++){
                temp_2 = temp_2 + new_temp_1.substring(j, j + 1) + temp_1.substring((j + 2) % 4, (j + 2) % 4 + 1);
            }


//            扩展至16位
            int XOR_value_2 = temp_2.charAt(0) ^ temp_2.charAt(1) ^temp_2.charAt(2) ^temp_2.charAt(3) ^ temp_2.charAt(4) ^ temp_2.charAt(5) ^temp_2.charAt(6) ^temp_2.charAt(7);
            for (int j = 0; j < 8; j ++){
                new_temp_2 = new_temp_2 + Integer.toString(XOR_value_2 ^ (temp_2.charAt(j) - '0'));
            }
//            混合.
            for (int j = 0; j < 8; j ++){
                bin_long_key = bin_long_key + new_temp_2.substring(j, j + 1) + temp_2.substring((j + 4) % 8, (j + 4) % 8 + 1);
            }


        }

        for (int i = 0; i < 31; i ++){
            long_key = long_key + Integer.toHexString(Integer.parseInt(bin_long_key.substring(4 * i + 2, 4 * i + 6),2));
        }
        long_key = long_key + Integer.toHexString(Integer.parseInt(bin_long_key.substring(30,32) + bin_long_key.substring(0,2)));

        return long_key;
    }

//    public String[] ENC(String[] all_plaintext){
//        String[] all_ciphertext = new String[all_plaintext.length];
//        for (int i = 0; i < all_plaintext.length; i ++){
//            String plaintext = all_plaintext[i];
//            all_ciphertext[i] = ENC(plaintext);
//        }
//        return all_ciphertext;
//    }

    public String stringENC(String plaintext){
        String[] Plaintext = new String[plaintext.length()];
        String[] hexCiphertext = new String[Plaintext.length];
        for (int i = 0; i < Plaintext.length; i ++){
            Plaintext[i] = plaintext.substring(i, i + 1);
        }
        for (int i = 0; i < hexCiphertext.length; i ++){
            String SinglePlaintext = Plaintext[i];
            String hexSinglePlaintext = Encrypt.toHexString(SinglePlaintext);
            String hexSingleCiphertext = hexSinglePlaintext.substring(0,2) + strenc(hexSinglePlaintext.substring(2,4))
                    + hexSinglePlaintext.substring(4,6) + strenc(hexSinglePlaintext.substring(6,8));
            hexCiphertext[i] = hexSingleCiphertext;
        }
        String ciphertext = "";
        for (int i = 0; i < hexCiphertext.length; i ++){
            ciphertext = ciphertext + hexCiphertext[i];
        }

        return ciphertext;
    }

//    public String[] DEC(String[] all_ciphertext){
//        String[] all_plaintext = new String[all_ciphertext.length];
//        for (int i = 0; i < all_ciphertext.length; i ++){
//            String ciphertext = all_ciphertext[i];
//            all_plaintext[i] = DEC(ciphertext);
//        }
//        return all_plaintext;
//    }

    public String stringDEC(String ciphertext){
        String[] plaintext = new String[ciphertext.length() / 8];
        for (int i = 0; i < plaintext.length; i ++){
            String hexSingleCiphertext = ciphertext.substring(8 * i, 8 * i + 8);
            String hex_plaintext = hexSingleCiphertext.substring(0,2) + strdec(hexSingleCiphertext.substring(2,4))
                    + hexSingleCiphertext.substring(4,6) + strdec(hexSingleCiphertext.substring(6,8));
            plaintext[i] = Encrypt.toUTF_8(hex_plaintext);
        }
        String single_plaintext = "";
        for (int i = 0 ; i < plaintext.length; i ++){
            single_plaintext = single_plaintext + plaintext[i];
        }

        return single_plaintext;

    }

    public String intENC(String plaintext){
        String ciphertext = "";
        for (int i = 0; i < plaintext.length(); i ++){
            if (Character.isDigit(plaintext.charAt(i))){
                int value = Integer.parseInt(plaintext.substring(i, i + 1));
                int arg = Integer.valueOf(this.intArg.substring(i % 3, i % 3 + 1));
                value = (value + arg) % 10;
                ciphertext = ciphertext + Integer.toString(value);
            }
            else {
                ciphertext = ciphertext + plaintext.charAt(i);
            }
        }
        return ciphertext;
    }

    public String intDEC(String ciphertext){
        String plaintext = "";
        for (int i = 0; i < ciphertext.length(); i ++){
            if (Character.isDigit(ciphertext.charAt(i))){
                int value = Integer.parseInt(ciphertext.substring(i, i + 1));
                int arg = Integer.valueOf(this.intArg.substring(i % 3, i % 3 + 1));
                value = value - arg;
                if (value < 0){
                    value = value + 10;
                }
                plaintext = plaintext + Integer.toString(value);
            }
            else {
                plaintext = plaintext + ciphertext.charAt(i);
            }
        }
        return plaintext;
    }


    private String strenc(String seed){
        int value = Integer.parseInt(seed,16);
        value = value * this.strArg % 256;
        String result = String.format("%02x", value);
        return result;
    }
    private String strdec(String seed){
        int value = Integer.parseInt(seed,16);
        while (value % this.strArg != 0){
            value = value + 256;
        }
        value = value / this.strArg;
        String result = String.format("%02x",value);
        return result;
    }


}
