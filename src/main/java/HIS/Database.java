package HIS;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class Database {
    static class Processing {
        static String[] seek(String IDnumber, String password) {
            String[] result=new String[6];
            JDBCFacade jdbc = new JDBCFacade();
            try {
                jdbc.open("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/hospital?serverTimezone=GMT%2B8", "root", "123456789hHH");

                String sql="select * from patient_data";
                ResultSet rs = jdbc.executeQuery(sql);
                while (rs.next()) {
                    String stored_IDnumber=rs.getString("IDnumber");
                    String stored_password=rs.getString("password");
                    if(IDnumber.equals(stored_IDnumber) && password.equals(stored_password)){
                        result[0]=IDnumber;
                        result[1]=rs.getString("name");
                        result[2]=rs.getString("sex");
                        result[3]=rs.getString("brithdate");
                        result[4]=rs.getString("phone_number");
                        String condition=rs.getString("condition");
                        result[5]= Database.Processing.decode(condition);
                        return result;
                    }else if(IDnumber.equals(stored_IDnumber) && !(password.equals(stored_password))){
                        result[0]="你输入的密码有误！";
                        return result;
                    }
                }
                result[0]="该用户不存在！";
                return result;
            } catch (Exception e) {
                result[0]="发生未知异常！";
                return result;
            }finally {
                jdbc.close();
            }
        }

        static void register(String IDnumber, String name, String sex, String brithdate, String phone_number, String condition, String password) {
            JDBCFacade jdbc = new JDBCFacade();
            try {
                jdbc.open("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/hospital?serverTimezone=GMT%2B8", "root", "123456789hHH");

                condition=encrypt(condition);
                String sql="insert into patient_data values('"+IDnumber+"','"+name+"','"+sex+"','"+brithdate+"','"+phone_number+"','"+condition+"','"+password+"')";
                jdbc.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                jdbc.close();
            }
        }

        static void update(String IDnumber,String newMessage,int index) {
            JDBCFacade jdbc = new JDBCFacade();
            String sql="";
            try {
                jdbc.open("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/hospital?serverTimezone=GMT%2B8", "root", "123456789hHH");
                if(index==1){
                    sql="update patient_data set name='"+newMessage+"' where IDnumber='"+IDnumber+"'";
                    jdbc.executeUpdate(sql);
                }else if(index==2){
                    sql="update patient_data set phone_number='"+newMessage+"' where IDnumber='"+IDnumber+"'";
                    jdbc.executeUpdate(sql);
                }else if(index==3){
                    newMessage=encrypt(newMessage);
                    sql="update patient_data set `condition`=? where IDnumber=?";
                    PreparedStatement pstmt =jdbc.conn.prepareStatement(sql);
                    pstmt.setString(1, newMessage);
                    pstmt.setString(2, IDnumber);
                    pstmt.execute();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                jdbc.close();
            }
        }

        static boolean ifExist(String IDnumber) {
            JDBCFacade jdbc = new JDBCFacade();
            try {
                jdbc.open("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/hospital?serverTimezone=GMT%2B8", "root", "123456789hHH");

                String sql="select * from patient_data";
                ResultSet rs = jdbc.executeQuery(sql);
                while (rs.next()) {
                    String stored_IDnumber=rs.getString("IDnumber");
                    if(IDnumber.equals(stored_IDnumber)){
                        return true;
                    }
                }
                return false;
            } catch (Exception e) {
                UnknownError unknownError=new UnknownError();
                unknownError.go();
                return false;
            }finally {
                jdbc.close();
            }
        }


        static String decode(String condition) throws UnsupportedEncodingException {
            byte[] str2byte=new byte[condition.length()];
            for(int i=0;i<condition.length();i++){
                str2byte[i]=(byte)condition.charAt(i);
            }
            for(int i=0;i<str2byte.length;i++){
                str2byte[i]=(byte)(str2byte[i]-Math.pow(i,2));
                if(str2byte[i]<-128){
                    str2byte[i]=(byte)(str2byte[i]+256);
                }
            }
            return new String(str2byte,"utf-8");
        }

        static String encrypt(String condition) throws UnsupportedEncodingException {
            byte[] str2byte=condition.getBytes("utf-8");
            for(int i=0;i<str2byte.length;i++){
                str2byte[i]=(byte)(str2byte[i]+Math.pow(i,2));
                if(str2byte[i]>127){
                    str2byte[i]=(byte)(str2byte[i]-256);
                }
            }
            String new_condition="";
            for(int i=0;i<str2byte.length;i++){
                new_condition=new_condition+(char)str2byte[i];
            }
            return new_condition;
        }
    }
}
