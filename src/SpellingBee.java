import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.meta.FireworkMeta;


public class SpellingBee implements Listener{

	private Main plugin;
	public SpellingBee(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onClick(PlayerInteractAtEntityEvent event) {
		Player p = event.getPlayer();
		if(event.getRightClicked().getType().equals(EntityType.BEE)) {
			if(event.getRightClicked().getCustomName()!=null && event.getRightClicked().getCustomName().equals(plugin.spellingBeeName)) {
				Bee bee = (Bee)event.getRightClicked();
				if(!plugin.firstClicked) {
					plugin.firstClicked = true;
					Bukkit.broadcastMessage(plugin.spellingBeeMessage);
				}
				else if(plugin.amount == 0) {
					if(plugin.holdsType(p, Material.BELL)) {
						plugin.decreaseItem(p);
						plugin.addItem(p, item.smartBellPickaxe());
						plugin.sendDelayedMessages(plugin.congratsMessage, 0);
						spawnFireworks(bee.getLocation());
						plugin.amount++;
					}
					else {
						plugin.sendDelayedMessages(plugin.firstWord, 0);
						plugin.sendDelayedMessages(plugin.bellMessage, 60);
					}
				}
				else if(plugin.amount == 1) {
					if(plugin.holdsType(p, Material.DIAMOND)) {
						plugin.decreaseItem(p);
						spawnFireworks(bee.getLocation());
						p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,p.getLocation(),30,1,1,1);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
						for(int i = 0;i<=64;i++) {
							p.getWorld().dropItem(plugin.addToLoc(p.getLocation(), rand.nextInt(10)-5, 3, rand.nextInt(10)-5), item.edibleDiamond());
						}
						plugin.amount++;
					}
					else {
						plugin.sendDelayedMessages(plugin.nextWord, 0);
						plugin.sendDelayedMessages(plugin.diamondMessage, 60);
					}
				}
				else if(plugin.amount == 2) {
					if(plugin.holdsType(p, Material.BEDROCK)) {
						plugin.decreaseItem(p);
						spawnFireworks(bee.getLocation());
						plugin.addItem(p, item.smartBedrockChest());
						plugin.amount++;
					}
					else {
						plugin.sendDelayedMessages(plugin.nextWord, 0);
						plugin.sendDelayedMessages(plugin.bedrockMessage, 60);
					}
				}
			}
		}
	}
	Random rand = new Random();
	public void spawnFireworks(Location loc) {
		FireworkEffect.Builder fwB = FireworkEffect.builder();
        fwB.flicker(rand.nextBoolean());
        fwB.trail(rand.nextBoolean());
        fwB.with(FireworkEffect.Type.BALL);
        fwB.withColor(Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        fwB.withFade(Color.fromRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        FireworkEffect fe = fwB.build();
		Firework f = (Firework) loc.getWorld().spawn(loc, Firework.class);
		FireworkMeta fm = f.getFireworkMeta();
		fm.addEffect(fe);
		f.setFireworkMeta(fm);
		f.detonate();
	}
}