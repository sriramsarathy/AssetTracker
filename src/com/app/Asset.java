package com.app;

import java.math.BigDecimal;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "asset")
public class Asset {

  @DatabaseField(generatedId = true)
  private Long id;
  @DatabaseField
  private String Name;
  @DatabaseField
  private Long categoryId;
  @DatabaseField
  private Long subCategoryId;
  @DatabaseField
  private String donor;
  @DatabaseField
  private Long locationId;
  @DatabaseField
  private int yearsOfUse;
  @DatabaseField
  private AssetStatusEnum status;
  @DatabaseField
  private BigDecimal price;
  @DatabaseField
  private Date creationDate;
  
  // Should not be persisted
  private transient BigDecimal currentValue;
  
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

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Long getSubCategoryId() {
    return subCategoryId;
  }

  public void setSubCategoryId(Long subCategoryId) {
    this.subCategoryId = subCategoryId;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getCurrentValue() {
    return currentValue;
  }

  public void setCurrentValue(BigDecimal currentValue) {
    this.currentValue = currentValue;
  }

}
