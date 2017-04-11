/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.utility;

/**
 *
 * @author lujamanandhar
 */
public class CheckListItem {

  private String label;
  private int id=0;
  private boolean isSelected = false;

  
  public CheckListItem(String label) {
    this.label = label;
    
  }
  public CheckListItem(int id, String label){
      this.label =label;
      this.id = id;
  }
  
  public int getId(){
      return id;
  }
  public void setId(int id){
      this.id = id;
  }
  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean isSelected) {
    this.isSelected = isSelected;
  }
  
  @Override
  public String toString() {
    return label;
  }
}
