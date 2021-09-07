package DAY6_POJO;

import com.google.gson.annotations.SerializedName;

public class Contact{

	@SerializedName("emailAddress")
	private String emailAddress;

	@SerializedName("contactId")
	private int contactId;

	@SerializedName("phone")
	private String phone;

	@SerializedName("premanentAddress")
	private String premanentAddress;

	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress(){
		return emailAddress;
	}

	public void setContactId(int contactId){
		this.contactId = contactId;
	}

	public int getContactId(){
		return contactId;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setPremanentAddress(String premanentAddress){
		this.premanentAddress = premanentAddress;
	}

	public String getPremanentAddress(){
		return premanentAddress;
	}

	@Override
 	public String toString(){
		return 
			"Contact{" + 
			"emailAddress = '" + emailAddress + '\'' + 
			",contactId = '" + contactId + '\'' + 
			",phone = '" + phone + '\'' + 
			",premanentAddress = '" + premanentAddress + '\'' + 
			"}";
		}
}