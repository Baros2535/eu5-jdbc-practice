package DAY6_POJO;

import com.google.gson.annotations.SerializedName;

public class Address{

	@SerializedName("zipCode")
	private int zipCode;

	@SerializedName("city")
	private String city;

	@SerializedName("street")
	private String street;

	@SerializedName("state")
	private String state;

	@SerializedName("addressId")
	private int addressId;

	public void setZipCode(int zipCode){
		this.zipCode = zipCode;
	}

	public int getZipCode(){
		return zipCode;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return street;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setAddressId(int addressId){
		this.addressId = addressId;
	}

	public int getAddressId(){
		return addressId;
	}

	@Override
 	public String toString(){
		return 
			"Address{" + 
			"zipCode = '" + zipCode + '\'' + 
			",city = '" + city + '\'' + 
			",street = '" + street + '\'' + 
			",state = '" + state + '\'' + 
			",addressId = '" + addressId + '\'' + 
			"}";
		}
}