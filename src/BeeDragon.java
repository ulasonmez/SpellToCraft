import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class BeeDragon implements Listener{

	private Main plugin;
	public BeeDragon(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onSpawn(EntitySpawnEvent event) {
		if(event.getEntity().getType().equals(EntityType.ENDER_DRAGON)) {
			EnderDragon dragon = (EnderDragon)event.getEntity();
			dragon.setHealth(0);
			new BukkitRunnable() {
				int counter = 0;
				@Override
				public void run() {
					counter++;
					for(int x = -20;x<=20;x++) {
						for(int y = -20;y<=20;y++) {
							for(int z = -20;z<=20;z++) {
								Location loc = plugin.addToLoc(Bukkit.getWorlds().get(2).getSpawnLocation(), x, y, z);
								if(loc.getBlock().getType().equals(Material.END_PORTAL)) {
									loc.getBlock().setType(Material.AIR);
								}
							}
						}
					}
					if(counter >= 4 * 30) {
						this.cancel();
					}
				}
			}.runTaskTimer(plugin, 20*2,5);
		}
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
