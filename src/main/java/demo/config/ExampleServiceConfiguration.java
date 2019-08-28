package demo.config;

import com.yammer.dropwizard.config.Configuration;


public class ExampleServiceConfiguration extends Configuration {


  private DatabaseConfiguration database;


  public DatabaseConfiguration getDatabase() {
    return database;
  }

  public void setDatabase(DatabaseConfiguration database) {
    this.database = database;
  }

}
