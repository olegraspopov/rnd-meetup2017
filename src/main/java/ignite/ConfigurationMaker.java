package ignite;

import org.apache.ignite.configuration.IgniteConfiguration;
import org.hibernate.ogm.datastore.ignite.IgniteConfigurationBuilder;

public class ConfigurationMaker implements IgniteConfigurationBuilder {
    @Override
    public IgniteConfiguration build() {
        return GridConfig.igniteConfiguration(false);
    }
}