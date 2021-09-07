package DAY6_POJO;

import com.google.gson.annotations.SerializedName;

public class StudentsItem{

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("gender")
	private String gender;

	@SerializedName("subject")
	private String subject;

	@SerializedName("batch")
	private int batch;

	@SerializedName("section")
	private String section;

	@SerializedName("birthDate")
	private String birthDate;

	@SerializedName("studentId")
	private int studentId;

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("password")
	private String password;

	@SerializedName("joinDate")
	private String joinDate;

	@SerializedName("major")
	private String major;

	@SerializedName("admissionNo")
	private String admissionNo;

	@SerializedName("contact")
	private Contact contact;

	@SerializedName("company")
	private Company company;

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setSubject(String subject){
		this.subject = subject;
	}

	public String getSubject(){
		return subject;
	}

	public void setBatch(int batch){
		this.batch = batch;
	}

	public int getBatch(){
		return batch;
	}

	public void setSection(String section){
		this.section = section;
	}

	public String getSection(){
		return section;
	}

	public void setBirthDate(String birthDate){
		this.birthDate = birthDate;
	}

	public String getBirthDate(){
		return birthDate;
	}

	public void setStudentId(int studentId){
		this.studentId = studentId;
	}

	public int getStudentId(){
		return studentId;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setJoinDate(String joinDate){
		this.joinDate = joinDate;
	}

	public String getJoinDate(){
		return joinDate;
	}

	public void setMajor(String major){
		this.major = major;
	}

	public String getMajor(){
		return major;
	}

	public void setAdmissionNo(String admissionNo){
		this.admissionNo = admissionNo;
	}

	public String getAdmissionNo(){
		return admissionNo;
	}

	public void setContact(Contact contact){
		this.contact = contact;
	}

	public Contact getContact(){
		return contact;
	}

	public void setCompany(Company company){
		this.company = company;
	}

	public Company getCompany(){
		return company;
	}

	@Override
 	public String toString(){
		return 
			"StudentsItem{" + 
			"lastName = '" + lastName + '\'' + 
			",gender = '" + gender + '\'' + 
			",subject = '" + subject + '\'' + 
			",batch = '" + batch + '\'' + 
			",section = '" + section + '\'' + 
			",birthDate = '" + birthDate + '\'' + 
			",studentId = '" + studentId + '\'' + 
			",firstName = '" + firstName + '\'' + 
			",password = '" + password + '\'' + 
			",joinDate = '" + joinDate + '\'' + 
			",major = '" + major + '\'' + 
			",admissionNo = '" + admissionNo + '\'' + 
			",contact = '" + contact + '\'' + 
			",company = '" + company + '\'' + 
			"}";
		}
}