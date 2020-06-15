package omnikron13.justmetals.common;

import net.minecraft.creativetab.CreativeTabs;

public class NuggetItem extends BaseItem {
    protected String TYPE() { return "nugget"; }

    public NuggetItem(String name) {
        super(name);
        setCreativeTab(CreativeTabs.MISC);
    }
}
