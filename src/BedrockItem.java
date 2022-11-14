import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class BedrockItem implements Listener{

	private Main plugin;
	public BedrockItem(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				if(plugin.holdsItem(p, item.bedrockMelonSlice())) {
					if(p.getFoodLevel()>=20) {
						p.setFoodLevel(19);
					}
				}
			}
		}
	}
	public LivingEntity getNearbyEntity(Player p, Location loc) {
		for(Entity ent : loc.getWorld().getNearbyEntities(loc,1,1,1)) {
			if(!ent.equals(p) && !ent.getType().equals(EntityType.ARMOR_STAND)) {
				if(ent instanceof LivingEntity) {
					return (LivingEntity)ent;
				}
			}
		}
		return null;
	}
	public ArmorStand spawnStand(Player p,int radius) {
		final float radPerSec = 6f;
		final float radPerTick = radPerSec / 20f;
		ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(getLocationAroundCircle(p.getLocation(), radius, radPerTick * 0), EntityType.ARMOR_STAND);
		as.setInvulnerable(true);
		as.setInvisible(true);
		as.setGravity(false);
		as.getEquipment().setHelmet(item.bedrockMelonSlice());
		startTurning(p,as,radius);
		return as;
	}
	public void startTurning(Player p,ArmorStand as,int radius) {
		final float radPerSec = 6f;
		final float radPerTick = radPerSec / 20f;
		new BukkitRunnable() {
			int tick = 0;
			public void run() {
				Location center = p.getLocation();
				++tick;
				Location loc = getLocationAroundCircle(center, radius, radPerTick * tick);
				as.setVelocity(new Vector(1, 0, 0));
				as.teleport(loc);
			}
		}.runTaskTimer(plugin, 0L, 1L);
	}
	public Location getLocationAroundCircle(Location center, double radius, double angleInRadian) {
		double x = center.getX() + radius * Math.cos(angleInRadian);
		double z = center.getZ() + radius * Math.sin(angleInRadian);
		double y = center.getY();

		Location loc = new Location(center.getWorld(), x, y, z);
		Vector difference = center.toVector().clone().subtract(loc.toVector());
		loc.setDirection(difference);
		return loc;
	}
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent event) {
		Player p = event.getPlayer();
		if(plugin.isSame(event.getItem(), item.bedrockMelonSlice())) {
			event.setCancelled(true);
			ArrayList<ArmorStand> ases = new ArrayList<>();
			new BukkitRunnable() {
				int counter = 0;
				@Override
				public void run() {
					counter++;
					ArmorStand as = spawnStand(p,5);
					ases.add(as);
					if(counter >= 5) {
						this.cancel();
					}
				}
			}.runTaskTimer(plugin, 0, 5);
			new BukkitRunnable() {
				int count = 0;
				@Override
				public void run() {
					count++;
					if(count>=20*20) {
						for(ArmorStand as1 : ases) {
							as1.remove();
						}
						this.cancel();
					}
					for(Entity ent : p.getNearbyEntities(5, 3, 5)) {
						if(ent.getType()!=EntityType.ARMOR_STAND) {
							Vector dir = (ent.getLocation().toVector()).clone().subtract((p.getLocation().toVector())).normalize();
							ent.setVelocity(dir);
						}
					}
				}
			}.runTaskTimer(plugin, 0, 1);
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				if(plugin.holdsItem(p, item.smartBedrockChest())) {
					Location loc = event.getClickedBlock().getRelative(event.getBlockFace()).getLocation();
					plugin.decreaseItem(p);
					plugin.placeBlockArmorStand(loc, item.smartBedrockChest());
				}
			}
		}
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				if(plugin.holdsItem(p, item.bedrockArmor())) {
					plugin.decreaseItem(p);
					p.getEquipment().setChestplate(item.bedrockArmor());
					//check this place out later
					new BukkitRunnable() {
						@Override
						public void run() {
							if(!plugin.isSame(p.getInventory().getChestplate(), item.bedrockArmor())) {
								this.cancel();
							}
							p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,20,0));
							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,20,0));
						}
					}.runTaskTimer(plugin, 0, 10);
				}
			}
		}
	}
	Random rand = new Random();
	@EventHandler
	public void onClick(PlayerInteractAtEntityEvent event) {
		Entity ent = event.getRightClicked();
		if(ent.getType().equals(EntityType.ARMOR_STAND)) {
			ArmorStand stand = (ArmorStand)ent;
			if(plugin.hasHelmet(stand, item.smartBedrockChest())) {
				event.setCancelled(true);
				stand.getEquipment().setHelmet(item.smartBedrockChestOpen());
				new BukkitRunnable() {
					int dropTime = 0;
					@Override
					public void run() {
						dropTime++;
						ItemStack dropStack = null;
						if(dropTime == 4*5) {
							dropStack = item.bedrockArmor();
						}
						else if(dropTime == 4*10) {
							dropStack = item.bedrockMelonSlice();
						}
						else {
							if(rand.nextBoolean()) {
								dropStack = new ItemStack(Material.BEDROCK);
							}
							else {
								dropStack = new ItemStack(Material.EXPERIENCE_BOTTLE);
							}

						}
						Item it = (Item)stand.getWorld().dropItem(stand.getLocation(), dropStack);
						it.setVelocity(new Vector(0,1,0));
						if(dropTime >= 4*15) {
							this.cancel();
						}
					}
				}.runTaskTimer(plugin, 0, 5);
			}
		}
	}

}
