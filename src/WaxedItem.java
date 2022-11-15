import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class WaxedItem implements Listener{

	private Main plugin;
	public WaxedItem(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onClick(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		if(plugin.holdsItem(p, item.waxedOxidizedCutCopperSlabItem())) {
			Location loc = event.getBlock().getLocation();
			new BukkitRunnable() {
				int radius = 0;
				@Override
				public void run() {
					radius++;
					for(int x = -radius;x<=radius;x++) {
						for(int y = -radius;y<=radius;y++) {
							for(int z = -radius;z<=radius;z++) {
								Location loc2 = plugin.addToLoc(loc, x, y, z);
								if(loc2.getBlock().getType().isSolid()) {
									loc2.getBlock().setType(Material.WAXED_OXIDIZED_CUT_COPPER);
								}
							}
						}
					}
					loc.getBlock().setType(Material.END_PORTAL);
					if(radius >= 5*10) {
						this.cancel();
					}
				}
			}.runTaskTimer(plugin, 1, 4);
		}
	}

}
