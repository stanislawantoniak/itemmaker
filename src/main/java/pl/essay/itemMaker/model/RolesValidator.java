package pl.essay.itemMaker.model;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class RolesValidator {
	@NotEmpty
	private List roles;

	
	public List getFavourite() {
		return roles;
	}

	public void setFavourite(List r) {
		this.roles = r;
	}
}
