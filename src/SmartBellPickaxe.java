import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SmartBellPickaxe implements Listener{

	private Main plugin;
	public SmartBellPickaxe(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				if(plugin.holdsItem(p, item.smartBellPickaxe())) {
					shoot(p);
				}
			}
		}
	}
	public void shoot(Player p) {
		ArmorStand stand = plugin.spawnArmorStand(p.getLocation(), item.bellWaves());
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
					paperLoc.getWorld().dropItemNaturally(paperLoc, new ItemStack(plugin.getLetters(paperLoc.getBlock().getType())));
					this.cancel();
				}
				LivingEntity lent = getNearbyEntity(paperLoc, 1);
				if(lent!=null) {
					stand.remove();
					lent.damage(8);
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
