import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class GoldenVillage implements Listener{

	private Main plugin;
	public GoldenVillage(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		Entity ent = event.getEntity();
		if(ent.getCustomName()!=null && ent.getCustomName().equals(plugin.goldenVillagerName)) {
			if(event.getCause().equals(DamageCause.FALL)) {
				event.setCancelled(true);
			}
		}
	}
	Random rand = new Random();
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		Entity ent = event.getEntity();
		if(ent.getCustomName()!=null && ent.getCustomName().equals(plugin.goldenVillagerName)) {
			for(ItemStack drop : event.getDrops()) {
				drop.setType(Material.AIR);
			}
			ent.getWorld().dropItemNaturally(ent.getLocation(), item.goldenCoin());
		}
		else if(ent.getCustomName()!=null && ent.getCustomName().equals(plugin.kingVillagerName)) {
			for(ItemStack drop : event.getDrops()) {
				drop.setType(Material.AIR);
			}
			for(int i = 0; i <= 10; i++) {
				ent.getWorld().dropItem(plugin.addToLoc(ent.getLocation(), rand.nextInt(2)-1, 0, rand.nextInt(2)-1), item.goldenCoin());
			}
		}
	}
}
