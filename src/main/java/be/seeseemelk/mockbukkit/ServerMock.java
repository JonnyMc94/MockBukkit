package be.seeseemelk.mockbukkit;

import be.seeseemelk.mockbukkit.block.data.BlockDataMock;
import be.seeseemelk.mockbukkit.boss.BossBarMock;
import be.seeseemelk.mockbukkit.boss.KeyedBossBarMock;
import be.seeseemelk.mockbukkit.command.CommandResult;
import be.seeseemelk.mockbukkit.command.ConsoleCommandSenderMock;
import be.seeseemelk.mockbukkit.command.MessageTarget;
import be.seeseemelk.mockbukkit.command.MockCommandMap;
import be.seeseemelk.mockbukkit.enchantments.EnchantmentsMock;
import be.seeseemelk.mockbukkit.entity.EntityMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMockFactory;
import be.seeseemelk.mockbukkit.help.HelpMapMock;
import be.seeseemelk.mockbukkit.inventory.AnvilInventoryMock;
import be.seeseemelk.mockbukkit.inventory.BarrelInventoryMock;
import be.seeseemelk.mockbukkit.inventory.BeaconInventoryMock;
import be.seeseemelk.mockbukkit.inventory.BrewerInventoryMock;
import be.seeseemelk.mockbukkit.inventory.ChestInventoryMock;
import be.seeseemelk.mockbukkit.inventory.DispenserInventoryMock;
import be.seeseemelk.mockbukkit.inventory.DropperInventoryMock;
import be.seeseemelk.mockbukkit.inventory.EnderChestInventoryMock;
import be.seeseemelk.mockbukkit.inventory.FurnaceInventoryMock;
import be.seeseemelk.mockbukkit.inventory.GrindstoneInventoryMock;
import be.seeseemelk.mockbukkit.inventory.HopperInventoryMock;
import be.seeseemelk.mockbukkit.inventory.InventoryMock;
import be.seeseemelk.mockbukkit.inventory.ItemFactoryMock;
import be.seeseemelk.mockbukkit.inventory.LecternInventoryMock;
import be.seeseemelk.mockbukkit.inventory.PlayerInventoryMock;
import be.seeseemelk.mockbukkit.inventory.ShulkerBoxInventoryMock;
import be.seeseemelk.mockbukkit.inventory.meta.ItemMetaMock;
import be.seeseemelk.mockbukkit.map.MapViewMock;
import be.seeseemelk.mockbukkit.plugin.PluginManagerMock;
import be.seeseemelk.mockbukkit.potion.MockPotionEffectType;
import be.seeseemelk.mockbukkit.profile.PlayerProfileMock;
import be.seeseemelk.mockbukkit.scheduler.BukkitSchedulerMock;
import be.seeseemelk.mockbukkit.scoreboard.ScoreboardManagerMock;
import be.seeseemelk.mockbukkit.services.ServicesManagerMock;
import be.seeseemelk.mockbukkit.tags.TagRegistry;
import be.seeseemelk.mockbukkit.tags.TagWrapperMock;
import be.seeseemelk.mockbukkit.tags.TagsMock;
import com.destroystokyo.paper.entity.ai.MobGoals;
import com.google.common.base.Preconditions;
import io.papermc.paper.datapack.DatapackManager;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Registry;
import org.bukkit.Server;
import org.bukkit.StructureType;
import org.bukkit.Tag;
import org.bukkit.Warning.WarningState;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldCreator;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.generator.ChunkGenerator.ChunkData;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.Recipe;
import org.bukkit.loot.LootTable;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.bukkit.potion.PotionBrewer;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.structure.StructureManager;
import org.bukkit.util.CachedServerIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ServerMock extends Server.Spigot implements Server
{

	private static final Component MOTD = Component.text("A Minecraft Server");

	private final Properties buildProperties = new Properties();
	private final Logger logger = Logger.getLogger("ServerMock");
	private final Thread mainThread = Thread.currentThread();
	private final MockUnsafeValues unsafe = new MockUnsafeValues();
	private final Map<String, TagRegistry> materialTags = new HashMap<>();
	private final Set<EntityMock> entities = new HashSet<>();
	private final List<World> worlds = new ArrayList<>();
	private final List<Recipe> recipes = new LinkedList<>();
	private final Map<NamespacedKey, KeyedBossBarMock> bossBars = new HashMap<>();
	private final ItemFactoryMock factory = new ItemFactoryMock();
	private final PlayerMockFactory playerFactory = new PlayerMockFactory(this);
	private final PluginManagerMock pluginManager = new PluginManagerMock(this);
	private final ScoreboardManagerMock scoreboardManager = new ScoreboardManagerMock();
	private final BukkitSchedulerMock scheduler = new BukkitSchedulerMock();
	private final ServicesManagerMock servicesManager = new ServicesManagerMock();
	private final MockPlayerList playerList = new MockPlayerList();
	private final MockCommandMap commandMap = new MockCommandMap(this);
	private final HelpMapMock helpMap = new HelpMapMock();
	private final StandardMessenger messenger = new StandardMessenger();
	private final Map<Integer, MapViewMock> mapViews = new HashMap<>();
	private int nextMapId = 1;

	private GameMode defaultGameMode = GameMode.SURVIVAL;
	private ConsoleCommandSender consoleSender;
	private int spawnRadius = 16;
	private @NotNull WarningState warningState = WarningState.DEFAULT;

	public ServerMock()
	{
		ServerMock.registerSerializables();

		// Register default Minecraft Potion Effect Types
		createPotionEffectTypes();
		TagsMock.loadDefaultTags(this, true);
		EnchantmentsMock.registerDefaultEnchantments();

		try
		{
			InputStream stream = ClassLoader.getSystemResourceAsStream("logger.properties");
			LogManager.getLogManager().readConfiguration(stream);
		}
		catch (IOException e)
		{
			logger.warning("Could not load file logger.properties");
		}

		try
		{
			buildProperties.load(ClassLoader.getSystemResourceAsStream("build.properties"));
		}
		catch (IOException | NullPointerException e)
		{
			logger.warning("Could not load build properties");
		}

		logger.setLevel(Level.ALL);

	}

	/**
	 * Checks if we are on the main thread. The main thread is the thread used to create this instance of the mock
	 * server.
	 *
	 * @return {@code true} if we are on the main thread, {@code false} if we are running on a different thread.
	 */
	public boolean isOnMainThread()
	{
		return mainThread.equals(Thread.currentThread());
	}

	/**
	 * Registers an entity so that the server can track it more easily. Should only be used internally.
	 *
	 * @param entity The entity to register
	 */
	public void registerEntity(@NotNull EntityMock entity)
	{
		AsyncCatcher.catchOp("entity add");
		entities.add(entity);
	}

	/**
	 * Returns a set of entities that exist on the server instance.
	 *
	 * @return A set of entities that exist on this server instance.
	 */
	@NotNull
	public Set<EntityMock> getEntities()
	{
		return Collections.unmodifiableSet(entities);
	}

	/**
	 * Add a specific player to the set.
	 *
	 * @param player The player to add.
	 */
	public void addPlayer(@NotNull PlayerMock player)
	{
		AsyncCatcher.catchOp("player add");
		playerList.addPlayer(player);

		CountDownLatch conditionLatch = new CountDownLatch(1);

		AsyncPlayerPreLoginEvent preLoginEvent = new AsyncPlayerPreLoginEvent(player.getName(),
				player.getAddress().getAddress(), player.getUniqueId());
		getPluginManager().callEventAsynchronously(preLoginEvent, (e) -> conditionLatch.countDown());

		try
		{
			conditionLatch.await();
		}
		catch (InterruptedException e)
		{
			getLogger().severe("Interrupted while waiting for AsyncPlayerPreLoginEvent! " +
					(StringUtils.isEmpty(e.getMessage()) ? "" : e.getMessage()));
			Thread.currentThread().interrupt();
		}

		Component joinMessage = MiniMessage.miniMessage()
				.deserialize("<name> has joined the Server!", Placeholder.component("name", player.displayName()));

		PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(player, joinMessage);
		Bukkit.getPluginManager().callEvent(playerJoinEvent);

		player.setLastPlayed(getCurrentServerTime());
		registerEntity(player);
	}

	/**
	 * Creates a random player and adds it.
	 *
	 * @return The player that was added.
	 */
	public @NotNull PlayerMock addPlayer()
	{
		AsyncCatcher.catchOp("player add");
		PlayerMock player = playerFactory.createRandomPlayer();
		addPlayer(player);
		return player;
	}

	/**
	 * Creates a player with a given name and adds it.
	 *
	 * @param name The name to give to the player.
	 * @return The added player.
	 */
	public @NotNull PlayerMock addPlayer(@NotNull String name)
	{
		AsyncCatcher.catchOp("player add");
		PlayerMock player = new PlayerMock(this, name);
		addPlayer(player);
		return player;
	}

	/**
	 * Set the numbers of mock players that are on this server. Note that it will remove all players that are already on
	 * this server.
	 *
	 * @param num The number of players that are on this server.
	 */
	public void setPlayers(int num)
	{
		AsyncCatcher.catchOp("set players");
		playerList.clearOnlinePlayers();

		for (int i = 0; i < num; i++)
			addPlayer();
	}

	/**
	 * Set the numbers of mock offline players that are on this server. Note that even players that are online are also
	 * considered offline player because an {@link OfflinePlayer} really just refers to anyone that has at some point in
	 * time played on the server.
	 *
	 * @param num The number of players that are on this server.
	 */
	public void setOfflinePlayers(int num)
	{
		AsyncCatcher.catchOp("set offline players");
		playerList.clearOfflinePlayers();

		for (PlayerMock player : getOnlinePlayers())
		{
			playerList.addPlayer(player);
		}

		for (int i = 0; i < num; i++)
		{
			OfflinePlayer player = playerFactory.createRandomOfflinePlayer();
			playerList.addOfflinePlayer(player);
		}
	}

	/**
	 * Get a specific mock player. A player's number will never change between invocations of {@link #setPlayers(int)}.
	 *
	 * @param num The number of the player to retrieve.
	 * @return The chosen player.
	 */
	public @NotNull PlayerMock getPlayer(int num)
	{
		return playerList.getPlayer(num);
	}

	/**
	 * Returns the {@link MockPlayerList} instance that is used by this server.
	 *
	 * @return The {@link MockPlayerList} instance.
	 */
	public @NotNull MockPlayerList getPlayerList()
	{
		return playerList;
	}

	@Override
	public @Nullable UUID getPlayerUniqueId(@NotNull String playerName)
	{
		return playerList.getOfflinePlayer(playerName).getUniqueId();
	}

	/**
	 * Adds a very simple super flat world with a given name.
	 *
	 * @param name The name to give to the world.
	 * @return The {@link WorldMock} that has been created.
	 */
	public @NotNull WorldMock addSimpleWorld(String name)
	{
		AsyncCatcher.catchOp("world creation");
		WorldMock world = new WorldMock();
		world.setName(name);
		worlds.add(world);
		return world;
	}

	/**
	 * Adds the given mocked world to this server.
	 *
	 * @param world The world to add.
	 */
	public void addWorld(WorldMock world)
	{
		AsyncCatcher.catchOp("world add");
		worlds.add(world);
	}

	/**
	 * Executes a command as the console.
	 *
	 * @param command The command to execute.
	 * @param args    The arguments to pass to the commands.
	 * @return The value returned by {@link Command#execute}.
	 */
	public @NotNull CommandResult executeConsole(@NotNull Command command, String... args)
	{
		return execute(command, getConsoleSender(), args);
	}

	/**
	 * Executes a command as the console.
	 *
	 * @param command The command to execute.
	 * @param args    The arguments to pass to the commands.
	 * @return The value returned by {@link Command#execute}.
	 */
	public CommandResult executeConsole(@NotNull String command, String... args)
	{
		return executeConsole(getCommandMap().getCommand(command), args);
	}

	/**
	 * Executes a command as a player.
	 *
	 * @param command The command to execute.
	 * @param args    The arguments to pass to the commands.
	 * @return The value returned by {@link Command#execute}.
	 */
	public @NotNull CommandResult executePlayer(@NotNull Command command, String... args)
	{
		AsyncCatcher.catchOp("command dispatch");

		if (playerList.isSomeoneOnline())
			return execute(command, getPlayer(0), args);
		else
			throw new IllegalStateException("Need at least one player to run the command");
	}

	/**
	 * Executes a command as a player.
	 *
	 * @param command The command to execute.
	 * @param args    The arguments to pass to the commands.
	 * @return The value returned by {@link Command#execute}.
	 */
	public CommandResult executePlayer(@NotNull String command, String... args)
	{
		return executePlayer(getCommandMap().getCommand(command), args);
	}

	/**
	 * Executes a command.
	 *
	 * @param command The command to execute.
	 * @param sender  The person that executed the command.
	 * @param args    The arguments to pass to the commands.
	 * @return The value returned by {@link Command#execute}.
	 */
	public @NotNull CommandResult execute(@NotNull Command command, CommandSender sender, String... args)
	{
		AsyncCatcher.catchOp("command dispatch");

		if (!(sender instanceof MessageTarget))
		{
			throw new IllegalArgumentException("Only a MessageTarget can be the sender of the command");
		}

		boolean status = command.execute(sender, command.getName(), args);
		return new CommandResult(status, (MessageTarget) sender);
	}

	/**
	 * Executes a command.
	 *
	 * @param command The command to execute.
	 * @param sender  The person that executed the command.
	 * @param args    The arguments to pass to the commands.
	 * @return The value returned by {@link Command#execute}.
	 */
	public @NotNull CommandResult execute(@NotNull String command, CommandSender sender, String... args)
	{
		AsyncCatcher.catchOp("command dispatch");
		return execute(getCommandMap().getCommand(command), sender, args);
	}

	@Override
	public @NotNull String getName()
	{
		return "ServerMock";
	}

	@Override
	public @NotNull String getVersion()
	{
		return String.format("MockBukkit (MC: %s)", getBukkitVersion());
	}

	@Override
	public @NotNull String getBukkitVersion()
	{
		return getMinecraftVersion();
	}

	@Override
	public @NotNull String getMinecraftVersion()
	{
		String apiVersion;
		if (buildProperties == null || (apiVersion = buildProperties.getProperty("full-api-version")) == null)
		{
			throw new IllegalStateException("Minecraft version could not be determined");
		}
		return apiVersion.split("-")[0];
	}

	@Override
	public @NotNull Collection<? extends PlayerMock> getOnlinePlayers()
	{
		return playerList.getOnlinePlayers();
	}

	@Override
	public OfflinePlayer @NotNull [] getOfflinePlayers()
	{
		return playerList.getOfflinePlayers();
	}

	@Override
	public @Nullable OfflinePlayer getOfflinePlayerIfCached(@NotNull String name)
	{
		//TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public Player getPlayer(@NotNull String name)
	{
		return playerList.getPlayer(name);
	}

	@Override
	public Player getPlayerExact(@NotNull String name)
	{
		return playerList.getPlayerExact(name);
	}

	@Override
	public @NotNull List<Player> matchPlayer(@NotNull String name)
	{
		return playerList.matchPlayer(name);
	}

	@Override
	public Player getPlayer(@NotNull UUID id)
	{
		return playerList.getPlayer(id);
	}

	@Override
	public @NotNull PluginManagerMock getPluginManager()
	{
		return pluginManager;
	}

	@NotNull
	public MockCommandMap getCommandMap()
	{
		return commandMap;
	}

	@Override
	public PluginCommand getPluginCommand(@NotNull String name)
	{
		Command command = getCommandMap().getCommand(name);
		return command instanceof PluginCommand ? (PluginCommand) command : null;
	}

	@Override
	public @NotNull Logger getLogger()
	{
		return logger;
	}

	@Override
	public @NotNull ConsoleCommandSender getConsoleSender()
	{
		if (consoleSender == null)
		{
			consoleSender = new ConsoleCommandSenderMock();
		}
		return consoleSender;
	}

	@Override
	public @NotNull CommandSender createCommandSender(@NotNull Consumer<? super Component> feedback)
	{
		//TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@NotNull
	@Deprecated
	public InventoryMock createInventory(InventoryHolder owner, @NotNull InventoryType type, String title, int size)
	{
		if (!type.isCreatable())
		{
			throw new IllegalArgumentException("Inventory Type is not creatable!");
		}

		switch (type)
		{
		case CHEST:
			return new ChestInventoryMock(owner, size > 0 ? size : 9 * 3);
		case DISPENSER:
			return new DispenserInventoryMock(owner);
		case DROPPER:
			return new DropperInventoryMock(owner);
		case PLAYER:
			if (owner instanceof HumanEntity)
			{
				return new PlayerInventoryMock((HumanEntity) owner);
			}
			else
			{
				throw new IllegalArgumentException("Cannot create a Player Inventory for: " + owner);
			}
		case ENDER_CHEST:
			return new EnderChestInventoryMock(owner);
		case HOPPER:
			return new HopperInventoryMock(owner);
		case SHULKER_BOX:
			return new ShulkerBoxInventoryMock(owner);
		case BARREL:
			return new BarrelInventoryMock(owner);
		case LECTERN:
			return new LecternInventoryMock(owner);
		case GRINDSTONE:
			return new GrindstoneInventoryMock(owner);
		case STONECUTTER:
			// TODO: This Inventory Type needs to be implemented
		case CARTOGRAPHY:
			// TODO: This Inventory Type needs to be implemented
		case SMOKER, FURNACE, BLAST_FURNACE:
			return new FurnaceInventoryMock(owner);
		case LOOM:
			// TODO: This Inventory Type needs to be implemented
		case ANVIL:
			return new AnvilInventoryMock(owner);
		case SMITHING:
			// TODO: This Inventory Type needs to be implemented
		case BEACON:
			return new BeaconInventoryMock(owner);
		case WORKBENCH:
			// TODO: This Inventory Type needs to be implemented
		case ENCHANTING:
			// TODO: This Inventory Type needs to be implemented
		case BREWING:
			return new BrewerInventoryMock(owner);
		case CRAFTING:
			// TODO: This Inventory Type needs to be implemented
		case CREATIVE:
			// TODO: This Inventory Type needs to be implemented
		case MERCHANT:
			// TODO: This Inventory Type needs to be implemented
		default:
			throw new UnimplementedOperationException("Inventory type not yet supported");
		}
	}

	@Override
	public @NotNull InventoryMock createInventory(InventoryHolder owner, @NotNull InventoryType type)
	{
		return createInventory(owner, type, "Inventory");
	}

	@Override
	public @NotNull InventoryMock createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type, @NotNull Component title)
	{
		//TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public @NotNull InventoryMock createInventory(InventoryHolder owner, @NotNull InventoryType type, String title)
	{
		return createInventory(owner, type, title, -1);
	}

	@Override
	public @NotNull InventoryMock createInventory(InventoryHolder owner, int size)
	{
		return createInventory(owner, size, "Inventory");
	}

	@Override
	public @NotNull InventoryMock createInventory(@Nullable InventoryHolder owner, int size, @NotNull Component title) throws IllegalArgumentException
	{
		//TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public @NotNull InventoryMock createInventory(InventoryHolder owner, int size, String title)
	{
		return createInventory(owner, InventoryType.CHEST, title, size);
	}

	@Override
	public @NotNull Merchant createMerchant(@Nullable Component title)
	{
		//TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull ItemFactoryMock getItemFactory()
	{
		return factory;
	}

	@Override
	public @NotNull List<World> getWorlds()
	{
		return new ArrayList<>(worlds);
	}

	@Override
	public World getWorld(String name)
	{
		return worlds.stream().filter(world -> world.getName().equals(name)).findAny().orElse(null);
	}

	@Override
	public World getWorld(UUID uid)
	{
		return worlds.stream().filter(world -> world.getUID().equals(uid)).findAny().orElse(null);
	}

	@Override
	public @Nullable World getWorld(@NotNull NamespacedKey worldKey)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@NotNull
	@Override
	public WorldBorder createWorldBorder()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull BukkitSchedulerMock getScheduler()
	{
		return scheduler;
	}

	@Override
	public int getMaxPlayers()
	{
		return playerList.getMaxPlayers();
	}

	@Override
	public void setMaxPlayers(int maxPlayers)
	{
		playerList.setMaxPlayers(maxPlayers);
	}

	@Override
	public @NotNull Set<String> getIPBans()
	{
		return this.playerList.getIPBans().getBanEntries().stream().map(BanEntry::getTarget)
				.collect(Collectors.toSet());
	}

	@Override
	public void banIP(@NotNull String address)
	{
		this.playerList.getIPBans().addBan(address, null, null, null);
	}

	@Override
	public void unbanIP(@NotNull String address)
	{
		this.playerList.getIPBans().pardon(address);
	}

	@Override
	public @NotNull BanList getBanList(@NotNull Type type)
	{
		return switch (type)
				{
					case IP -> playerList.getIPBans();
					case NAME -> playerList.getProfileBans();
				};
	}

	@Override
	public @NotNull Set<OfflinePlayer> getOperators()
	{
		return playerList.getOperators();
	}

	@Override
	public @NotNull GameMode getDefaultGameMode()
	{
		return this.defaultGameMode;
	}

	@Override
	public void setDefaultGameMode(GameMode mode)
	{
		this.defaultGameMode = mode;
	}

	@Override
	@Deprecated
	public int broadcastMessage(@NotNull String message)
	{
		Collection<? extends PlayerMock> players = getOnlinePlayers();

		for (Player player : players)
		{
			player.sendMessage(message);
		}

		return players.size();
	}

	@Override
	@Deprecated
	public int broadcast(@NotNull String message, @NotNull String permission)
	{
		Collection<? extends PlayerMock> players = getOnlinePlayers();
		int count = 0;

		for (Player player : players)
		{
			if (player.hasPermission(permission))
			{
				player.sendMessage(message);
				count++;
			}
		}
		return count;
	}

	@Override
	public int broadcast(@NotNull Component message)
	{
		Collection<? extends PlayerMock> players = getOnlinePlayers();

		for (Player player : players)
		{
			player.sendMessage(message);
		}

		return players.size();
	}

	@Override
	public int broadcast(@NotNull Component message, @NotNull String permission)
	{
		Collection<? extends PlayerMock> players = getOnlinePlayers();
		int count = 0;

		for (Player player : players)
		{
			if (player.hasPermission(permission))
			{
				player.sendMessage(message);
				count++;
			}
		}
		return count;
	}

	/**
	 * Registers any classes that are serializable with the ConfigurationSerializable system of Bukkit.
	 */
	public static void registerSerializables()
	{
		ConfigurationSerialization.registerClass(ItemMetaMock.class);
	}

	@Override
	public boolean addRecipe(Recipe recipe)
	{
		recipes.add(recipe);
		return true;
	}

	@Override
	public @NotNull List<Recipe> getRecipesFor(@NotNull ItemStack item)
	{
		return recipes.stream().filter(recipe ->
		{
			ItemStack result = recipe.getResult();
			// Amount is explicitly ignored here
			return result.getType() == item.getType() && result.getItemMeta().equals(item.getItemMeta());
		}).collect(Collectors.toList());
	}

	@Override
	public Recipe getRecipe(NamespacedKey key)
	{
		for (Recipe recipe : recipes)
		{
			// Seriously why can't the Recipe interface itself just extend Keyed...
			if (recipe instanceof Keyed && ((Keyed) recipe).getKey().equals(key))
			{
				return recipe;
			}
		}

		return null;
	}

	@Nullable
	@Override
	public Recipe getCraftingRecipe(@NotNull ItemStack[] craftingMatrix, @NotNull World world)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@NotNull
	@Override
	public ItemStack craftItem(@NotNull ItemStack[] craftingMatrix, @NotNull World world, @NotNull Player player)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean removeRecipe(NamespacedKey key)
	{
		Iterator<Recipe> iterator = recipeIterator();

		while (iterator.hasNext())
		{
			Recipe recipe = iterator.next();

			// Seriously why can't the Recipe interface itself just extend Keyed...
			if (recipe instanceof Keyed && ((Keyed) recipe).getKey().equals(key))
			{
				iterator.remove();
				return true;
			}
		}

		return false;
	}

	@Override
	public @NotNull Iterator<Recipe> recipeIterator()
	{
		return recipes.iterator();
	}

	@Override
	public void clearRecipes()
	{
		recipes.clear();
	}

	@Override
	public boolean dispatchCommand(@NotNull CommandSender sender, @NotNull String commandLine)
	{
		AsyncCatcher.catchOp("command dispatch");
		String[] commands = commandLine.split(" ");
		String commandLabel = commands[0];
		String[] args = Arrays.copyOfRange(commands, 1, commands.length);
		Command command = getCommandMap().getCommand(commandLabel);

		if (command != null)
		{
			return command.execute(sender, commandLabel, args);
		}
		else
		{
			return false;
		}
	}

	public @NotNull List<String> getCommandTabComplete(@NotNull CommandSender sender, @NotNull String commandLine)
	{
		AsyncCatcher.catchOp("command tabcomplete");
		int idx = commandLine.indexOf(' ');
		String commandLabel = commandLine.substring(0, idx);
		String[] args = commandLine.substring(idx + 1).split(" ", -1);
		Command command = getCommandMap().getCommand(commandLabel);

		if (command != null)
		{
			return command.tabComplete(sender, commandLabel, args);
		}
		else
		{
			return Collections.emptyList();
		}
	}

	@Override
	public @NotNull HelpMapMock getHelpMap()
	{
		return helpMap;
	}

	@Override
	public void sendPluginMessage(@NotNull Plugin source, @NotNull String channel, byte[] message)
	{
		StandardMessenger.validatePluginMessage(this.getMessenger(), source, channel, message);

		for (Player player : this.getOnlinePlayers())
		{
			player.sendPluginMessage(source, channel, message);
		}
	}

	@Override
	public @NotNull Set<String> getListeningPluginChannels()
	{
		Set<String> result = new HashSet<>();

		for (Player player : this.getOnlinePlayers())
		{
			result.addAll(player.getListeningPluginChannels());
		}

		return result;
	}

	@Override
	public int getPort()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public int getViewDistance()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull String getIp()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull String getWorldType()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean getGenerateStructures()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean getAllowEnd()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean getAllowNether()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@NotNull
	@Override
	public String getResourcePack()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@NotNull
	@Override
	public String getResourcePackHash()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@NotNull
	@Override
	public String getResourcePackPrompt()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean isResourcePackRequired()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean hasWhitelist()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public void setWhitelist(boolean value)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean isWhitelistEnforced()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public void setWhitelistEnforced(boolean value)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull Set<OfflinePlayer> getWhitelistedPlayers()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public void reloadWhitelist()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull String getUpdateFolder()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull File getUpdateFolderFile()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public long getConnectionThrottle()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public int getTicksPerAnimalSpawns()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public int getTicksPerMonsterSpawns()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull ServicesManagerMock getServicesManager()
	{
		return servicesManager;
	}

	@Override
	public World createWorld(@NotNull WorldCreator creator)
	{
		WorldMock world = new WorldMock(creator);
		addWorld(world);
		return world;
	}

	@Override
	public boolean unloadWorld(String name, boolean save)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean unloadWorld(World world, boolean save)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull MapViewMock createMap(@NotNull World world)
	{
		MapViewMock mapView = new MapViewMock(world, nextMapId++);
		mapViews.put(mapView.getId(), mapView);
		new MapInitializeEvent(mapView).callEvent();
		return mapView;
	}

	@Override
	public void reload()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public void reloadData()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public void savePlayers()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public void resetRecipes()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull Map<String, String[]> getCommandAliases()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public int getSpawnRadius()
	{
		return spawnRadius;
	}

	@Override
	public void setSpawnRadius(int spawnRadius)
	{
		this.spawnRadius = spawnRadius;
	}

	@Override
	public boolean shouldSendChatPreviews()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean isEnforcingSecureProfiles()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean getOnlineMode()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean getAllowFlight()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean isHardcore()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public void shutdown()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public @NotNull OfflinePlayer getOfflinePlayer(@NotNull String name)
	{
		return playerList.getOfflinePlayer(name);
	}

	@Override
	public @NotNull OfflinePlayer getOfflinePlayer(@NotNull UUID id)
	{
		OfflinePlayer player = playerList.getOfflinePlayer(id);

		if (player != null)
		{
			return player;
		}
		else
		{
			return playerFactory.createOfflinePlayer(id);
		}
	}

	@Override
	public @NotNull Set<OfflinePlayer> getBannedPlayers()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull File getWorldContainer()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull Messenger getMessenger()
	{
		return this.messenger;
	}

	@Override
	@Deprecated
	public @NotNull Merchant createMerchant(String title)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public int getMaxChainedNeighborUpdates()
	{
		//TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public int getMonsterSpawnLimit()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public int getAnimalSpawnLimit()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public int getWaterAnimalSpawnLimit()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public int getAmbientSpawnLimit()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean isPrimaryThread()
	{
		return this.isOnMainThread();
	}

	@Override
	public @NotNull Component motd()
	{
		return MOTD;
	}

	@Override
	@Deprecated
	public @NotNull String getMotd()
	{
		return LegacyComponentSerializer.legacySection().serialize(MOTD);
	}

	@Override
	public @Nullable Component shutdownMessage()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public String getShutdownMessage()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	/**
	 * Sets the return value of {@link #getWarningState}.
	 *
	 * @param warningState The {@link WarningState} to set.
	 */
	public void setWarningState(@NotNull WarningState warningState)
	{
		Preconditions.checkNotNull(warningState, "warningState cannot be null");
		this.warningState = warningState;
	}

	@Override
	public @NotNull WarningState getWarningState()
	{
		return this.warningState;
	}

	@Override
	public @NotNull ScoreboardManagerMock getScoreboardManager()
	{
		return scoreboardManager;
	}

	@Override
	public CachedServerIcon getServerIcon()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull CachedServerIcon loadServerIcon(File file)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull CachedServerIcon loadServerIcon(BufferedImage image)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public void setIdleTimeout(int threshold)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public int getIdleTimeout()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull ChunkData createChunkData(@NotNull World world)
	{
		Preconditions.checkNotNull(world, "World cannot be null");
		return new MockChunkData(world);
	}

	@Override

	@Deprecated(forRemoval = true)
	public @NotNull ChunkData createVanillaChunkData(@NotNull World world, int x, int z)
	{
		//TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull BossBar createBossBar(@NotNull String title, @NotNull BarColor color, @NotNull BarStyle style, BarFlag... flags)
	{
		return new BossBarMock(title, color, style, flags);
	}

	@Override
	public @Nullable Entity getEntity(@NotNull UUID uuid)
	{
		Preconditions.checkNotNull(uuid, "uuid cannot be null");

		for (EntityMock entity : entities)
		{
			if (entity.getUniqueId().equals(uuid))
			{
				return entity;
			}
		}
		return null;
	}

	@Override
	public @NotNull double[] getTPS()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull long[] getTickTimes()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public double getAverageTickTime()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public Advancement getAdvancement(NamespacedKey key)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull Iterator<Advancement> advancementIterator()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public @NotNull MockUnsafeValues getUnsafe()
	{
		return unsafe;
	}

	@Override
	public @NotNull BlockData createBlockData(@NotNull Material material)
	{
		Preconditions.checkNotNull(material, "Must provide material");
		return BlockDataMock.mock(material);
	}

	@Override
	public @NotNull BlockData createBlockData(@NotNull Material material, @Nullable Consumer<BlockData> consumer)
	{
		BlockData blockData = createBlockData(material);

		if (consumer != null)
		{
			consumer.accept(blockData);
		}

		return blockData;
	}

	@Override
	public @NotNull BlockData createBlockData(String data)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull BlockData createBlockData(Material material, String data)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	/**
	 * This creates a new Mock {@link Tag} for the {@link Material} class.<br>
	 * Call this in advance before you are gonna access {@link #getTag(String, NamespacedKey, Class)} or any of the
	 * constants defined in {@link Tag}.
	 *
	 * @param key         The {@link NamespacedKey} for this {@link Tag}
	 * @param registryKey The name of the {@link TagRegistry}.
	 * @param materials   {@link Material Materials} which should be covered by this {@link Tag}
	 * @return The newly created {@link Tag}
	 */
	@NotNull
	public Tag<Material> createMaterialTag(@NotNull NamespacedKey key, @NotNull String registryKey, @NotNull Material... materials)
	{
		Preconditions.checkNotNull(key, "A NamespacedKey must never be null");

		TagRegistry registry = materialTags.get(registryKey);
		TagWrapperMock tag = new TagWrapperMock(registry, key);
		registry.getTags().put(key, tag);
		return tag;
	}

	public void addTagRegistry(@NotNull TagRegistry registry)
	{
		materialTags.put(registry.getRegistry(), registry);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Keyed> Tag<T> getTag(String registryKey, NamespacedKey key, Class<T> clazz)
	{
		if (clazz == Material.class)
		{
			TagRegistry registry = materialTags.get(registryKey);

			if (registry != null)
			{
				Tag<Material> tag = registry.getTags().get(key);

				if (tag != null)
				{
					return (Tag<T>) tag;
				}
			}
		}

		// Per definition this method should return null if the given tag does not exist.
		return null;
	}

	/**
	 * This registers Minecrafts default {@link PotionEffectType PotionEffectTypes}. It also prevents any new effects to
	 * be created afterwards.
	 */
	private void createPotionEffectTypes()
	{
		for (PotionEffectType type : PotionEffectType.values())
		{
			// We probably already registered all Potion Effects
			// otherwise this would be null
			if (type != null)
			{
				// This is not perfect, but it works.
				return;
			}
		}

		registerPotionEffectType(1, "SPEED", false, 8171462);
		registerPotionEffectType(2, "SLOWNESS", false, 5926017);
		registerPotionEffectType(3, "HASTE", false, 14270531);
		registerPotionEffectType(4, "MINING_FATIGUE", false, 4866583);
		registerPotionEffectType(5, "STRENGTH", false, 9643043);
		registerPotionEffectType(6, "INSTANT_HEALTH", true, 16262179);
		registerPotionEffectType(7, "INSTANT_DAMAGE", true, 4393481);
		registerPotionEffectType(8, "JUMP_BOOST", false, 2293580);
		registerPotionEffectType(9, "NAUSEA", false, 5578058);
		registerPotionEffectType(10, "REGENERATION", false, 13458603);
		registerPotionEffectType(11, "RESISTANCE", false, 10044730);
		registerPotionEffectType(12, "FIRE_RESISTANCE", false, 14981690);
		registerPotionEffectType(13, "WATER_BREATHING", false, 3035801);
		registerPotionEffectType(14, "INVISIBILITY", false, 8356754);
		registerPotionEffectType(15, "BLINDNESS", false, 2039587);
		registerPotionEffectType(16, "NIGHT_VISION", false, 2039713);
		registerPotionEffectType(17, "HUNGER", false, 5797459);
		registerPotionEffectType(18, "WEAKNESS", false, 4738376);
		registerPotionEffectType(19, "POISON", false, 5149489);
		registerPotionEffectType(20, "WITHER", false, 3484199);
		registerPotionEffectType(21, "HEALTH_BOOST", false, 16284963);
		registerPotionEffectType(22, "ABSORPTION", false, 2445989);
		registerPotionEffectType(23, "SATURATION", true, 16262179);
		registerPotionEffectType(24, "GLOWING", false, 9740385);
		registerPotionEffectType(25, "LEVITATION", false, 13565951);
		registerPotionEffectType(26, "LUCK", false, 3381504);
		registerPotionEffectType(27, "UNLUCK", false, 12624973);
		registerPotionEffectType(28, "SLOW_FALLING", false, 16773073);
		registerPotionEffectType(29, "CONDUIT_POWER", false, 1950417);
		registerPotionEffectType(30, "DOLPHINS_GRACE", false, 8954814);
		registerPotionEffectType(31, "BAD_OMEN", false, 745784);
		registerPotionEffectType(32, "HERO_OF_THE_VILLAGE", false, 4521796);
		registerPotionEffectType(33, "DARKNESS", false, 2696993);
		PotionEffectType.stopAcceptingRegistrations();
	}

	private void registerPotionEffectType(int id, @NotNull String name, boolean instant, int rgb)
	{
		NamespacedKey key = NamespacedKey.minecraft(name.toLowerCase(Locale.ROOT));
		PotionEffectType type = new MockPotionEffectType(key, id, name, instant, Color.fromRGB(rgb));
		PotionEffectType.registerPotionEffectType(type);
	}

	@Override
	public LootTable getLootTable(NamespacedKey key)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull ItemStack createExplorerMap(World world, Location location, StructureType structureType)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull ItemStack createExplorerMap(World world, Location location, StructureType structureType, int radius,
												boolean findUnexplored)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull KeyedBossBar createBossBar(@NotNull NamespacedKey key, @NotNull String title, @NotNull BarColor color, @NotNull BarStyle style, BarFlag... flags)
	{
		Preconditions.checkNotNull(key, "A NamespacedKey must never be null");
		KeyedBossBarMock bar = new KeyedBossBarMock(key, title, color, style, flags);
		bossBars.put(key, bar);
		return bar;
	}

	@Override
	public @NotNull Iterator<KeyedBossBar> getBossBars()
	{
		return bossBars.values().stream().map(bossbar -> (KeyedBossBar) bossbar).iterator();
	}

	@Override
	public KeyedBossBar getBossBar(NamespacedKey key)
	{
		Preconditions.checkNotNull(key, "A NamespacedKey must never be null");
		return bossBars.get(key);
	}

	@Override
	public boolean removeBossBar(NamespacedKey key)
	{
		Preconditions.checkNotNull(key, "A NamespacedKey must never be null");
		return bossBars.remove(key, bossBars.get(key));
	}

	@Override
	public @NotNull List<Entity> selectEntities(CommandSender sender, String selector)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@NotNull
	@Override
	public StructureManager getStructureManager()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();

	}

	@Override
	public @Nullable <T extends Keyed> Registry<T> getRegistry(@NotNull Class<T> tClass)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public MapViewMock getMap(int id)
	{
		return mapViews.get(id);
	}

	@Override
	public <T extends Keyed> @NotNull Iterable<Tag<T>> getTags(String registry, Class<T> clazz)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public int getTicksPerWaterSpawns()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public int getTicksPerAmbientSpawns()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	/**
	 * This returns the current time of the {@link Server} in milliseconds
	 *
	 * @return The current {@link Server} time
	 */
	protected long getCurrentServerTime()
	{
		return System.currentTimeMillis();
	}

	@Override
	public int getTicksPerWaterAmbientSpawns()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public int getTicksPerWaterUndergroundCreatureSpawns()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();

	}

	@Override
	@Deprecated
	public int getWaterAmbientSpawnLimit()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public int getWaterUndergroundCreatureSpawnLimit()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();

	}

	@Override
	public int getMaxWorldSize()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public int getSimulationDistance()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean getHideOnlinePlayers()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public Server.@NotNull Spigot spigot()
	{
		return this;
	}


	@Override
	public void reloadPermissions()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean reloadCommandAliases()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean suggestPlayerNamesWhenNullTabCompletions()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull String getPermissionMessage()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull Component permissionMessage()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull PlayerProfileMock createProfile(@NotNull UUID uuid)
	{
		return createProfile(uuid, null);
	}

	@Override
	public @NotNull PlayerProfileMock createProfile(@NotNull String name)
	{
		return createProfile(null, name);
	}

	@Override
	public @NotNull PlayerProfileMock createProfile(@Nullable UUID uuid, @Nullable String name)
	{
		return new PlayerProfileMock(name, uuid);
	}

	@Override
	public @NotNull PlayerProfileMock createProfileExact(@Nullable UUID uuid, @Nullable String name)
	{
		return new PlayerProfileMock(name, uuid);
	}

	@Override
	public int getCurrentTick()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean isStopping()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull MobGoals getMobGoals()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull DatapackManager getDatapackManager()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@NotNull
	@Override
	public YamlConfiguration getConfig()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public void broadcast(@NotNull BaseComponent component)
	{
		for (Player player : getOnlinePlayers())
		{
			player.spigot().sendMessage(component);
		}
	}

	@Override
	@Deprecated
	public void broadcast(@NotNull BaseComponent... components)
	{
		for (Player player : getOnlinePlayers())
		{
			player.spigot().sendMessage(components);
		}
	}

	@Override
	public void restart()
	{
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public int getTicksPerSpawns(@NotNull SpawnCategory spawnCategory)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public @NotNull PlayerProfileMock createPlayerProfile(@Nullable UUID uniqueId, @Nullable String name)
	{
		return new PlayerProfileMock(name, uniqueId);
	}

	@Override
	@Deprecated
	public @NotNull PlayerProfileMock createPlayerProfile(@NotNull UUID uniqueId)
	{
		return createPlayerProfile(uniqueId, null);
	}

	@Override
	@Deprecated
	public @NotNull PlayerProfileMock createPlayerProfile(@NotNull String name)
	{
		return createPlayerProfile(null, name);
	}

	@Override
	public int getSpawnLimit(@NotNull SpawnCategory spawnCategory)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull PotionBrewer getPotionBrewer()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @NotNull File getPluginsFolder()
	{
		try
		{
			return getPluginManager().getParentTemporaryDirectory();
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public @NotNull Iterable<? extends Audience> audiences()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

}
