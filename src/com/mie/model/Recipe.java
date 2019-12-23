package com.mie.model;


public class Recipe {
	/**
	 * This class contains all of the relevant information, and getter/setter
	 * methods for the recipe object.
	 */

	private int recipeid;
	private String recipeName;
	private String recipeDescrip;
	private String recipeType;
	private int recipeTime;
	private String recipeRestrict;
	private String recipeInstruction;
	private String rawMaterial;
	private double measurement;
	private String unit;
	private String link;
	private String ingredients;
	
	
	public int getRecipeid() {
		return recipeid;
	}

	public void setRecipeid(int id) {
		this.recipeid = id;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String name) {
		this.recipeName = name;
	}

	public String getRecipeDescrip() {
		return recipeDescrip;
	}

	public void setRecipeDescrip(String description) {
		this.recipeDescrip = description;
	}

	public String getRecipeType(){
		return recipeType;
	}
	
	public void setRecipeType(String type){
		this.recipeType = type;
	}

	public int getRecipeTime() {
		return recipeTime;
	}

	public void setRecipeTime(int time) {
		this.recipeTime = time;
	}
	
	public String getRecipeRestrict(){
		return recipeRestrict;
	}
	
	public void setRecipeRestrict(String restriction){
		this.recipeRestrict = restriction;
	}
	
	public String getRecipeInstruction(){
		return recipeInstruction;
	}
	
	public void setRecipeInstruction(String instruction){
		this.recipeInstruction = instruction;
		
	}
	
	public String getRawMaterials(){
		return rawMaterial;
	}
	
	public void setRawMaterials(String rawMaterial){
		this.rawMaterial = rawMaterial;
	}
	
	public double getMeasurement(){
		return measurement;
	}
	
	public void setMeasurement(double measurement){
		this.measurement = measurement;
	}
	
	public String getUnit(){
		return unit;
	}
	
	public void setUnit(String unit){
		this.unit = unit;
	}
	
	public String getLink(){
		return link;
	}
	
	public void setLink(String link){
		this.link = link;
	}
	
	public void setIngredients(String ingredients){
		this.ingredients = ingredients;
	}
	
	public String getIngredients(){
		return ingredients;
	}

	@Override
	public String toString() {
		return "Recipe [recipeid=" + recipeid + ", recipeName=" + recipeName
				+ ", recipeDescription=" + recipeDescrip + ", recipeType=" + recipeType + ", recipeTime="
				+ recipeTime + ", recipeRestriction=" + recipeRestrict + ", recipeInstruction=" + recipeInstruction 
				+ ", rawMaterial=" + rawMaterial +", measurement = " +measurement+",unit =" +unit +"]";
	}

}
