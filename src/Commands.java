import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
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
			if(label.equalsIgnoreCase("giveitem")) {
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
						for(Entity ent : Bukkit.getWorlds().get(2).getEntities()){
							if(ent.getType().equals(EntityType.ENDER_DRAGON)) {
								spawnDragon((EnderDragon)ent);
								break;
							}
						}
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
	public void spawnDragon(EnderDragon dragon) {
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
				stand.teleport(dragon);

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
