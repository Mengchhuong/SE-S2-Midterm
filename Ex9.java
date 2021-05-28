
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Ex9{

    public static void main(String[] args) throws Exception{
        Student s = new Student();
       
        s.dataInput();
        System.out.println("(ID:"+s.getId()+"\t"+"Name: "+s.getName()+"\t"+"City: "+ s.getCity()+"\t"+"Contact: "+ s.getTelephone()+"\t"+"Group: "+s.getGroupName()+"\t"+"DOB: "+s.getDoB()+")");
        
    }

}
class Student{
    String id;
    String name;
    Date DoB;
    String telephone;
    String city;
    String groupName;

    Scanner sc = new Scanner(System.in);
    public void dataInput() throws Exception{
        System.out.print("Enter your ID: ");
        setId(sc.nextLine());
        System.out.print("Enter your name: ");
        setName(sc.nextLine());
        System.out.print("Input date of birth(dd/mm/yyyy): ");
        String dobs = sc.nextLine();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dobs);
        setDoB(date);
        System.out.print("Enter your telephone by format[+855......]: ");
        setTelephone(sc.nextLine());
        System.out.print("Enter your city: ");
        setCity(sc.nextLine());
        System.out.print("Enter your Group Name by format[I4A, I3CA, T1B, T2AB]: ");
        setGroupName(sc.nextLine());

    }



    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        FieldException.checkNullEmptyBlank(id, "ID");
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        FieldException.checkNullEmptyBlank(name, "Name");
        this.name = name;
    }

    public Date getDoB() {
        return this.DoB;
    }

    public void setDoB(Date DoB) throws BirthDateException{
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 15);
        Date twelveYearsOld = cal.getTime();
        if(DoB.compareTo(twelveYearsOld) <= 0) {
            this.DoB = DoB;
        }else
            throw new BirthDateException();
    }

    public String getTelephone() {

        return this.telephone;
    }

    public void setTelephone(String telephone) throws PhoneNumberExeption {
     
        FieldException.checkNullEmptyBlank(telephone, "Telephone");

        Pattern pattern = Pattern.compile("(\\+855|0)[0-9]{8,9}$");
        Matcher matcher = pattern.matcher(telephone);
        boolean matchFound = matcher.find();
        if(matchFound){
            this.telephone = telephone;
        }else throw new PhoneNumberExeption();
        
    }


    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        FieldException.checkNullEmptyBlank(city, "City");
        this.city = city;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) throws GroupException {
        FieldException.checkNullEmptyBlank(groupName, "Group Name");
        // this.groupName = groupName;
        Pattern pattern = Pattern.compile("(I[1-5]|T[1-2])[A-Z]{1,2}$");
        Matcher match = pattern.matcher(groupName);
        boolean matchFound = match.find();
        if(matchFound){
            this.groupName = groupName;
        }else{
            throw new GroupException();
        }
    }

}

class FieldException {
    public static void checkNullEmptyBlank(String field, String label){
        
        if(field.isEmpty() || field.isBlank()){
            throw new IllegalArgumentException(label+ " should not be empty or blank");
        }
    }
}

class BirthDateException extends Exception{
    public BirthDateException(){
        super("Invalid date of birth.");
    }
}
// class CheckTel extends Exception {
//     public TelException(){
//         super("Invalid telephone");
//     }    
// }

class PhoneNumberExeption extends Exception{
    public PhoneNumberExeption(){
        super("Invalid phone number");
    }
}

class GroupException extends Exception{
    public GroupException(){
        super("Group Invalid");
    }
}