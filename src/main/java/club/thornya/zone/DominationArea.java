//gerado marotamente pelo bard.google.com

package club.thornya.zone;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public final class DominationArea extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}




public class LandProtectionPlugin implements Listener {

    private static final int CHUNK_SIZE = 16;
    private static final int VIRTUAL_CHUNK_SIZE = 3 * CHUNK_SIZE;

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material material = block.getType();

        if (material == Material.OBSIDIAN && player.getItemInHand().getType() == Material.STICK) {
            // The player placed an obsidian block and clicked with a stick.

            // Get the coordinates of the obsidian block.
            int x = block.getX();
            int y = block.getY();
            int z = block.getZ();

            // Get the virtual chunk coordinates of the obsidian block.
            int virtualChunkX = x / CHUNK_SIZE;
            int virtualChunkY = y / CHUNK_SIZE;
            int virtualChunkZ = z / CHUNK_SIZE;

            // Protect the 3x3 virtual chunk around the obsidian block.
            for (int i = virtualChunkX - 1; i <= virtualChunkX + 1; i++) {
                for (int j = virtualChunkY - 1; j <= virtualChunkY + 1; j++) {
                    for (int k = virtualChunkZ - 1; k <= virtualChunkZ + 1; k++) {
                        // Get the block at the specified virtual chunk coordinates.
                        Block targetBlock = block.getWorld().getBlockAt(i * CHUNK_SIZE, j * CHUNK_SIZE, k * CHUNK_SIZE);

                        // If the block is not air, protect it.
                        if (targetBlock.getType() != Material.AIR) {
                            targetBlock.setMetadata("protected", new FixedMetadataValue(this, true));
                        }
                    }
                }
            }

            // Send a message to the player informing them that their land has been protected.
            player.sendMessage(ChatColor.GREEN + "Your land has been protected!");
        }
    }
}
