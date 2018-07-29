package thefloydman.ltta.proxy;

import com.xcompwiz.mystcraft.api.MystObjects;
import com.xcompwiz.mystcraft.api.exception.APIUndefined;
import com.xcompwiz.mystcraft.api.exception.APIVersionRemoved;
import com.xcompwiz.mystcraft.api.exception.APIVersionUndefined;
import com.xcompwiz.mystcraft.api.hook.RenderAPI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy{
    private RenderAPI renderApi;

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        try {
            renderApi = (RenderAPI)MystObjects.entryPoint.getProviderInstance().getAPIInstance("render-1");
            // TODO: Log caught errors
        } catch(APIVersionRemoved e1) {
        } catch(APIVersionUndefined e2) {
        } catch(APIUndefined e3) {
        }
    }

    @Override
    public void drawPoemWord(float x, float y, float z, float scale, String word) {
        renderApi.drawWord(x, y, z, scale, word);
    }
}
