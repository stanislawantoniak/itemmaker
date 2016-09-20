package pl.essay.itemMaker.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.essay.itemMaker.model.Item;
import pl.essay.itemMaker.model.ItemComponent;
import pl.essay.itemMaker.service.ItemService;

@Controller
public class ItemController extends BaseController {

	protected static final Logger logger = LoggerFactory.getLogger(ItemController.class);

	private ItemService itemService;

	@Autowired(required=true)
	@Qualifier(value="itemService")
	public void setItemService(ItemService ps){
		this.itemService = ps;
	}
	
	@InitBinder
	public void registerCustomEditors(WebDataBinder binder) {
	    binder.registerCustomEditor(Item.class, null, new ItemMakerPropertyEditor(this.itemService));
	}
	
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String listItems(Model model) {

		this.addGenericDataToModel(model);

		List<Item> theList = (List<Item>) this.itemService.listItems();
		logger.info("list size: "+theList.size());

		model.addAttribute("itemsList", theList);

		return "itemList";
	}

	@RequestMapping(value= "/item/update", method = RequestMethod.POST)
	public String updateItem(@Valid @ModelAttribute("item") Item item,  
			BindingResult result, //must follow modelattribute!!!!
			RedirectAttributes redirectAttributes,
			Model model){
		logger.info("update item data: "+item);
		logger.info("has errors?:"+result.hasFieldErrors());
		if (result.hasErrors()) {
			this.addGenericDataToModel(model);
			//disable language selector - because staying on the same url and it doesnt support RequestMethod.GET
			model.addAttribute("languageSelectorClass","disabled"); 
			
			model.addAttribute("item", item);
			model.addAttribute("itemComponents", this.itemService.listItemComponent(item.getId()));
			return "itemEdit";
		} else {
			if (item.getId() == 0)
				this.itemService.addItem(item);
			else
				this.itemService.updateItem(item);
			return  "redirect:/items" ;
		}
	}

	//update add component
	@RequestMapping(value= "/itemComponent/update", method = RequestMethod.POST)
	public String updateItemComponent(@Valid @ModelAttribute("itemComponent") ItemComponent itemComponent,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes){

		if (result.hasErrors()){
			this.addGenericDataToModel(model);
			
			System.out.println("component: "+itemComponent.getComponent());
			System.out.println("parent: "+itemComponent.getParent());
			
			//disable language selector - because staying on the same url and it doesnt support RequestMethod.GET
			model.addAttribute("languageSelectorClass","disabled"); 
			
			model.addAttribute("allItems", getItemListForSelect());
			model.addAttribute("item", itemComponent.getParent());
			model.addAttribute("itemComponent", itemComponent);
			
			return "componentEdit";
		} else {

			this.itemService.addItemComponent(itemComponent);

			return  "redirect:/edit/"+itemComponent.getParent().getId();
		}
	}

	@RequestMapping("/item/deleteComponent/{id}")
	public String deleteItemComponent(@PathVariable("id") int id){
		ItemComponent ic = this.itemService.getItemComponent(id);
		Item item = ic.getParent();
		if( id != 0){ //never should be zero 
			this.itemService.removeItemComponent(id);
		}
		return "redirect:/item/edit/"+item.getId();
	}

	@RequestMapping("/item/addComponent/{id}")
	public String addItemComponent(@PathVariable("id") int itemId, Model model){
		logger.info("from controller.addItemComponent");

		this.addGenericDataToModel(model);

		ItemComponent ic = new ItemComponent();
		Item item = this.itemService.getItemById(itemId);
		ic.setParent(item);
		model.addAttribute("allItems", getItemListForSelect());
		model.addAttribute("item", item);
		model.addAttribute("itemComponent", ic);
		return "componentEdit";
	}

	protected Map<String, String> getItemListForSelect() {
		Map<String,String> allItems = new LinkedHashMap<String, String>();
		Map<String,String> notSorted = new TreeMap<String, String>();
		for (Item i: this.itemService.listItems())
			notSorted.put(i.getName(), ""+i.getId()); //sort by names
		for (Map.Entry<String, String> i : notSorted.entrySet())
			allItems.put(i.getValue(), i.getKey()+" (#"+i.getValue()+")");//put in linked map to save order
		return allItems;
	}
	@RequestMapping("/item/editComponent/{id}")
	public String editItemComponent(@PathVariable("id") int id, Model model){
		this.addGenericDataToModel(model);

		ItemComponent ic = this.itemService.getItemComponent(id);
		Item item = ic.getParent();
		model.addAttribute("allItems", getItemListForSelect());
		model.addAttribute("item", item);
		model.addAttribute("itemComponent", ic);
		return "componentEdit";
	}

	@RequestMapping("/remove/{id}")
	public String removeItem(@PathVariable("id") int id){
		this.itemService.removeItem(id);
		return "redirect:/items";
	}

	@RequestMapping("/item/edit/{id}")
	public String editItem(@PathVariable("id") int id, Model model){
		logger.info("from controller.editItem");
		this.addGenericDataToModel(model);

		model.addAttribute("item", (id != 0 ? this.itemService.getItemById(id) : new Item()));
		if (id != 0 )
			model.addAttribute("itemComponents", this.itemService.listItemComponent(id));
		return "itemEdit";
	}
}
