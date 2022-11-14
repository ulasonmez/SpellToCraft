import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DiamondItem implements Listener{

	private Main plugin;
	public DiamondItem(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				if(plugin.holdsItem(p, item.edibleDiamond())) {
					if(p.getFoodLevel()>=20) {
						p.setFoodLevel(19);
					}
				}
			}
		}
	}
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if(diamondPlayers.contains(p)) {
			Location belowLoc = plugin.addToLoc(p.getLocation(), 0, -1, 0);
			if(belowLoc.getBlock().getType().isSolid()) {
				belowLoc.getBlock().setType(Material.DIAMOND_BLOCK);
			}
		}
	}
	ArrayList<Player> diamondPlayers = new ArrayList<>();
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent event) {
		Player p = event.getPlayer();
		if(plugin.isSame(event.getItem(), item.edibleDiamond())) {
			if(diamondPlayers.contains(p)) {
				diamondPlayers.remove(p);
			}
			new BukkitRunnable() {
				@Override
				public void run() {
					if(diamondPlayers.contains(p)) {
						diamondPlayers.remove(p);
					}
				}
			}.runTaskLater(plugin, 20*20);
			p.getWorld().playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,20*20,2));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,20*20,2));
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,20*20,2));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,20*20,2));
		}
	}
}
