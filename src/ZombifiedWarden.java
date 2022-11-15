import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
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
	public void onDamage(EntityDamageEvent event) {
		if(wardenPlayer.contains(event.getEntity())) {
			event.setDamage(event.getDamage() / 5);
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
						plugin.decreaseItem(p);
						shoot(p, p.getInventory().getItemInMainHand());
					}
					else {
						if(!hasCustomItem(p.getInventory().getItemInMainHand())) {
							plugin.decreaseItem(p);
							shootNormalItems(p, p.getInventory().getItemInMainHand());
						}
					}
				}
			}
		}
	}
	public boolean hasCustomItem(ItemStack it) {
		for(ItemStack allIt : item.allItems) {
			if(plugin.isSame(it, allIt)) {
				return true;
			}
		}
		return false;
	}
	public void shootNormalItems(Player p,ItemStack it) {
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
					for(int x = -3;x<=3;x++) {
						for(int y = -3;y<=3;y++) {
							for(int z = -3;z<=3;z++) {
								Location loc2 = plugin.addToLoc(paperLoc, x, y, z);
								if(loc2.distance(paperLoc)<=2) {
									loc2.getWorld().dropItemNaturally(loc2, plugin.getLetters(loc2.getBlock().getType()));
									loc2.getBlock().setType(Material.AIR);
								}
							}
						}
					}
					for(String letter : it.getType().toString().split("")) {
						paperLoc.getWorld().dropItemNaturally(paperLoc, plugin.getLetterByString(letter));
					}
					if(it.hasItemMeta()) {
						for(Enchantment enchant : it.getItemMeta().getEnchants().keySet()) {
							for(String s : enchant.toString().split("")) {
								paperLoc.getWorld().dropItemNaturally(paperLoc, plugin.getLetterByString(s));
							}
						}
					}
					paperLoc.getWorld().playSound(paperLoc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
					paperLoc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,paperLoc,5);
					this.cancel();
				}
				loc.subtract(a,b,c);
			}
		}.runTaskTimer(plugin, 0, 1);
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
					for(int x = -3;x<=3;x++) {
						for(int y = -3;y<=3;y++) {
							for(int z = -3;z<=3;z++) {
								Location loc2 = plugin.addToLoc(paperLoc, x, y, z);
								if(loc2.distance(paperLoc)<=2) {
									loc2.getWorld().dropItemNaturally(loc2, plugin.getLetters(loc2.getBlock().getType()));
									loc2.getBlock().setType(Material.AIR);
								}
							}
						}
					}
					paperLoc.getWorld().playSound(paperLoc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
					paperLoc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,paperLoc,5);
					this.cancel();
				}
				LivingEntity lent = getNearbyEntity(paperLoc, 1);
				if(lent!=null) {
					stand.remove();
					paperLoc.getWorld().playSound(paperLoc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
					paperLoc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,paperLoc,5);
					lent.damage(10);
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
