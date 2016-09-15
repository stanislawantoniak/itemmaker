package pl.essay.itemMaker.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.essay.itemMaker.dao.ItemDaoImpl;
import pl.essay.itemMaker.model.Item;
import pl.essay.itemMaker.model.ItemComponent;
import pl.essay.itemMaker.service.ItemService;

@Controller
public class ItemController {

	private ItemService itemService;
	
	private static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);

	@Autowired(required=true)
	@Qualifier(value="itemService")
	public void setItemService(ItemService ps){
		this.itemService = ps;
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String listItems(Model model) {
		logger.info("from controller.items");
		model.addAttribute("item", new Item());
		model.addAttribute("itemsList", this.itemService.listItems());
		return "itemList";
	}

	@RequestMapping(value= "/item/add", method = RequestMethod.POST)
	public String addItem(@ModelAttribute("item") Item p,  RedirectAttributes redirectAttributes){
		int id = this.itemService.addItem(p);
		logger.info("from controller.addItem: "+p);
		String returnString =
				( p.getIsComposed() ? "redirect:/edit/"+id
						: "redirect:/items" );
		System.out.println(returnString);
		return returnString;
	}
	
	@RequestMapping(value= "/item/update", method = RequestMethod.POST)
	public String updateItem(@ModelAttribute("item") Item p,  RedirectAttributes redirectAttributes){
		this.itemService.updateItem(p);
		return  "redirect:/items" ;
	}
	
	//update add component
	@RequestMapping(value= "/itemComponent/update", method = RequestMethod.POST)
	public String updateItemComponent(//@ModelAttribute("itemComponent") ItemComponent ic,
			@RequestParam("id") int icId,
			@RequestParam("parentItem.id") int parentId,
			@RequestParam("componentItem") int itemId,
			@RequestParam("quantity") int quantity,
			RedirectAttributes redirectAttributes){

		ItemComponent itemComponent;
		if (icId == 0){
			itemComponent = new ItemComponent();
			itemComponent.setParentItem(this.itemService.getItemById(parentId));
		}
		else
			itemComponent = this.itemService.getItemComponent(icId);
		logger.info("from controller.updateComponent: "+itemComponent);
		itemComponent.setQuantity(quantity);
		itemComponent.setComponentItem( this.itemService.getItemById(itemId));
		this.itemService.addItemComponent(itemComponent);

		return  "redirect:/edit/"+itemComponent.getParentItem().getId();
	}
	
	@RequestMapping("/item/deleteComponent/{id}")
	public String deleteItemComponent(@PathVariable("id") int id){
		ItemComponent ic = this.itemService.getItemComponent(id);
		Item item = ic.getParentItem();
		if( id != 0){ //never should be zero 
			this.itemService.removeItemComponent(id);
		}
		return "redirect:/edit/"+item.getId();
	}
	
	@RequestMapping("/item/addComponent/{id}")
	public String addItemComponent(@PathVariable("id") int itemId, Model model){
		logger.info("from controller.addItemComponent");
		ItemComponent ic = new ItemComponent();
		Item item = this.itemService.getItemById(itemId);
		ic.setParentItem(item);
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
		System.out.println("from controller.editItemComponent");
		ItemComponent ic = this.itemService.getItemComponent(id);
		Item item = ic.getParentItem();
	
		
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

	@RequestMapping("/edit/{id}")
	public String editItem(@PathVariable("id") int id, Model model){
		logger.info("from controller.editItem");
		model.addAttribute("item", this.itemService.getItemById(id));
		model.addAttribute("itemComponents", this.itemService.listItemComponent(id));
		return "itemEdit";
	}
}
