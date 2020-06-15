package omnikron13.justmetals.common;

import net.minecraft.creativetab.CreativeTabs;

public class DustItem extends BaseItem {
    protected String TYPE() { return "dust"; }

    public DustItem(String name) {
        super(name);
        setCreativeTab(CreativeTabs.MISC);
    }
}
