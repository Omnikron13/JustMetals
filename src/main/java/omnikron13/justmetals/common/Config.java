package omnikron13.justmetals.common;

import lombok.Getter;
import lombok.SneakyThrows;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Config {
    public static final Pattern METALS_FILE_PATTERN = Pattern.compile(".*\\.yaml", Pattern.CASE_INSENSITIVE);
    public static final Pattern METALS_FOLDER_PATTERN = Pattern.compile("metals/.*", Pattern.CASE_INSENSITIVE);
    private static final Reflections REFLECTIONS = new Reflections(null, new ResourcesScanner());

    @Getter private static Path configDir;
    @Getter private static Path metalsDir;

    public static void preInit(FMLPreInitializationEvent event) {
        configDir = event.getSuggestedConfigurationFile().getParentFile().toPath().resolve(JustMetals.MODID);
        metalsDir = configDir.resolve("metals");

        findResources(METALS_FILE_PATTERN.asPredicate()).stream()
            .filter(METALS_FOLDER_PATTERN.asPredicate())
            .forEach(r -> copyResource(r, configDir.resolve(r), false)
        );
    }

    public static Set<String> findResources(Predicate<String> nameFilter) {
        return REFLECTIONS.getResources(nameFilter::test);
    }

    @SneakyThrows
    public static void copyResource(String from, Path to, boolean overwrite) {
        if (overwrite || !Files.exists(to)) {
            Files.createDirectories(to.getParent());
            Files.copy(getResourceAsStream(from), to, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static InputStream getResourceAsStream(String location) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
        return in;
    }
}
