import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

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
	String spellingBeeMessage = eMark+ChatColor.GREEN+"Hello there! Welcome to the "+spellingBeeChallenge+ChatColor.GREEN+"! You will have to spell to craft. If you fail to spell the word I give you, you "+dieMessage;
	String spellingBeeName = ChatColor.GREEN+"Spelling Bee";
	String firstWord = eMark+ChatColor.GREEN+"Your first word to spell is...";
	String bellMessage = ChatColor.YELLOW +"BELL";
	String congratsMessage = eMark + ChatColor.GREEN+"Congrats you know how to spell B-E-L-L! Here's your reward...";
	String nextWord = eMark+ChatColor.GREEN+"Your next word to spell is...";
	String diamondMessage = eMark+ChatColor.AQUA+"DIAMOND";
	String bedrockMessage = eMark+ChatColor.DARK_GRAY+"BEDROCK";
	String goldenVillageMessage = eMark+ChatColor.YELLOW+"GOLDEN VILLAGE";
	String villageBuildMessage = eMark + ChatColor.GREEN+"Congrats you know how to spell Golden Village! We'll build you one as a reward...";
	String kingVillagerName = "King Villager";
	String goldenVillagerName = "Golden Villager";
	String zombifiedWardenMessage = ChatColor.DARK_PURPLE+"ZOMBIFIED WARDEN";
	String wardenSpellMessage = eMark+ChatColor.GREEN+"Congrats on conqering the word Zombified Warden! Now you must defeat one...";
	String zombifiedWardenName = "Zombified Warden";
	String waxOxidizedCutCopperSlab = ChatColor.DARK_AQUA + "WAX OXIDIZED CUT COPPER SLAB";
	String congratsWaxed = eMark+ChatColor.GREEN+"Congrats on spelling Waxed Oxidized Cut Copper Slab! Place it down to complete the challenge...";
	String spellingBeeDragon = "Spelling Bee Dragon";

	boolean firstClicked = false;
	int amount = 0;


	File MainDir = getDataFolder().toPath().toFile();
	File SchemDir = getDataFolder().toPath().resolve("schems").toFile();
	File[] schematics;

	@Override
	public void onEnable() { 
		if (!this.MainDir.exists())
			this.MainDir.mkdir(); 
		if (!this.SchemDir.exists())
			this.SchemDir.mkdir(); 
		this.schematics = this.SchemDir.listFiles(new FilenameFilter() {
			public boolean accept(File f, String name) {
				return (name.endsWith(".schematic") || name.endsWith(".schem"));
			}
		});
		getCommand("giveitem").setExecutor(new Commands(this));
		getCommand("giveletters").setExecutor(new Commands(this));
		getCommand("spawn").setExecutor(new Commands(this));
		getCommand("setnext").setExecutor(new Commands(this));
		getServer().getPluginManager().registerEvents(new SpellingCraftingTable(this), this);
		getServer().getPluginManager().registerEvents(new GettingLetters(this), this);
		getServer().getPluginManager().registerEvents(new SmartBellPickaxe(this), this);
		getServer().getPluginManager().registerEvents(new SpellingBee(this), this);
		getServer().getPluginManager().registerEvents(new BedrockItem(this), this);
		getServer().getPluginManager().registerEvents(new DiamondItem(this), this);
		getServer().getPluginManager().registerEvents(new GoldenVillage(this), this);
		getServer().getPluginManager().registerEvents(new WaxedItem(this), this);
		getServer().getPluginManager().registerEvents(new ZombifiedWarden(this), this);
		getServer().getPluginManager().registerEvents(new BeeDragon(this), this);
		getServer().getPluginManager().registerEvents(new LastAdded(this), this);

		bellRecipe();diamondRecipe();bedrockRecipe();goldenRecipe();villageRecipe();
		goldenVillageRecipe();zombifiedRecipe();wardenRecipe();zombifiedWardenRecipe();
		waxedRecipe();oxidizedRecipe();cutRecipe();copperRecipe();slabRecipe();
		waxedOxidizeCutCapperSlabRecipe();

	}
	public boolean holdsType(Player p, Material mat) {
		if(p.getInventory().getItemInMainHand()!=null) {
			if(p.getInventory().getItemInMainHand().getType().equals(mat)) {
				return true;
			}
		}
		return false;
	}

	NamespacedKey a,b,c,d,e,f,g,h,j,k,l,m,n,o,p,r,s,t,aa;

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
	public void goldenRecipe() {
		ItemStack result = item.golden();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.G());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.O());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.L());
		RecipeChoice usedItem4 = new RecipeChoice.ExactChoice(item.D());
		RecipeChoice usedItem5 = new RecipeChoice.ExactChoice(item.E());
		RecipeChoice usedItem6 = new RecipeChoice.ExactChoice(item.N());
		this.d = new NamespacedKey(this, "d");
		ShapedRecipe recipe = new ShapedRecipe(d, result);
		recipe.shape("GOL","DEN","   ");
		recipe.setIngredient('G', usedItem);
		recipe.setIngredient('O', usedItem2);
		recipe.setIngredient('L', usedItem3);
		recipe.setIngredient('D', usedItem4);
		recipe.setIngredient('E', usedItem5);
		recipe.setIngredient('N', usedItem6);
		Bukkit.addRecipe(recipe);
	}
	public void villageRecipe() {
		ItemStack result = item.village();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.V());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.I());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.L());
		RecipeChoice usedItem4 = new RecipeChoice.ExactChoice(item.A());
		RecipeChoice usedItem5 = new RecipeChoice.ExactChoice(item.G());
		RecipeChoice usedItem6 = new RecipeChoice.ExactChoice(item.E());
		this.e = new NamespacedKey(this, "e");
		ShapedRecipe recipe = new ShapedRecipe(e, result);
		recipe.shape("VIL","LAG","E  ");
		recipe.setIngredient('V', usedItem);
		recipe.setIngredient('I', usedItem2);
		recipe.setIngredient('L', usedItem3);
		recipe.setIngredient('A', usedItem4);
		recipe.setIngredient('G', usedItem5);
		recipe.setIngredient('E', usedItem6);
		Bukkit.addRecipe(recipe);
	}
	public void zombifiedRecipe() {
		ItemStack result = item.zombified();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.Z());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.O());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.M());
		RecipeChoice usedItem4 = new RecipeChoice.ExactChoice(item.B());
		RecipeChoice usedItem5 = new RecipeChoice.ExactChoice(item.I());
		RecipeChoice usedItem6 = new RecipeChoice.ExactChoice(item.F());
		RecipeChoice usedItem7 = new RecipeChoice.ExactChoice(item.E());
		RecipeChoice usedItem8 = new RecipeChoice.ExactChoice(item.D());
		this.f = new NamespacedKey(this, "f");
		ShapedRecipe recipe = new ShapedRecipe(f, result);
		recipe.shape("ZOM","BIF","IED");
		recipe.setIngredient('Z', usedItem);
		recipe.setIngredient('O', usedItem2);
		recipe.setIngredient('M', usedItem3);
		recipe.setIngredient('B', usedItem4);
		recipe.setIngredient('I', usedItem5);
		recipe.setIngredient('F', usedItem6);
		recipe.setIngredient('E', usedItem7);
		recipe.setIngredient('D', usedItem8);
		Bukkit.addRecipe(recipe);
	}
	public void wardenRecipe() {
		ItemStack result = item.warden();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.W());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.A());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.R());
		RecipeChoice usedItem4 = new RecipeChoice.ExactChoice(item.D());
		RecipeChoice usedItem5 = new RecipeChoice.ExactChoice(item.E());
		RecipeChoice usedItem6 = new RecipeChoice.ExactChoice(item.N());
		this.g = new NamespacedKey(this, "g");
		ShapedRecipe recipe = new ShapedRecipe(g, result);
		recipe.shape("WAR","DEN","   ");
		recipe.setIngredient('W', usedItem);
		recipe.setIngredient('A', usedItem2);
		recipe.setIngredient('R', usedItem3);
		recipe.setIngredient('D', usedItem4);
		recipe.setIngredient('E', usedItem5);
		recipe.setIngredient('N', usedItem6);
		Bukkit.addRecipe(recipe);
	}
	public void zombifiedWardenRecipe() {
		ItemStack result = item.zombifiedWarden();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.zombified());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.warden());
		this.h = new NamespacedKey(this, "h");
		ShapedRecipe recipe = new ShapedRecipe(h, result);
		recipe.shape("ZW ","   ","   ");
		recipe.setIngredient('Z', usedItem);
		recipe.setIngredient('W', usedItem2);
		Bukkit.addRecipe(recipe);
	}
	public void goldenVillageRecipe() {
		ItemStack result = item.goldenVillage();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.golden());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.village());
		this.aa = new NamespacedKey(this, "aa");
		ShapedRecipe recipe = new ShapedRecipe(aa, result);
		recipe.shape("GV ","   ","   ");
		recipe.setIngredient('G', usedItem);
		recipe.setIngredient('V', usedItem2);
		Bukkit.addRecipe(recipe);
	}

	public void waxedRecipe() {
		ItemStack result = item.waxed();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.W());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.A());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.X());
		RecipeChoice usedItem4 = new RecipeChoice.ExactChoice(item.E());
		RecipeChoice usedItem5 = new RecipeChoice.ExactChoice(item.D());
		this.j = new NamespacedKey(this, "j");
		ShapedRecipe recipe = new ShapedRecipe(j, result);
		recipe.shape("WAX","ED ","   ");
		recipe.setIngredient('W', usedItem);
		recipe.setIngredient('A', usedItem2);
		recipe.setIngredient('X', usedItem3);
		recipe.setIngredient('E', usedItem4);
		recipe.setIngredient('D', usedItem5);
		Bukkit.addRecipe(recipe);
	}
	public void oxidizedRecipe() {
		ItemStack result = item.oxidized();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.O());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.X());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.I());
		RecipeChoice usedItem4 = new RecipeChoice.ExactChoice(item.D());
		RecipeChoice usedItem5 = new RecipeChoice.ExactChoice(item.Z());
		RecipeChoice usedItem6 = new RecipeChoice.ExactChoice(item.E());
		this.k = new NamespacedKey(this, "k");
		ShapedRecipe recipe = new ShapedRecipe(k, result);
		recipe.shape("OXI","DIZ","ED ");
		recipe.setIngredient('O', usedItem);
		recipe.setIngredient('X', usedItem2);
		recipe.setIngredient('I', usedItem3);
		recipe.setIngredient('D', usedItem4);
		recipe.setIngredient('Z', usedItem5);
		recipe.setIngredient('E', usedItem6);
		Bukkit.addRecipe(recipe);
	}
	public void cutRecipe() {
		ItemStack result = item.cut();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.C());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.U());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.T());
		this.l = new NamespacedKey(this, "l");
		ShapedRecipe recipe = new ShapedRecipe(l, result);
		recipe.shape("CUT","   ","   ");
		recipe.setIngredient('C', usedItem);
		recipe.setIngredient('U', usedItem2);
		recipe.setIngredient('T', usedItem3);
		Bukkit.addRecipe(recipe);
	}

	public void copperRecipe() {
		ItemStack result = item.copper();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.C());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.O());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.P());
		RecipeChoice usedItem4 = new RecipeChoice.ExactChoice(item.E());
		RecipeChoice usedItem5 = new RecipeChoice.ExactChoice(item.R());
		this.m = new NamespacedKey(this, "m");
		ShapedRecipe recipe = new ShapedRecipe(m, result);
		recipe.shape("COP","PER","   ");
		recipe.setIngredient('C', usedItem);
		recipe.setIngredient('O', usedItem2);
		recipe.setIngredient('P', usedItem3);
		recipe.setIngredient('E', usedItem4);
		recipe.setIngredient('R', usedItem5);
		Bukkit.addRecipe(recipe);
	}

	public void slabRecipe() {
		ItemStack result = item.slab();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.S());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.L());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.A());
		RecipeChoice usedItem4 = new RecipeChoice.ExactChoice(item.B());
		this.n = new NamespacedKey(this, "n");
		ShapedRecipe recipe = new ShapedRecipe(n, result);
		recipe.shape("SLA","B  ","   ");
		recipe.setIngredient('S', usedItem);
		recipe.setIngredient('L', usedItem2);
		recipe.setIngredient('A', usedItem3);
		recipe.setIngredient('B', usedItem4);
		Bukkit.addRecipe(recipe);
	}

	public void waxedOxidizeCutCapperSlabRecipe() {
		ItemStack result = item.waxedOxidizedCutCopperSlab();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.waxed());
		RecipeChoice usedItem2 = new RecipeChoice.ExactChoice(item.oxidized());
		RecipeChoice usedItem3 = new RecipeChoice.ExactChoice(item.cut());
		RecipeChoice usedItem4 = new RecipeChoice.ExactChoice(item.copper());
		RecipeChoice usedItem5 = new RecipeChoice.ExactChoice(item.slab());
		this.o = new NamespacedKey(this, "o");
		ShapedRecipe recipe = new ShapedRecipe(o, result);
		recipe.shape("WOC","RS ","   ");
		recipe.setIngredient('W', usedItem);
		recipe.setIngredient('O', usedItem2);
		recipe.setIngredient('C', usedItem3);
		recipe.setIngredient('R', usedItem4);
		recipe.setIngredient('S', usedItem5);
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
	public ItemStack getLetterByString(String s) {
		for(ItemStack letter : item.letters) {
			if(letter.getItemMeta().getDisplayName().equals(s.toUpperCase(Locale.ENGLISH))) {
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
