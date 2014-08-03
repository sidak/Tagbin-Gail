package com.sidak.tagbinGail;

import java.util.ArrayList;

import android.app.Application;

public class MyApplication extends Application {
	private String name;
	private String companyName;
	private String state;
	private String email;
	private String phoneNo;
	private String imageUrl;
	private String industry;
	private ArrayList<String> materials;
	private ArrayList<String> products;
	private String consumption;
    private static MyApplication mInstance = null;
	
	public static MyApplication getInstance(){
        if(mInstance == null)
        {
            mInstance = new MyApplication();
        }
        return mInstance;
    }
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	
	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}
	public String getCompanyName(){
		return companyName;
	}
	
	public void setState(String state){
		this.state=state;
	}
	public String getState(){
		return state;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return email;
	}
	
	public void setPhoneNo(String phoneNo){
		this.phoneNo=phoneNo;
	}
	public String getPhoneNo(){
		return phoneNo;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl=imageUrl;
	}
	public String getImageUrl(){
		return imageUrl;
	}
	
	public void setIndustry(String industry){
		this.industry=industry;
	}
	public String getIndustry(){
		return industry;
	}
	
	public void setConsumption(String consum){
		this.consumption=consum;
	}
	public String getConsumption(){
		return consumption;
	}
	
	public void addMaterial(String mat){
		if(materials!=null){
		materials.add(mat);
		}else{
			materials= new ArrayList<String>();
			materials.add(mat);
		}
	}
	public void deleteMaterial(String mat){
		materials.remove(mat);
	}
	
	public void addProduct(String pro){
		products.add(pro);
	}
	public void deleteProducts(String pro){
		products.remove(pro);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		products = new ArrayList<String>();
		materials= new ArrayList<String>();
		
	}
}
