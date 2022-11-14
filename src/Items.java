import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

	Material coal = Material.NAUTILUS_SHELL;
	List<ItemStack> allItems = List.of(spellingCraftingTable());
	List<ItemStack> letters = List.of(A(),B(),C(),D(),E(),F(),G(),H(),I(),J(),K(),L(),M(),N(),O(),P(),Q(),R(),S(),T(),U(),V(),W(),X(),Y(),Z());

	public ItemStack spellingCraftingTable() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(31);
		meta.setDisplayName("Spelling Crafting Table");
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack spellingBeeDragon(int cmd) {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(cmd);
		meta.setDisplayName("Spelling Crafting Table");
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack smartBellPickaxe() {
		ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Smart Bell Pickaxe");
		List<String> lore = List.of(ChatColor.YELLOW+"Uses sound waves to destroy",ChatColor.YELLOW+"blocks...",
				ChatColor.DARK_BLUE+"Smart reward for knowing how to",ChatColor.DARK_BLUE+"spell");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack smartBedrockChest() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(28);
		meta.setDisplayName("Smart Bedrock Chest");
		List<String> lore = List.of(ChatColor.YELLOW+"Container holding illegal",ChatColor.YELLOW+"Bedrock items...",
				ChatColor.DARK_BLUE+"Smart reward for knowing how to",ChatColor.DARK_BLUE+"spell");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack smartBedrockChestOpen() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(29);
		meta.setDisplayName("Smart Bedrock Chest Open");
		List<String> lore = List.of(ChatColor.YELLOW+"Container holding illegal",ChatColor.YELLOW+"Bedrock items...",
				ChatColor.DARK_BLUE+"Smart reward for knowing how to",ChatColor.DARK_BLUE+"spell");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack zombifiedWardenSerum() {
		ItemStack item = new ItemStack(Material.APPLE);
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(2);
		meta.setDisplayName("Zombified Warden Serum");
		List<String> lore = List.of(ChatColor.YELLOW+"The Serum of a Zombified Warden can",
				ChatColor.YELLOW+"grant powers strong enough to rip",
				ChatColor.YELLOW+"apart words!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack waxedOxidizedCutCopperSlab() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("WAXED OXIDIZED CUT COPPER SLAB");
		meta.setCustomModelData(40);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack waxedOxidizedCutCopperSlabItem() {
		ItemStack item = new ItemStack(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Waxed Oxidized Cut Copper Slab");
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack waxed() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("WAXED");
		meta.setCustomModelData(41);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack oxidized() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("OXIDIZED");
		meta.setCustomModelData(36);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack cut() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("CUT");
		meta.setCustomModelData(33);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack copper() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(32);
		meta.setDisplayName("COPPER");
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack slab() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("SLAB");
		meta.setCustomModelData(37);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack bedrockArmor() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(27);
		meta.setDisplayName("Bedrock Armor");
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack bedrockMelonSlice() {
		ItemStack item = new ItemStack(Material.APPLE);
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(1);
		meta.setDisplayName("Bedrock Melon Slice");
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack edibleDiamond() {
		ItemStack item = new ItemStack(Material.APPLE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Edible Diamond");
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack bellWaves() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Bell Waves");
		meta.setCustomModelData(30);
		item.setItemMeta(meta);
		return item;
	}


	public ItemStack golden() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("GOLDEN");
		meta.setCustomModelData(35);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack village() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("VILLAGE");
		meta.setCustomModelData(38);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack goldenVillage() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("GOLDEN VILLAGE");
		meta.setCustomModelData(34);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack goldenCoin() {
		ItemStack item = new ItemStack(Material.EGG);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Golden Coin");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack zombified() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("ZOMBIFIED");
		meta.setCustomModelData(43);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack warden() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("WARDEN");
		meta.setCustomModelData(39);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack zombifiedWarden() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("ZOMBIFIED WARDEN");
		meta.setCustomModelData(42);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack A() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("A");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack B() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("B");
		meta.setCustomModelData(2);

		item.setItemMeta(meta);
		return item;
	}
	public ItemStack C() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("C");
		meta.setCustomModelData(3);

		item.setItemMeta(meta);
		return item;
	}
	public ItemStack D() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("D");
		meta.setCustomModelData(4);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack E() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("E");
		meta.setCustomModelData(5);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack F() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("F");
		meta.setCustomModelData(6);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack G() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("G");
		meta.setCustomModelData(7);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack H() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("H");
		meta.setCustomModelData(8);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack I() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("I");
		meta.setCustomModelData(9);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack J() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("J");
		meta.setCustomModelData(10);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack K() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("K");
		meta.setCustomModelData(11);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack L() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("L");
		meta.setCustomModelData(12);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack M() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("M");
		meta.setCustomModelData(13);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack N() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("N");
		meta.setCustomModelData(14);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack O() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("O");
		meta.setCustomModelData(15);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack P() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("P");
		meta.setCustomModelData(16);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack Q() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Q");
		meta.setCustomModelData(17);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack R() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("R");
		meta.setCustomModelData(18);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack S() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("S");
		meta.setCustomModelData(19);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack T() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("T");
		meta.setCustomModelData(20);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack U() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("U");
		meta.setCustomModelData(21);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack V() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("V");
		meta.setCustomModelData(22);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack W() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("W");
		meta.setCustomModelData(23);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack X() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("X");
		meta.setCustomModelData(24);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack Y() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Y");
		meta.setCustomModelData(25);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack Z() {
		ItemStack item = new ItemStack(coal);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Z");
		meta.setCustomModelData(26);
		item.setItemMeta(meta);
		return item;
	}

}
