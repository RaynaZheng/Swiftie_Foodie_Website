package com.mie.model;


public class Raw_Material {
	/**
	 * This class contains all of the relevant information, and getter/setter
	 * methods for the Member object.
	 */
	private int material_id;
	private String raw_material;
	private boolean valid;

	public int getMaterialid() {
		return material_id;
	}

	public void setMaterialid(int material_id) {
		this.material_id = material_id;
	}

	public String getRawmaterial() {
		return raw_material;
	}

	public void setRawmaterial(String raw_material) {
		this.raw_material = raw_material;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean newValid) {
		valid = newValid;
	}

	@Override
	public String toString() {
		return "Raw Material [Material_ID = " + material_id + ", Raw_Material =" + raw_material + "]";
	}
}
