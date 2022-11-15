import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Commands implements CommandExecutor{

	Items item = new Items();
	private Main plugin;
	public Commands(Main plugin) {
		this.plugin=plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(label.equals("setnext")) {
				if(args[0].equalsIgnoreCase("bell")) {
					plugin.firstClicked = false;
					plugin.amount = 0;
				}
				else if(args[0].equalsIgnoreCase("diamond")) {
					plugin.firstClicked = true;
					plugin.amount = 1;
				}
				else if(args[0].equalsIgnoreCase("bedrock")) {
					plugin.firstClicked = true;
					plugin.amount = 2;
				}
				else if(args[0].equalsIgnoreCase("goldenvillage")) {
					plugin.firstClicked = true;
					plugin.amount = 3;
				}
				else if(args[0].equalsIgnoreCase("zombifiedwarden")) {
					plugin.firstClicked = true;
					plugin.amount = 4;
				}
				else if(args[0].equalsIgnoreCase("waxedslab")) {
					plugin.firstClicked = true;
					plugin.amount = 5;
				}
			}
			else if(label.equalsIgnoreCase("giveitem")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("all")) {
						for(ItemStack allItem : item.allItems) {
							plugin.addItem(p, allItem);
						}
					}
					else if(args[0].equals("spellingtable")) {
						plugin.addItem(p, item.spellingCraftingTable());
					}
				}
			}
			else if(label.equals("spawn")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("spellingbee")) {
						Bee bee = (Bee)p.getWorld().spawnEntity(p.getLocation(), EntityType.BEE);
						bee.setCustomName(plugin.spellingBeeName);
					}
					else if(args[0].equals("dragon")) {
						Wither w = (Wither)Bukkit.getWorlds().get(2).spawnEntity(Bukkit.getWorlds().get(2).getSpawnLocation(), EntityType.WITHER);
						w.setCustomName(plugin.spellingBeeDragon);w.setInvisible(true);
						spawnDragon(w);
						new BukkitRunnable() {
							@Override
							public void run() {
								if(w.isDead()) {
									this.cancel();
								}
								for(Player p : w.getWorld().getPlayers()) {
									if(p!=null) {
										w.setTarget(p);
									}
								}
							}
						}.runTaskTimer(plugin, 0, 5);
					}
				}
			}
			else if(label.equals("giveletters")) {
				if(args.length == 1) {
					for(String s : args[0].split("")) {
						ItemStack it = plugin.getLetterByString(s);
						plugin.addItem(p, it);
					}
				}
			}
		}
		return false;
	}
	HashMap<Entity,Location> entityLocation = new HashMap<>();
	public void spawnDragon(Wither dragon) {
		for(Entity ent : Bukkit.getWorlds().get(2).getEntities()) {
			if(plugin.hasHelmet(ent, item.spellingBeeDragon(1))) {
				ent.remove();
			}
		}
		int startCmd = 1,endCmd=8;
		ArmorStand stand = spawnArmorStand(dragon.getLocation(), item.spellingBeeDragon(startCmd));
		entityLocation.clear();
		entityLocation.put(dragon, dragon.getLocation());
		new BukkitRunnable() {
			@Override
			public void run() {
				if(dragon.isDead()) {
					stand.remove();
					this.cancel();
				}
				Location loc2 = dragon.getLocation().clone();
				loc2.setYaw(loc2.getYaw()+180);
				stand.teleport(loc2);

				int cmd = stand.getEquipment().getHelmet().getItemMeta().getCustomModelData();

				if(plugin.isClose(dragon.getLocation(), entityLocation.get(dragon), 0.1)) {
					cmd = startCmd;
				}
				else {
					if(cmd + 1 <= endCmd) {
						cmd++;
					}
					else {
						cmd = startCmd;
					}
					entityLocation.remove(dragon);
					entityLocation.put(dragon, dragon.getLocation());
				}
				stand.getEquipment().setHelmet(item.spellingBeeDragon(cmd));
			}
		}.runTaskTimer(plugin, 0, 3);
	}
	public ArmorStand spawnArmorStand(Location loc, ItemStack it) {
		ArmorStand stand = (ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		stand.setVisible(false);
		stand.setGravity(false);
		stand.setInvulnerable(true);
		stand.setRemoveWhenFarAway(false);
		stand.setMarker(true);
		stand.getEquipment().setHelmet(it);
		return stand;
	}
}
