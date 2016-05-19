package com.app;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.app.AssetStatusEnum;

@DatabaseTable(tableName = "asset")
public class Asset {

  @DatabaseField(generatedId = true)
  private Long id;
  @DatabaseField
  private String Name;
  @DatabaseField
  private Long categoryId;
  @DatabaseField
  private Long subCategoryID;
  @DatabaseField
  private String donor;
  @DatabaseField
  private Long locationId;
  @DatabaseField
  private int yearsOfUse;
  @DatabaseField
  private AssetStatusEnum status;
  @DatabaseField
  private Long price;
  
  // Should not be persisted
  private transient Long currentValue;
  
  public Asset() {
  }
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return Name;
  }
  public void setName(String name) {
    Name = name;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Long getSubCategoryID() {
    return subCategoryID;
  }

  public void setSubCategoryID(Long subCategoryID) {
    this.subCategoryID = subCategoryID;
  }

  public String getDonor() {
    return donor;
  }
  public void setDonor(String donor) {
    this.donor = donor;
  }

  public Long getLocationId() {
    return locationId;
  }

  public void setLocationId(Long locationId) {
    this.locationId = locationId;
  }

  public int getYearsOfUse() {
    return yearsOfUse;
  }
  public void setYearsOfUse(int yearsOfUse) {
    this.yearsOfUse = yearsOfUse;
  }
  public AssetStatusEnum getStatus() {
    return status;
  }
  public void setStatus(AssetStatusEnum status) {
    this.status = status;
  }
  public Long getPrice() {
    return price;
  }
  public void setPrice(Long price) {
    this.price = price;
  }
  public Long getCurrentValue() {
    return currentValue;
  }
  public void setCurrentValue(Long currentValue) {
    this.currentValue = currentValue;
  }
  
}
