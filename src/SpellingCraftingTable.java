import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class SpellingCraftingTable implements Listener{

	private Main plugin;
	public SpellingCraftingTable(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				if(plugin.holdsItem(p, item.spellingCraftingTable())) {
					Location loc = event.getClickedBlock().getRelative(event.getBlockFace()).getLocation();
					plugin.decreaseItem(p);
					plugin.placeBlockArmorStand(loc, item.spellingCraftingTable());
				}
			}
		}
	}
	ArrayList<Projectile> goldenCoin = new ArrayList<>();
	@EventHandler
	public void onShoot(ProjectileLaunchEvent event) {
		if(event.getEntity().getShooter() instanceof Player) {
			Player p = (Player)event.getEntity().getShooter();
			if(plugin.holdsItem(p, item.goldenCoin())) {
				goldenCoin.add(event.getEntity());
			}
		}
	}
	@EventHandler
	public void onHit(ProjectileHitEvent event) {
		if(goldenCoin.contains(event.getEntity())) {
			event.setCancelled(true);
			event.getEntity().remove();
			event.getEntity().getWorld().strikeLightning(event.getEntity().getLocation());
		}
	}
	@EventHandler
	public void onClick(PlayerInteractAtEntityEvent event) {
		Player p = event.getPlayer();
		Entity ent = event.getRightClicked();
		if(ent.getType().equals(EntityType.ARMOR_STAND)) {
			ArmorStand stand = (ArmorStand)ent;
			if(plugin.hasHelmet(stand, item.spellingCraftingTable())) {
				event.setCancelled(true);
				p.openWorkbench(null, true);
			}
		}
	}
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			if(event.getEntity() instanceof ArmorStand) {
				ArmorStand stand = (ArmorStand)event.getEntity();
				if(plugin.hasHelmet(stand, item.spellingCraftingTable())) {
					stand.remove();
					stand.getWorld().dropItemNaturally(stand.getLocation(), item.spellingCraftingTable());
				}
			}
		}
	}
}
