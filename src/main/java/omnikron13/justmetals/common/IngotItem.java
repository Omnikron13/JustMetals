package omnikron13.justmetals.common;

import net.minecraft.creativetab.CreativeTabs;

public class IngotItem extends BaseItem {
    protected String TYPE() { return "ingot"; }

    public IngotItem(String name) {
        super(name);
        setCreativeTab(CreativeTabs.MISC);
    }
}
