package com.app;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assetcategory")
public class AssetCategory {
  public AssetCategory() {
    
  }
  @DatabaseField(generatedId = true)
  private Long id;
  
  @DatabaseField
  private String name;
  
  @DatabaseField
  private String depType;
  
  @ForeignCollectionField
  private ForeignCollection<ItemSubCategory> subCategory;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  
  public ForeignCollection<ItemSubCategory> getSubCategory() {
    return subCategory;
  }
  public void setSubCategory(ForeignCollection<ItemSubCategory> subCategory) {
    this.subCategory = subCategory;
  }
  public String getDepType() {
    return depType;
  }
  public void setDepType(String depType) {
    this.depType = depType;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

}
