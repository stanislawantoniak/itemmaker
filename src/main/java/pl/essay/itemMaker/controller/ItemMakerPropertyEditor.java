package pl.essay.itemMaker.controller;

import java.beans.PropertyEditorSupport;

import pl.essay.itemMaker.model.Item;
import pl.essay.itemMaker.service.ItemService;

public class ItemMakerPropertyEditor extends PropertyEditorSupport {

	private ItemService itemService;

	public ItemMakerPropertyEditor(ItemService ps){
		this.itemService = ps;
	}
	//transform item id into Item object 
    public void setAsText(String id) {
    	//System.out.println("inside property editor id: "+id);
    	//System.out.println(this.itemService);
    	
    	try {
    		int i = Integer.valueOf(id);
    		Item item = this.itemService.getItemById(Integer.valueOf(id)); 
            setValue(item);
            System.out.println(item);
            
    	} catch (Exception e) {
    		setValue(null);
    	}
    	    	
    }
}