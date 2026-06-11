import java.util.ArrayList;

interface Vegan {
	void madeOf();
}

interface Washable {
	void howToWash();
}

public abstract class Item {
	private double vat;
	private double basePrice;
	
	public double getVat() {
		return vat;
	}
	
	public void setVat(double vat) {
		this.vat = vat;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	abstract double calculatePrice();
}

abstract class Clothing extends Item implements Washable {
	Clothing(){
		setVat(0.18);
	}
}

abstract class Food extends Item {
	Food(){
		setVat(0.08);
	}
}

class Dairy extends Food {
	Dairy(){
		this(8.0);
	}
	Dairy(double basePrice){
		super.setBasePrice(basePrice);
	}
	
	public double calculatePrice() {
		return (super.getBasePrice() + (super.getBasePrice() * super.getVat())) * 1.3;
	}
}

class Vegetable extends Food implements Vegan, Washable {
	Vegetable() {
		this(10.0);
	}
	Vegetable(double basePrice) {
		super.setBasePrice(basePrice);
	}
	
	public double calculatePrice() {
		return (super.getBasePrice() + (super.getBasePrice() * super.getVat())) * 1.25;
	}
	
	public void howToWash() {
		System.out.println("Wash Vegetable with warm water.");
	}
	public void madeOf() {
		System.out.println("It is made only of vegetables.");
	}
}

class Fruit extends Food implements Vegan, Washable {
	Fruit () {
		this(6.0);
	}
	Fruit (double basePrice) {
		super.setBasePrice(basePrice);
	}
	
	public double calculatePrice() {
		return (super.getBasePrice() + (super.getBasePrice() * super.getVat())) * 1.2;
	}
	
	public void howToWash() {
		System.out.println("Wash Fruit with cold water.");
	}
	public void madeOf() {
		System.out.println("It is made only of fruits.");
	}
}

class Top extends Clothing {
	Top () {
		this(20.0);
	}
	Top (double basePrice) {
		super.setBasePrice(basePrice);
	}
	
	public double calculatePrice() {
		return (super.getBasePrice() + (super.getBasePrice() * super.getVat())) * 1.2;
	}
	public void howToWash() {
		System.out.println("Wash Top at 40 degrees.");
	}
}

class Underwear extends Clothing {
	Underwear() {
		this(30.0);
	}
	Underwear(double basePrice) {
		super.setBasePrice(basePrice);
	}
	
	public double calculatePrice() {
		return (super.getBasePrice() + (super.getBasePrice() * super.getVat())) * 1.45;
	}
	public void howToWash() {
		System.out.println("Wash Underwear at 60 degrees.");
	}
}

class Trousers extends Clothing {
	Trousers() {
		this(40.0);
	}
	Trousers(double basePrice) {
		super.setBasePrice(basePrice);
	}
	
	public double calculatePrice() {
		return (super.getBasePrice() + (super.getBasePrice() * super.getVat())) * 1.2;
	}
	public void howToWash() {
		System.out.println("Wash Trousers at 30 degrees.");
	}
}

class ShoppingMall{
	private ArrayList<Item> items;
	
	ShoppingMall(){
		items = new ArrayList<>();
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void addDairy() {
		items.add(new Dairy());
	}
	public void addFruit() {
		items.add(new Fruit());
	}
	public void addTop() {
		items.add(new Top());
	}
	public void addTrousers() {
		items.add(new Trousers());
	}
	public void addUnderwear() {
		items.add(new Underwear());
	}
	public void addVegetable() {
		items.add(new Vegetable());
	}
	public void addArbitrary(Item item) {
		items.add(item);
	}
	public double bill() {
		double totalPrice = 0;
		
		for (int i = 0; i < items.size(); i++) {
			totalPrice += items.get(i).calculatePrice();
		}
		return totalPrice;
	}
}

class TestShop{
	public static void main(String[] args) {
		ShoppingMall sm = new ShoppingMall();
		sm.addDairy();
		sm.addFruit();
		sm.addTop();
		sm.addTrousers();
		sm.addUnderwear();
		sm.addVegetable();
		sm.addArbitrary(new Top(100));
		printContent(sm.getItems());
		printWashingInstructions(sm.getItems());
		System.out.println(sm.bill());
	}
	
	public static void printContent(ArrayList<Item> items){
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) instanceof Vegan)
				((Vegan)(items.get(i))).madeOf();
		}
	}
	public static void printWashingInstructions(ArrayList<Item> items){
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) instanceof Washable)
				((Washable)(items.get(i))).howToWash();
		}
	}
}