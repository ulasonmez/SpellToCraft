import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GettingLetters implements Listener{

	private Main plugin;
	public GettingLetters(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onInteract(BlockBreakEvent event) {
		Material mat = event.getBlock().getType();
		event.getBlock().setType(Material.AIR);
		event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), plugin.getLetters(mat));
	}
}
