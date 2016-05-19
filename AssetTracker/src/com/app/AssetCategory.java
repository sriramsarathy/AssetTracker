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
  
  @ForeignCollectionField
  private ForeignCollection<ItemSubCategory> subCategory;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
   
}
