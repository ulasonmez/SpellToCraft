import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin{


	String eMark = ChatColor.GREEN+"["+ChatColor.YELLOW+"!"+ChatColor.GREEN+"] ";
	String spellingBeeChallenge = ChatColor.YELLOW+"Spelling Bee Challenge";
	String dieMessage = ChatColor.RED+"DIE";
	String spellingBeeMessage = eMark+ChatColor.GREEN+"Hello there! Welcome to the "+spellingBeeChallenge+"! You will have to spell to craft. If you fail to spell the word I give you, you "+dieMessage;
	String spellingBeeName = ChatColor.GREEN+"Spelling Bee";
	String firstWord = eMark+ChatColor.GREEN+"Your first word to spell is...";
	String bellMessage = ChatColor.YELLOW +"BELL";
	String congratsMessage = eMark + ChatColor.GREEN+"Congrats you know how to spell B-E-L-L! Here's your reward...";
	String nextWord = eMark+ChatColor.GREEN+"Your first word to spell is...";
	String diamondMessage = eMark+ChatColor.AQUA+"DIAMOND";
	String bedrockMessage = eMark+ChatColor.DARK_GRAY+"BEDROCK";

	boolean firstClicked = false;
	int amount = 0;

	@Override
	public void onEnable() {
		getCommand("giveitem").setExecutor(new Commands(this));
		getServer().getPluginManager().registerEvents(new SpellingCraftingTable(this), this);
		getServer().getPluginManager().registerEvents(new GettingLetters(this), this);
		getServer().getPluginManager().registerEvents(new SmartBellPickaxe(this), this);
		getServer().getPluginManager().registerEvents(new SpellingBee(this), this);
		getServer().getPluginManager().registerEvents(new BedrockItems(this), this);
		bellRecipe();diamondRecipe();bedrockRecipe();
	}
	public boolean holdsType(Player p, Material mat) {
		if(p.getInventory().getItemInMainHand()!=null) {
			if(p.getInventory().getItemInMainHand().getType().equals(mat)) {
				return true;
			}
		}
		return false;
	}


	NamespacedKey a,b,c,d,e,f,g,h;

	public void bellRecipe() {
		ItemStack result = new ItemStack(Material.BELL);
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.B());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.E());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.L());
		this.a = new NamespacedKey(this, "a");
		ShapedRecipe recipe = new ShapedRecipe(a, result);
		recipe.shape("BEL","L  ","   ");
		recipe.setIngredient('B', usedItem);
		recipe.setIngredient('E', usedItem2);
		recipe.setIngredient('L', usedItem3);
		Bukkit.addRecipe(recipe);
	}
	public void diamondRecipe() {
		ItemStack result = new ItemStack(Material.DIAMOND);
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.D());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.I());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.A());
		RecipeChoice usedItem4 = new RecipeChoice.ExactChoice(item.M());
		RecipeChoice usedItem5 = new RecipeChoice.ExactChoice(item.O());
		RecipeChoice usedItem6 = new RecipeChoice.ExactChoice(item.N());
		this.b = new NamespacedKey(this, "b");
		ShapedRecipe recipe = new ShapedRecipe(b, result);
		recipe.shape("DIA","MON","D  ");
		recipe.setIngredient('D', usedItem);
		recipe.setIngredient('I', usedItem2);
		recipe.setIngredient('A', usedItem3);
		recipe.setIngredient('M', usedItem4);
		recipe.setIngredient('O', usedItem5);
		recipe.setIngredient('N', usedItem6);
		Bukkit.addRecipe(recipe);
	}
	public void bedrockRecipe() {
		ItemStack result = new ItemStack(Material.BEDROCK);
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.B());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.E());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.D());
		RecipeChoice usedItem4 = new RecipeChoice.ExactChoice(item.R());
		RecipeChoice usedItem5 = new RecipeChoice.ExactChoice(item.O());
		RecipeChoice usedItem6 = new RecipeChoice.ExactChoice(item.C());
		RecipeChoice usedItem7 = new RecipeChoice.ExactChoice(item.K());
		this.c = new NamespacedKey(this, "c");
		ShapedRecipe recipe = new ShapedRecipe(c, result);
		recipe.shape("BED","ROC","K  ");
		recipe.setIngredient('B', usedItem);
		recipe.setIngredient('E', usedItem2);
		recipe.setIngredient('D', usedItem3);
		recipe.setIngredient('R', usedItem4);
		recipe.setIngredient('O', usedItem5);
		recipe.setIngredient('C', usedItem6);
		recipe.setIngredient('K', usedItem7);
		Bukkit.addRecipe(recipe);
	}
	public void useAsTotem(Player p, ItemStack totemItem) {
		ItemStack it = p.getInventory().getItemInMainHand().clone();
		p.getInventory().setItemInMainHand(totemItem);
		p.damage(p.getHealth() * 2);
		new BukkitRunnable() {
			@Override
			public void run() {
				p.getInventory().setItemInMainHand(it);
			}
		}.runTaskLater(this,4);
	}
	public void removeItem(Player p,ItemStack it) {
		for(ItemStack it2 : p.getInventory().getContents()) {
			if(isSame(it2, it)) {
				it2.setAmount(it2.getAmount() - 1);
			}
		}
	}
	public ArmorStand getSpesificArmorStand(ItemStack it) {
		for(World w : Bukkit.getWorlds()) {
			for(Entity ent : w.getEntities()) {
				if(ent instanceof ArmorStand) {
					if(hasHelmet(ent, it)) {
						return (ArmorStand)ent;
					}
				}
			}
		}
		return null;
	}
	public void sendDelayedMessages(String text,int delay) {
		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage(text);
			}
		}.runTaskLater(this, delay);
	}
	Items item = new Items();
	public ItemStack getLetters(Material mat) {
		for(ItemStack letter : item.letters) {
			if(letter.getItemMeta().getDisplayName().equals(mat.toString().split("")[0])) {
				return letter;
			}
		}
		return null;
	}

	public LivingEntity getNearbyEntity(Location loc, int radius,Player p) {
		for(Entity ent : loc.getWorld().getNearbyEntities(loc,radius,radius,radius)) {
			if(ent instanceof LivingEntity) {
				LivingEntity lent = (LivingEntity)ent;
				if(lent!=p && lent.getType()!=EntityType.ARMOR_STAND) {
					return lent;
				}
			}
		}
		return null;
	}
	public void removeActivePotionEffects(Player p) {
		for(PotionEffect pe : p.getActivePotionEffects()) {
			p.removePotionEffect(pe.getType());
		}
	}

	public boolean isClose(Location loc1, Location loc2,double radius) {
		if(loc1!=null && loc2!=null) {
			if(loc1.getWorld().equals(loc2.getWorld())) {
				if(loc1.distance(loc2)<=radius) {
					return true;
				}
			}
		}
		return false;
	}
	public double returnMaxHealth(LivingEntity lent) {
		return lent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
	}
	public ArmorStand spawnHologram(Location loc, String text) {
		ArmorStand stand = (ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		stand.setVisible(false);
		stand.setCustomNameVisible(true);
		stand.setGravity(false);
		stand.setCustomName(text);
		return stand;
	}
	public ArmorStand spawnArmorStand(Location loc, ItemStack it) {
		ArmorStand stand = (ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		stand.setVisible(false);
		stand.setGravity(false);
		stand.setInvulnerable(true);
		stand.getEquipment().setHelmet(it);
		return stand;
	}
	public ArmorStand spawnPlayerModelStand(Location loc, ItemStack it) {
		ArmorStand stand = (ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		stand.setVisible(false);
		stand.setGravity(false);
		stand.setMarker(true);
		stand.setCollidable(false);
		stand.setInvulnerable(true);
		stand.getEquipment().setHelmet(it);
		return stand;
	}
	public ArmorStand placeBlockArmorStand(Location loc, ItemStack block) {
		loc.setX((int)loc.getX());
		loc.setZ((int)loc.getZ());
		ArmorStand stand = (ArmorStand)loc.getWorld().spawnEntity(this.addToLoc(loc, 0.5, 0, 0.5), EntityType.ARMOR_STAND);
		stand.setGravity(false);
		stand.getEquipment().setHelmet(block);
		stand.setSilent(true);
		return stand;
	}
	public void decreaseItem(Player p) {
		p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
	}
	public boolean hasHelmet(Entity lent, ItemStack hat) {
		if(lent instanceof LivingEntity) {
			LivingEntity lent2 = (LivingEntity)lent;
			if(isSame(lent2.getEquipment().getHelmet(), hat)) {
				return true;
			}
		}
		return false;
	}
	public Location addToLoc(Location loc, double x, double y, double z) {
		return new Location(loc.getWorld(), loc.getX()+x, loc.getY()+y, loc.getZ()+z);
	}
	public void addItem(Player p, ItemStack it) {
		if(p.getInventory().firstEmpty() == -1) {
			p.getWorld().dropItemNaturally(p.getLocation(), it);
		}
		else {
			p.getInventory().addItem(it);
		}
	}
	public Player getNearestPlayer(Location loc) {
		double length = Double.MAX_VALUE;
		Player nearestPlayer = null;
		for(Player p : loc.getWorld().getPlayers()) {
			if(loc.distance(p.getLocation())<length) {
				length = loc.distance(p.getLocation());
				nearestPlayer = p;
			}
		}
		return nearestPlayer;
	}
	public boolean holdsItem(Player p, ItemStack it) {
		if(isSame(p.getInventory().getItemInMainHand(), it))
			return true;
		return false;
	}
	public boolean isSame(ItemStack it1, ItemStack it2) {
		if(it1!=null) {
			if(it1.getItemMeta()!=null) {
				if(it1.getItemMeta().getDisplayName().equals(it2.getItemMeta().getDisplayName())) {
					return true;
				}
			}
		}
		return false;
	}
	public int getMaxHealth(Player p) {
		return (int) p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
	}
	public void setMaxHealth(LivingEntity lent, double health,boolean shouldSetMaxHealth) {
		new BukkitRunnable() {
			@Override
			public void run() {
				lent.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
				if(shouldSetMaxHealth) {
					lent.setHealth(lent.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
				}
			}
		}.runTaskLater(this, 10);
	}
	public boolean hasItemInInventory(Player p, ItemStack it) {
		for(ItemStack pItem : p.getInventory().getContents()) {
			if(isSame(pItem, it)) {
				return true;
			}
		}
		return false;
	}
	public void sendMessage(String sender, String message) {
		Bukkit.broadcastMessage(sender+ChatColor.WHITE+": "+message);
	}
}
