package com.app;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "location")
public class Location {

  public Location () {
    
  }
  @DatabaseField(generatedId = true)
  private int id;
  
  @DatabaseField
  private String name;
  @DatabaseField
  private String city;
  @DatabaseField
  private String state;
  @DatabaseField
  private String address;
  @DatabaseField
  private String latitude;
  @DatabaseField
  private String longitude;
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getLatitude() {
    return latitude;
  }
  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }
  public String getLongitude() {
    return longitude;
  }
  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }
}
