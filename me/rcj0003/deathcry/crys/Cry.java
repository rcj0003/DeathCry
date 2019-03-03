package me.rcj0003.deathcry.crys;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import me.rcj0003.deathcry.utils.ItemBuilder;

public enum Cry {
	None(null, null, null),
	Villager("Villager", Material.EMERALD, Sound.VILLAGER_DEATH),
	Cat("Cat", Material.LEASH, Sound.CAT_HISS),
	Spider("Spider", Material.WEB, Sound.SPIDER_DEATH),
	Zombie("Zombie", Material.ROTTEN_FLESH, Sound.ZOMBIE_DEATH),
	Skeleton("Skeleton", Material.BONE, Sound.SKELETON_DEATH),
	Horse("Horse", Material.SADDLE, Sound.HORSE_DEATH),
	Donkey("Donkey", Material.SADDLE, Sound.DONKEY_DEATH),
	Chicken("Chicken", Material.COOKED_CHICKEN, Sound.CHICKEN_HURT),
	Pig("Pig", Material.GRILLED_PORK, Sound.PIG_DEATH),
	Cow("Cow", Material.COOKED_BEEF, Sound.COW_HURT),
	Blaze("Blaze", Material.BLAZE_POWDER, Sound.BLAZE_BREATH),
	Enderman("Enderman", Material.ENDER_PEARL, Sound.ENDERMAN_DEATH),
	Creeper("Creeper", Material.SULPHUR, Sound.CREEPER_DEATH),
	ZombiePig("Zombie Pigman", Material.GOLD_NUGGET, Sound.ZOMBIE_PIG_DEATH),
	IronGolem("Iron Golem", Material.IRON_INGOT, Sound.IRONGOLEM_DEATH),
	Anvil("Anvil", Material.ANVIL, Sound.ANVIL_LAND),
	Explosion("Explosion", Material.TNT, Sound.EXPLODE),
	Firework("Firework", Material.FIREWORK, Sound.FIREWORK_LARGE_BLAST),
	Cave("Cave", Material.MOSSY_COBBLESTONE, Sound.AMBIENCE_CAVE),
	Thunder("Thunder", Material.GLOWSTONE_DUST, Sound.AMBIENCE_THUNDER),
	Wither("Wither", Material.SOUL_SAND, Sound.WITHER_DEATH),
	Dragon("Dragon", Material.DRAGON_EGG, Sound.ENDERDRAGON_GROWL);

	private String name;
	private Material icon;
	private Sound sfx;

	Cry(String name, Material icon, Sound sfx) {
		this.name = name;
		this.icon = icon;
		this.sfx = sfx;
	}

	public String getName() {
		return name != null ? name : "Nothing";
	}

	public Sound getSound() {
		return sfx;
	}

	public Material getIcon() {
		return icon != null ? icon : Material.AIR;
	}

	public ItemStack getItemStack() {
		return !isPlaceholder()
				? new ItemBuilder(getIcon(), 1).setDisplayName("&6&l" + getName() + " Death Cry")
						.setLore("&aYou have permission!").createItem()
				: new ItemBuilder(Material.BARRIER, 1).setDisplayName("&c&lRemove Death Cry")
						.setLore("&cRemove the sound.").createItem();
	}

	public String getPermission() {
		return name != null ? "deathcry.cry." + ChatColor.stripColor(getName().toLowerCase().replace(' ', '\0'))
				: "deathcry.cry";
	}

	public boolean isPlaceholder() {
		return name == null && sfx == null && icon == null;
	}
}