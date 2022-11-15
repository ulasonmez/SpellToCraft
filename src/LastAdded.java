import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class LastAdded implements Listener{

	private Main plugin;
	public LastAdded(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();

	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		if(event.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
			ArmorStand stand = (ArmorStand)event.getEntity();
			if(plugin.hasHelmet(stand, item.spellingCraftingTable())) {
				for(ItemStack drops : event.getDrops()) {
					drops.setType(Material.AIR);
				}
			}
		}
		else if(event.getEntity().getType().equals(EntityType.MINECART)) {
			for(ItemStack drops : event.getDrops()) {
				drops.setType(Material.AIR);
			}
			event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), item.M());
		}
	}
	@EventHandler
	public void onBreak(PlayerInteractEvent event) {
		if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if(event.getClickedBlock().getType().equals(Material.WAXED_OXIDIZED_CUT_COPPER)) {
				event.getClickedBlock().setType(Material.AIR);
			}
		}
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				if(plugin.holdsItem(event.getPlayer(), item.zombifiedWardenSerum())) {
					if(event.getPlayer().getFoodLevel() >= 20) {
						event.getPlayer().setFoodLevel(19);
					}
				}
			}
		}
	}
	Random rand = new Random();
	@EventHandler
	public void onSpawn(EntitySpawnEvent event) {
		if(event.getEntity().getType().equals(EntityType.WITHER_SKULL)) {
			WitherSkull df = (WitherSkull)event.getEntity();
			new BukkitRunnable() {
				ArmorStand stand = plugin.spawnArmorStand(df.getLocation(), item.letters.get(rand.nextInt(item.letters.size())));
				@Override
				public void run() {
					if(df.isDead()) {
						stand.remove();
						this.cancel();
					}
					stand.teleport(df);
				}
			}.runTaskTimer(plugin, 0, 1);
		}
	}
}
