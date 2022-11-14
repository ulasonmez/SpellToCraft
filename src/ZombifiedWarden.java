import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ZombifiedWarden implements Listener{

	private Main plugin;
	public ZombifiedWarden(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();

	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		Entity ent = event.getEntity();
		if(ent.getCustomName()!=null && ent.getCustomName().equals(plugin.zombifiedWardenName)) {
			if(ent.getType().equals(EntityType.WARDEN)) {
				for(ItemStack it : event.getDrops()) {
					it.setType(Material.AIR);
				}
				ent.getWorld().dropItemNaturally(ent.getLocation(), item.zombifiedWardenSerum());
			}
		}
	}
	ArrayList<Player> wardenPlayer = new ArrayList<>();
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent event) {
		Player p = event.getPlayer();
		if(plugin.isSame(event.getItem(), item.zombifiedWardenSerum())) {
			if(!wardenPlayer.contains(p)) {
				wardenPlayer.add(p);
			}
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(!wardenPlayer.contains(p))return;
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				if(p.getInventory().getItemInMainHand()!=null) {
					if(item.letters.contains(p.getInventory().getItemInMainHand())) {
						shoot(p, p.getInventory().getItemInMainHand());
					}
				}
			}
		}
	}
	public void shoot(Player p,ItemStack it) {
		ArmorStand stand = plugin.spawnArmorStand(p.getLocation(), it);
		new BukkitRunnable() {
			Vector dir = p.getLocation().getDirection().normalize();
			Location loc = p.getLocation();
			double t = 1;
			@Override
			public void run() {
				t+=1;
				double a = dir.getX() * t;
				double b = dir.getY() * t;
				double c = dir.getZ() * t;
				loc.add(a,b,c);
				Location paperLoc = plugin.addToLoc(loc, 0, 1, 0);
				stand.teleport(loc);
				if(paperLoc.getBlock().getType().isSolid()) {
					stand.remove();
					paperLoc.getWorld().createExplosion(paperLoc, 3);
					this.cancel();
				}
				LivingEntity lent = getNearbyEntity(paperLoc, 1);
				if(lent!=null) {
					stand.remove();
					paperLoc.getWorld().createExplosion(paperLoc, 3);
					this.cancel();
				}
				loc.subtract(a,b,c);
			}
		}.runTaskTimer(plugin, 0, 1);
	}

	public LivingEntity getNearbyEntity(Location loc, double radius) {
		for(Entity ent : loc.getWorld().getNearbyEntities(loc,radius,radius,radius)) {
			if(ent instanceof LivingEntity) {
				LivingEntity lent = (LivingEntity)ent;
				if(!lent.getType().equals(EntityType.PLAYER) && !lent.getType().equals(EntityType.ARMOR_STAND)) {
					return lent;
				}
			}
		}
		return null;
	}

}
