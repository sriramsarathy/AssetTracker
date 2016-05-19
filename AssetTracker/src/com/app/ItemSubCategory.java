package com.app;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assetsubcategory")
public class ItemSubCategory {
  
  public ItemSubCategory () {
    
  }
  @DatabaseField(generatedId = true)
  private Long id; 
  @DatabaseField
  private String name;
  
  @DatabaseField(canBeNull = false, foreign = true)
  private AssetCategory assetCategory;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public AssetCategory getAssetCategory() {
    return assetCategory;
  }
  public void setAssetCategory(AssetCategory assetCategory) {
    this.assetCategory = assetCategory;
  } 
  
  
}
