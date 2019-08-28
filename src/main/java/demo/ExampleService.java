package demo;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import demo.config.DatabaseConfiguration;
import demo.config.ExampleServiceConfiguration;
import demo.repository.UserRepository;
import demo.service.UserService;
import demo.service.UserServiceImpl;
import demo.controller.UserResource;

import java.util.Properties;

public class ExampleService extends Service<ExampleServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new ExampleService().run(args);
    }

    @Override
    public void initialize(final Bootstrap<ExampleServiceConfiguration> bootstrap) {
    }

    private Injector createInjector(final ExampleServiceConfiguration conf) {
        return Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(ExampleServiceConfiguration.class).toInstance(conf);
                bind(UserService.class).to(UserServiceImpl.class);

            }
        }, createJpaPersistModule(conf.getDatabase()));
    }

    private JpaPersistModule createJpaPersistModule(DatabaseConfiguration conf) {
        Properties props = new Properties();
        props.put("javax.persistence.jdbc.url", conf.getUrl());
        props.put("javax.persistence.jdbc.user", conf.getUser());
        props.put("javax.persistence.jdbc.password", conf.getPassword());
        props.put("javax.persistence.jdbc.driver", conf.getDriverClass());
        JpaPersistModule jpaModule = new JpaPersistModule("Default");
        jpaModule.properties(props);
        return jpaModule;
    }

    @Override
    public void run(final ExampleServiceConfiguration conf, final Environment env) {
        Injector injector = createInjector(conf);
        env.addFilter(injector.getInstance(PersistFilter.class), "/*");
        env.addResource(injector.getInstance(UserResource.class));
    }

}
