package DAY6_POJO;

import com.google.gson.annotations.SerializedName;

public class Company{

	@SerializedName("companyId")
	private int companyId;

	@SerializedName("address")
	private Address address;

	@SerializedName("companyName")
	private String companyName;

	@SerializedName("title")
	private String title;

	@SerializedName("startDate")
	private String startDate;

	public void setCompanyId(int companyId){
		this.companyId = companyId;
	}

	public int getCompanyId(){
		return companyId;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getCompanyName(){
		return companyName;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public String getStartDate(){
		return startDate;
	}

	@Override
 	public String toString(){
		return 
			"Company{" + 
			"companyId = '" + companyId + '\'' + 
			",address = '" + address + '\'' + 
			",companyName = '" + companyName + '\'' + 
			",title = '" + title + '\'' + 
			",startDate = '" + startDate + '\'' + 
			"}";
		}
}