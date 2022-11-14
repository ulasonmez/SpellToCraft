import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor{

	Items item = new Items();
	private Main plugin;
	public Commands(Main plugin) {
		this.plugin=plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(label.equalsIgnoreCase("giveitem")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("all")) {
						for(ItemStack allItem : item.allItems) {
							plugin.addItem(p, allItem);
						}
					}
				}
			}
			else if(label.equals("spawn")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("spellingbee")) {
						Bee bee = (Bee)p.getWorld().spawnEntity(p.getLocation(), EntityType.BEE);
						bee.setCustomName(plugin.spellingBeeName);
					}
				}
			}
			else if(label.equals("giveletters")) {
				if(args.length == 1) {
					for(String s : args[0].split("")) {
						ItemStack it = plugin.getLetterByString(s);
						plugin.addItem(p, it);
					}
				}
			}
		}
		return false;
	}
}
