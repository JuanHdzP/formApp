package com.juan.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

public class MayusEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(text.toUpperCase().trim());
	}

}
