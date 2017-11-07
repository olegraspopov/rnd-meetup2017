package ignite.ogm;

import data.ogm.breed_n_dog.Breed;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.hibernate.ogm.datastore.ignite.IgniteConfigurationBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import static org.apache.ignite.cache.CacheAtomicityMode.TRANSACTIONAL;

public class ConfigurationMaker implements IgniteConfigurationBuilder {
    @Override
    public IgniteConfiguration build() {
        IgniteConfiguration config = new IgniteConfiguration();
        config.setPeerClassLoadingEnabled(true);
        config.setClientMode(false);
        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        discoverySpi.setNetworkTimeout(60000);
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
//        ArrayList<String> addrs = new ArrayList<>();
//        addrs.add("127.0.0.1:47500..47509");
        ipFinder.setAddresses(Arrays.asList("127.0.0.1", "127.0.0.1:47500..47509"));
        discoverySpi.setIpFinder(ipFinder);
        config.setDiscoverySpi(discoverySpi);
        CacheConfiguration accountCacheCfg = new CacheConfiguration()
                .setName("BREED")
                .setAtomicityMode(TRANSACTIONAL)
                .setIndexedTypes(
                        String.class, Breed.class
                );
        config.setCacheConfiguration(accountCacheCfg);
        return config;
    }
}